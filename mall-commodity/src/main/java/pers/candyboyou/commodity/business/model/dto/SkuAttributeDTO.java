package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class SkuAttributeDTO {

    private Long id;

    private Long attributeId;

    private String name;

    private String valueListStr;
}
