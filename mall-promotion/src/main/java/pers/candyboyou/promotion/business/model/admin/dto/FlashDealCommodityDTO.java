package pers.candyboyou.mallpromotion.business.model.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealCommodityParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class FlashDealCommodityDTO {

    /**
     * 商品的id
     */
    private Long commodityId;

    private String name;

    private BigDecimal price;

    private BigDecimal flashDealPrice;

    private Integer flashDealCount;

    private Integer flashDealLeftCount;

    private Integer flashDealLimit;

    public static List<FlashDealCommodityDTO> convertFlashDealCommodities(List<FlashDealCommodityParam> flashDealCommodityParams) {
        List<FlashDealCommodityDTO> flashDealCommodityDTOs = new ArrayList<>(flashDealCommodityParams.size());
        for (FlashDealCommodityParam flashDealCommodityParam : flashDealCommodityParams) {
            FlashDealCommodityDTO flashDealCommodityDTO = convertFlashDealCommodity(flashDealCommodityParam);
            flashDealCommodityDTOs.add(flashDealCommodityDTO);
        }
        return flashDealCommodityDTOs;
    }

    public static FlashDealCommodityDTO convertFlashDealCommodity(FlashDealCommodityParam flashDealCommodityParam) {
        FlashDealCommodityDTO flashDealCommodityDTO = new FlashDealCommodityDTO();
        flashDealCommodityDTO.setCommodityId(flashDealCommodityParam.getId());
        flashDealCommodityDTO.setName(flashDealCommodityParam.getName());
        flashDealCommodityDTO.setPrice(flashDealCommodityParam.getPrice());
        flashDealCommodityDTO.setFlashDealPrice(flashDealCommodityParam.getFlashDealPrice());
        flashDealCommodityDTO.setFlashDealCount(flashDealCommodityParam.getFlashDealCount());
        flashDealCommodityDTO.setFlashDealLeftCount(flashDealCommodityParam.getFlashDealLeftCount());
        flashDealCommodityDTO.setFlashDealLimit(flashDealCommodityParam.getFlashDealLimit());
        return flashDealCommodityDTO;
    }
}
