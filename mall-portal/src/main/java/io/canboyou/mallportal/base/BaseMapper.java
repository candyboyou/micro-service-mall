package io.canboyou.mallportal.base;

import io.canboyou.mallportal.base.extend.mapper.BatchLogicDeleteMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface BaseMapper<T> extends Mapper<T>,
        BatchLogicDeleteMapper<T>, InsertListMapper<T> {

}
