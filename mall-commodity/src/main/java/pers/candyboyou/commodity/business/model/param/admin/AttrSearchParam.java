package pers.candyboyou.commodity.business.model.param.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttrSearchParam extends QueryParam {

    private String name;

    private Integer isSale;

}
