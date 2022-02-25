package io.candyboyou.mallcommodity.business.controller;

import io.candyboyou.common.framework.model.vo.Result;
import io.candyboyou.mallcommodity.business.model.param.BrandSaveOrUpdateParam;
import io.candyboyou.mallcommodity.business.model.param.BrandSearchParam;
import io.candyboyou.mallcommodity.business.model.vo.BrandVO;
import io.candyboyou.mallcommodity.business.service.AdminBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/brand")
@Api(value = "/admin/brand", tags = "后台品牌接口")
public class AdminBrandController {

    @Autowired
    private AdminBrandService adminBrandService;

    @ApiOperation("")
    @GetMapping("/get")
    public Result getBrands(BrandSearchParam searchParam) {
        List<BrandVO> brandVOs = adminBrandService.getBrands(searchParam);
        return Result.ok(brandVOs);
    }

    public Result saveOrUpdateBrand(BrandSaveOrUpdateParam BrandSaveOrUpdateParam) {
        return Result.ok();
    }

}