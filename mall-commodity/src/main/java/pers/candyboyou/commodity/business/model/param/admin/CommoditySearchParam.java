package pers.candyboyou.commodity.business.model.param.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommoditySearchParam extends QueryParam {

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("分类id")
    private List<Long> categoryIdList;

    @ApiModelProperty("是否已上架")
    private Integer publishStatus;

    @ApiModelProperty("是否是新品")
    private Integer newStatus;

    @ApiModelProperty("是否推荐")
    private Integer recommendStatus;

    @ApiModelProperty("是否已审核")
    private Integer verifyStatus;
}
