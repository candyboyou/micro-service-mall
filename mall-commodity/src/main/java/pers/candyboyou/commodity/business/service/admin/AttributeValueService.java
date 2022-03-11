package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.param.admin.SpuAttributeValueSaveParam;

import java.util.List;

public interface AttributeValueService {

    void saveAttributeValue(List<SpuAttributeValueSaveParam> attributeOfCommoditySaveParams, Long commodityId);
}
