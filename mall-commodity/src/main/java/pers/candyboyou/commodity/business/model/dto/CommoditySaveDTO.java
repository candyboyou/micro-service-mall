package pers.candyboyou.commodity.business.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySaveParam;
import pers.candyboyou.commodity.business.model.param.admin.CommodityUpdateParam;

import java.math.BigDecimal;

@Data
public class CommoditySaveDTO {

    // 商品信息
    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品详细标题
     */
    private String detailName;

    // 商品的一些状态
    /**
     * 上架状态：0->下架；1->上架
     */
    private Integer isPublish;

    /**
     * 新品状态:0->不是新品；1->新品
     */
    private Integer isNew;

    /**
     * 推荐状态；0->不推荐；1->推荐
     */
    private Integer isRecommend;

    /**
     * 审核状态：0->未审核；1->审核通过
     */
    private Integer verifyStatus;

    /**
     * 是否为预告商品：0->不是；1->是
     */
    private Integer isPreview;

    /**
     * 排序
     */
    private Integer sort;

    // 商品属性
    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * spu属性id
     */
    private Long spuId;

    /**
     * 详细描述
     */
    private String detailDesc;

    public static CommoditySaveDTO convertCommoditySaveParam(CommoditySaveParam commoditySaveParam) {
        CommoditySaveDTO commoditySaveDTO = new CommoditySaveDTO();
        fillCommoditySaveDTO(commoditySaveDTO, commoditySaveParam);
        return commoditySaveDTO;
    }

    public static CommoditySaveDTO convertCommodityUpdateParam(CommodityUpdateParam commodityUpdateParam) {
        CommoditySaveDTO commoditySaveDTO = new CommoditySaveDTO();
        commoditySaveDTO.setId(commodityUpdateParam.getId());
        fillCommoditySaveDTO(commoditySaveDTO, commodityUpdateParam);
        return commoditySaveDTO;
    }

    private static void fillCommoditySaveDTO(CommoditySaveDTO commoditySaveDTO, CommoditySaveParam commodityParam) {
        commoditySaveDTO.setName(commodityParam.getName());
        commoditySaveDTO.setDetailName(commodityParam.getDetailName());
        commoditySaveDTO.setIsPublish(commodityParam.getIsPublish());
        commoditySaveDTO.setIsNew(commodityParam.getIsNew());
        commoditySaveDTO.setIsRecommend(commodityParam.getIsRecommend());
        commoditySaveDTO.setVerifyStatus(commodityParam.getVerifyStatus());
        commoditySaveDTO.setIsPreview(commodityParam.getIsPreview());
        commoditySaveDTO.setSort(commodityParam.getSort());
        commoditySaveDTO.setCategoryId(commodityParam.getCategoryId());
        commoditySaveDTO.setDetailDesc(commodityParam.getDetailDesc());
    }
}
