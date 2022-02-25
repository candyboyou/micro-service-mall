package io.candyboyou.mallcommodity.business.model.param.admin;

import lombok.Data;

@Data
public class CategorySaveParam {

    private String name;

    private Integer parentCategory;

    private Integer unit;

}
