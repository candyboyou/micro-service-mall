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

    @ApiModelProperty("是否是销售属性")
    private Integer isSaleAttr;

    @ApiModelProperty("是否多选")
    private Integer isMultiple;

    @ApiModelProperty("是否必选")
    private Integer isRequired;

    @ApiModelProperty("排序字段")
    private Integer sort;

    @ApiModelProperty("是否删除")
    private Integer isDelete = 0;
}
