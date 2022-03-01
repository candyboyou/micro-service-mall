package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySaveParam;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySearchParam;
import pers.candyboyou.commodity.business.model.vo.CommodityDetailVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityVO;
import pers.candyboyou.commodity.business.service.admin.AdminCommodityService;

import java.util.List;

@RestController
@RequestMapping("/admin/commodity")
@Api(value = "商品接口", tags = "/admin/commodity")
public class CommodityController {

    @Autowired
    private AdminCommodityService adminCommodityService;

    @ApiOperation("分页查询商品list")
    @GetMapping("/getCommodities")
    public Result getCommodityVOS(CommoditySearchParam commoditySearchParam) {
        ListVO<CommodityVO> commodityListVO = adminCommodityService.getCommodityVOS(commoditySearchParam);
        return Result.ok(commodityListVO);
    }

    @ApiOperation("根据id获取商品详情")
    @GetMapping("/getCommodityDetail")
    public Result getCommodityDetailVO(Long id) {
        CommodityDetailVO commodityDetailVO = adminCommodityService.getCommodityDetailVO(id);
        return Result.ok(commodityDetailVO);
    }

    @ApiOperation("保存、更新、删除商品详情")
    @PostMapping("/saveOrUpdateCommodity")
    public Result saveOrUpdateCommodity(List<CommoditySaveParam> commoditySaveParams) {
        adminCommodityService.saveOrUpdateCommodity(commoditySaveParams);
        return Result.ok();
    }
}
