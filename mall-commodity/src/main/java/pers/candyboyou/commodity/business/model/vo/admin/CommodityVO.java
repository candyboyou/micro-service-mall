package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CommodityVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3622175846076687164L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品主图")
    private String img_url;

    @ApiModelProperty("商品价格")
    private BigDecimal price;

    @ApiModelProperty("是否上架")
    private Integer isPublish;

    @ApiModelProperty("是否新品")
    private Integer isNew;

    @ApiModelProperty("是否推荐")
    private Integer isRecommend;

    @ApiModelProperty("spu销量")
    private Integer spuSale;

    @ApiModelProperty("是否已审核")
    private Integer isVerify;

}
