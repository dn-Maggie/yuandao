package com.dongnao.workbench.school.dao;

import java.util.List;

import com.dongnao.workbench.common.bean.ChinaArea;
import com.dongnao.workbench.common.bean.Nation;
import com.dongnao.workbench.school.model.Employee;
/**
 * 描述：员工模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-07-15
 */
public interface EmployeeMapper  {

	/**
	 * 新增员工方法
	 * @param employee Employee:实体类
	 */
	void add(Employee employee);
	
	/**
	 * 删除员工方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工实体方法
	 * @param key String：实体主键（查询条件）
	 * @return Employee: 实体
	 */
	public Employee getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工列表方法
	 * @param Employee employee：实体对象（查询条件）
	 * @return List<Employee>: 实体对象的list
	 */
	public List<Employee>  listByCondition(Employee employee);
	
	/**
	 * 修改员工方法
	 * @param employee Employee：实体对象
	 */	
	public void update(Employee employee);

	/**
	 * 获取员工的数量
	 * @param userId 
	 */
	public Employee getEmpCount(String month);
	/**
	 * 获取所有直接负责人列表
	 * @param employee Employee 实体对象
	 */	
	public List<Employee> getStraightLeader();
	/**
	 * 计算员工工资
	 */
	public List<Employee> countEmployeeSal(Employee employee);
	/**
	 * 获取当前可用员工序列号
	 * @param userId 
	 */
	public Employee getNowEmpNo();

	public Employee getEmpByEmpNo(String empNo);

	/**
	 * 删除员工方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	int checkNameValidation(String checkName);
	/**
	 * 获取所有民族信息
	 */
	public List<Nation> getAllNation();
	/**
	 * 根据pid查找省市区地域列表方法
	 * @param 父地域的id
	 * @return List<ChinaArea>: 实体对象的list
	 */
	public List<ChinaArea> selectByPid(Integer pid);
}