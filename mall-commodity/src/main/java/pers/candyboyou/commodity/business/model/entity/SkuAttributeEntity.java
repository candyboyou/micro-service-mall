package pers.candyboyou.commodity.business.model.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

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
