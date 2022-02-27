package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.candyboyou.commodity.business.model.vo.admin.CategoryPropertiesVO;
import pers.candyboyou.commodity.business.service.admin.AdminPropertyService;

@RestController
@RequestMapping("/admin/property")
@Api(value = "商品属性接口", tags = "/admin/property")
public class PropertyController {

    @Autowired
    private AdminPropertyService adminPropertyService;

    @ApiOperation(value = "商品分类相关属性list", tags = "/getCategoryProperties")
    public Result getCategoryProperties() {
        CategoryPropertiesVO categoryProperties = adminPropertyService.getCategoryProperties();
        return Result.ok(categoryProperties);
    }

}
