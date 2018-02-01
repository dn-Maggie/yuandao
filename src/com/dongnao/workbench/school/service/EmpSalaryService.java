package com.dongnao.workbench.school.service;

import java.util.List;

import com.dongnao.workbench.account.model.ExpenseAccount;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.EmpSalary;
import com.dongnao.workbench.school.model.EmpSocialSecurity;

/**
 * 描述：员工工资表模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-12-14
 */
public interface EmpSalaryService  {

	/**
	 * 新增员工工资表方法
	 * @param empSalary EmpSalary:实体类
	 */
	public ResultMessage add(EmpSalary empSalary);
	
	
	/**
	 * 批量新增员工工资数据
	 * @param empSalary EmpSalary:实体类
	 */
	public ResultMessage addBatch(List<EmpSalary> emp);
	/**
	 * 删除员工工资表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工工资表实体方法
	 * @param key String：实体主键
	 * @return empSalary EmpSalary 实体对象
	 */
	public EmpSalary getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工工资表列表方法
	 * @param empSalary EmpSalary 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<EmpSalary> listByCondition(EmpSalary empSalary);
	
	/**
	 * 修改员工工资表方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage update(EmpSalary empSalary);
	/**
	 * 按月份统计员工实发工资方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public EmpSalary getEmpSalaryStatistic(EmpSalary empSalary);
	/**
	 * 自动生成当月员工工资条方法
	 * @param empSalary 
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage autoAdd(EmpSalary empSalary);
	/**
	 * 审核员工工资表方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage audit(EmpSalary entity);
	/**
	 * 发送员工工资表方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage send(EmpSalary empSalary);
	/**
	 * 发送员工工资表方法
	 * 更新工资单assign_flag
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage assign(EmpSalary empSalary);


	/**
	 * @param empSocialSecurity
	 * @return
	 */
	public List<EmpSalary> listEmpSocialSecurity(EmpSalary empSalary);


	public List<EmpSalary> listEmpCost(EmpSalary empSalary);


	public ResultMessage updateEmpCost(EmpSalary empSalary);


	public ResultMessage addEmpCost(EmpSalary empSalary);


	public void updateEmpCostBonus(EmpSalary empSalary);

	
}