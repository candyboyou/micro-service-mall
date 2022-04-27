package pers.candyboyou.mallpromotion.business.model.admin.param;

import io.candyboyou.common.framework.model.vo.ListVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class FlashDealSaveParam {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("秒杀活动名称")
    private String name;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    @ApiModelProperty("活动状态")
    private Integer status = 0;

    @ApiModelProperty("是否上线")
    private Integer isOnline = 0;

    @ApiModelProperty("秒杀商品列表")
    private List<FlashDealCommodityParam> flashDealCommodities;

}
