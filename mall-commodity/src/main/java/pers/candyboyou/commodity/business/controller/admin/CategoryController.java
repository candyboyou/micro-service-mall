package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryVO;
import pers.candyboyou.commodity.business.model.vo.admin.SimpleCommodityInfoVO;
import pers.candyboyou.commodity.business.service.admin.AdminCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Api(value = "商品分类接口", tags = "/admin/category")
public class CategoryController {

    @Autowired
    private AdminCategoryService adminCategoryService;

    @ApiOperation(value = "配置商品分类")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdateCategory(@RequestBody CategorySaveOrUpdateParam categorySaveOrUpdateParam) {
        adminCategoryService.saveOrUpdateCategory(categorySaveOrUpdateParam);
        return Result.ok();
    }

    @ApiOperation(value = "获取分类列表")
    @GetMapping("/getCategories")
    public Result getCategories(CategorySearchParam searchParam) {
        ListVO<CategoryVO> categories = adminCategoryService.getCategories(searchParam);
        return Result.ok(categories);
    }

    @ApiOperation(value = "根据分类ID获取商品列表")
    @GetMapping("/getCommodities")
    public Result getCommodities(Long categoryId, QueryParam queryParam) {
        ListVO<SimpleCommodityInfoVO> commodityInfoVOListVO = adminCategoryService.getSimpleCommodityInfos(categoryId, queryParam);
        return Result.ok(commodityInfoVOListVO);
    }

    @ApiOperation(value = "批量添加或删除分类下的商品")
    @PostMapping("/relateCommodities")
    public Result relateCommodities(RelateCategoryParam relateCategoryParam) {
        adminCategoryService.relateCommodities(relateCategoryParam);
        return Result.ok();
    }


}
