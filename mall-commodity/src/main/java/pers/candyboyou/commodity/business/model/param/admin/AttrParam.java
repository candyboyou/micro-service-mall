package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AttrParam {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("属性id")
    private Long attributeId;

    @ApiModelProperty("属性值list")
    private List<Long> valueIds;

    @ApiModelProperty("是否删除")
    private Integer isDelete = 0;
}
