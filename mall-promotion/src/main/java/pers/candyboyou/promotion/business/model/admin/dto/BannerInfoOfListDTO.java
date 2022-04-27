package pers.candyboyou.mallpromotion.business.model.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BannerInfoOfListDTO {

    private Long bannerId;

    private String bannerName;

    private Long startTime;

    private Long endTime;

    private Integer isOnline;

    private Integer clickCount;

    private Integer orderCount;
}
