package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuOfCommoditySaveDTO {

    private Long id;

    private BigDecimal price;

    private BigDecimal promotionPrice;

    private Integer stock;

    private Integer lockStock;

    private Integer lowStock;

}
