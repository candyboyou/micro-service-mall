package pers.candyboyou.commodity.business.model.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommodityEntity extends Entity {

    @Serial
    private static final long serialVersionUID = -7680578326662394739L;

    /**
     * 商品的名称
     */
    private String name;

    /**
     * 商品的副标题
     */
    private String detailName;

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
     * 分类id
     */
    private Long categoryId;

    /**
     * 商品spu的id
     */
    private Long spuId;

    /**
     * 商品详情Url
     */
    private String detailDesc;

    /**
     * 排序
     */
    private Integer sort;
}
