package pers.candyboyou.mallpromotion.business.model.admin.vo;

import io.candyboyou.common.framework.model.vo.ListVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.candyboyou.mallpromotion.business.model.admin.entity.FlashDealEntity;

@EqualsAndHashCode(callSuper = true)
@Data
public class FlashDealDetailVO extends FlashDealOfListVO {

    @ApiModelProperty("秒杀商品信息")
    private ListVO<FlashDealCommodityVO> flashDealCommodityVOList;

    public static FlashDealDetailVO convertFlashDealEntity(FlashDealEntity flashDealEntity) {
        FlashDealDetailVO flashDealDetailVO = new FlashDealDetailVO();
        flashDealDetailVO.setId(flashDealEntity.getId());
        flashDealDetailVO.setName(flashDealEntity.getName());
        flashDealDetailVO.setStartTime(flashDealEntity.getStartTime());
        flashDealDetailVO.setEndTime(flashDealEntity.getEndTime());
        flashDealDetailVO.setStatus(flashDealEntity.getStatus());
        flashDealDetailVO.setIsOnline(flashDealEntity.getIsOnline());
        return flashDealDetailVO;
    }
}
