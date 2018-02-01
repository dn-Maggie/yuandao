package com.dongnao.workbench.account.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.account.model.ExpenseAccount;
import com.dongnao.workbench.account.model.OrderInfo;

/**
 * 描述：报销单模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-08-18
 */
public interface ExpenseAccountService  {

	/**
	 * 新增报销单方法
	 * @param expenseAccount ExpenseAccount:实体类
	 */
	public ResultMessage add(ExpenseAccount expenseAccount);
	
	/**
	 * 删除报销单方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找报销单实体方法
	 * @param key String：实体主键
	 * @return expenseAccount ExpenseAccount 实体对象
	 */
	public ExpenseAccount getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找报销单列表方法
	 * @param expenseAccount ExpenseAccount 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<ExpenseAccount> listByCondition(ExpenseAccount expenseAccount);
	
	/**
	 * 修改报销单方法
	 * @param expenseAccount ExpenseAccount 实体对象
	 */	
	public ResultMessage update(ExpenseAccount expenseAccount);
	
	//批量审核报销单
	public void auditByKey(ExpenseAccount entity);
	/**
	 * 按员工统计信息
	 */
	public List<ExpenseAccount> listByEmployee(ExpenseAccount expenseAccount);

	public List<ExpenseAccount> sumAllExpense(ExpenseAccount expenseAccount);

	public List<ExpenseAccount> subgridlist(ExpenseAccount expenseAccount,String startDate,String endDate);
	/**
	 * 发放金额
	 */
	public void assignByKey(ExpenseAccount entity);

	/**
	 * 获取某员工的报销信息
	 * @param string 
	 */
	public ExpenseAccount getMyExpense(String userId,String startDate);

	public List<ExpenseAccount> getStatistical(ExpenseAccount expenseAccount);
}