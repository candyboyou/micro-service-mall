package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminAttributeMapper;
import pers.candyboyou.commodity.business.mapper.admin.AdminSkuAttributeMapper;
import pers.candyboyou.commodity.business.model.dto.AttributeDTO;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeDTO;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeVO;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeVO;
import pers.candyboyou.commodity.business.service.admin.AttributeService;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AdminSkuAttributeMapper skuAttributeMapper;

    @Autowired
    private AdminAttributeMapper attributeMapper;

    @Override
    public void saveOrUpdateSkuAttr(SkuAttrParam skuAttrParam) {
        if (skuAttrParam.getId() == null) {
            skuAttributeMapper.insertSkuAttr(skuAttrParam);
            return;
        }
        if (skuAttrParam.getIsDelete() == 1) {
            skuAttributeMapper.deleteSupAttrById(skuAttrParam.getId());
            return;
        }
        skuAttributeMapper.updateSupAttr(skuAttrParam);
    }

    @Override
    public ListVO<SkuAttributeVO> getSkuAttributesById(Long attributeId, QueryParam queryParam) {
        ListVO<SkuAttributeVO> skuAttributeVOListVO = new ListVO<>();
        if (attributeId == null) {
            return skuAttributeVOListVO;
        }
        List<SkuAttributeDTO> skuAttributeDTOList = skuAttributeMapper.selectSkuAttributeDTOS(attributeId, queryParam);
        List<SkuAttributeVO> skuAttributeVOS = SkuAttributeVO.convertSkuAttributeDTOS(skuAttributeDTOList);
        int count = skuAttributeMapper.selectSkuAttributeDTOSCount(attributeId);
        skuAttributeVOListVO.setList(skuAttributeVOS);
        skuAttributeVOListVO.setTotal(count);
        skuAttributeVOListVO.setPageNum(queryParam.getPageNum());
        skuAttributeVOListVO.setPageNum(skuAttributeVOS.size());
        return skuAttributeVOListVO;
    }

    @Override
    public void saveOrUpdateAttr(AttrParam AttrParam) {
        if (AttrParam.getId() == null) {
            attributeMapper.insertAttribute(AttrParam);
            return;
        }
        if (AttrParam.getIsDelete() == 1) {
            attributeMapper.deleteAttrById(AttrParam.getId());
            return;
        }
        attributeMapper.updateAttr(AttrParam);
    }

    @Override
    public ListVO<AttributeVO> getAttributeVOSById(Long attributeId, QueryParam queryParam) {
        ListVO<AttributeVO> attributeVOListVO = new ListVO<>();
        if (attributeId == null) {
            return attributeVOListVO;
        }
        List<AttributeDTO> AttributeDTOList = attributeMapper.selectAttributeDTOS(attributeId, queryParam);
        List<AttributeVO> AttributeVOS = AttributeVO.convertAttributeDTOS(AttributeDTOList);
        int count = attributeMapper.selectAttributeDTOSCount(attributeId);
        attributeVOListVO.setList(AttributeVOS);
        attributeVOListVO.setTotal(count);
        attributeVOListVO.setPageNum(queryParam.getPageNum());
        attributeVOListVO.setPageNum(AttributeVOS.size());
        return attributeVOListVO;
    }

}
