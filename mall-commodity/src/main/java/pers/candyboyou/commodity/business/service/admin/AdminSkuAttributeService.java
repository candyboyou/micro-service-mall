package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.entity.SkuAttributeEntity;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueOfSkuVO;

import java.util.List;
import java.util.Map;

public interface AdminSkuAttributeService {

    List<SkuAttributeEntity> getSkuAttributesOfCommodityId(Long id);

    Map<Long, List<AttributeValueOfSkuVO>> getSkuIdToAttributesMap(List<Long> skuIds);
}
