package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class SpuAttrParam implements Serializable {

    @Serial
    private static final long serialVersionUID = -6412173745590605674L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("属性值")
    private String name;

    @ApiModelProperty("类型ID")
    private Long attributeId;

    @ApiModelProperty("商品属性属性是否关联")
    private Integer isRelated;

    @ApiModelProperty("属性是否可选")
    private Integer isCanSelect;

    @ApiModelProperty("属性值的录入方式")
    private Integer isManual;

    @ApiModelProperty("属性值list ID")
    private Long valueListId;

    @ApiModelProperty("属性值字符串")
    private List<String> valueStr;

    @ApiModelProperty("是否删除")
    private Integer isDelete = 0;

}
