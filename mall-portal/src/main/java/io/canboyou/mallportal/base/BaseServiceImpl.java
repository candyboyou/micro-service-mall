package io.canboyou.mallportal.base;

import io.canboyou.mallportal.utils.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BaseMapper<T> mapper;

    private final Class<T> modelClass;

    private volatile ConcurrentMap<String, String> columnPropertyMap;

    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Override
    public T create(T model) {
        model.setUpdateTime(new Date());
        model.setCreateTime(new Date());
        model.setStatus(true);
        mapper.insert(model);
        return model;
    }

    @Override
    public T insertSelective(T model) {
        model.setUpdateTime(new Date());
        model.setCreateTime(new Date());
        model.setStatus(true);
        mapper.insertSelective(model);
        return model;
    }

    @Override
    public List<T> insertList(List<T> models) {
        for (T model : models) {
            model.setStatus(true);
            model.setCreateTime(new Date());
            model.setUpdateTime(new Date());
        }
        mapper.insertList(models);
        return models;
    }

    @Override
    public T get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public T get(Long id, String... properties) {
        Example example = new Example(modelClass);
        example.selectProperties(properties);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return mapper.selectOneByExample(example);
    }

    @Override
    public T save(T model) {
        model.setUpdateTime(new Date());
        if (model.getId() != null) {
            T t = get(model.getId(), "id");
            if (t != null) {
                mapper.updateByPrimaryKeySelective(model);
                updateCallBack(model); // 更新回调
                return model;
            }
        }
        model.setCreateTime(new Date());
        mapper.insertSelective(model);
        insertCallback(model); // 插入回调
        return model;
    }

    @Override
    public void delete(T model) {
        model.setUpdateTime(new Date());
        mapper.delete(model);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteBatch(Collections.singletonList(id));
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
//        Example example = new Example(modelClass);
//        example.createCriteria().andIn("id", ids);
//        mapper.deleteByExample(example);
        mapper.deleteBatch(ids);
    }

    /**
     * 判断指定主键得数据是否存在
     * @param ids 主键列表
     * @return 返回已存在得主键列表
     */
    @Override
    public List<Long> existsByIds(List<Long> ids) {
    	if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        Example example = new Example(modelClass);
        example.createCriteria().andIn("id", ids);
        example.selectProperties("id");
        List<T> models = mapper.selectByExample(example);
    	return models.stream().map(BaseEntity::getId).collect(Collectors.toList());
    }

    @Override
    public void updateSelective(T model) {
        model.setUpdateTime(new Date());
        mapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void update(T model) {
        model.setUpdateTime(new Date());
        mapper.updateByPrimaryKey(model);
    }

    @Override
    public T findOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public List<T> selectByExample(T t) {
        return mapper.select(t);
    }

    @Override
    public List<T> selectByExample(T t, String... selectColumns) {
        if (ArrayUtils.isEmpty(selectColumns)) {
            return Collections.emptyList();
        }
        Example example = new Example(modelClass);
        example.selectProperties(selectColumns);
        try {
            appendWhere(example, t);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyList();
        }
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> findByIds(Iterable<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        Example example = new Example(modelClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCount(T t) {
        return mapper.selectCount(t);
    }

    /**
     * 插入回调方法，此处位空实现，由子类重写此方法
     */
    protected void insertCallback(T t) {
        // do nothing
    }

    /**
     * 更新回调方法，此处位空实现，由子类重写此方法
     */
    protected void updateCallBack(T t) {
        // do nothing
    }

    /**
     * 拼接条件
     */
    protected void appendWhere(Example example, T t) throws IllegalAccessException {
        List<Field> fieldList = getAllField();
        Example.Criteria criteria = example.createCriteria();
        for (Field field : fieldList) {
            String columnName = getColumnName(field);
            if (StringUtils.isBlank(columnName)) {
                continue;
            }
            field.setAccessible(true);
            Object o = field.get(t);
            if (o != null) {
                criteria.andEqualTo(columnName, o);
            }
        }
        List<Example.Criterion> allCriteria = criteria.getAllCriteria();
        if (CollectionUtils.isEmpty(allCriteria)) {
            throw new IllegalArgumentException("不支持无条件全量查询数据");
        }
    }

    private List<Field> getAllField() {
        Field[] fields = modelClass.getDeclaredFields();
        Field[] parentFields = BaseEntity.class.getDeclaredFields();
        List<Field> fieldList = Arrays.stream(fields).collect(Collectors.toList());
        List<Field> parentFieldList = Arrays.stream(parentFields).collect(Collectors.toList());
        return CollectionUtils.merge(fieldList, parentFieldList);
    }

    // 根据字段名称获取列名
    private String getColumnName(Field field) {
        if (columnPropertyMap == null) { // double check
            synchronized (this) {
                if (columnPropertyMap == null) {
                    columnPropertyMap = new ConcurrentHashMap<>();
                    Set<EntityColumn> columns = EntityHelper.getColumns(modelClass);
                    for (EntityColumn column : columns) {
                        columnPropertyMap.put(column.getProperty(), column.getColumn());
                    }
                }
            }
        }
        String name = field.getName();
        return columnPropertyMap.get(name);
    }
}
