package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.entity.SkuAttributeEntity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class SkuAttributeDetailVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7771520762644112020L;

    @ApiModelProperty("sku的id")
    private Long id;

    @ApiModelProperty("sku编码")
    private String skuNum;

    @ApiModelProperty("sku的属性list")
    private List<AttributeValueOfSkuVO> attributeOfSKUVOS;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("锁定库存")
    private Integer lockStock;

    @ApiModelProperty("低库存")
    private Integer lowStock;

    @ApiModelProperty("销量")
    private Integer sale;

    public static List<SkuAttributeDetailVO> convertSkuAttributeEntities(List<SkuAttributeEntity> skuAttributes, Map<Long, List<AttributeValueOfSkuVO>> skuIdToAttributeValueOfSkuVOMap) {
        List<SkuAttributeDetailVO> skuAttributeDetailVOS = new ArrayList<>(skuAttributes.size());
        for (SkuAttributeEntity skuAttribute : skuAttributes) {
            SkuAttributeDetailVO skuAttributeDetailVO = new SkuAttributeDetailVO();
            skuAttributeDetailVO.setId(skuAttribute.getId());
            List<AttributeValueOfSkuVO> attributeValueOfSkuVOS = skuIdToAttributeValueOfSkuVOMap.get(skuAttribute.getId());
            skuAttributeDetailVO.setAttributeOfSKUVOS(attributeValueOfSkuVOS);
            skuAttributeDetailVO.setPrice(skuAttribute.getPrice());
            skuAttributeDetailVO.setPromotionPrice(skuAttribute.getPromotionPrice());
            skuAttributeDetailVO.setStock(skuAttribute.getStock());
            skuAttributeDetailVO.setLockStock(skuAttribute.getLockStock());
            skuAttributeDetailVO.setLowStock(skuAttribute.getLowStock());
            skuAttributeDetailVO.setSale(skuAttribute.getSale());
            skuAttributeDetailVOS.add(skuAttributeDetailVO);
        }
        return skuAttributeDetailVOS;
    }
}
