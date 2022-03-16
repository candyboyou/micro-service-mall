package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class AttributeValueDTO {

    private Long id;

    private String value;

    public static AttributeValueDTO convertAttributeWithValueDTO(AttributeWithValueDTO attributeWithValueDTO) {
        AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
        attributeValueDTO.setId(attributeWithValueDTO.getValueId());
        attributeValueDTO.setValue(attributeWithValueDTO.getValue());
        return attributeValueDTO;
    }
}
