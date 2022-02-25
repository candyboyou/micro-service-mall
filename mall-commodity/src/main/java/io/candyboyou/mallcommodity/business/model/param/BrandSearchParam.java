package io.candyboyou.mallcommodity.business.model.param;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BrandSearchParam extends QueryParam {

    @ApiModelProperty("品牌名称")
    private String brandName;

}
