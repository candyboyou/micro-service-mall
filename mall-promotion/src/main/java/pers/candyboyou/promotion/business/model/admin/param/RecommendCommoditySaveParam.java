package pers.candyboyou.mallpromotion.business.model.admin.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecommendCommoditySaveParam {

    @ApiModelProperty("商品id")
    private Long commodityId;

    @ApiModelProperty("推荐状态：0->不推荐;1->推荐")
    private Integer status;

    @ApiModelProperty("排序")
    private Integer sort;

}
