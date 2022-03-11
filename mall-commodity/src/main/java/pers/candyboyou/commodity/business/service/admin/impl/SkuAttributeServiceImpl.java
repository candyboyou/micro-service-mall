package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.TCommoditySkuAttributeRelationMapper;
import pers.candyboyou.commodity.business.mapper.admin.TCommoditySkuMapper;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeRelationDTO;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeValueDTO;
import pers.candyboyou.commodity.business.model.entity.SkuAttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.SkuSaveParam;
import pers.candyboyou.commodity.business.model.param.admin.SpuAttributeValueSaveParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueOfSkuVO;
import pers.candyboyou.commodity.business.service.admin.SkuAttributeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SkuAttributeServiceImpl implements SkuAttributeService {

    @Autowired
    private TCommoditySkuMapper tCommoditySkuMapper;

    @Autowired
    private TCommoditySkuAttributeRelationMapper tCommoditySkuAttributeRelationMapper;

    @Override
    public List<SkuAttributeEntity> getSkuAttributesOfCommodityId(Long id) {
        return tCommoditySkuMapper.selectSkuAttributeByCommodityId(id);
    }

    @Override
    public Map<Long, List<AttributeValueOfSkuVO>> getSkuIdToAttributesMap(List<Long> skuIds) {
        List<SkuAttributeRelationDTO> skuAttributeRelationDTOS =  tCommoditySkuAttributeRelationMapper.selectAttributesOfSkuBySkuIds(skuIds);
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
    public void saveSkuAttributeValue(List<SkuSaveParam> skuSaveParams, Long id) {
        if (CollectionUtils.isEmpty(skuSaveParams)) {
            return;
        }
        // 先将sku的库存值保存起来
        List<Long> skuIds = tCommoditySkuMapper.saveSkuAttributeValue(skuSaveParams, id);
        // 然后再将sku的id和属性关联起来
        // 这个地方就是打平sku参数
        List<SkuAttributeValueDTO> skuAttributeValueDTOS = new ArrayList<>();
        int i = 0;
        for (SkuSaveParam skuAttributeOfCommoditySaveParam : skuSaveParams) {
            Long skuId = skuIds.get(i++);
            List<SpuAttributeValueSaveParam> attributeValueSaveParams = skuAttributeOfCommoditySaveParam.getSkuAttributeValueSaveParam();
            for (SpuAttributeValueSaveParam attributeValueSaveParam : attributeValueSaveParams) {
                SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
                skuAttributeValueDTO.setSkuId(skuId);
                skuAttributeValueDTO.setAttributeId(attributeValueSaveParam.getAttributeId());
                skuAttributeValueDTO.setValueId(attributeValueSaveParam.getAttributeValueId());
                skuAttributeValueDTO.setValue(attributeValueSaveParam.getAttributeValue());
                skuAttributeValueDTOS.add(skuAttributeValueDTO);
            }
        }
        // 保存sku对应的属性值
        tCommoditySkuAttributeRelationMapper.batchInsertSkuAttribute(skuAttributeValueDTOS);
    }
}
