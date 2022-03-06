package pers.candyboyou.commodity.business.service.admin;

import java.util.List;
import java.util.Map;

public interface AdminAttributeService {

    Map<Long, Integer> getAttributeIdToIsSaleMap(List<Long> attributeIds);
}
