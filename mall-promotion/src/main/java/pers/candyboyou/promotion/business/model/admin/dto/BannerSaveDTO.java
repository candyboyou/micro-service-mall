package pers.candyboyou.mallpromotion.business.model.admin.dto;

import io.candyboyou.common.expection.BusinessException;
import io.candyboyou.common.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.mallpromotion.business.model.admin.param.BannerSaveParam;

import java.sql.Date;

@Data
public class BannerSaveDTO {

    private Long bannerId;

    private Long commodityId;

    private String bannerName;

    private String commodityName;

    private Date startTime;

    private Date endTime;

    private Integer isOnline;

    private String picUrl;

    private Integer sort;

    public static BannerSaveDTO convertBannerSaveParam(BannerSaveParam bannerSaveParam) {
        if (bannerSaveParam == null) {
            throw new BusinessException("参数不能为null");
        }
        String bannerName = bannerSaveParam.getBannerName();
        if (StringUtils.isBlank(bannerName)) {
            throw new BusinessException("活动名称不能为空");
        }
        Long commodityId = bannerSaveParam.getCommodityId();
        if (commodityId == null) {
            throw new BusinessException("商品不能为空");
        }
        Long startTime = bannerSaveParam.getStartTime();
        if (startTime == null) {
            throw new BusinessException("开始时间不能为空");
        }
        Long endTime = bannerSaveParam.getEndTime();
        if (endTime == null) {
            throw new BusinessException("结束时间不能为空");
        }
        String picUrl = bannerSaveParam.getPicUrl();
        if (picUrl == null) {
            throw new BusinessException("商品图片不能为空");
        }
        BannerSaveDTO bannerSaveDTO = new BannerSaveDTO();
        bannerSaveDTO.setBannerId(bannerSaveParam.getBannerId());
        bannerSaveDTO.setCommodityId(commodityId);
        bannerSaveDTO.setBannerName(bannerName);
        bannerSaveDTO.setCommodityName(bannerSaveParam.getCommodityName());
        bannerSaveDTO.setStartTime(new Date(startTime));
        bannerSaveDTO.setEndTime(new Date(endTime));
        bannerSaveDTO.setIsOnline(bannerSaveParam.getIsOnline());
        bannerSaveDTO.setPicUrl(picUrl);
        bannerSaveDTO.setSort(bannerSaveParam.getSort());
        return bannerSaveDTO;
    }
}
