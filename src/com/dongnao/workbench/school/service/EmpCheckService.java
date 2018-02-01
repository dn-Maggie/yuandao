package com.dongnao.workbench.school.service;

import java.util.List;

import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.EmpCheck;
import com.dongnao.workbench.school.model.Employee;

public interface EmpCheckService {

	/**
	 * 根据条件查找员工列表方法
	 * @param empCheck EmpCheck 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<EmpCheck> listByCondition(EmpCheck empCheck);
	
	
	/**
	 * 根据主键查找员工姓名作为查询条件查询该员工所有审核信息的方法
	 * @param key String：实体主键
	 * @return employee Employee 实体对象
	 */
	public EmpCheck getByPrimaryKey(String key);
	
	
	/**
	 * 批量新增部门员工未审核数据的方法
	 * @param List<EmpCheck> :集合
	 */
	public void add(List<EmpCheck> empc);
	
	/**
	 * 更新方法
	 * @param EmpCheck
	 */
	public ResultMessage update(EmpCheck empc);
	
	
	/**
	 * 根据主键查找员工姓名作为查询条件查询该员工所有审核信息的方法
	 * @param key String：实体主键
	 * @return employee Employee 实体对象
	 */
	public EmpCheck showCheckFormKey(String key);
	
	/**
	 * 员工确认考核单方法
	 * @param List<EmpCheck> :集合
	 */
	public int empConfirm(EmpCheck empc);
}
