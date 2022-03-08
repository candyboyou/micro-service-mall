package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySaveParam;
import pers.candyboyou.commodity.business.model.param.admin.CommoditySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.CommodityStatusParam;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityDetailVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityOfListVO;
import pers.candyboyou.commodity.business.service.admin.AdminCommodityService;

@RestController
@RequestMapping("/admin/commodity")
@Api(value = "商品接口", tags = "/admin/commodity")
public class CommodityController {

    @Autowired
    private AdminCommodityService adminCommodityService;

    @ApiOperation("分页查询商品list")
    @GetMapping("/getCommodities")
    public Result getCommodityVOS(CommoditySearchParam commoditySearchParam) {
        ListVO<CommodityOfListVO> commodityListVO = adminCommodityService.getCommodityVOS(commoditySearchParam);
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
    public Result saveOrUpdateCommodity(CommoditySaveParam commoditySaveParams) {
        adminCommodityService.saveOrUpdateCommodity(commoditySaveParams);
        return Result.ok();
    }

    @ApiModelProperty("在页面上更新商品信息")
    @PostMapping("/updateCommodityStatus")
    public Result updateCommodityStatus(CommodityStatusParam commodityStatusParam) {
        adminCommodityService.updateCommodityStatus(commodityStatusParam);
        return Result.ok();
    }
}
