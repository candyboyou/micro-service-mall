package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class CategoryCascadeInfoDTO {

    private Long id;

    private Long parentId;

    private String name;

    private Integer level;

}
