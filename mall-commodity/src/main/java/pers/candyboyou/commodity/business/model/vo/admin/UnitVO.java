package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UnitVO {

    @ApiModelProperty("数量单位id")
    private Long id;

    @ApiModelProperty("数量单位名称")
    private String name;
}
