package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.candyboyou.commodity.business.model.entity.CategoryEntity;

import java.io.Serial;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDetailVO extends CategoryBaseVO {

    @Serial
    private static final long serialVersionUID = -7114670893685491466L;

    @ApiModelProperty("商品的数量")
    private Integer commodityCount;

    @ApiModelProperty("分类图标")
    private String iconUrl;

    @ApiModelProperty("分类的描述")
    private String description;

    @ApiModelProperty("在同一级别中的排序值")
    private Integer sort;

    @ApiModelProperty("关联的属性")
    private List<OptionCommonVO> relationAttributes;

    @ApiModelProperty("未关联的属性")
    private List<OptionCommonVO> notRelationAttributes;

    public static CategoryDetailVO convertCategoryEntity(CategoryEntity categoryEntity) {
        CategoryDetailVO categoryDetailVO = new CategoryDetailVO();
        categoryDetailVO.setCommodityCount(categoryEntity.getCommodityCount());
        categoryDetailVO.setIconUrl(categoryEntity.getIconUrl());
        categoryDetailVO.setDescription(categoryEntity.getDescription());
        categoryDetailVO.setSort(categoryEntity.getSort());
        categoryDetailVO.setId(categoryEntity.getId());
        categoryDetailVO.setName(categoryEntity.getName());
        categoryDetailVO.setParentId(categoryEntity.getParentId());
        categoryDetailVO.setLevelId(categoryEntity.getLevel());
        categoryDetailVO.setPlaceId(categoryEntity.getPlace());
        categoryDetailVO.setShowStatusId(categoryEntity.getIsValid());
        return categoryDetailVO;
    }
}
