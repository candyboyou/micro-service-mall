package pers.candyboyou.commodity.business.model.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品spu属性对象
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpuAttributeEntity extends Entity {

    /**
     * spu库存
     */
    private Integer stock;

    /**
     * spu预警库存
     */
    private Integer lowStock;

    /**
     * spu销量
     */
    private Integer sale;

    /**
     * spu价格
     */
    private BigDecimal price;
}
