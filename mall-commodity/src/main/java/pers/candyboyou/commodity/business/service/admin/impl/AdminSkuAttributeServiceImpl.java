package pers.candyboyou.commodity.business.service.admin.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminSkuAttributeMapper;
import pers.candyboyou.commodity.business.mapper.admin.AdminSkuAttributeRelationMapper;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeRelationDTO;
import pers.candyboyou.commodity.business.model.entity.SkuAttributeEntity;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueOfSkuVO;
import pers.candyboyou.commodity.business.service.admin.AdminSkuAttributeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AdminSkuAttributeServiceImpl implements AdminSkuAttributeService {

    @Autowired
    private AdminSkuAttributeMapper adminSkuAttributeMapper;

    @Autowired
    private AdminSkuAttributeRelationMapper skuAttributeRelationMapper;

    @Override
    public List<SkuAttributeEntity> getSkuAttributesOfCommodityId(Long id) {
        return adminSkuAttributeMapper.selectSkuAttributeByCommodityId(id);
    }

    @Override
    public Map<Long, List<AttributeValueOfSkuVO>> getSkuIdToAttributesMap(List<Long> skuIds) {
        List<SkuAttributeRelationDTO> skuAttributeRelationDTOS =  skuAttributeRelationMapper.selectAttributesOfSkuBySkuIds(skuIds);
        Map<Long, List<AttributeValueOfSkuVO>> skuIdToAttributeValueOfSkuVOMap = new HashMap<>();
        for (SkuAttributeRelationDTO skuAttributeRelationDTO : skuAttributeRelationDTOS) {
            AttributeValueOfSkuVO attributeValueOfSkuVO = AttributeValueOfSkuVO.convertAttributeWithValueDTO(skuAttributeRelationDTO);
            Long skuId = skuAttributeRelationDTO.getSkuId();
            List<AttributeValueOfSkuVO> attributeValueOfSkuVOS = skuIdToAttributeValueOfSkuVOMap.get(skuId);
            if (attributeValueOfSkuVOS == null) {
                attributeValueOfSkuVOS = new ArrayList<>();
            }
            attributeValueOfSkuVOS.add(attributeValueOfSkuVO);
            skuIdToAttributeValueOfSkuVOMap.put(skuId, attributeValueOfSkuVOS);
        }
        return skuIdToAttributeValueOfSkuVOMap;
    }
}
