package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.commodity.business.mapper.admin.AdminCategoryAttributeRelationMapper;
import pers.candyboyou.commodity.business.mapper.admin.AdminCategoryMapper;
import pers.candyboyou.commodity.business.mapper.admin.AdminCommodityMapper;
import pers.candyboyou.commodity.business.model.dto.*;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueVO;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryVO;
import pers.candyboyou.commodity.business.model.vo.admin.SimpleCommodityInfoVO;
import pers.candyboyou.commodity.business.service.admin.AdminCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.service.admin.AdminAttributeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    private AdminAttributeService adminAttributeService;

    @Autowired
    private AdminCategoryMapper adminCategoryMapper;

    @Autowired
    private AdminCommodityMapper adminCommodityMapper;

    @Autowired
    private AdminCategoryAttributeRelationMapper categoryAttributeRelationMapper;

    @Override
    public void saveOrUpdateCategory(CategorySaveOrUpdateParam categorySaveOrUpdateParam) {
        if (categorySaveOrUpdateParam == null) {
            return;
        }
        CategorySaveOrUpdateDTO categorySaveOrUpdateDTO = CategorySaveOrUpdateDTO.convert(categorySaveOrUpdateParam);
        Long categoryId = categorySaveOrUpdateParam.getCategoryId();
        if (categoryId == null) {
            adminCategoryMapper.insertCategory(categorySaveOrUpdateDTO);
            return;
        }

        if (categorySaveOrUpdateParam.getIsDelete() == 1) {
            adminCategoryMapper.deleteCategory(categoryId);
            return;
        }

        adminCategoryMapper.updateCategory(categorySaveOrUpdateDTO);
    }

    @Override
    public ListVO<CategoryVO> getCategories(CategorySearchParam searchParam) {
        ListVO<CategoryVO> categoryVOSListVO = new ListVO<>();
        List<CategoryDTO> categoryDTOList = adminCategoryMapper.selectCategoriesBySearchParam(searchParam);
        List<CategoryVO> categoryVOS = CategoryVO.convertCategoryDTOList(categoryDTOList);
        int count = adminCategoryMapper.selectCategoriesCountBySearchParam(searchParam);
        categoryVOSListVO.setList(categoryVOS);
        categoryVOSListVO.setTotal(count);
        categoryVOSListVO.setPageNum(searchParam.getPageNum());
        categoryVOSListVO.setPageSize(categoryVOS.size());
        return categoryVOSListVO;
    }

    @Override
    public ListVO<SimpleCommodityInfoVO> getSimpleCommodityInfos(Long categoryId, QueryParam queryParam) {
        ListVO<SimpleCommodityInfoVO> simpleCommodityInfoVOListVO = new ListVO<>();
        List<SimpleCommodityDTO> simpleCommodities = adminCommodityMapper.selectSimpleCommodityByCategoryId(categoryId, queryParam);
        List<SimpleCommodityInfoVO> simpleCommodityVOS = SimpleCommodityInfoVO.convertSimpleCommodities(simpleCommodities);
        int count = adminCommodityMapper.selectSimpleCommodityCountsByCategoryId(categoryId, queryParam);
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
            adminCommodityMapper.deleteCategory(relateCategoryParam.getCommodityId());
        }
        adminCommodityMapper.updateCategoryOfCommodity(relateCategoryParam.getCategoryId(), relateCategoryParam.getCommodityId());
    }

    @Override
    public Map<Long, String> getCategoryNamesByIds(List<Long> categoryIds) {
        List<CategoryIdWithNameDTO> categoryIdWithNames = adminCategoryMapper.selectCategoryIdWithNamesByIds(categoryIds);
        return categoryIdWithNames.stream().collect(Collectors.toMap(CategoryIdWithNameDTO::getId, CategoryIdWithNameDTO::getName));
    }

    @Override
    public List<AttributeValueVO> getAttributeValuesById(Long categoryId) {
        List<AttributeWithValueDTO> AttributeWithValueDTOS = categoryAttributeRelationMapper.getAttributeIdsByCategoryId(categoryId);
        List<Long> attributeIds = AttributeWithValueDTOS.stream().map(AttributeWithValueDTO::getAttributeId).toList();
        Map<Long, Integer> attributeIdToIsSaleMap = adminAttributeService.getAttributeIdToIsSaleMap(attributeIds);
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
