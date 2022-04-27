package pers.candyboyou.mallpromotion.business.model.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import pers.candyboyou.mallpromotion.business.model.admin.dto.BannerInfoOfListDTO;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BannerInfoOfListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5565578295367044362L;

    @ApiModelProperty("bannerId")
    private Long bannerId;

    @ApiModelProperty("banner活动名称")
    private String bannerName;

    @ApiModelProperty("是否上线")
    private Integer isOnline;

    @ApiModelProperty("点击数量")
    private Integer clickCount;

    @ApiModelProperty("订单数量")
    private Integer orderCount;

    public static List<BannerInfoOfListVO> convertBannerInfoOfListDTOs(List<BannerInfoOfListDTO> bannerInfoOfListDTOs) {
        List<BannerInfoOfListVO> bannerInfoOfListVOS = new ArrayList<>(bannerInfoOfListDTOs.size());
        for (BannerInfoOfListDTO bannerInfoOfListDTO : bannerInfoOfListDTOs) {
            BannerInfoOfListVO bannerInfoOfListVO = new BannerInfoOfListVO();
            bannerInfoOfListVO.setBannerId(bannerInfoOfListDTO.getBannerId());
            bannerInfoOfListVO.setBannerName(bannerInfoOfListDTO.getBannerName());
            bannerInfoOfListVO.setIsOnline(bannerInfoOfListDTO.getIsOnline());
            bannerInfoOfListVO.setClickCount(bannerInfoOfListDTO.getClickCount());
            bannerInfoOfListVO.setOrderCount(bannerInfoOfListDTO.getOrderCount());
            bannerInfoOfListVOS.add(bannerInfoOfListVO);
        }
        return bannerInfoOfListVOS;
    }
}
