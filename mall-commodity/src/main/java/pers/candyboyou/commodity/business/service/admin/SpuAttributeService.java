package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.entity.SpuAttributeEntity;

import java.util.List;
import java.util.Map;

public interface SpuAttributeService {

    List<SpuAttributeEntity> getSpuAttributesByIds(List<Long> ids);

    Map<Long, SpuAttributeEntity> getSpuIdWithSpuByIds(List<Long> spuIds);

    SpuAttributeEntity getSpuAttributesById(Long spuId);
}
