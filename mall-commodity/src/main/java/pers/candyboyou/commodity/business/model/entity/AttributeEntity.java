package pers.candyboyou.commodity.business.model.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttributeEntity extends Entity {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 是否支持查询
     */
    private Integer isSearch;

    /**
     * 是否是销售属性
     */
    private Integer isSaleAttr;

    /**
     * 是否可以多选
     */
    private Integer isMultiple;

    /**
     * 是否必填
     */
    private Integer isRequired;

    /**
     * 排序
     */
    private Integer sort;

}
