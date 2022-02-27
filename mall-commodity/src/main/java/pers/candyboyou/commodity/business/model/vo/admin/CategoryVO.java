package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CategoryVO implements Serializable{

    @Serial
    private static final long serialVersionUID = -1460828958561201779L;

    @ApiModelProperty("分类ID")
    private Long categoryId;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("级别")
    private Integer level;

    @ApiModelProperty("上级分类ID")
    private Integer parentId;

    @ApiModelProperty("商品数量")
    private String count;

    @ApiModelProperty("数量单位ID")
    private Integer unitId;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    private Integer isShow;

    @ApiModelProperty("分类描述")
    private String description;

}
