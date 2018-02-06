package com.dongnao.workbench.school.dao;

import java.util.List;

import com.dongnao.workbench.school.model.EmpCost;
/**
 * 描述：员工工资表模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-12-14
 */
public interface EmpCostMapper  {
	/**
	 * 新增员工工资表方法
	 * @param EmpCost EmpCost:实体类
	 */
	void add(EmpCost EmpCost);
	/**
	 * 删除员工工资表方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	/**
	 * 根据主键查找员工工资表实体方法
	 * @param key String：实体主键（查询条件）
	 * @return EmpCost: 实体
	 */
	public EmpCost getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工工资表列表方法
	 * @param EmpCost EmpCost：实体对象（查询条件）
	 * @return List<EmpCost>: 实体对象的list
	 */
	public List<EmpCost>  listByCondition(EmpCost empCost);
	/**
	 * 修改员工工资表方法
	 * @param empCost EmpCost：实体对象
	 */	
	public void update(EmpCost empCost);
}