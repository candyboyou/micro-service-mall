package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.param.admin.AttributeOfCommoditySaveParam;

import java.util.List;
import java.util.Map;

public interface AdminAttributeService {

    Map<Long, Integer> getAttributeIdToIsSaleMap(List<Long> attributeIds);
}
