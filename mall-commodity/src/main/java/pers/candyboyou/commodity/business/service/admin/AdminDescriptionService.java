package pers.candyboyou.commodity.business.service.admin;

import java.util.List;
import java.util.Map;

public interface AdminDescriptionService {

    Map<Long, String> getDescIdWithImgByIds(List<Long> descriptionIds);
}
