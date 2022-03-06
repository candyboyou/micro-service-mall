package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.SkuAttributeRelationDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AttributeValueOfSkuVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4946265062788649058L;

    @ApiModelProperty("属性ID")
    private Long attributeId;

    @ApiModelProperty("属性值ID")
    private Long valueId;

    public static List<AttributeValueOfSkuVO> convertAttributeWithValueDTOS(List<SkuAttributeRelationDTO> attributeWithValueDTOS) {
        List<AttributeValueOfSkuVO> AttributeValueOfSkuVOS = new ArrayList<>(attributeWithValueDTOS.size());
        for (SkuAttributeRelationDTO attributeWithValueDTO : attributeWithValueDTOS) {
            AttributeValueOfSkuVO AttributeValueOfSkuVO = new AttributeValueOfSkuVO();
            AttributeValueOfSkuVO.setAttributeId(attributeWithValueDTO.getAttributeId());
            AttributeValueOfSkuVO.setValueId(attributeWithValueDTO.getValueId());
            AttributeValueOfSkuVOS.add(AttributeValueOfSkuVO);
        }
        return AttributeValueOfSkuVOS;
    }

    public static AttributeValueOfSkuVO convertAttributeWithValueDTO(SkuAttributeRelationDTO skuAttributeRelationDTO) {
        AttributeValueOfSkuVO AttributeValueOfSkuVO = new AttributeValueOfSkuVO();
        AttributeValueOfSkuVO.setAttributeId(skuAttributeRelationDTO.getAttributeId());
        AttributeValueOfSkuVO.setValueId(skuAttributeRelationDTO.getValueId());
        return AttributeValueOfSkuVO;
    }
}
