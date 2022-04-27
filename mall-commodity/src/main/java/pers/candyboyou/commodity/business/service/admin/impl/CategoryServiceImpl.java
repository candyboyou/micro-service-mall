package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.PageResult;
import io.candyboyou.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import pers.candyboyou.commodity.business.enums.LevelEnum;
import pers.candyboyou.commodity.business.enums.ShowStatusEnum;
import pers.candyboyou.commodity.business.enums.PlaceEnum;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityCategoryAttributeRelationMapper;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityCategoryMapper;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityCategoryRelationMapper;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityMapper;
import pers.candyboyou.commodity.business.model.dto.*;
import pers.candyboyou.commodity.business.model.entity.CategoryEntity;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.*;
import pers.candyboyou.commodity.business.service.admin.AttributeService;
import pers.candyboyou.commodity.business.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private TCommodityCategoryMapper tCommodityCategoryMapper;

    @Autowired
    private TCommodityMapper tCommodityMapper;

    @Autowired
    private TCommodityCategoryAttributeRelationMapper tCommodityCategoryAttributeRelationMapper;

    @Autowired
    private TCommodityCategoryRelationMapper tCommodityCategoryRelationMapper;

    @Override
    @Transactional
    public Long saveOrUpdateCategory(CategorySaveOrUpdateParam categorySaveOrUpdateParam) {
        if (categorySaveOrUpdateParam == null) {
            return null;
        }

        CategoryEntity categoryEntity = CategoryEntity.convert(categorySaveOrUpdateParam);
        Long categoryId = categorySaveOrUpdateParam.getId();

        if (categorySaveOrUpdateParam.getIsDelete() == 1) {
            tCommodityCategoryMapper.deleteCategory(categoryId);
        }

        if (categoryId == null) {
            tCommodityCategoryMapper.insertCategory(categoryEntity);
            categoryId = categoryEntity.getId();
        } else {
            tCommodityCategoryMapper.updateCategory(categoryEntity);
        }

        // 保存或者更新分类的属性
        List<RelationAttributeVO> relationAttributes = categorySaveOrUpdateParam.getRelationAttributes();
        List<Long> attributesIds = tCommodityCategoryAttributeRelationMapper.selectAttributesByCategoryId(categoryId);
        HashSet<Long> attributeIdSet = new HashSet<>(attributesIds);
        List<Long> insertAttributeIds = new ArrayList<>();
        List<Long> deleteAttributeIds = new ArrayList<>();
        for (RelationAttributeVO relationAttribute : relationAttributes) {
            Long attributeId = Long.valueOf(relationAttribute.getId());
            if (relationAttribute.getIsDelete() == 1) {
                deleteAttributeIds.add(attributeId);
                continue;
            }
            if (!attributeIdSet.contains(attributeId)) {
                insertAttributeIds.add(attributeId);
            }
        }
        if (insertAttributeIds.size() > 0) {
            tCommodityCategoryAttributeRelationMapper.batchInsertCategoryAttributeRelation(categoryId, insertAttributeIds);
        }
        if (deleteAttributeIds.size() > 0) {
            tCommodityCategoryAttributeRelationMapper.batchDeleteCategoryAttributeRelation(categoryId, deleteAttributeIds);
        }

        // 更新排名
        List<OptionCommonVO> sameLevelCategoryList = categorySaveOrUpdateParam.getSameLevelCategoryList();
        for (int i = 0; i < sameLevelCategoryList.size(); i++) {
            OptionCommonVO optionCommonVO = sameLevelCategoryList.get(i);
            if (optionCommonVO.getId().equals("-1")) {
                tCommodityCategoryMapper.updateCategorySort(i, categoryId);
                continue;
            }
            tCommodityCategoryMapper.updateCategorySort(i, Long.valueOf(optionCommonVO.getId()));
        }

        return categoryEntity.getId();
    }

    @Override
    public PageResult<CategoryBaseVO> getCategories(CategorySearchParam searchParam) {
        PageResult<CategoryBaseVO> categoryVOsPageResult = new PageResult<>();
        List<CategoryDTO> categoryDTOList = tCommodityCategoryMapper.selectCategoriesBySearchParam(searchParam);
        List<CategoryBaseVO> categoryBaseVOS = CategoryBaseVO.convertCategoryDTOList(categoryDTOList);
        int total = tCommodityCategoryMapper.selectCategoriesCountBySearchParam(searchParam);
        categoryVOsPageResult.setList(categoryBaseVOS);
        categoryVOsPageResult.setTotal(total);
        categoryVOsPageResult.setPageNum(searchParam.getPageNum());
        int pageSize = searchParam.getPageSize();
        categoryVOsPageResult.setPageSize(categoryBaseVOS.size());
        int totalPage = total / pageSize + total % pageSize;
        categoryVOsPageResult.setTotalPage(totalPage);
        return categoryVOsPageResult;
    }

    @Override
    public PageResult<SimpleCommodityInfoVO> getSimpleCommodityInfos(Long categoryId, QueryParam queryParam) {
        PageResult<SimpleCommodityInfoVO> simpleCommodityInfoVOPageResult = new PageResult<>();
        List<SimpleCommodityDTO> simpleCommodities = tCommodityMapper.selectSimpleCommodityByCategoryId(categoryId, queryParam);
        List<SimpleCommodityInfoVO> simpleCommodityVOs = SimpleCommodityInfoVO.convertSimpleCommodities(simpleCommodities);
        int total = tCommodityMapper.selectSimpleCommodityCountsByCategoryId(categoryId, queryParam);
        simpleCommodityInfoVOPageResult.setList(simpleCommodityVOs);
        simpleCommodityInfoVOPageResult.setTotal(total);
        simpleCommodityInfoVOPageResult.setPageNum(queryParam.getPageNum());
        int pageSize = queryParam.getPageSize();
        simpleCommodityInfoVOPageResult.setPageSize(pageSize);
        int totalPage = total / pageSize + total % pageSize;
        simpleCommodityInfoVOPageResult.setTotalPage(totalPage);
        return simpleCommodityInfoVOPageResult;
    }

    @Override
    public void relateCommodities(RelateCategoryParam relateCategoryParam) {
        if (relateCategoryParam.getCommodityId() == null) {
            return;
        }
        if (relateCategoryParam.getIsDelete() == 1) {
            tCommodityMapper.deleteCategory(relateCategoryParam.getCommodityId());
        }
        tCommodityMapper.updateCategoryOfCommodity(relateCategoryParam.getCategoryId(), relateCategoryParam.getCommodityId());
    }

    @Override
    public Map<Long, String> getCategoryNamesByIds(List<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return null;
        }
        List<CategoryCascadeInfoDTO> categoryIdWithNames = tCommodityCategoryMapper.selectCategoryIdWithNamesByIds(categoryIds);
        return categoryIdWithNames.stream().collect(Collectors.toMap(CategoryCascadeInfoDTO::getId, CategoryCascadeInfoDTO::getName));
    }

    @Override
    public List<AttributeNameDTO> getAttributeNamesByCategoryId(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return tCommodityCategoryAttributeRelationMapper.getAttributeNamesByCategoryId(categoryId);
    }

    @Override
    public List<CategoryIdAndNameVO> getAllCategoryIdAndName() {
        List<CategoryCascadeInfoDTO> categoryIdWithNameDTOS = tCommodityCategoryMapper.selectAllCategoryIdWithNames();
        return CategoryIdAndNameVO.convertCategoryIdWithNameDTOS(categoryIdWithNameDTOS);
    }

    @Override
    public void saveOrUpdateCategoryWithCommodityRelation(Long id, Long categoryId, Long commodityId) {
        if (categoryId == null || commodityId == null) {
            return;
        }
        if (id == null) {
            tCommodityCategoryRelationMapper.insertCategoryWithCommodity(categoryId, commodityId);
        } else {
            tCommodityCategoryRelationMapper.updateCategoryWithCommodity(id, categoryId, commodityId);
        }
    }

    @Override
    public AllListParamOfCategoryVO getAllList() {
        AllListParamOfCategoryVO allListParamOfCategoryVO = new AllListParamOfCategoryVO();
        allListParamOfCategoryVO.setLevelList(LevelEnum.getAllList());
        allListParamOfCategoryVO.setIsShowList(ShowStatusEnum.getAllList());
        allListParamOfCategoryVO.setPlaceList(PlaceEnum.getAllList());
        return allListParamOfCategoryVO;
    }

    @Override
    public List<OptionCommonVO> getSubCategoryListById(Long id) {
        List<CategoryNameDTO> categoryNameDTOS = tCommodityCategoryMapper.getSubCategoryListById(id);
        return OptionCommonVO.convertCategoryNameDTO(categoryNameDTOS);
    }

    @Override
    public List<OptionCommonVO> getCategoryListByLevel(Integer level) {
        List<CategoryNameDTO> categoryNameDTOS = tCommodityCategoryMapper.getCategoryListByLevel(level);
        return OptionCommonVO.convertCategoryNameDTO(categoryNameDTOS);
    }

    @Override
    public CategoryDetailVO getCategoryById(Long id) {
        CategoryEntity categoryEntity = tCommodityCategoryMapper.selectCategoryById(id);
        if (categoryEntity == null) {
            return null;
        }
        CategoryDetailVO categoryDetailVO = CategoryDetailVO.convertCategoryEntity(categoryEntity);
        List<Long> relationAttributeIds = tCommodityCategoryAttributeRelationMapper.selectAttributesByCategoryId(id);
        List<AttributeNameDTO> relationAttributeDTOS = attributeService.getAttributesByAttributeIds(relationAttributeIds);
        List<OptionCommonVO> relationAttributes = OptionCommonVO.convertAttributeNameDTOS(relationAttributeDTOS);
        List<AttributeNameDTO> allAttributeDTOs = attributeService.getAllAttributes();
        List<OptionCommonVO> notRelationAttributes = OptionCommonVO.convertAttributeNameDTOS(allAttributeDTOs);
        Map<Long, String> attributeIdToNameMap = attributeService.getAttributeNamesByAttributeIds(new ArrayList<>());
        for (OptionCommonVO relationAttribute : relationAttributes) {
            String key = relationAttribute.getId();
            if (attributeIdToNameMap.containsKey(key)) {
                attributeIdToNameMap.remove(key);
                notRelationAttributes.remove(relationAttribute);
            }
        }
        categoryDetailVO.setRelationAttributes(relationAttributes);
        categoryDetailVO.setNotRelationAttributes(notRelationAttributes);
        return categoryDetailVO;
    }

    @Override
    public List<CategoryWithCommodityVO> getCommodityListOfCategory(List<Long> categoryIds, Long commodityId) {
        List<CategoryWithCommodityDTO> categoryWithCommodityDTOS  = tCommodityCategoryRelationMapper.batchSelectCommodityListByCategoryId(categoryIds);
        HashMap<Long, ArrayList<CategoryWithCommodityDTO>> idToDTOMap = categoryWithCommodityDTOS.stream().collect(
                Collectors.groupingBy(CategoryWithCommodityDTO::getCategoryId, HashMap::new, Collectors.toCollection(ArrayList::new))
        );
        List<CategoryWithCommodityVO> categoryWithCommodityVOS = new ArrayList<>();
        for (Long categoryId : idToDTOMap.keySet()) {
            ArrayList<CategoryWithCommodityDTO> currentCommodityList = idToDTOMap.get(categoryId);
            CategoryWithCommodityVO categoryWithCommodityVO = new CategoryWithCommodityVO();
            if (CollectionUtils.isNotEmpty(currentCommodityList)) {
                categoryWithCommodityVO.setId(currentCommodityList.get(0).getCategoryId());
                categoryWithCommodityVO.setName(currentCommodityList.get(0).getCategoryName());
                List<OptionCommonVO> commodityList = new ArrayList<>();
                int sort = 0;
                for (CategoryWithCommodityDTO categoryWithCommodityDTO : currentCommodityList) {
                    OptionCommonVO optionCommonVO = new OptionCommonVO();
                    Long curCommodityId = categoryWithCommodityDTO.getCommodityId();
                    optionCommonVO.setId(String.valueOf(curCommodityId));
                    optionCommonVO.setLabel(categoryWithCommodityDTO.getCommodityName());
                    commodityList.add(optionCommonVO);
                    if (commodityId.equals(curCommodityId)) {
                        categoryWithCommodityVO.setSort(sort);
                    }
                    sort++;
                }
                categoryWithCommodityVO.setCommodityList(commodityList);
            }
            categoryWithCommodityVOS.add(categoryWithCommodityVO);
        }
        return categoryWithCommodityVOS;
    }


}
