package com.dongnao.workbench.school.service;

import java.util.List;

import com.dongnao.workbench.common.bean.ChinaArea;
import com.dongnao.workbench.common.bean.Nation;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.Employee;

/**
 * 描述：员工模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-07-15
 */
public interface EmployeeService  {

	/**
	 * 新增员工方法
	 * @param employee Employee:实体类
	 */
	public ResultMessage add(Employee employee);
	
	/**
	 * 删除员工方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工实体方法
	 * @param key String：实体主键
	 * @return employee Employee 实体对象
	 */
	public Employee getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工列表方法
	 * @param employee Employee 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<Employee> listByCondition(Employee employee);
	
	/**
	 * 修改员工方法
	 * @param employee Employee 实体对象
	 */	
	public ResultMessage update(Employee employee);

	/**
	 * 获取某员工总数
	 * @param month 
	 */
	public Employee getEmpCount(String month);
	
	/**
	 * 获取某员工的信息
	 * @param userId 
	 */
	public Employee getMyMessage(String userId);
	/**
	 * 计算员工工资
	 * @param userId 
	 */
	public List<Employee> countEmployeeSal(Employee employee);
	/**
	 * 获取当前可用员工序列号
	 * @param userId 
	 */
	public Employee getNowEmpNo();

	public Employee getEmpByEmpNo(String empNo);

	/**
	 * 
	 */
	public int checkNameValidation(String checkName);
	/**
	 *获取所有名族信息 
	 */
	public List<Nation> getAllNation();
	
	/**
	 * 根据父区域返回下面的所有子区域集合对象
	 * @param pid String：父级区域的id
	 * @return List<ChinaArea>根据pid查询出来的结果，最终以json格式相应客户端
	 */	
	public List<ChinaArea> loadAreaByParent(Integer pid);
}