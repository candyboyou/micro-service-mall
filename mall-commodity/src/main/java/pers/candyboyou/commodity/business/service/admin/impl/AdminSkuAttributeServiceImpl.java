package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminSkuAttributeMapper;
import pers.candyboyou.commodity.business.mapper.admin.AdminSkuAttributeRelationMapper;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeRelationDTO;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeValueDTO;
import pers.candyboyou.commodity.business.model.entity.SkuAttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.AttributeOfCommoditySaveParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttributeOfCommoditySaveParam;
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

    // skuid - attributeId - attributeValue
    //       - attributeId - attributeValue
    //       - attributeId - attributeValue
    @Override
    public void saveSkuAttributeValue(List<SkuAttributeOfCommoditySaveParam> skuAttributeOfCommoditySaveParams, Long id) {
        if (CollectionUtils.isEmpty(skuAttributeOfCommoditySaveParams)) {
            return;
        }
        // 先将sku的库存值保存起来
        List<Long> skuIds = adminSkuAttributeMapper.saveSkuAttributeValue(skuAttributeOfCommoditySaveParams, id);
        // 然后再将sku的id和属性关联起来
        // 这个地方就是打平sku参数
        List<SkuAttributeValueDTO> skuAttributeValueDTOS = new ArrayList<>();
        for (SkuAttributeOfCommoditySaveParam skuAttributeOfCommoditySaveParam : skuAttributeOfCommoditySaveParams) {
            List<AttributeOfCommoditySaveParam> attributeOfCommoditySaveParams = skuAttributeOfCommoditySaveParam.getAttributeOfCommoditySaveParams();
            for (AttributeOfCommoditySaveParam attributeOfCommoditySaveParam : attributeOfCommoditySaveParams) {
                SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
                skuAttributeValueDTO.setSkuId(0L);
                skuAttributeValueDTO.setAttributeId(0L);
                skuAttributeValueDTO.setValueId(0L);
            }
        }
    }
}
