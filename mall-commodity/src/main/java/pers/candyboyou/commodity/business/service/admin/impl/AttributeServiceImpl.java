package pers.candyboyou.commodity.business.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminAttributeMapper;
import pers.candyboyou.commodity.business.model.param.admin.SpuAttrParam;
import pers.candyboyou.commodity.business.service.admin.AttributeService;

@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AdminAttributeMapper attributeMapper;

    @Override
    public void saveOrUpdateSpuAttr(SpuAttrParam spuAttrParam) {
        if (spuAttrParam.getId() == null) {
            attributeMapper.insertSpuAttr(spuAttrParam);
        }
    }
}
