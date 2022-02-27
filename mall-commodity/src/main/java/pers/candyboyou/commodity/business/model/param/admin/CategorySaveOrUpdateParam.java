package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品固有分类对象
 */
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

    @ApiModelProperty("分类描述")
    private String description;

    @ApiModelProperty("是否删除")
    private Integer isDelete = 0;
}
