package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityCategoryAttributeRelationMapper;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityCategoryMapper;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityMapper;
import pers.candyboyou.commodity.business.model.dto.*;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueVO;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryVO;
import pers.candyboyou.commodity.business.model.vo.admin.SimpleCommodityInfoVO;
import pers.candyboyou.commodity.business.service.admin.AttributeService;
import pers.candyboyou.commodity.business.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private TCommodityCategoryMapper tCommodityCategoryMapper;

    @Autowired
    private TCommodityMapper tCommodityMapper;

    @Autowired
    private TCommodityCategoryAttributeRelationMapper categoryAttributeRelationMapper;

    @Override
    public void saveOrUpdateCategory(CategorySaveOrUpdateParam categorySaveOrUpdateParam) {
        if (categorySaveOrUpdateParam == null) {
            return;
        }
        CategorySaveOrUpdateDTO categorySaveOrUpdateDTO = CategorySaveOrUpdateDTO.convert(categorySaveOrUpdateParam);
        Long categoryId = categorySaveOrUpdateParam.getCategoryId();
        if (categoryId == null) {
            tCommodityCategoryMapper.insertCategory(categorySaveOrUpdateDTO);
            return;
        }

        if (categorySaveOrUpdateParam.getIsDelete() == 1) {
            tCommodityCategoryMapper.deleteCategory(categoryId);
            return;
        }

        tCommodityCategoryMapper.updateCategory(categorySaveOrUpdateDTO);
    }

    @Override
    public ListVO<CategoryVO> getCategories(CategorySearchParam searchParam) {
        ListVO<CategoryVO> categoryVOSListVO = new ListVO<>();
        List<CategoryDTO> categoryDTOList = tCommodityCategoryMapper.selectCategoriesBySearchParam(searchParam);
        List<CategoryVO> categoryVOS = CategoryVO.convertCategoryDTOList(categoryDTOList);
        int count = tCommodityCategoryMapper.selectCategoriesCountBySearchParam(searchParam);
        categoryVOSListVO.setList(categoryVOS);
        categoryVOSListVO.setTotal(count);
        categoryVOSListVO.setPageNum(searchParam.getPageNum());
        categoryVOSListVO.setPageSize(categoryVOS.size());
        return categoryVOSListVO;
    }

    @Override
    public ListVO<SimpleCommodityInfoVO> getSimpleCommodityInfos(Long categoryId, QueryParam queryParam) {
        ListVO<SimpleCommodityInfoVO> simpleCommodityInfoVOListVO = new ListVO<>();
        List<SimpleCommodityDTO> simpleCommodities = tCommodityMapper.selectSimpleCommodityByCategoryId(categoryId, queryParam);
        List<SimpleCommodityInfoVO> simpleCommodityVOS = SimpleCommodityInfoVO.convertSimpleCommodities(simpleCommodities);
        int count = tCommodityMapper.selectSimpleCommodityCountsByCategoryId(categoryId, queryParam);
        simpleCommodityInfoVOListVO.setList(simpleCommodityVOS);
        simpleCommodityInfoVOListVO.setTotal(count);
        simpleCommodityInfoVOListVO.setPageNum(queryParam.getPageNum());
        simpleCommodityInfoVOListVO.setPageSize(queryParam.getPageSize());
        return simpleCommodityInfoVOListVO;
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
        List<CategoryIdWithNameDTO> categoryIdWithNames = tCommodityCategoryMapper.selectCategoryIdWithNamesByIds(categoryIds);
        return categoryIdWithNames.stream().collect(Collectors.toMap(CategoryIdWithNameDTO::getId, CategoryIdWithNameDTO::getName));
    }

    @Override
    public List<AttributeValueVO> getAttributeValuesById(Long categoryId) {
        List<AttributeWithValueDTO> AttributeWithValueDTOS = categoryAttributeRelationMapper.getAttributeIdsByCategoryId(categoryId);
        List<Long> attributeIds = AttributeWithValueDTOS.stream().map(AttributeWithValueDTO::getAttributeId).toList();
        Map<Long, Integer> attributeIdToIsSaleMap = attributeService.getAttributeIdToIsSaleMap(attributeIds);
        List<AttributeValueVO> attributeValueVOS = new ArrayList<>(AttributeWithValueDTOS.size());
        for (AttributeWithValueDTO AttributeWithValueDTO : AttributeWithValueDTOS) {
            AttributeValueVO attributeValueVO = new AttributeValueVO();
            Long attributeId = AttributeWithValueDTO.getAttributeId();
            attributeValueVO.setAttributeId(attributeId);
            attributeValueVO.setAttributeValueId(AttributeWithValueDTO.getValueId());
            attributeValueVO.setIsSaleAttribute(attributeIdToIsSaleMap.get(attributeId));
            attributeValueVOS.add(attributeValueVO);
        }
        return attributeValueVOS;
    }


}
