package com.dongnao.workbench.student.dao;

import java.util.List;

import com.dongnao.workbench.student.model.MarketStudent;
/**
 * 描述：录入学员信息模块dao接口，提供数据库操作方法
 *
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
public interface MarketStudentMapper  {

	/**
	 * 新增录入学员信息方法
	 * @param marketStudent MarketStudent:实体类
	 */
	void add(MarketStudent marketStudent);
	
	/**
	 * 删除录入学员信息方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找录入学员信息实体方法
	 * @param key String：实体主键（查询条件）
	 * @return MarketStudent: 实体
	 */
	public MarketStudent getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找录入学员信息列表方法
	 * @param MarketStudent marketStudent：实体对象（查询条件）
	 * @return List<MarketStudent>: 实体对象的list
	 */
	public List<MarketStudent>  listByCondition(MarketStudent marketStudent);
	
	/**
	 * 修改录入学员信息方法
	 * @param marketStudent MarketStudent：实体对象
	 */	
	public void update(MarketStudent marketStudent);

	public MarketStudent getMarkStuCount(String month);

	public List<MarketStudent> getMarketStatistic(MarketStudent ms);
}