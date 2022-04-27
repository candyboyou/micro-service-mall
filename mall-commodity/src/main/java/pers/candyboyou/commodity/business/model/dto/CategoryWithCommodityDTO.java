package pers.candyboyou.commodity.business.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.commodity.business.model.vo.admin.OptionCommonVO;

import java.util.List;

@Data
public class CategoryWithCommodityDTO {

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 商品名称
     */
    private String commodityName;

}
