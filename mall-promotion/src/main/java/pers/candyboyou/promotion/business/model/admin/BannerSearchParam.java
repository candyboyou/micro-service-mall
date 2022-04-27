package pers.candyboyou.mallpromotion.business.model.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class BannerSearchParam extends QueryParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -6452367864976031320L;

    @ApiModelProperty("商品名称")
    private String bannerName;

    @ApiModelProperty("是否上线")
    private String isOnline;
}
