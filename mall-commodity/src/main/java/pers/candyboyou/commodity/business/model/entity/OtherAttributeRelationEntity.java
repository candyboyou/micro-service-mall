package pers.candyboyou.commodity.business.model.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OtherAttributeRelationEntity extends Entity {

    /**
     * 商品ID
     */
    private Long commodityId;

    /**
     * 属性ID
     */
    private Long attributeId;

    /**
     * 属性值ID
     */
    private Long attributeValueId;

}
