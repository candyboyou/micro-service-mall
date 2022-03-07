package pers.candyboyou.commodity.business.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.param.admin.AttributeOfCommoditySaveParam;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuOfCommoditySaveDTO {

    private Long id;

    private BigDecimal price;

    private BigDecimal promotionPrice;

    private Integer stock;

    private Integer lockStock;

    private Integer lowStock;

}
