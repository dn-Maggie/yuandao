package com.dongnao.workbench.school.dao;

import java.util.List;
import com.dongnao.workbench.school.model.Standard;
import com.dongnao.workbench.student.model.Statistical;
/**
 * 描述：考核标准表模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-07-18
 */
public interface StandardMapper  {

	/**
	 * 新增考核标准表方法
	 * @param standard Standard:实体类
	 */
	void add(Standard standard);
	
	/**
	 * 删除考核标准表方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找考核标准表实体方法
	 * @param key String：实体主键（查询条件）
	 * @return Standard: 实体
	 */
	public Standard getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找考核标准表列表方法
	 * @param Standard standard：实体对象（查询条件）
	 * @return List<Standard>: 实体对象的list
	 */
	public List<Standard>  listByCondition(Standard standard);
	
	/**
	 * 修改考核标准表方法
	 * @param standard Standard：实体对象
	 */	
	public void update(Standard standard);

	/**
	 * 查询所有流量来源方法
	 * @param standard Standard 实体对象
	 */	
	public List<Standard> getAllResourceId();
	
	/**
	 * 查询所有转化人类型方法
	 * @param standard Standard 实体对象
	 */	
	public List<Standard> getAllFollowerId();

	/**
	 * @return
	 */
	public List<Standard> getAllSubResourceId();
}