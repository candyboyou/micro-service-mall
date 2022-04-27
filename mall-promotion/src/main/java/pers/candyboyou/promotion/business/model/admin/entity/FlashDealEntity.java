package pers.candyboyou.mallpromotion.business.model.admin.entity;

import io.candyboyou.common.framework.model.base.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class FlashDealEntity extends Entity {

    @Serial
    private static final long serialVersionUID = 663631836685143412L;

    private String name;

    private Long startTime;

    private Long endTime;

    private Integer status;

    private Integer isOnline;
}
