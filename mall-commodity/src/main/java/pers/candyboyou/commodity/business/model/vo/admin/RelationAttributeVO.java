package pers.candyboyou.commodity.business.model.vo.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class RelationAttributeVO extends OptionCommonVO{

    @Serial
    private static final long serialVersionUID = -1350636133185002737L;

    @ApiModelProperty("是否删除")
    private Integer isDelete = 0;
}
