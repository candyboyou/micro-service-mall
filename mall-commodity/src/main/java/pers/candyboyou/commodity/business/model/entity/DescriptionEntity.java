package pers.candyboyou.commodity.business.model.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DescriptionEntity extends Entity {

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品标题
     */
    private String detailTitle;

    /**
     *
     */
    private String detailHtml;

    /**
     *
     */
    private String detailDesc;

    /**
     * 移动端网页详情
     */
    private String detailMobileHtml;

}
