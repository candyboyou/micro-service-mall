package io.canboyou.mallportal.base;

import java.util.List;

/**
 * 通用增删改查类
 * @author kaiquan.chen
 * @param <T> 实体类类型
 */
public interface BaseService <T extends BaseEntity> {

	/**
	 * 创建实体对象，底层使用insert()
	 * @param model 实体对象
	 * @return 插入后的实体对象
	 */
	T create(T model);

	/**
	 * 原声insertSelective()，null不插入
	 */
	T insertSelective(T model);

	/**
	 * 批量插入
	 * @param models 待插入实体对象列表
	 */
	List<T> insertList(List<T> models);

	/**
	 * 根据主键id获取实体对象
	 * @param id 主键id
	 * @return 实体对象
	 */
	T get(Long id);

	/**
	 * 根据主键id获取实体对象，但是只获取指定的列
	 * @param id 主键id
	 * @param properties 指定的列数组
	 * @return 返回包含指定查询列的实体对象
	 */
	T get(Long id, String... properties);

	/**
	 * 通用查询，根据model的字段条件进行等值匹配
	 * 注意，null值字段不参与匹配
	 */
	List<T> selectByExample(T t);

	/**
	 * 通用查询，根据model的字段条件进行等值匹配
	 * 注意，null值字段不参与匹配，只返回指定查询的字段
	 */
	List<T> selectByExample(T t, String... selectColumns);

	/**
	 * 根据model条件进行查找，只会返回一个对象，
	 * 如果可能命中多个结果集请使用{@link #selectByExample(BaseEntity)}
	 * @param t model对象
	 */
	T findOne(T t);

	/**
	 * 批量查询操作，根据主键id列表进行批量查询
	 * @param ids id主键列表
	 * @return  实体对象列表
	 */
	List<T> findByIds(Iterable<Long> ids);

	/**
	 * 保存对象，有则更新，无则创建
	 * 根据model的id是否存在判断是创建还是更新
	 * 更新时使用updateSelective()，null值不更新
	 */
	T save(T model);

	/**
	 * 更新操作，null值不更新
	 * @param model 待更新对象
	 */
	void updateSelective(T model);// 不更新null值

	/**
	 * 更新操作，null的字段也会更新
	 * @param model 待更新对象
	 */
	void update(T model);

	/**
	 * 均为物理删除，满足实体类对象条件的会被物理删除
	 * 如果不需要请使用{@link #update(BaseEntity)}或者{@link #updateSelective(BaseEntity)}
	 * @param model 实体类条件对象
	 */
	void delete(T model);

	/**
	 * 根据主键id进行物理删除，如果是逻辑删除请勿用
	 * @param id 主键id
	 */
	void deleteById(Long id);

	/**
	 * 批量操作，根据主键ids进行物理删除，如果是逻辑删除请勿用
	 * @param ids 主键id列表
	 */
	void deleteByIds(List<Long> ids);

	/**
	 * 批量查询，判断主键id是否存在
	 * @param ids id列表
	 * @return 返回存在的id列表，客户端需自己过滤不存在的id
	 */
	List<Long> existsByIds(List<Long> ids);

	/**
	 * 根据条件查询总数
	 */
	int selectCount(T t);
}
