package pers.candyboyou.commodity.business.model.param.admin;

import io.candyboyou.common.framework.model.param.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommoditySearchParam extends QueryParam {

    private String name;

    private Integer categoryId;

    private Integer isPublish;

    private Integer isNew;

    private Integer isRecommend;

    private Integer isVerify;
}
