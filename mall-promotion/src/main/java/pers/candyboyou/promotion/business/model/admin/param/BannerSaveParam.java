package pers.candyboyou.mallpromotion.business.model.admin.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class BannerSaveParam implements Serializable{

    @Serial
    private static final long serialVersionUID = -5521143949442589551L;

    @ApiModelProperty("banner的id")
    private Long bannerId;

    @ApiModelProperty("商品的id")
    private Long commodityId;

    @ApiModelProperty("banner活动的名称")
    private String bannerName;

    @ApiModelProperty("商品名称")
    private String commodityName;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    @ApiModelProperty("是否上线")
    private Integer isOnline = 0;

    @ApiModelProperty("图片URL")
    private String picUrl;

    @ApiModelProperty("排序")
    private Integer sort = 0;

}
