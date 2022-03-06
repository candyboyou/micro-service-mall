package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class AttributeValueVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7539680043935797304L;

    @ApiModelProperty("属性Id")
    private Long attributeId;

    @ApiModelProperty("属性值id")
    private Long attributeValueId;

    @ApiModelProperty("是否是销售属性")
    private Integer isSaleAttribute;

}
