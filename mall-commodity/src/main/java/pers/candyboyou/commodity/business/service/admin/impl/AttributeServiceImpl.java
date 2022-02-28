package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminConcreteAttributeMapper;
import pers.candyboyou.commodity.business.mapper.admin.AdminSkuAttributeMapper;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeDTO;
import pers.candyboyou.commodity.business.model.param.admin.ConcreteAttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.vo.ConcreteAttributeVO;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeVO;
import pers.candyboyou.commodity.business.service.admin.AttributeService;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AdminSkuAttributeMapper skuAttributeMapper;

    @Autowired
    private AdminConcreteAttributeMapper concreteAttributeMapper;

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
    public void saveOrUpdateConcreteAttr(ConcreteAttrParam concreteAttrParam) {
        if (concreteAttrParam.getId() == null) {
            concreteAttributeMapper.insertConcreteAttr(concreteAttrParam);
            return;
        }
        if (concreteAttrParam.getIsDelete() == 1) {
            concreteAttributeMapper.deleteConcreteAttrById(concreteAttrParam.getId());
            return;
        }
        concreteAttributeMapper.updateConcreteAttr(concreteAttrParam);
    }

    @Override
    public ListVO<ConcreteAttributeVO> getConcreteAttributesById(Long attributeId, QueryParam queryParam) {
        ListVO<ConcreteAttributeVO> concreteAttributeVOListVO = new ListVO<>();
        if (attributeId == null) {
            return concreteAttributeVOListVO;
        }
        List<ConcreteAttributeVO> concreteAttributeDTOList = concreteAttributeMapper.selectConcreteAttributeDTOS(attributeId, queryParam);
        List<ConcreteAttributeVO> concreteAttributeVOS = ConcreteAttributeVO.convertConcreteAttributeDTOS(concreteAttributeDTOList);
        int count = concreteAttributeMapper.selectConcreteAttributeDTOSCount(attributeId);
        concreteAttributeVOListVO.setList(concreteAttributeVOS);
        concreteAttributeVOListVO.setTotal(count);
        concreteAttributeVOListVO.setPageNum(queryParam.getPageNum());
        concreteAttributeVOListVO.setPageNum(concreteAttributeVOS.size());
        return concreteAttributeVOListVO;
    }

}
