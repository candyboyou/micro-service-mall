package pers.candyboyou.mallpromotion.business.controller.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.candyboyou.mallpromotion.business.model.admin.BannerSearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.param.BannerSaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.BannerCommodityVO;
import pers.candyboyou.mallpromotion.business.model.admin.vo.BannerInfoOfListVO;
import pers.candyboyou.mallpromotion.business.service.admin.BannerService;

import java.util.List;

@RestController
@RequestMapping("/admin/BannerController")
@Api(value = "/admin/BannerController", tags = "banner图管理")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "分页获取banner商品列表")
    @PostMapping("/getBannerCommodityList")
    public Result getBannerCommodityList(BannerSearchParam bannerSearchParam) {
        ListVO<BannerInfoOfListVO> bannerCommodityVOListVO = bannerService.getBannerList(bannerSearchParam);
        return Result.ok(bannerCommodityVOListVO);
    }

    @ApiOperation(value = "保存或更新banner商品")
    @PostMapping("/saveOrUpdateBannerCommodity")
    public Result saveOrUpdateBannerCommodity(BannerSaveParam bannerSaveParam) {
        bannerService.saveOrUpdateBannerCommodity(bannerSaveParam);
        return Result();
    }

}
