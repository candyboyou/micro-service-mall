package io.canboyou.mallportal.base.extend.mapper;

import io.canboyou.mallportal.base.extend.provider.BatchLogicDeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * 批量逻辑删除mapper
 */
public interface BatchLogicDeleteMapper<T> {

    @SuppressWarnings("all")
    @UpdateProvider(type = BatchLogicDeleteProvider.class, method = "dynamicSQL")
    int deleteBatch(@Param("list") List<Long> ids);

}
