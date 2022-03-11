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
     * 商品主图Id
     */
    private Long imgId;

    /**
     * 是否已审核
     */
    private Integer isVerify;

}
