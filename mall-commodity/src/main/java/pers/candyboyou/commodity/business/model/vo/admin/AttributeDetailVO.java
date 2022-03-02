package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class AttributeDetailVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -2869474438735161404L;

    @ApiModelProperty("属性ID")
    private Long attributeId;

    @ApiModelProperty("属性名称")
    private String attributeName;

    @ApiModelProperty("属性值list")
    private List<AttributeValueVO> valueVOS;

}
