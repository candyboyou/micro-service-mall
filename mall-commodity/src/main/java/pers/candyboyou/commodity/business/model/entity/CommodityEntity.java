package pers.candyboyou.commodity.business.model.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommodityEntity extends Entity {

    /**
     * 商品的名称
     */
    private String name;

    /**
     * 商品的副标题
     */
    private String subName;

    /**
     * 是否发布
     */
    private Integer isPublish;

    /**
     * 是否是新品
     */
    private Integer isNew;

    /**
     * 是否推荐
     */
    private Integer isRecommend;

    /**
     * 是否审核通过
     */
    private Integer verifyStatus;

    /**
     * 是否是预告商品
     */
    private Integer isPreview;

    /**
     * 市场价格
     */
    private BigDecimal originalPrice;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 商品详情id
     */
    private Long descriptionId;

    /**
     * 商品spu的id
     */
    private Long spuId;

    /**
     * 排序
     */
    private Integer sort;
}
