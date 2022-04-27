package pers.candyboyou.mallpromotion.business.service.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import pers.candyboyou.mallpromotion.business.model.admin.BannerSearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.param.BannerSaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.BannerCommodityVO;
import pers.candyboyou.mallpromotion.business.model.admin.vo.BannerInfoOfListVO;

public interface BannerService {

    void saveOrUpdateBannerCommodity(BannerSaveParam bannerSaveParam);

    ListVO<BannerInfoOfListVO> getBannerList(BannerSearchParam bannerSearchParam);
}
