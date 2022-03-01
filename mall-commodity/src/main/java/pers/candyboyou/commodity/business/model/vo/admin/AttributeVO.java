package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.AttributeDTO;

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

    public static List<AttributeVO> convertAttributeDTOS(List<AttributeDTO> attributeDTOList) {
        if (CollectionUtils.isEmpty(attributeDTOList)) {
            return new ArrayList<>();
        }
        List<AttributeVO> attributeVOS = new ArrayList<>();
        for (AttributeDTO attributeDTO : attributeDTOList) {
            AttributeVO attributeVO = new AttributeVO();
            attributeVO.setId(attributeDTO.getId());
            attributeVO.setIsSearch(attributeDTO.getIsSearch());
            attributeVO.setIsSaleAttr(attributeDTO.getIsSaleAttr());
            attributeVO.setIsMultiple(attributeDTO.getIsMultiple());
            attributeVO.setIsRequired(attributeDTO.getIsRequired());
            attributeVO.setSort(attributeDTO.getSort());
            attributeVOS.add(attributeVO);
        }
        return attributeVOS;
    }
}
