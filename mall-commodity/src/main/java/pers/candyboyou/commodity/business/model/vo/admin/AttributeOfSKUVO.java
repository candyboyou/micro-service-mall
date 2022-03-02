package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AttributeOfSKUVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4946265062788649058L;

    @ApiModelProperty("属性ID")
    private Long id;

    @ApiModelProperty("属性名")
    private String name;

    @ApiModelProperty("属性值ID")
    private Long valueId;

    @ApiModelProperty("属性值")
    private String value;
}
