package pers.candyboyou.commodity.business.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CommodityOfListDTO {

    /**
     * 商品id
     */
    private String id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 是否已发布
     */
    private Integer isPublish;

    /**
     * 是否是新品
     */
    private Integer isNew;

    /**
     * 是否是推荐商品
     */
    private Integer isRecommend;

    /**
     * 分类Id
     */
    private Long categoryId;

    /**
     * spuId
     */
    private Long spuId;

    /**
     * 商品描述Id
     */
    private Long descriptionId;

    /**
     * 是否已审核
     */
    private Integer isVerify;

}
