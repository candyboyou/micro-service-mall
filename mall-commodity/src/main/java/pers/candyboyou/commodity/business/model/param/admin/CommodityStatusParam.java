package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommodityStatusParam {

    private Long id;

    @ApiModelProperty("是否上架")
    private Integer isPublish;

    @ApiModelProperty("是否新品")
    private Integer isNew;

    @ApiModelProperty("是否推荐")
    private Integer isRecommend;

    @ApiModelProperty("是否删除")
    private Integer isDelete = 0;
}
