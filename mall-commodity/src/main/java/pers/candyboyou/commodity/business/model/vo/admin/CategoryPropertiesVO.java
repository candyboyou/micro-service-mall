package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryPropertiesVO {

    @ApiModelProperty("数量单位list")
    private List<UnitVO> unitVOs;

    @ApiModelProperty("分类list")
    private List<ParentCategoryVO> parentCategoryVOs;
}
