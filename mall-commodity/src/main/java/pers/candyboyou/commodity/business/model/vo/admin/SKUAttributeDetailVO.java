package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class SKUAttributeDetailVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7771520762644112020L;

    @ApiModelProperty("sku的id")
    private Long id;

    @ApiModelProperty("sku编码")
    private String skuNum;

    @ApiModelProperty("sku的属性list")
    private List<AttributeOfSKUVO> attributeOfSKUVOS;

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
