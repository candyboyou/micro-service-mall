package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class CategoryDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 父类id
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 分类的位置
     */
    private Integer place;

    /**
     * 是否启用
     */
    private Integer isShow;

    /**
     * 同一父类型下的排序
     */
    private Integer sort;

}
