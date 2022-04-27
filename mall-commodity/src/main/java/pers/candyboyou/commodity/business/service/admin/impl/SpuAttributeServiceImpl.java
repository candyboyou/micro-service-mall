package pers.candyboyou.commodity.business.service.admin.impl;

import io.candyboyou.common.utils.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.candyboyou.commodity.business.mapper.admin.TCommoditySpuMapper;
import pers.candyboyou.commodity.business.model.entity.SpuAttributeEntity;
import pers.candyboyou.commodity.business.service.admin.SpuAttributeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SpuAttributeServiceImpl implements SpuAttributeService {

    @Autowired
    private TCommoditySpuMapper tCommoditySpuMapper;

    @Override
    public List<SpuAttributeEntity> getSpuAttributesByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }
        List<SpuAttributeEntity> spuAttributeEntities = tCommoditySpuMapper.selectSpuAttributeEntities(ids);
        return spuAttributeEntities;
    }

    @Override
    public Map<Long, SpuAttributeEntity> getSpuIdWithSpuByIds(List<Long> spuIds) {
        if (CollectionUtils.isEmpty(spuIds)) {
            return new HashMap<>();
        }
        List<SpuAttributeEntity> spuAttributesByIds = getSpuAttributesByIds(spuIds);
        return spuAttributesByIds.stream().collect(Collectors.toMap(SpuAttributeEntity::getId, SpuAttributeEntity -> SpuAttributeEntity));
    }

    @Override
    public SpuAttributeEntity getSpuAttributesById(Long spuId) {
        if (spuId == null) {
            return null;
        }
        List<SpuAttributeEntity> spuAttributesByIds = getSpuAttributesByIds(List.of(spuId));
        if (CollectionUtils.isEmpty(spuAttributesByIds)) {
            return null;
        }
        return spuAttributesByIds.get(0);
    }


}
