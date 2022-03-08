package pers.candyboyou.commodity.business.model.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * SPU 属性(不会影响到库存和价格的属性, 又叫关键属性)
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SkuAttributeEntity extends Entity {

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * sku价格
     */
    private BigDecimal price;

    /**
     * sku促销价格
     */
    private BigDecimal promotionPrice;

    /**
     * sku库存
     */
    private Integer stock;

    /**
     * 锁定库存
     */
    private Integer lockStock;

    /**
     * 预警库存
     */
    private Integer lowStock;

    /**
     * 销量
     */
    private Integer sale;
}
