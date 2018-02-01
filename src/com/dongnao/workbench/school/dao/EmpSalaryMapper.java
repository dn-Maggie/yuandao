package com.dongnao.workbench.school.dao;

import java.util.List;

import com.dongnao.workbench.account.model.ExpenseAccount;
import com.dongnao.workbench.school.model.EmpSalary;
import com.dongnao.workbench.school.model.EmpSocialSecurity;
/**
 * 描述：员工工资表模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-12-14
 */
public interface EmpSalaryMapper  {
	/**
	 * 新增员工工资表方法
	 * @param empSalary EmpSalary:实体类
	 */
	void add(EmpSalary empSalary);
	/**
	 * 批量新增员工工资数据
	 */
	void addBatch(List<EmpSalary> emp);
	/**
	 * 删除员工工资表方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	/**
	 * 根据主键查找员工工资表实体方法
	 * @param key String：实体主键（查询条件）
	 * @return EmpSalary: 实体
	 */
	public EmpSalary getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工工资表列表方法
	 * @param EmpSalary empSalary：实体对象（查询条件）
	 * @return List<EmpSalary>: 实体对象的list
	 */
	public List<EmpSalary>  listByCondition(EmpSalary empSalary);
	/**
	 * 根据条件查找员工社保表列表方法
	 * @param empSocialSecurity
	 * @return
	 */
	public List<EmpSalary> listEmpSocialSecurity(EmpSalary empSalary);
	/**
	 * 修改员工工资表方法
	 * @param empSalary EmpSalary：实体对象
	 */	
	public void update(EmpSalary empSalary);

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
	public void autoAdd(EmpSalary empSalary);
	/**
	 * 修改员工工资表方法
	 * @param empSalary EmpSalary：实体对象
	 */	
	public void audit(EmpSalary empSalary);
	/**
	 * 发送工资信息
	 * @param empSalary EmpSalary：实体对象
	 */	
	public void send(EmpSalary empSalary);
	/**
	 * 审核工资信息
	 * @param empSalary EmpSalary：实体对象
	 */	
	public void assign(EmpSalary empSalary);
	/**
	 * 展示员工成本
	 * @param empSalary EmpSalary：实体对象
	 */	
	public List<EmpSalary> listEmpCost(EmpSalary empSalary);
	/**
	 * 更新员工成本
	 * @param empSalary EmpSalary：实体对象
	 */	
	public void updateEmpCost(EmpSalary empSalary);
	/**
	 * 插入员工成本
	 * @param empSalary EmpSalary：实体对象
	 */	
	public void addEmpCost(EmpSalary empSalary);
	/**
	 * 更新整个员工成本业绩
	 * @param empSalary EmpSalary：实体对象
	 */	
	public void updateEmpCostBonus(EmpSalary empSalary);
}