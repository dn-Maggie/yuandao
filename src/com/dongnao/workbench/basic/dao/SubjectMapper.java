package com.dongnao.workbench.basic.dao;

import java.util.List;

import com.dongnao.workbench.basic.model.Subject;
/**
 * 描述：学科表模块dao接口，提供数据库操作方法
 *
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
public interface SubjectMapper  {
	/**
	 * 新增学科表方法
	 * @param subject Subject:实体类
	 */
	void add(Subject subject);
	
	/**
	 * 删除学科表方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找学科表实体方法
	 * @param key String：实体主键（查询条件）
	 * @return Subject: 实体
	 */
	public Subject getByPrimaryKey(String key);
	/**
	 * 根据条件查找学科表列表方法
	 * @param Subject subject：实体对象（查询条件）
	 * @return List<Subject>: 实体对象的list
	 */
	public List<Subject>  listByCondition(Subject subject);
	
	/**
	 * 修改学科表方法
	 * @param subject Subject：实体对象
	 */	
	public void update(Subject subject);
	
	/* (non-Javadoc)
	 * 查询总业绩目标方法
	 */
	public int queryPerfTarget();
}