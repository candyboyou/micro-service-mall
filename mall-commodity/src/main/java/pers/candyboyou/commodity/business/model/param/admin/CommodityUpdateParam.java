package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommodityUpdateParam extends CommoditySaveParam {

    @ApiModelProperty(value = "商品id")
    private Long id;

    @ApiModelProperty("是否删除")
    private Integer isDelete = 0;

}
