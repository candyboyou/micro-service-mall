package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class AttributeIdWithIsSaleDTO {

    /**
     * 销售属性id
     */
    private Long id;

    /**
     * 是否是销售属性
     */
    private Integer isSaleAttr;

}
