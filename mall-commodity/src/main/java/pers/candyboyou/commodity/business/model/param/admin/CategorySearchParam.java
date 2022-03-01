package pers.candyboyou.commodity.business.model.param.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类查询参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategorySearchParam extends QueryParam {

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父类ID")
    private Long parentId;

    @ApiModelProperty("级别")
    private Integer level;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否显示")
    private Integer isShow;
}
