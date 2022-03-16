package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.entity.AttributeEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AttributeVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2146651231533306770L;

    @ApiModelProperty("类型id")
    private Long id;

    @ApiModelProperty("属性名称")
    private Integer isSearch;

    @ApiModelProperty("是否是销售属性")
    private Integer isSaleAttr;

    @ApiModelProperty("是否多选")
    private Integer isMultiple;

    @ApiModelProperty("是否必选")
    private Integer isRequired;

    @ApiModelProperty("排序字段")
    private Integer sort;

    public static List<AttributeVO> convertAttributeEntities(List<AttributeEntity> attributeEntities) {
        if (CollectionUtils.isEmpty(attributeEntities)) {
            return new ArrayList<>();
        }
        List<AttributeVO> attributeVOs = new ArrayList<>();
        for (AttributeEntity attributeEntity : attributeEntities) {
            AttributeVO attributeVO = new AttributeVO();
            attributeVO.setId(attributeEntity.getId());
            attributeVO.setIsSearch(attributeEntity.getIsSearch());
            attributeVO.setIsSaleAttr(attributeEntity.getIsSaleAttr());
            attributeVO.setIsMultiple(attributeEntity.getIsMultiple());
            attributeVO.setIsRequired(attributeEntity.getIsRequired());
            attributeVO.setSort(attributeEntity.getSort());
            attributeVOs.add(attributeVO);
        }
        return attributeVOs;
    }
}
