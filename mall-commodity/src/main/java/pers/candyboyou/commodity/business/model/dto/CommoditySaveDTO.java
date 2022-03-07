package pers.candyboyou.commodity.business.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySaveParam;

import java.math.BigDecimal;

@Data
public class CommoditySaveDTO {

    // 商品信息
    @ApiModelProperty(value = "商品id")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品详细标题")
    private String detailName;

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

    // 商品属性
    @ApiModelProperty("分类id")
    private Long categoryId;

    @ApiModelProperty(value = "详细描述")
    private String detailDesc;

    public static CommoditySaveDTO convertCommoditySaveParam(CommoditySaveParam commoditySaveParam) {
        CommoditySaveDTO commoditySaveDTO = new CommoditySaveDTO();
        commoditySaveDTO.setId(commoditySaveParam.getId());
        commoditySaveDTO.setName(commoditySaveParam.getName());
        commoditySaveDTO.setDetailName(commoditySaveParam.getDetailName());
        commoditySaveDTO.setIsPublish(commoditySaveParam.getIsPublish());
        commoditySaveDTO.setIsNew(commoditySaveParam.getIsNew());
        commoditySaveDTO.setIsRecommend(commoditySaveParam.getIsRecommend());
        commoditySaveDTO.setVerifyStatus(commoditySaveParam.getVerifyStatus());
        commoditySaveDTO.setIsPreview(commoditySaveParam.getIsPreview());
        commoditySaveDTO.setSort(commoditySaveParam.getSort());
        commoditySaveDTO.setCategoryId(commoditySaveParam.getCategoryId());
        commoditySaveDTO.setDetailDesc(commoditySaveParam.getDetailDesc());
        return commoditySaveDTO;
    }
}
