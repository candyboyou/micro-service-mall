package pers.candyboyou.mallpromotion.business.controller.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.framework.model.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealSaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.FlashDealDetailVO;
import pers.candyboyou.mallpromotion.business.model.admin.vo.FlashDealOfListVO;
import pers.candyboyou.mallpromotion.business.model.admin.param.FlashDealSearchParam;
import pers.candyboyou.mallpromotion.business.service.admin.FlashDealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/flashDealController")
@Api(value = "/admin/flashDealController", tags = "后台商品秒杀管理")
public class FlashDealController {

    @Autowired
    private FlashDealService flashDealService;

    @ApiOperation(value = "分页获取秒杀列表")
    @PostMapping("/getFlashDealList")
    public Result getFlashDealList(FlashDealSearchParam flashDealSearchParam) {
        ListVO<FlashDealOfListVO> flashDealListVO = flashDealService.getFlashDealList(flashDealSearchParam);
        return Result.ok(flashDealListVO);
    }

    @ApiOperation(value = "获取秒杀的详情")
    @GetMapping("/getFlashDealDetail")
    public Result getFlashDealDetail(Long flashDealId) {
        FlashDealDetailVO flashDealDetailVO = flashDealService.getFlashDealDetail(flashDealId);
        return Result.ok(flashDealDetailVO);
    }

    @ApiOperation(value = "添加秒杀详情")
    @PostMapping("/saveOrUpdateFlashDeal")
    public Result saveOrUpdateFlashDeal(FlashDealSaveParam flashDealSaveParam) {
        if (flashDealSaveParam == null) {
            return Result.ok("秒杀详情不能为null");
        }
        flashDealService.saveOrUpdateFlashDeal(flashDealSaveParam);
        return Result.ok();
    }

}
