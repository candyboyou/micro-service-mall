package pers.candyboyou.commodity.business.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SpuOfCommoditySaveDTO {

    @ApiModelProperty("属性Id")
    private Long attributeId;

    @ApiModelProperty("属性值id")
    private Long attributeValueId;

}
