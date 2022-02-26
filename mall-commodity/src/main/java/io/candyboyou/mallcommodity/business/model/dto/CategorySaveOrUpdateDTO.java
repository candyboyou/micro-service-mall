package io.candyboyou.mallcommodity.business.model.dto;

import io.candyboyou.common.framework.model.base.Entity;
import io.candyboyou.mallcommodity.business.model.param.admin.CategorySaveOrUpdateParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategorySaveOrUpdateDTO extends Entity {

    @Serial
    private static final long serialVersionUID = 7594666187351315237L;

    private String name;

    private Integer parentCategoryId;

    private Integer unitId;

    private Integer isDisplay;

    private Integer isUpIndex;

    private String imgURL;

    private Integer isDoneIndex;

    private String description;

    public static CategorySaveOrUpdateDTO convert(CategorySaveOrUpdateParam categorySaveOrUpdateParam) {
        CategorySaveOrUpdateDTO categorySaveOrUpdateDTO = new CategorySaveOrUpdateDTO();
        categorySaveOrUpdateDTO.setParentCategoryId(categorySaveOrUpdateParam.getParentCategoryId());
        categorySaveOrUpdateDTO.setUnitId(categorySaveOrUpdateParam.getUnitId());
        categorySaveOrUpdateDTO.setIsDisplay(categorySaveOrUpdateParam.getIsDisplay());
        categorySaveOrUpdateDTO.setIsUpIndex(categorySaveOrUpdateParam.getIsUpIndex());
        categorySaveOrUpdateDTO.setImgURL(categorySaveOrUpdateParam.getImgURL());
        categorySaveOrUpdateDTO.setIsDoneIndex(categorySaveOrUpdateParam.getIsDoneIndex());
        categorySaveOrUpdateDTO.setDescription(categorySaveOrUpdateParam.getDescription());
        categorySaveOrUpdateDTO.setName(categorySaveOrUpdateParam.getName());
        return categorySaveOrUpdateDTO;
    }
}
