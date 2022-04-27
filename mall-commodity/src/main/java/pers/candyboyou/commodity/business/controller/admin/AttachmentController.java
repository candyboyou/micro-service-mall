package pers.candyboyou.commodity.business.controller.admin;

import io.candyboyou.common.framework.model.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.candyboyou.commodity.business.model.param.admin.CategorySaveOrUpdateParam;

@RestController
@RequestMapping("/admin/attachmentController")
@Api(value = "附件接口", tags = "/admin/attachmentController")
@Slf4j
public class AttachmentController {

    @ApiOperation(value = "配置商品分类")
    @PostMapping("/saveOrUpdateAttachment")
    public Result saveOrUpdateAttachment() {
        log.info("调用接口");
        return Result.ok();
    }

}
