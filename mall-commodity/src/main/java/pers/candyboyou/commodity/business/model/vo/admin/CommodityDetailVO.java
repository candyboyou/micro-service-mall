package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.entity.CommodityEntity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class CommodityDetailVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6261781299597211803L;

    // 商品信息
    @ApiModelProperty(value = "商品id")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品副标题")
    private String detailName;

    // spu属性
    @ApiModelProperty(value = "商品售价")
    private BigDecimal spuPrice;

    @ApiModelProperty(value = "库存")
    private Integer spuStock;

    @ApiModelProperty(value = "库存预警值")
    private Integer spuLowStock;

    // 商品的一些状态
    @ApiModelProperty(value = "上架状态：0->下架；1->上架")
    private Integer isPublish;

    @ApiModelProperty(value = "新品状态:0->不是新品；1->新品")
    private Integer isNew;

    @ApiModelProperty(value = "推荐状态；0->不推荐；1->推荐")
    private Integer isRecommend;

    @ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
    private Integer verifyStatus;

    @ApiModelProperty(value = "是否为预告商品：0->不是；1->是")
    private Integer isPreview;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    private String serviceIds;

//    这些放在商品的详情里面
//    @ApiModelProperty(value = "单位")
//    private Long unitId;
//
//    @ApiModelProperty(value = "商品重量，默认为克")
//    private BigDecimal weight;

    // 商品属性
    @ApiModelProperty("分类id")
    private Long categoryId;

    // 商品属性太多，没法打平了
    @ApiModelProperty("商品详细属性")
    private List<AttributeValueVO> attributeDetailVOS;

    // sku属性同样没法打平
    @ApiModelProperty("sku属性")
    private List<SkuAttributeDetailVO> SkuAttributeVOS;

    // 商品描述以及图片
    @ApiModelProperty(value = "画册图片，连产品图片限制为5张，以逗号分割")
    private String albumPics;

    @ApiModelProperty(value = "商品分类名称")
    private String productCategoryName;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "详细描述")
    private String detailDesc;

    @ApiModelProperty(value = "产品详情网页内容")
    private String detailHtml;

    @ApiModelProperty(value = "移动端网页详情")
    private String detailMobileHtml;

    public static CommodityDetailVO convertCommodityEntity(CommodityEntity commodity) {
        CommodityDetailVO commodityDetailVO = new CommodityDetailVO();
        commodityDetailVO.setId(commodity.getId());
        commodityDetailVO.setName(commodity.getName());
        commodityDetailVO.setDetailName(commodity.getSubName());
        commodityDetailVO.setIsPublish(commodity.getIsPublish());
        commodityDetailVO.setIsNew(commodity.getIsNew());
        commodityDetailVO.setIsRecommend(commodity.getIsRecommend());
        commodityDetailVO.setVerifyStatus(commodity.getVerifyStatus());
        commodityDetailVO.setIsPreview(commodity.getIsPreview());
        commodityDetailVO.setSort(commodity.getSort());
        return commodityDetailVO;
    }
}
