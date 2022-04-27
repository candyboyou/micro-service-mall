package pers.candyboyou.commodity.business.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AttributeOfListDTO {

    private Long id;

    private String name;

    private Integer isSale;

    private Integer inputType;

    private Integer isValid;

}
