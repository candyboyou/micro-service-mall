package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.dto.CommodityOfListDTO;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CommodityOfListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3622175846076687164L;

    @ApiModelProperty("商品id")
    private String id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("商品主图")
    private String imgUrl;

    @ApiModelProperty("商品价格")
    private BigDecimal spuPrice;

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

    public static CommodityOfListVO convertCommodityOfListDTO(CommodityOfListDTO commodityOfListDTO) {
        if (commodityOfListDTO == null) {
            return null;
        }
        CommodityOfListVO commodityOfListVO = new CommodityOfListVO();
        commodityOfListVO.setId(commodityOfListDTO.getId());
        commodityOfListVO.setName(commodityOfListDTO.getName());
        commodityOfListVO.setIsPublish(commodityOfListDTO.getIsPublish());
        commodityOfListVO.setIsNew(commodityOfListDTO.getIsNew());
        commodityOfListVO.setIsRecommend(commodityOfListDTO.getIsRecommend());
        commodityOfListVO.setIsVerify(commodityOfListDTO.getIsVerify());
        return commodityOfListVO;
    }
}
