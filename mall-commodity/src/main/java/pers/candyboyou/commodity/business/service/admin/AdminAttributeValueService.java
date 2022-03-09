package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.param.admin.AttributeOfCommoditySaveParam;

import java.util.List;

public interface AdminAttributeValueService {

    void saveAttributeValue(List<AttributeOfCommoditySaveParam> attributeOfCommoditySaveParams, Long commodityId);
}
