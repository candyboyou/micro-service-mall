package pers.candyboyou.commodity.business.model.vo.admin;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.enums.InputTypeEnum;
import pers.candyboyou.commodity.business.enums.IsSaleEnum;
import pers.candyboyou.commodity.business.enums.IsValidEnum;
import pers.candyboyou.commodity.business.enums.SearchTypeEnum;
import pers.candyboyou.commodity.business.model.dto.AttributeOfListDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AttributeOfListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3408485508871720108L;

    @ApiModelProperty("类型id")
    private Long id;

    @ApiModelProperty("属性名称")
    private String name;

    @ApiModelProperty("是否是销售属性")
    private String isSale;

    @ApiModelProperty("是否支持检索")
    private String inputType;

    @ApiModelProperty("是否启用")
    private String isValid;

    public static List<AttributeOfListVO> convertAttributeOfListDTO(List<AttributeOfListDTO> attributeOfListDTOS) {
        if (CollectionUtils.isEmpty(attributeOfListDTOS)) {
            return new ArrayList<>();
        }
        List<AttributeOfListVO> attributeOfListVOS = new ArrayList<>();
        for (AttributeOfListDTO attributeOfListDTO : attributeOfListDTOS) {
            AttributeOfListVO attributeOfListVO = new AttributeOfListVO();
            attributeOfListVO.setId(attributeOfListDTO.getId());
            attributeOfListVO.setName(attributeOfListDTO.getName());
            attributeOfListVO.setIsSale(IsSaleEnum.getNameById(attributeOfListDTO.getIsSale()));
            attributeOfListVO.setInputType(InputTypeEnum.getNameById(attributeOfListDTO.getInputType()));
            attributeOfListVO.setIsValid(IsValidEnum.getNameById(attributeOfListDTO.getIsValid()));
            attributeOfListVOS.add(attributeOfListVO);
        }
        return attributeOfListVOS;
    }
}
