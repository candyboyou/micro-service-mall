package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.PageResult;
import io.candyboyou.common.framework.model.vo.Result;
import io.candyboyou.common.utils.CollectionUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;
import pers.candyboyou.commodity.business.model.param.admin.CategorySearchParam;
import pers.candyboyou.commodity.business.model.param.admin.RelateCategoryParam;
import pers.candyboyou.commodity.business.model.vo.admin.*;
import pers.candyboyou.commodity.business.service.admin.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Api(value = "商品分类接口", tags = "/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "配置商品分类")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdateCategory(@RequestBody CategorySaveOrUpdateParam categorySaveOrUpdateParam) {
        categoryService.saveOrUpdateCategory(categorySaveOrUpdateParam);
        return Result.ok();
    }

    @ApiOperation(value = "获取所有下拉列表")
    @GetMapping("/getAllList")
    public Result getAllList() {
        AllListParamOfCategoryVO allListParamOfCategory = categoryService.getAllList();
        return Result.ok(allListParamOfCategory);
    }

    @ApiOperation(value = "获取分类列表")
    @GetMapping("/getCategories")
    public Result getCategories(CategorySearchParam searchParam) {
        PageResult<CategoryBaseVO> categories = categoryService.getCategories(searchParam);
        return Result.ok(categories);
    }

    @ApiOperation(value = "根据分类ID获取商品列表")
    @GetMapping("/getCommodities")
    public Result getCommodities(Long categoryId, QueryParam queryParam) {
        PageResult<SimpleCommodityInfoVO> commodityInfoVOPageResult = categoryService.getSimpleCommodityInfos(categoryId, queryParam);
        return Result.ok(commodityInfoVOPageResult);
    }

    @ApiOperation(value = "批量添加或删除分类下的商品")
    @PostMapping("/relateCommodities")
    public Result relateCommodities(RelateCategoryParam relateCategoryParam) {
        categoryService.relateCommodities(relateCategoryParam);
        return Result.ok();
    }

    @ApiOperation(value = "")
    @GetMapping("/getAllCategoryIdAndName")
    public Result getAllCategoryIdAndName() {
        List<CategoryIdAndNameVO> categoryIdAndNames = categoryService.getAllCategoryIdAndName();
        return Result.ok(categoryIdAndNames);
    }

    @GetMapping("/getParentCategoryListByLevel")
    public Result getParentCategoryListByLevel(Integer level) {
        if (level == 0) {
            return Result.ok();
        }
        if (level > 0) {
            --level;
        }
        List<OptionCommonVO> categoryNameVOS = categoryService.getCategoryListByLevel(level);
        return Result.ok(categoryNameVOS);
    }

    @GetMapping("/getCategoryListByLevel")
    public Result getCategoryListByLevel(Integer level) {
        List<OptionCommonVO> categoryNameVOS = categoryService.getCategoryListByLevel(level);
        return Result.ok(categoryNameVOS);
    }

    @GetMapping("/getSubCategoryListById")
    public Result getSubCategoryListById(Long id) {
        if (id == null) {
            return Result.ok();
        }
        List<OptionCommonVO> categoryNameVOS = categoryService.getSubCategoryListById(id);
        return Result.ok(categoryNameVOS);
    }

    @ApiOperation("获取分类详情")
    @GetMapping("/getCategoryById")
    public Result getCategoryById(Long id) {
        CategoryDetailVO categoryDetailVO = categoryService.getCategoryById(id);
        return Result.ok(categoryDetailVO);
    }

    @ApiOperation("获取分类下的商品列表")
    @GetMapping("/getCommodityListOfCategory")
    public Result getCommodityListOfCategory(List<Long> categoryIds) {
        if (CollectionUtils.isEmpty(categoryIds)) {
            return Result.ok();
        }
        List<CategoryWithCommodityVO> categoryWithCommodityVOS =  categoryService.getCommodityListOfCategory(categoryIds, null);
        return Result.ok(categoryWithCommodityVOS);
    }
}
