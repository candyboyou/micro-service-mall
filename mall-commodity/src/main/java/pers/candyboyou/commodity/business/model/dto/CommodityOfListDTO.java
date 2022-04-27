package pers.candyboyou.commodity.business.model.dto;

import lombok.Data;

@Data
public class CommodityOfListDTO {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    private Long categoryID;

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
     * spuId
     */
    private Long spuId;

    /**
     * 审核状态
     */
    private Integer verifyStatus;

}
