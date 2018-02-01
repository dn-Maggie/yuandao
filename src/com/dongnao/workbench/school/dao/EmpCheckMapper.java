package com.dongnao.workbench.school.dao;

import java.util.List;

import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.school.model.EmpCheck;
import com.dongnao.workbench.school.model.Employee;

public interface EmpCheckMapper {
	/**
	 * 根据条件查找员工列表方法
	 * @param empCheck EmpCheck 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<EmpCheck> listByCondition(EmpCheck empCheck);
	
	
	/**
	 * 根据条件查找员工列表方法
	 * @param empCheck EmpCheck 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public EmpCheck getByPrimaryKey(String key);
	
	public EmpCheck showCheckFormKey(String key);
	
	/**
	 * 批量新增部门员工未审核数据的方法
	 * @param List<EmpCheck> :集合
	 */
	void add(List<EmpCheck> empc);
	
	/**
	 * 更新方法
	 * @param EmpCheck empc :
	 */
	void update(EmpCheck empc);
	
	int empConfirm(EmpCheck empc);
}
