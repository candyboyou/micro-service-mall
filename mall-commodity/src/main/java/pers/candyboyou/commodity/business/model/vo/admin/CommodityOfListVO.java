package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.enums.NewStatusEnum;
import pers.candyboyou.commodity.business.enums.PublishStatusEnum;
import pers.candyboyou.commodity.business.enums.RecommendStatusEnum;
import pers.candyboyou.commodity.business.enums.VerifyStatusEnum;
import pers.candyboyou.commodity.business.model.dto.CommodityOfListDTO;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CommodityOfListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 3622175846076687164L;

    @ApiModelProperty("商品id")
    private Long id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("分类名称")
    private String category;

    @ApiModelProperty("spu销量")
    private Integer spuSale;

    @ApiModelProperty("商品主图")
    private String pictureUrl;

    @ApiModelProperty("是否上架")
    private String publishStatus;

    @ApiModelProperty("是否新品")
    private String newStatus;

    @ApiModelProperty("是否推荐")
    private String recommendStatus;

    @ApiModelProperty("是否已审核")
    private String verifyStatus;

    public static CommodityOfListVO convertCommodityOfListDTO(CommodityOfListDTO commodityOfListDTO) {
        if (commodityOfListDTO == null) {
            return null;
        }
        CommodityOfListVO commodityOfListVO = new CommodityOfListVO();
        commodityOfListVO.setId(commodityOfListDTO.getId());
        commodityOfListVO.setName(commodityOfListDTO.getName());
        commodityOfListVO.setPictureUrl("");
        commodityOfListVO.setPublishStatus(PublishStatusEnum.getNameById(commodityOfListDTO.getIsPublish()));
        commodityOfListVO.setNewStatus(NewStatusEnum.getNameById(commodityOfListDTO.getIsNew()));
        commodityOfListVO.setRecommendStatus(RecommendStatusEnum.getNameById(commodityOfListDTO.getIsRecommend()));
        commodityOfListVO.setVerifyStatus(VerifyStatusEnum.getNameById(commodityOfListDTO.getVerifyStatus()));
        return commodityOfListVO;
    }
}
