package pers.candyboyou.mallpromotion.business.model.admin.entity;

import io.candyboyou.common.framework.model.base.Entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class FlashDealCommodityEntity extends Entity {

    @Serial
    private static final long serialVersionUID = 4053579495507608604L;

    private Long flashDealId;

    private Long commodityId;

    private String name;

    private BigDecimal price;

    private BigDecimal flashDealPrice;

    private Integer flashDealCount;

    private Integer flashDealLeftCount;

    private Integer flashDealLimit;

}
