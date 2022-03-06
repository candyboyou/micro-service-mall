package pers.candyboyou.commodity.business.model.param.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommoditySearchParam extends QueryParam {

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("是否已上架")
    private Integer isPublish;

    @ApiModelProperty("是否是新品")
    private Integer isNew;

    @ApiModelProperty("是否推荐")
    private Integer isRecommend;

    @ApiModelProperty("是否已审核")
    private Integer isVerify;
}
