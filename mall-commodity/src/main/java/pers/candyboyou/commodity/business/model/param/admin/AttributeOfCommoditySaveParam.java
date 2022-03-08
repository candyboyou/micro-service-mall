package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AttributeOfCommoditySaveParam {

    private Long attributeId;

    private Long attributeValueId;

    private String attributeValue;
}
