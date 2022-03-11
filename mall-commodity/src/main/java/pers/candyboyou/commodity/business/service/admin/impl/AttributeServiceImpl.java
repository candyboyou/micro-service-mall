package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.TCommodityAttributeMapper;
import pers.candyboyou.commodity.business.mapper.admin.TCommoditySkuMapper;
import pers.candyboyou.commodity.business.model.dto.AttributeIdWithIsSaleDTO;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeDTO;
import pers.candyboyou.commodity.business.model.entity.AttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SpuAttributeValueSaveParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeVO;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeVO;
import pers.candyboyou.commodity.business.service.admin.AttributeService;

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
    public ListVO<SkuAttributeVO> getSkuAttributesById(Long attributeId, QueryParam queryParam) {
        ListVO<SkuAttributeVO> skuAttributeVOListVO = new ListVO<>();
        if (attributeId == null) {
            return skuAttributeVOListVO;
        }
        List<SkuAttributeDTO> skuAttributeDTOList = tCommoditySkuMapper.selectSkuAttributeDTOS(attributeId, queryParam);
        List<SkuAttributeVO> skuAttributeVOS = SkuAttributeVO.convertSkuAttributeDTOS(skuAttributeDTOList);
        int count = tCommoditySkuMapper.selectSkuAttributeDTOSCount(attributeId);
        skuAttributeVOListVO.setList(skuAttributeVOS);
        skuAttributeVOListVO.setTotal(count);
        skuAttributeVOListVO.setPageNum(queryParam.getPageNum());
        skuAttributeVOListVO.setPageNum(skuAttributeVOS.size());
        return skuAttributeVOListVO;
    }

    @Override
    public void saveOrUpdateAttr(AttrParam AttrParam) {
        if (AttrParam.getId() == null) {
            tCommodityAttributeMapper.insertAttribute(AttrParam);
            return;
        }
        if (AttrParam.getIsDelete() == 1) {
            tCommodityAttributeMapper.deleteAttrById(AttrParam.getId());
            return;
        }
        tCommodityAttributeMapper.updateAttr(AttrParam);
    }

    @Override
    public ListVO<AttributeVO> getAttributeVOSById(Long attributeId, QueryParam queryParam) {
        ListVO<AttributeVO> attributeVOListVO = new ListVO<>();
        if (attributeId == null) {
            return attributeVOListVO;
        }
        List<AttributeEntity> attributeEntities = tCommodityAttributeMapper.selectAttributeDTOS(attributeId, queryParam);
        List<AttributeVO> AttributeVOS = AttributeVO.convertAttributeEntities(attributeEntities);
        int count = tCommodityAttributeMapper.selectAttributeDTOSCount(attributeId);
        attributeVOListVO.setList(AttributeVOS);
        attributeVOListVO.setTotal(count);
        attributeVOListVO.setPageNum(queryParam.getPageNum());
        attributeVOListVO.setPageNum(AttributeVOS.size());
        return attributeVOListVO;
    }

    @Override
    public Map<Long, Integer> getAttributeIdToIsSaleMap(List<Long> attributeIds) {
        List<AttributeIdWithIsSaleDTO> attributeIdWithIsSaleDTOS = tCommodityAttributeMapper.selectAttributeIdWithIsSale(attributeIds);
        return attributeIdWithIsSaleDTOS.stream().collect(Collectors.toMap(AttributeIdWithIsSaleDTO::getId, AttributeIdWithIsSaleDTO::getIsSaleAttr));
    }

    @Override
    public void saveAttributeValue(List<SpuAttributeValueSaveParam> spuAttributeValueSaveParams, Long id) {

    }

}
