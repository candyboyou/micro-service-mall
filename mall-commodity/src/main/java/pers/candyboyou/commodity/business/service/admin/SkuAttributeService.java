package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.entity.SkuAttributeEntity;
import pers.candyboyou.commodity.business.model.param.admin.SkuSaveParam;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueOfSkuVO;

import java.util.List;
import java.util.Map;

public interface SkuAttributeService {

    List<SkuAttributeEntity> getSkuAttributesOfCommodityId(Long id);

    Map<Long, List<AttributeValueOfSkuVO>> getSkuIdToAttributesMap(List<Long> skuIds);

    void saveSkuAttributeValue(List<SkuSaveParam> skuSaveParams, Long id);

    /**
     * 批量更新商品的sku属性
     */
    void updateSkuAttributeValue(List<SkuSaveParam> skuSaveParams, Long commodityId);
}
