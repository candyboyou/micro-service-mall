package io.candyboyou.common.framework.model.vo;

import io.swagger.annotations.ApiModelProperty;

public interface Paginate {

    @ApiModelProperty("当前页码")
    public int pageNum = 0;

    @ApiModelProperty("每页数量")
    public int pageSize = 0;

    @ApiModelProperty("总页数")
    public int total = 0;

}
