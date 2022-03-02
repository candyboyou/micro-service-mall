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

    @ApiModelProperty("属性值ID")
    private Long id;

    @ApiModelProperty("属性值")
    private String value;

    @ApiModelProperty("是否是颜色属性")
    private Integer isColor;

    @ApiModelProperty("如果是颜色，图片的url")
    private String url;

}
