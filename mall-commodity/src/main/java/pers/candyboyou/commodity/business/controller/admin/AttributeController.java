package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.ListVO;
import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.candyboyou.commodity.business.model.param.admin.ConcreteAttrParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.vo.ConcreteAttributeVO;
import pers.candyboyou.commodity.business.model.vo.admin.SkuAttributeVO;
import pers.candyboyou.commodity.business.service.admin.AttributeService;

@RestController
@RequestMapping("/admin/attribute")
@Api(value = "商品类型接口", tags = "/admin/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @PostMapping("/saveOrUpdateAttribute")
    public void saveOrUpdateAttribute() {

    }

    @ApiOperation("保存、更新、删除sku属性")
    @PostMapping("/saveOrUpdateSkuAttr")
    public Result saveOrUpdateSkuAttr(@RequestBody SkuAttrParam skuAttrParam) {
        attributeService.saveOrUpdateSkuAttr(skuAttrParam);
        return Result.ok();
    }

    @ApiOperation("根据类型ID分页查询sku属性")
    @GetMapping("/getSkuAttributes")
    public Result getSkuAttributes(Long attributeId, QueryParam queryParam) {
        ListVO<SkuAttributeVO> attributeVOListVO = attributeService.getSkuAttributesById(attributeId, queryParam);
        return Result.ok(attributeVOListVO);
    }


    @ApiOperation("")
    @PostMapping("/saveOrUpdateConcreteAttr")
    public Result saveOrUpdateConcreteAttr(@RequestBody ConcreteAttrParam concreteAttrParam) {
        attributeService.saveOrUpdateConcreteAttr(concreteAttrParam);
        return Result.ok();
    }

    @ApiOperation("根据类型ID分页查询Concrete属性")
    @GetMapping("/getConcreteAttributes")
    public Result getConcreteAttributes(Long attributeId, QueryParam queryParam) {
        ListVO<ConcreteAttributeVO> attributeVOListVO = attributeService.getConcreteAttributesById(attributeId, queryParam);
        return Result.ok(attributeVOListVO);
    }

}
