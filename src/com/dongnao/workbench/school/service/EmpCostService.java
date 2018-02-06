package com.dongnao.workbench.school.service;

import java.util.List;

import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.EmpCost;

/**
 * 描述：员工工资表模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-12-14
 */
public interface EmpCostService  {

	/**
	 * 新增员工工资表方法
	 * @param empCost EmpCost:实体类
	 */
	public ResultMessage add(EmpCost empCost);
	/**
	 * 删除员工工资表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	/**
	 * 根据主键查找员工工资表实体方法
	 * @param key String：实体主键
	 * @return empCost EmpCost 实体对象
	 */
	public EmpCost getByPrimaryKey(String key);
	/**
	 * 根据条件查找员工工资表列表方法
	 * @param empCost EmpCost 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<EmpCost> listByCondition(EmpCost empCost);
	/**
	 * 修改员工工资表方法
	 * @param empCost EmpCost 实体对象
	 */	
	public ResultMessage update(EmpCost empCost);
	
}