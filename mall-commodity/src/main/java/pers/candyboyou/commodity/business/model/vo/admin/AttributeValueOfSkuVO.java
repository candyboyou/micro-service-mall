package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeRelationDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class AttributeValueOfSkuVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4946265062788649058L;

    @ApiModelProperty("属性ID")
    private Long attributeId;

    @ApiModelProperty("属性值ID")
    private Long valueId;

    public static List<AttributeValueOfSkuVO> convertAttributeWithValueDTOs(List<SkuAttributeRelationDTO> attributeWithValueDTOs) {
        List<AttributeValueOfSkuVO> AttributeValueOfSkuVOs = new ArrayList<>(attributeWithValueDTOs.size());
        for (SkuAttributeRelationDTO attributeWithValueDTO : attributeWithValueDTOs) {
            AttributeValueOfSkuVO AttributeValueOfSkuVO = new AttributeValueOfSkuVO();
            AttributeValueOfSkuVO.setAttributeId(attributeWithValueDTO.getAttributeId());
            AttributeValueOfSkuVO.setValueId(attributeWithValueDTO.getValueId());
            AttributeValueOfSkuVOs.add(AttributeValueOfSkuVO);
        }
        return AttributeValueOfSkuVOs;
    }

    public static AttributeValueOfSkuVO convertAttributeWithValueDTO(SkuAttributeRelationDTO skuAttributeRelationDTO) {
        AttributeValueOfSkuVO AttributeValueOfSkuVO = new AttributeValueOfSkuVO();
        AttributeValueOfSkuVO.setAttributeId(skuAttributeRelationDTO.getAttributeId());
        AttributeValueOfSkuVO.setValueId(skuAttributeRelationDTO.getValueId());
        return AttributeValueOfSkuVO;
    }
}
