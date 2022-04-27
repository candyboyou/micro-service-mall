package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;
import pers.candyboyou.commodity.business.model.vo.admin.RelationAttributeVO;

import java.util.List;

@Data
public class CategorySaveOrUpdateParam {

    @ApiModelProperty("分类ID")
    private Long id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("上级分类ID")
    private Long parentId;

    @ApiModelProperty("级别")
    private Integer level;

    @ApiModelProperty("在同一级别中的排序值")
    private Integer sort;

    @ApiModelProperty("商品的数量")
    private Integer commodityCount;

    @ApiModelProperty("显示状态")
    private Integer isShow;

    @ApiModelProperty("分类的位置")
    private Integer place;

    @ApiModelProperty("相同等级分类")
    private List<OptionCommonVO> sameLevelCategoryList;

    @ApiModelProperty("分类图标")
    private String iconUrl;

    @ApiModelProperty("关联属性")
    private List<RelationAttributeVO> relationAttributes;

    @ApiModelProperty("关键字")
    private List<String> keyWords;

    @ApiModelProperty("分类的描述")
    private String description;

    @ApiModelProperty("是否删除")
    private Integer isDelete = 0;

}
