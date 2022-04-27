package pers.candyboyou.mallpromotion.business.controller.admin;

import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.candyboyou.mallpromotion.business.model.admin.param.RecommendCommoditySaveParam;
import pers.candyboyou.mallpromotion.business.model.admin.param.RecommendCommoditySearchParam;
import pers.candyboyou.mallpromotion.business.model.admin.vo.RecommendCommodityVO;
import pers.candyboyou.mallpromotion.business.service.admin.RecommendService;

import java.util.List;

@RestController
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @ApiOperation("分页获取人气推荐商品")
    @PostMapping("/getRecommendCommodities")
    public Result getRecommendCommodities(RecommendCommoditySearchParam recommendCommoditySearchParam) {
        ListVO<RecommendCommodityVO> recommendCommodityVOListVO =
                recommendService.getRecommendCommodities(recommendCommoditySearchParam);
        return Result.ok(recommendCommodityVOListVO);
    }

    @ApiOperation("新增或更新人气推荐商品")
    @PostMapping("/saveOrUpdateRecommendCommodities")
    public Result saveOrUpdateRecommendCommodities(List<RecommendCommoditySaveParam> recommendCommoditySaveParams) {
        recommendService.saveOrUpdateRecommendCommodities(recommendCommoditySaveParams);
        return Result.ok();
    }
}
