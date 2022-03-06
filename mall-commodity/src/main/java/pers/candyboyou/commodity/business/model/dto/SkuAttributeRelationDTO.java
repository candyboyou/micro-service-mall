package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class SkuAttributeRelationDTO {

    /**
     * skuid
     */
    private Long skuId;

    /**
     * 属性id
     */
    private Long attributeId;

    /**
     * 属性值id
     */
    private Long valueId;
}
