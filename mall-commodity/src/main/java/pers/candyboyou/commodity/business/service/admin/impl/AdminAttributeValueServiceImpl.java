package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.AdminAttributeValueMapper;
import pers.candyboyou.commodity.business.model.param.admin.AttributeOfCommoditySaveParam;
import pers.candyboyou.commodity.business.service.admin.AdminAttributeValueService;

import java.util.List;

@Service
@Slf4j
public class AdminAttributeValueServiceImpl implements AdminAttributeValueService {

    @Autowired
    private AdminAttributeValueMapper adminAttributeValueMapper;

    @Override
    public void saveAttributeValue(List<AttributeOfCommoditySaveParam> attributeOfCommoditySaveParams, Long commodityId) {
        if (CollectionUtils.isEmpty(attributeOfCommoditySaveParams)) {
            return;
        }
        adminAttributeValueMapper.saveAttributeValueOfCommodity(attributeOfCommoditySaveParams, commodityId);
    }
}
