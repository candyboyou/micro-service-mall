package io.candyboyou.common.framework.model.base;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Entity implements Serializable {

    @Serial
    private static final long serialVersionUID = -1596941992689957498L;

    public Long id;

    public Boolean status;

    public Date creatTime;

    public Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
