package io.candyboyou.mallcommodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategorySaveOrUpdateParam {

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("上级分类ID")
    private Integer parentCategoryId;

    @ApiModelProperty("数量单位ID")
    private Integer unitId;

    @ApiModelProperty("是否显示")
    private Integer isDisplay;

    @ApiModelProperty("是否在首页上方显示")
    private Integer isUpIndex;

    @ApiModelProperty("首页上方显示时，分类的图标URL")
    private String imgURL;

    @ApiModelProperty("是否在首页下方显示")
    private Integer isDoneIndex;

    @ApiModelProperty("分类描述")
    private String description;

}
