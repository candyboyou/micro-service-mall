package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class AttributeDTO {

    private Long id;

    private String name;

    private Integer isSearch;

    private Integer isSaleAttr;

    private Integer isMultiple;

    private Integer isRequired;

    private Integer sort;
}
