package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.vo.admin.AttributeValueOfSkuVO;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuSaveParam {

    @ApiModelProperty("sku的id")
    private Long id;

    @ApiModelProperty("sku的属性list")
    private List<AttributeValueSaveParam> skuAttributeValueSaveParam;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("锁定库存")
    private Integer lockStock;

    @ApiModelProperty("低库存")
    private Integer lowStock;

    @ApiModelProperty("销量")
    private Integer sale;
}
