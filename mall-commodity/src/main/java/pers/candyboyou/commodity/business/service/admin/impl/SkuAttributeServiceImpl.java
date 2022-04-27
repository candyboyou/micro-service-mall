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
import pers.candyboyou.commodity.business.model.param.admin.AttributeValueSaveParam;
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
        List<SkuAttributeRelationDTO> skuAttributeRelationDTOs =  tCommoditySkuAttributeRelationMapper.selectAttributesOfSkuBySkuIds(skuIds);
        Map<Long, List<AttributeValueOfSkuVO>> skuIdToAttributeValueOfSkuVOMap = new HashMap<>();
        for (SkuAttributeRelationDTO skuAttributeRelationDTO : skuAttributeRelationDTOs) {
            AttributeValueOfSkuVO attributeValueOfSkuVO = AttributeValueOfSkuVO.convertAttributeWithValueDTO(skuAttributeRelationDTO);
            Long skuId = skuAttributeRelationDTO.getSkuId();
            List<AttributeValueOfSkuVO> attributeValueOfSkuVOs = skuIdToAttributeValueOfSkuVOMap.get(skuId);
            if (attributeValueOfSkuVOs == null) {
                attributeValueOfSkuVOs = new ArrayList<>();
            }
            attributeValueOfSkuVOs.add(attributeValueOfSkuVO);
            skuIdToAttributeValueOfSkuVOMap.put(skuId, attributeValueOfSkuVOs);
        }
        return skuIdToAttributeValueOfSkuVOMap;
    }

    // skuid - attributeId - attributeValue
    //       - attributeId - attributeValue
    //       - attributeId - attributeValue
    @Override
    public void saveSkuAttributeValue(List<SkuSaveParam> skuSaveParams, Long commodityId) {
        if (CollectionUtils.isEmpty(skuSaveParams)) {
            return;
        }
        // 先将sku的库存值保存起来
//        List<Long> skuIds = tCommoditySkuMapper.saveSkuAttributeValue(skuSaveParams, commodityId);
        tCommoditySkuMapper.saveSingleSkuAttributeValue(skuSaveParams.get(0));
        // 然后再将sku的id和属性关联起来
        // 这个地方就是打平sku参数
//        List<SkuAttributeValueDTO> skuAttributeValueDTOs = new ArrayList<>();
//        int i = 0;
//        for (SkuSaveParam skuAttributeOfCommoditySaveParam : skuSaveParams) {
//            Long skuId = skuIds.get(i++);
//            List<AttributeValueSaveParam> attributeValueSaveParams = skuAttributeOfCommoditySaveParam.getSkuAttributeValueSaveParam();
//            for (AttributeValueSaveParam attributeValueSaveParam : attributeValueSaveParams) {
//                SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
//                skuAttributeValueDTO.setSkuId(skuId);
//                skuAttributeValueDTO.setAttributeId(attributeValueSaveParam.getAttributeId());
//                skuAttributeValueDTO.setValueId(attributeValueSaveParam.getAttributeValueId());
//                skuAttributeValueDTO.setValue(attributeValueSaveParam.getAttributeValue());
//                skuAttributeValueDTOs.add(skuAttributeValueDTO);
//            }
//        }
//        // 保存sku对应的属性值
//        tCommoditySkuAttributeRelationMapper.batchInsertSkuAttribute(skuAttributeValueDTOs);
    }

    @Override
    public void updateSkuAttributeValue(List<SkuSaveParam> skuUpdateParams, Long commodityId) {
        if (CollectionUtils.isEmpty(skuUpdateParams)) {
            return;
        }
        List<Long> newSkuIds = skuUpdateParams.stream().map(SkuSaveParam::getId).toList();
        List<Long> delSkuIds = getSkuIdByCommodityId(commodityId);
        delSkuIds.removeAll(newSkuIds);

        // 删除响应的sku
        if (CollectionUtils.isNotEmpty(delSkuIds)) {
            tCommoditySkuMapper.batchDeleteSkuById(delSkuIds);
        }
        // 先将sku的库存值保存起来
        tCommoditySkuMapper.batchUpdateSku(skuUpdateParams);
        // 然后再将sku的id和属性关联起来
        // 这个地方就是打平sku参数
        List<SkuAttributeValueDTO> skuAttributeValueDTOs = new ArrayList<>();
        for (SkuSaveParam skuUpdateParam : skuUpdateParams) {
            Long skuId = skuUpdateParam.getId();
            List<AttributeValueSaveParam> attributeValueSaveParams = skuUpdateParam.getSkuAttributeValueSaveParam();
            for (AttributeValueSaveParam attributeValueSaveParam : attributeValueSaveParams) {
                SkuAttributeValueDTO skuAttributeValueDTO = new SkuAttributeValueDTO();
                skuAttributeValueDTO.setSkuId(skuId);
                skuAttributeValueDTO.setAttributeId(attributeValueSaveParam.getAttributeId());
                skuAttributeValueDTO.setValueId(attributeValueSaveParam.getAttributeValueId());
                skuAttributeValueDTO.setValue(attributeValueSaveParam.getAttributeValue());
                skuAttributeValueDTOs.add(skuAttributeValueDTO);
            }
        }
        // 保存sku对应的属性值
        tCommoditySkuAttributeRelationMapper.batchUpdateAttributeValueOfSku(skuAttributeValueDTOs);
    }

    private List<Long> getSkuIdByCommodityId(Long commodityId) {
        return tCommoditySkuMapper.selectSkuIdsByCommodityId(commodityId);
    }
}
