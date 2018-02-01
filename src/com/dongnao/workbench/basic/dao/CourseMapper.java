package com.dongnao.workbench.basic.dao;

import java.util.List;

import com.dongnao.workbench.basic.model.Course;
/**
 * 描述：课程表模块dao接口，提供数据库操作方法
 *
 * @author cheng.mo
 * @version 1.0 2016-05-01
 */
public interface CourseMapper  {

	/**
	 * 新增课程表方法
	 * @param course Course:实体类
	 */
	void add(Course course);
	
	/**
	 * 删除课程表方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找课程表实体方法
	 * @param key String：实体主键（查询条件）
	 * @return Course: 实体
	 */
	public Course getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找课程表列表方法
	 * @param Course course：实体对象（查询条件）
	 * @return List<Course>: 实体对象的list
	 */
	public List<Course>  listByCondition(Course course);
	
	/**
	 * 修改课程表方法
	 * @param course Course：实体对象
	 */	
	public void update(Course course);
}