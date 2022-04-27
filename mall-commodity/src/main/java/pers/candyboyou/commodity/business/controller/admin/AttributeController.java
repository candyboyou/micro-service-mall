package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.expection.BusinessException;
import io.candyboyou.common.framework.model.param.QueryParam;
import io.candyboyou.common.framework.model.vo.PageResult;
import io.candyboyou.common.framework.model.vo.Result;
import io.candyboyou.common.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.candyboyou.commodity.business.model.dto.AttributeNameDTO;
import pers.candyboyou.commodity.business.model.param.admin.AttrParam;
import pers.candyboyou.commodity.business.model.param.admin.AttrSearchParam;
import pers.candyboyou.commodity.business.model.param.admin.SkuAttrParam;
import pers.candyboyou.commodity.business.model.vo.admin.*;
import pers.candyboyou.commodity.business.service.admin.AttributeService;

import java.util.List;

@RestController
@RequestMapping("/admin/attribute")
@Api(value = "商品类型接口", tags = "/admin/attribute")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @ApiOperation("保存、更新、删除sku属性")
    @PostMapping("/saveOrUpdateSkuAttr")
    public Result saveOrUpdateSkuAttr(@RequestBody SkuAttrParam skuAttrParam) {
        attributeService.saveOrUpdateSkuAttr(skuAttrParam);
        return Result.ok();
    }

    @ApiOperation("根据类型ID分页查询sku属性")
    @GetMapping("/getSkuAttributes")
    public Result getSkuAttributes(Long attributeId, QueryParam queryParam) {
        PageResult<SkuAttributeVO> attributeVOPageResult = attributeService.getSkuAttributesById(attributeId, queryParam);
        return Result.ok(attributeVOPageResult);
    }

    @ApiOperation("保存、更新、删除商品属性")
    @PostMapping("/saveOrUpdateAttribute")
    public Result saveOrUpdateAttr(@RequestBody AttrParam attrParam) {
        if (attrParam == null || StringUtils.isBlank(attrParam.getName())) {
            throw new BusinessException("属性名不能为null");
        }
        attributeService.saveOrUpdateAttr(attrParam);
        return Result.ok();
    }

    @ApiOperation("根据类型ID分页查询Concrete属性")
    @GetMapping("/getAttributesById")
    public Result getAttributes(Long attributeId, QueryParam queryParam) {
        PageResult<AttributeVO> attributeVOPageResult = attributeService.getAttributeVOsById(attributeId, queryParam);
        return Result.ok(attributeVOPageResult);
    }

    @ApiOperation("获取查询属性列表时的枚举")
    @GetMapping("/getAllListOfQuery")
    public Result getAllListOfQuery() {
        AllListQueryParamOfAttributeVO allList = attributeService.getAllListOfQuery();
        return Result.ok(allList);
    }

    @ApiOperation("获取新增属性时的枚举")
    @GetMapping("/getAllListOfSave")
    public Result getAllListOfSave() {
        AllListSaveParamOfAttributeVO allList = attributeService.getAllListOfSave();
        return Result.ok(allList);
    }

    @ApiOperation("分页查询属性列表")
    @GetMapping("/getAttributes")
    public Result getAttributes(AttrSearchParam attrSearchParam) {
        PageResult<AttributeOfListVO> attributes = attributeService.getAttributes(attrSearchParam);
        return Result.ok(attributes);
    }

    @ApiOperation("根据类型ID分页查询属性详情")
    @GetMapping("/getAttributeById")
    public Result getAttributeById(Long attributeId) {
        AttributeVO attributeVO = attributeService.getAttributeById(attributeId);
        return Result.ok(attributeVO);
    }

    @ApiOperation("获取所有的属性")
    @GetMapping("/getAllAttribute")
    public Result getAllAttributes() {
        List<AttributeNameDTO> allAttributeDTOS = attributeService.getAllAttributes();
        List<OptionCommonVO> allAttributes = OptionCommonVO.convertAttributeNameDTOS(allAttributeDTOS);
        return Result.ok(allAttributes);
    }
}
