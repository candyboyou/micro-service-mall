package pers.candyboyou.commodity.business.model.dto;

import io.swagger.annotations.ApiModelProperty;
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
    private Integer parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 商品数量
     */
    private String count;

    /**
     * 单位ID
     */
    private Integer unitId;

    /**
     * 是否启用
     */
    private Integer isShow;

    /**
     * 描述
     */
    private String description;

}
