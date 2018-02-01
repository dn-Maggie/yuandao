package com.dongnao.workbench.account.dao;

import java.util.List;
import com.dongnao.workbench.account.model.ExpenseAccount;
/**
 * 描述：报销单模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-08-18
 */
public interface ExpenseAccountMapper  {

	/**
	 * 新增报销单方法
	 * @param expenseAccount ExpenseAccount:实体类
	 */
	void add(ExpenseAccount expenseAccount);
	
	/**
	 * 删除报销单方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找报销单实体方法
	 * @param key String：实体主键（查询条件）
	 * @return ExpenseAccount: 实体
	 */
	public ExpenseAccount getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找报销单列表方法
	 * @param ExpenseAccount expenseAccount：实体对象（查询条件）
	 * @return List<ExpenseAccount>: 实体对象的list
	 */
	public List<ExpenseAccount>  listByCondition(ExpenseAccount expenseAccount);
	
	/**
	 * 修改报销单方法
	 * @param expenseAccount ExpenseAccount：实体对象
	 */	
	public void update(ExpenseAccount expenseAccount);

	/**
	 * 批量审核报销单
	 * @param expenseAccount ExpenseAccount：实体对象
	 */
	void auditByKey(ExpenseAccount expenseAccount);
	/**
	 * 按员工统计信息
	 */
	public List<ExpenseAccount> listByEmployee(ExpenseAccount expenseAccount);

	public List<ExpenseAccount> sumAllExpense(ExpenseAccount expenseAccount);

	public List<ExpenseAccount> subgridlist(ExpenseAccount expenseAccount);
	/**
	 * 发放金额
	 */
	public void assignByKey(ExpenseAccount expenseAccount);

	public ExpenseAccount getMyExpense(ExpenseAccount expenseAccount);

	public List<ExpenseAccount> staticExpenseAccount(ExpenseAccount expenseAccount);
	
	
}