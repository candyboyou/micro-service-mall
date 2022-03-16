package pers.candyboyou.commodity.business.service.admin;

import pers.candyboyou.commodity.business.model.dto.AttributeValueDTO;
import pers.candyboyou.commodity.business.model.param.admin.AttributeValueSaveParam;

import java.util.List;
import java.util.Map;

public interface AttributeValueService {

    /**
     * 保存商品关联属性对应的属性值
     */
    void saveAttributeValueByCommodityId(List<AttributeValueSaveParam> attributeValueSaveParams , Long commodityId);

    /**
     * 更新商品关联属性对应的属性值
     */
    void updateAttributeValues(List<AttributeValueSaveParam> attributeValueSaveParams);

    /**
     * 根据属性值id批量获取对应的属性值
     */
    Map<Long, String> getAttributeValuesByValueIds(List<Long> attributeValueIds);

    /**
     * 根据属性id批量获取对应的属性值对象
     */
    Map<Long, AttributeValueDTO> getAttributeValuesByAttributeIdsAndCommodityId(List<Long> attributeIds, Long commodityId);

    /**
     * 删除商品关联的属性值
     */
    void deleteByCommodityId(Long commodityId);

}
