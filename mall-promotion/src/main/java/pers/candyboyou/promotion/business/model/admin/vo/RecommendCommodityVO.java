package pers.candyboyou.mallpromotion.business.model.admin.vo;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.mallpromotion.business.model.admin.entity.RecommendCommodityEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class RecommendCommodityVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7228571120110846319L;

    @ApiModelProperty("推荐信息id")
    private Long id;

    @ApiModelProperty("商品id")
    private Long commodityId;

    @ApiModelProperty("商品名称")
    private String commodityName;

    @ApiModelProperty("推荐状态")
    private Integer status;

    public static List<RecommendCommodityVO> convertRecommendCommodityEntities(List<RecommendCommodityEntity> recommendCommodityEntities) {
        if (CollectionUtils.isEmpty(recommendCommodityEntities)) {
            return new ArrayList<>();
        }
        List<RecommendCommodityVO> recommendCommodityVOs = new ArrayList<>(recommendCommodityEntities.size());
        for (RecommendCommodityEntity recommendCommodityEntity : recommendCommodityEntities) {
            RecommendCommodityVO recommendCommodityVO = new RecommendCommodityVO();
            recommendCommodityVO.setId(recommendCommodityEntity.getId());
            recommendCommodityVO.setCommodityId(recommendCommodityEntity.getCommodityId());
            recommendCommodityVO.setStatus(recommendCommodityEntity.getStatus());
            recommendCommodityVOs.add(recommendCommodityVO);
        }
        return recommendCommodityVOs;
    }
}
