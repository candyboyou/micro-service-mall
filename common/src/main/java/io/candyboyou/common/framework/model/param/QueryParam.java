package io.candyboyou.common.framework.model.param;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

public class QueryParam {

    @ApiModelProperty(value = "当前页", example = "1")
    private int pageNum = 1;

    @ApiModelProperty(value = "当前页数量", example = "10")
    private int pageSize = 10;

    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private int start;

    public int getStart() {
        return (this.pageNum - 1) * this.pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
