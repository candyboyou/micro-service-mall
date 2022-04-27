package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AttrParam {

    @ApiModelProperty("属性id")
    private Long id;

    @ApiModelProperty("属性名称")
    private String name;

    @ApiModelProperty("属性录入方式：0->手工录入；1->从列表中选取")
    private Integer inputType;

    @ApiModelProperty("是否是销售属性；0->否；1->是")
    private Integer isSale;

    @ApiModelProperty("属性选择类型：0->单选；1->多选")
    private Integer selectType;

    @ApiModelProperty("检索类型；0->不支持检索；1->支持检索")
    private Integer searchType;

    @ApiModelProperty("预置选项")
    private List<String> attributeValues;

    @ApiModelProperty("是否禁用")
    private Integer isValid;
}