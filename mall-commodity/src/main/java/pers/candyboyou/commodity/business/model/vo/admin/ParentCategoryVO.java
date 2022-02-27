package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ParentCategoryVO {

    @ApiModelProperty("分类Code")
    private Long code;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("下一级分类list")
    private List<ParentCategoryVO> parentCategoryVOS;
}
