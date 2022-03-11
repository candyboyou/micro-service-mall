package pers.candyboyou.commodity.business.model.param.admin;

import lombok.Data;

@Data
public class SpuAttributeValueSaveParam {

    private Long attributeId;

    private Long attributeValueId;

    private String attributeValue;
}
