package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.vo.PageResult;
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
import pers.candyboyou.commodity.business.model.param.admin.CommodityUpdateParam;
import pers.candyboyou.commodity.business.model.vo.admin.AllListParamOfCommodityVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityDetailVO;
import pers.candyboyou.commodity.business.model.vo.admin.CommodityOfListVO;
import pers.candyboyou.commodity.business.service.admin.CommodityService;

@RestController
@RequestMapping("/admin/commodity")
@Api(value = "商品接口", tags = "/admin/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @ApiModelProperty("获取所有的参数list")
    @GetMapping("/getAllParamList")
    public Result getAllParamList() {
        AllListParamOfCommodityVO allListOfCommodityVO = commodityService.getAllParamList();
        return Result.ok(allListOfCommodityVO);
    }

    @ApiOperation("分页查询商品list")
    @GetMapping("/paginateGetCommodities")
    public Result paginateGetCommodities(CommoditySearchParam commoditySearchParam) {
        PageResult<CommodityOfListVO> commodityPageResult = commodityService.paginateGetCommodities(commoditySearchParam);
        return Result.ok(commodityPageResult);
    }

    @ApiOperation("根据id获取商品详情")
    @GetMapping("/getCommodityDetail")
    public Result getCommodityDetailVO(Long id) {
        CommodityDetailVO commodityDetailVO = commodityService.getCommodityDetailVO(id);
        return Result.ok(commodityDetailVO);
    }

    @ApiOperation("保存商品详情")
    @PostMapping("/newCommodity")
    public Result newCommodity(CommoditySaveParam commoditySaveParam) {
        commodityService.newCommodity(commoditySaveParam);
        return Result.ok();
    }

    @ApiOperation("更新、删除商品")
    @PostMapping("/updateCommodity")
    public Result updateCommodity(CommodityUpdateParam commodityUpdateParam) {
        commodityService.updateCommodity(commodityUpdateParam);
        return Result.ok();
    }

    @ApiModelProperty("在页面上更新商品信息")
    @PostMapping("/updateCommodityStatus")
    public Result updateCommodityStatus(CommodityStatusParam commodityStatusParam) {
        commodityService.updateCommodityStatus(commodityStatusParam);
        return Result.ok();
    }
}
