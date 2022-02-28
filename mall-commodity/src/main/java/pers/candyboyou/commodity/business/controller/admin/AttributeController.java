package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.candyboyou.commodity.business.model.param.admin.SpuAttrParam;
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

    @PostMapping("/saveOrUpdateSpuAttr")
    public Result saveOrUpdateSpuAttr(@RequestBody SpuAttrParam spuAttrParam) {
        attributeService.saveOrUpdateSpuAttr(spuAttrParam);
        return Result.ok();
    }


}
