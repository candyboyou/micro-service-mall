package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AttributeValueSaveParam {

    @ApiModelProperty("属性ID")
    private Long attributeId;

    @ApiModelProperty("属性值的ID")
    private Long attributeValueId;

    @ApiModelProperty("具体的属性值")
    private String attributeValue;
}
