package pers.candyboyou.mallpromotion.business.model.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class FlashDealSaveDTO {

    private Long id;

    /**
     * 秒杀活动名称
     */
    private String name;

    /**
     * 秒杀活动开始时间
     */
    private Date startTime;

    /**
     * 秒杀活动结束时间
     */
    private Date endTime;

    /**
     * 秒杀活动状态
     */
    private Integer status;

    /**
     * 是否上线
     */
    private Integer isOnline;
}
