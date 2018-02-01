package com.dongnao.workbench.basic.service;

import java.util.List;

import com.dongnao.workbench.basic.model.Course;
import com.dongnao.workbench.common.bean.ResultMessage;

/**
 * 描述：课程表模块service接口，提供controller操作所需方法
 *
 * @author cheng.mo
 * @version 1.0 2016-05-01
 */
public interface CourseService  {

	/**
	 * 新增课程表方法
	 * @param course Course:实体类
	 */
	public ResultMessage add(Course course);
	
	/**
	 * 删除课程表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找课程表实体方法
	 * @param key String：实体主键
	 * @return course Course 实体对象
	 */
	public Course getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找课程表列表方法
	 * @param course Course 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<Course> listByCondition(Course course);
	
	/**
	 * 修改课程表方法
	 * @param course Course 实体对象
	 */	
	public ResultMessage update(Course course);
}