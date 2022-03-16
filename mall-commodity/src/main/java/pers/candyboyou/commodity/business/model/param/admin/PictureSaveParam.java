package pers.candyboyou.commodity.business.model.param.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PictureSaveParam {

    @ApiModelProperty("图片的id")
    private Long id;

    @ApiModelProperty("图片URL")
    private String pictureUrl;

    @ApiModelProperty("是否是主图")
    private Integer isMain;

}
