package io.candyboyou.mallcommodity.business.controller.admin;

import io.candyboyou.common.framework.model.vo.Result;
import io.candyboyou.mallcommodity.business.model.param.admin.CategorySaveParam;
import io.candyboyou.mallcommodity.business.service.admin.AdminCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private AdminCategoryService adminCategoryService;

    @ApiOperation(value = "新增或更新分类")
    @PostMapping("/saveOrUpdateCategory")
    public Result saveOrUpdateCategory(@RequestBody CategorySaveParam categorySaveParam) {

        return Result.ok();
    }
}
