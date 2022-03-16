package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityAttributeValueMapper;
import pers.candyboyou.commodity.business.model.dto.AttributeValueDTO;
import pers.candyboyou.commodity.business.model.dto.AttributeWithValueDTO;
import pers.candyboyou.commodity.business.model.param.admin.AttributeValueSaveParam;
import pers.candyboyou.commodity.business.service.admin.AttributeValueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AttributeValueServiceImpl implements AttributeValueService {

    @Autowired
    private TCommodityAttributeValueMapper tCommodityAttributeValueMapper;

    @Override
    public void saveAttributeValueByCommodityId(List<AttributeValueSaveParam> attributeValueSaveParams, Long commodityId) {
        if (CollectionUtils.isEmpty(attributeValueSaveParams)) {
            return;
        }
        tCommodityAttributeValueMapper.insertAttributeValueOfCommodity(attributeValueSaveParams, commodityId);
    }

    @Override
    public void updateAttributeValues(List<AttributeValueSaveParam> attributeValueSaveParams) {
        if (CollectionUtils.isEmpty(attributeValueSaveParams)) {
            return;
        }
        tCommodityAttributeValueMapper.updateAttributeValues(attributeValueSaveParams);
    }

    @Override
    public Map<Long, String> getAttributeValuesByValueIds(List<Long> attributeValueIds) {
        if (CollectionUtils.isEmpty(attributeValueIds)) {
            return null;
        }
        List<AttributeValueDTO> attributeValueDTOS = tCommodityAttributeValueMapper.batchSelectAttributeValueDTOByValueIds(attributeValueIds);
        return attributeValueDTOS.stream().collect(Collectors.toMap(AttributeValueDTO::getId, AttributeValueDTO::getValue));
    }

    @Override
    public Map<Long, AttributeValueDTO> getAttributeValuesByAttributeIdsAndCommodityId(List<Long> attributeIds, Long commodityId) {
        if (CollectionUtils.isEmpty(attributeIds)) {
            return null;
        }
        List<AttributeWithValueDTO> attributeWithValueDTOs = tCommodityAttributeValueMapper.batchSelectAttributeWithValueDTOByAttributeIdAndCommodity(attributeIds, commodityId);
        Map<Long, AttributeValueDTO> attributeIdToValueMap = new HashMap<>();
        for (AttributeWithValueDTO attributeWithValueDTO : attributeWithValueDTOs) {
            AttributeValueDTO attributeValueDTO = AttributeValueDTO.convertAttributeWithValueDTO(attributeWithValueDTO);
            attributeIdToValueMap.put(attributeWithValueDTO.getAttributeId(), attributeValueDTO);
        }
        return attributeIdToValueMap;
    }

    @Override
    public void deleteByCommodityId(Long commodityId) {
        tCommodityAttributeValueMapper.deleteByCommodityId(commodityId);
    }
}
