package pers.candyboyou.mallpromotion.business.model.admin.param;

import io.candyboyou.common.framework.model.param.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RecommendCommoditySearchParam extends QueryParam {

    private String commodityName;

    private Integer status;

}
