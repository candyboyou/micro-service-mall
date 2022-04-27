package pers.candyboyou.mallpromotion.business.model.admin.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendCommodityEntity extends Entity {

    @Serial
    private static final long serialVersionUID = -825044049114392869L;

    private Long commodityId;

    private Integer status;

}
