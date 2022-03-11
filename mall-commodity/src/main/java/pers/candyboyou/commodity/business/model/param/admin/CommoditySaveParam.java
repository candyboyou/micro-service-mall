package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeDetailVO;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CommoditySaveParam {

    // 商品信息
    @ApiModelProperty(value = "商品id")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品详细标题")
    private String detailName;

    // spu属性
    @ApiModelProperty(value = "商品SPU售价")
    private BigDecimal spuPrice;

    @ApiModelProperty(value = "SPU库存")
    private Integer spuStock;

    @ApiModelProperty(value = "SPU库存预警值")
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

    // 商品属性
    @ApiModelProperty("分类id")
    private Long categoryId;

    // 商品属性太多，没法打平了
    @ApiModelProperty("商品详细属性")
    private List<SpuAttributeValueSaveParam> spuAttributeValueSaveParams;

    // sku属性同样没法打平
    @ApiModelProperty("sku属性")
    private List<SkuSaveParam> skuSaveParams;

    // 商品描述以及图片
    @ApiModelProperty(value = "商品图片。默认第一张为主图")
    private List<String> albumPics;

    @ApiModelProperty(value = "详细描述")
    private String detailDesc;


}
