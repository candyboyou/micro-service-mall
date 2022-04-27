package pers.candyboyou.mallpromotion.business.model.admin.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FlashDealCommodityParam {

    @ApiModelProperty("秒杀商品id")
    private Long Id;

    @ApiModelProperty("秒杀商品名称")
    private String name;

    @ApiModelProperty("商品原价")
    private BigDecimal price;

    @ApiModelProperty("秒杀商品价格")
    private BigDecimal flashDealPrice;

    @ApiModelProperty("秒杀库存")
    private Integer flashDealCount;

    @ApiModelProperty("秒杀商品剩余库存")
    private Integer flashDealLeftCount;

    @ApiModelProperty("秒杀商品限购数量")
    private Integer flashDealLimit;

}
