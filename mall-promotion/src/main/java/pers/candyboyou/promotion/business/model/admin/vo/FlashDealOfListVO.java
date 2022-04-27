package pers.candyboyou.mallpromotion.business.model.admin.vo;

import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.mallpromotion.business.model.admin.entity.FlashDealEntity;

import java.util.ArrayList;
import java.util.List;

@Data
public class FlashDealOfListVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("秒杀活动名称")
    private String name;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    @ApiModelProperty("活动状态")
    private Integer status;

    @ApiModelProperty("是否上线")
    private Integer isOnline;

    public static List<FlashDealOfListVO> convertFlashDealEntities(List<FlashDealEntity> flashDealEntities) {
        if (CollectionUtils.isEmpty(flashDealEntities)) {
            return new ArrayList<>();
        }
        List<FlashDealOfListVO> flashDealOfListVOs = new ArrayList<>(flashDealEntities.size());
        for (FlashDealEntity flashDealEntity : flashDealEntities) {
            FlashDealOfListVO flashDealOfListVO = new FlashDealOfListVO();
            flashDealOfListVO.setId(flashDealEntity.getId());
            flashDealOfListVO.setName(flashDealEntity.getName());
            flashDealOfListVO.setStartTime(flashDealEntity.getStartTime());
            flashDealOfListVO.setEndTime(flashDealEntity.getEndTime());
            flashDealOfListVO.setStatus(flashDealEntity.getStatus());
            flashDealOfListVO.setIsOnline(flashDealEntity.getIsOnline());
            flashDealOfListVOs.add(flashDealOfListVO);
        }
        return flashDealOfListVOs;
    }

}
