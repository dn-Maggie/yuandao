package com.dongnao.workbench.school.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongnao.workbench.common.bean.ChinaArea;
import com.dongnao.workbench.common.bean.Nation;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.school.dao.EmployeeMapper;
import com.dongnao.workbench.school.model.Employee;
 
/**
 * 描述：员工模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-07-15
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{
        @Resource
	private EmployeeMapper employeeMapper;
	
 
	/**
	 * 新增员工方法
	 * @param employee:实体类
	 */	
	public ResultMessage add(Employee employee){
		employeeMapper.add(employee);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找员工实体方法
	 * @param key String 实体主键
	 * @return Employee 实体对象
	 */
	public Employee getByPrimaryKey(String key){
		return employeeMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除员工方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		employeeMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找员工列表方法
	 * @param employee Employee 实体对象（查询条件）
	 * @return List<Employee> 实体对象的list
	 */
	public List<Employee> listByCondition(Employee employee){
		return employeeMapper.listByCondition(employee);
	}
	
	/**
	 * 修改员工方法
	 * @param employee Employee 实体对象
	 */	
	public ResultMessage update(Employee employee){
		try {
			employeeMapper.update(employee);
		} catch (Exception e) {
			return AjaxUtils.getResultMessage(0,e.getMessage());
		}
		return AjaxUtils.getSuccessMessage();
	}

	
	public Employee getEmpCount(String month) {
		return employeeMapper.getEmpCount(month);
	}

	/**
	 * 获取某员工的信息
	 */
	public Employee getMyMessage(String key) {
		return employeeMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 计算员工工资
	 */
	public List<Employee> countEmployeeSal(Employee employee){
		return employeeMapper.countEmployeeSal(employee);
	};

	/**
	 * 获取当前可用员工序列号
	 * @param userId 
	 */
	public Employee getNowEmpNo(){
		return employeeMapper.getNowEmpNo();
	}

	/**
	 * 获取当前可用员工序列号
	 * @param userId 
	 */
	public Employee getEmpByEmpNo(String empNo) {
		return employeeMapper.getEmpByEmpNo(empNo);
	}

	/* (non-Javadoc)
	 * @see com.dongnao.workbench.school.service.EmployeeService#checkNameValidation(java.lang.String)
	 */
	@Override
	public int checkNameValidation(String checkName) {
		return employeeMapper.checkNameValidation(checkName);
	}
	/**
	 *获取所有名族信息 
	 */
	@Override
	public List<Nation> getAllNation() {
		return employeeMapper.getAllNation();
	}
	
	@Override
	public List<ChinaArea> loadAreaByParent(Integer pid) {
		return employeeMapper.selectByPid(pid);
	}

}