package io.canboyou.mallportal.base;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    /**
     * 实体类主键id
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    @GeneratedValue(generator = "JDBC")
    protected Long id;

    /**
     * 数据状态标识
     */
    @Column(name = "status")
    protected Boolean status;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 更新时间
     */
    protected Date updateTime;
}
