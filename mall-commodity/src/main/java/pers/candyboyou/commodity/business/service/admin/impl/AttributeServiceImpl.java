package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.PageResult;
import io.candyboyou.common.utils.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.enums.*;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityAttributeMapper;
import pers.candyboyou.commodity.business.mapper.admin.TCommoditySkuMapper;
import pers.candyboyou.commodity.business.model.dto.AttributeIdWithIsSaleDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeOfListDTO;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeDTO;
import pers.candyboyou.commodity.business.model.entity.AttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;
import pers.candyboyou.commodity.business.model.param.admin.AttrSearchParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.vo.admin.*;
import pers.candyboyou.commodity.business.service.admin.AttributeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private TCommoditySkuMapper tCommoditySkuMapper;

    @Autowired
    private TCommodityAttributeMapper tCommodityAttributeMapper;

    @Override
    public void saveOrUpdateSkuAttr(SkuAttrParam skuAttrParam) {
        if (skuAttrParam.getId() == null) {
            tCommoditySkuMapper.insertSkuAttr(skuAttrParam);
            return;
        }
        if (skuAttrParam.getIsDelete() == 1) {
            tCommoditySkuMapper.deleteSupAttrById(skuAttrParam.getId());
            return;
        }
        tCommoditySkuMapper.updateSupAttr(skuAttrParam);
    }

    @Override
    public PageResult<SkuAttributeVO> getSkuAttributesById(Long attributeId, QueryParam queryParam) {
        PageResult<SkuAttributeVO> skuAttributeVOPageResult = new PageResult<>();
        if (attributeId == null) {
            return skuAttributeVOPageResult;
        }
        List<SkuAttributeDTO> skuAttributeDTOList = tCommoditySkuMapper.selectSkuAttributeDTOs(attributeId, queryParam);
        List<SkuAttributeVO> skuAttributeVOs = SkuAttributeVO.convertSkuAttributeDTOs(skuAttributeDTOList);
        int count = tCommoditySkuMapper.selectSkuAttributeDTOsCount(attributeId);
        skuAttributeVOPageResult.setList(skuAttributeVOs);
        skuAttributeVOPageResult.setTotal(count);
        skuAttributeVOPageResult.setPageNum(queryParam.getPageNum());
        skuAttributeVOPageResult.setPageNum(skuAttributeVOs.size());
        return skuAttributeVOPageResult;
    }

    @Override
    public void saveOrUpdateAttr(AttrParam attrParam) {
        AttributeEntity attributeEntity = AttributeEntity.convertAttrParam(attrParam);

        if (attrParam.getId() == null) {
            tCommodityAttributeMapper.insertAttribute(attributeEntity);
            return;
        }
        if (attrParam.getIsValid() == 0) {
            tCommodityAttributeMapper.deleteAttrById(attributeEntity.getId());
            return;
        }
        tCommodityAttributeMapper.updateAttr(attributeEntity);
    }

    @Override
    public PageResult<AttributeVO> getAttributeVOsById(Long attributeId, QueryParam queryParam) {
        PageResult<AttributeVO> attributeVOPageResult = new PageResult<>();
        if (attributeId == null) {
            return attributeVOPageResult;
        }
        List<AttributeEntity> attributeEntities = tCommodityAttributeMapper.selectAttributeDTOs(attributeId, queryParam);
        List<AttributeVO> AttributeVOs = AttributeVO.convertAttributeEntities(attributeEntities);
        int count = tCommodityAttributeMapper.selectAttributeDTOsCount(attributeId);
        attributeVOPageResult.setList(AttributeVOs);
        attributeVOPageResult.setTotal(count);
        attributeVOPageResult.setPageNum(queryParam.getPageNum());
        attributeVOPageResult.setPageNum(AttributeVOs.size());
        return attributeVOPageResult;
    }

    @Override
    public Map<Long, Integer> getAttributeIdToIsSaleMap(List<Long> attributeIds) {
        List<AttributeIdWithIsSaleDTO> attributeIdWithIsSaleDTOs = tCommodityAttributeMapper.selectAttributeIdWithIsSale(attributeIds);
        return attributeIdWithIsSaleDTOs.stream().collect(Collectors.toMap(AttributeIdWithIsSaleDTO::getId, AttributeIdWithIsSaleDTO::getIsSaleAttr));
    }

    @Override
    public Map<Long, String> getAttributeNamesByAttributeIds(List<Long> attributeIds) {
        List<AttributeNameDTO> attributeNameDTOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(attributeIds)) {
            attributeNameDTOS = tCommodityAttributeMapper.batchSelectAllAttributeName();
        } else {
            attributeNameDTOS = tCommodityAttributeMapper.batchSelectAttributeNameByAttributeIds(attributeIds);
        }
        return attributeNameDTOS.stream().collect(Collectors.toMap(AttributeNameDTO::getId, AttributeNameDTO::getName));
    }

    @Override
    public void deleteByCommodityId(Long commodityId) {
        tCommodityAttributeMapper.deleteByCommodityId(commodityId);
    }

    @Override
    public AllListQueryParamOfAttributeVO getAllListOfQuery() {
        AllListQueryParamOfAttributeVO allListParamOfAttributeVO = new AllListQueryParamOfAttributeVO();
        List<OptionCommonVO> isSaleList = IsSaleEnum.getAllList();
        allListParamOfAttributeVO.setIsSaleList(isSaleList);
        return allListParamOfAttributeVO;
    }

    @Override
    public PageResult<AttributeOfListVO> getAttributes(AttrSearchParam attrSearchParam) {
        PageResult<AttributeOfListVO> attributeVOPageResult = new PageResult<>();
        List<AttributeOfListDTO> attributeOfListDTOS = tCommodityAttributeMapper.selectAttributeDTOsByParam(attrSearchParam);
        List<AttributeOfListVO> attributeOfListVOS = AttributeOfListVO.convertAttributeOfListDTO(attributeOfListDTOS);
        int count = tCommodityAttributeMapper.selectAttributeDTOsCountByParam(attrSearchParam);
        attributeVOPageResult.setList(attributeOfListVOS);
        attributeVOPageResult.setTotal(count);
        attributeVOPageResult.setPageNum(attrSearchParam.getPageNum());
        attributeVOPageResult.setPageSize(attributeOfListVOS.size());
        return attributeVOPageResult;
    }

    @Override
    public AllListSaveParamOfAttributeVO getAllListOfSave() {
        AllListSaveParamOfAttributeVO allListSaveParamOfAttributeVO = new AllListSaveParamOfAttributeVO();
        List<OptionCommonVO> isSaleList = IsSaleEnum.getAllList();
        List<OptionCommonVO> isValidList = IsValidEnum.getAllList();
        List<OptionCommonVO> selectTypeList = SelectTypeEnum.getAllList();
        List<OptionCommonVO> searchTypeList = SearchTypeEnum.getAllList();
        List<OptionCommonVO> inputTypeList = InputTypeEnum.getAllList();
        allListSaveParamOfAttributeVO.setIsSaleList(isSaleList);
        allListSaveParamOfAttributeVO.setIsValidList(isValidList);
        allListSaveParamOfAttributeVO.setSelectTypeList(selectTypeList);
        allListSaveParamOfAttributeVO.setSearchTypeList(searchTypeList);
        allListSaveParamOfAttributeVO.setInputTypeList(inputTypeList);
        return allListSaveParamOfAttributeVO;
    }

    @Override
    public AttributeVO getAttributeById(Long attributeId) {
        AttributeEntity attributeEntity = tCommodityAttributeMapper.selectAttributeById(attributeId);
        return AttributeVO.convertAttributeEntity(attributeEntity);
    }

    @Override
    public List<AttributeNameDTO> getAttributesByAttributeIds(List<Long> relationAttributeIds) {
        if (CollectionUtils.isEmpty(relationAttributeIds)) {
            return new ArrayList<>();
        }
        return tCommodityAttributeMapper.batchSelectAttributeNameByAttributeIds(relationAttributeIds);
    }

    @Override
    public List<AttributeNameDTO> getAllAttributes() {
        return tCommodityAttributeMapper.batchSelectAllAttributeName();
    }

}
