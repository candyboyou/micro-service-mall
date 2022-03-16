package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityOtherAttributeRelationMapper;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeValueDTO;
import pers.candyboyou.commodity.business.model.entity.OtherAttributeRelationEntity;
import pers.candyboyou.commodity.business.model.param.admin.AttributeValueSaveParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueVO;
import pers.candyboyou.commodity.business.service.admin.AttributeService;
import pers.candyboyou.commodity.business.service.admin.AttributeValueService;
import pers.candyboyou.commodity.business.service.admin.OtherAttributeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OtherAttributeServiceImpl implements OtherAttributeService {

    @Autowired
    AttributeService attributeService;

    @Autowired
    AttributeValueService attributeValueService;

    @Autowired
    TCommodityOtherAttributeRelationMapper otherAttributeRelationMapper;

    @Override
    public List<AttributeValueVO> getAttributeValuesByCommodityId(Long commodityId) {
        List<OtherAttributeRelationEntity> otherAttributeRelationEntities = otherAttributeRelationMapper.selectEntityByCommodityId(commodityId);
        Map<Long, Long> attributeIdToValueMap
                = otherAttributeRelationEntities.stream().collect(Collectors.toMap(OtherAttributeRelationEntity::getAttributeId, OtherAttributeRelationEntity::getAttributeValueId));
        List<Long> attributeIds = otherAttributeRelationEntities.stream().map(OtherAttributeRelationEntity::getAttributeId).toList();
        List<Long> attributeValueIds = otherAttributeRelationEntities.stream().map(OtherAttributeRelationEntity::getAttributeValueId).toList();
        Map<Long, String> attributeIdToNameMap = attributeService.getAttributeNamesByAttributeIds(attributeIds);
        Map<Long, String> valueIdToValueMap = attributeValueService.getAttributeValuesByValueIds(attributeValueIds);
        List<AttributeValueVO> attributeValueVOs = new ArrayList<>();
        for (Long attributeId : attributeIds) {
            AttributeValueVO attributeValueVO = new AttributeValueVO();
            String attributeName = attributeIdToNameMap.get(attributeId);
            Long valueId = attributeIdToValueMap.get(attributeId);
            String valueName = valueIdToValueMap.get(valueId);
            attributeValueVO.setAttributeId(attributeId);
            attributeValueVO.setAttributeName(attributeName);
            attributeValueVO.setAttributeValueId(valueId);
            attributeValueVO.setAttributeValue(valueName);
//            attributeValueVO.setIsSaleAttribute(0);
            attributeValueVOs.add(attributeValueVO);
        }
        return attributeValueVOs;
    }

    @Override
    public void saveAttributeValueByCommodityId(List<AttributeValueSaveParam> otherAttributeValueSaveParams, Long commodityId) {
        if (CollectionUtils.isEmpty(otherAttributeValueSaveParams)) {
            return;
        }
        otherAttributeRelationMapper.insertAttributeValueOfCommodity(otherAttributeValueSaveParams, commodityId);
    }

    @Override
    public void updateAttributeValues(List<AttributeValueSaveParam> otherAttributeValueSaveParams) {
        if (CollectionUtils.isEmpty(otherAttributeValueSaveParams)) {
            return;
        }
        otherAttributeRelationMapper.updateAttributeValues(otherAttributeValueSaveParams);
    }

    @Override
    public void deleteByCommodityId(Long commodityId) {
        otherAttributeRelationMapper.deleteByCommodityId(commodityId);
    }
}
