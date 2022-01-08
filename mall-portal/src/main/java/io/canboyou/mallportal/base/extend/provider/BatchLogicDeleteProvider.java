package io.canboyou.mallportal.base.extend.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

/**
 * 批量逻辑删除provider
 */
public class BatchLogicDeleteProvider extends MapperTemplate {

    public BatchLogicDeleteProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     * 批量逻辑删除provider
     */
    public String deleteBatch(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        // 开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, tableName(entityClass)));
        sql.append("<set>");
        sql.append("status = 0");
        sql.append(", update_time = NOW()"); // 记录更新时间
        sql.append("</set>");
        sql.append(" WHERE id IN ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" open=\"(\" close=\")\" >");
        sql.append("#{record,javaType=java.lang.Long}");
        sql.append("</foreach>");
        return sql.toString();
    }

}
