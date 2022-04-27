package pers.candyboyou.mallpromotion.business.model.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FlashDealCommodityVO {

    @ApiModelProperty("商品的id")
    private Long commodityId;

    @ApiModelProperty("商品的名称")
    private String commodityName;

    @ApiModelProperty("秒杀价格")
    private BigDecimal flashDealPrice;

    @ApiModelProperty("秒杀数量")
    private Integer flashDealCount;

    @ApiModelProperty("剩余秒杀数量")
    private Integer flashDealLeftCount;

    @ApiModelProperty("限购数量")
    private Integer flashDealLimit;

}
