package pers.candyboyou.mallpromotion.business.model.admin.param;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FlashDealSearchParam extends QueryParam {

    @ApiModelProperty("秒杀的活动名称")
    private String name;

    @ApiModelProperty("秒杀的活动状态")
    private Integer status;

    @ApiModelProperty("是否上线")
    private Integer isOnline;
}
