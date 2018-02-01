package com.dongnao.workbench.account.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongnao.workbench.account.dao.ExpenseAccountMapper;
import com.dongnao.workbench.account.model.ExpenseAccount;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：报销单模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-08-18
 */
@Service("expenseAccountService")
public class ExpenseAccountServiceImpl implements ExpenseAccountService{
        @Resource
	private ExpenseAccountMapper expenseAccountMapper;
	
 
	/**
	 * 新增报销单方法
	 * @param expenseAccount:实体类
	 */	
	public ResultMessage add(ExpenseAccount expenseAccount){
		expenseAccountMapper.add(expenseAccount);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找报销单实体方法
	 * @param key String 实体主键
	 * @return ExpenseAccount 实体对象
	 */
	public ExpenseAccount getByPrimaryKey(String key){
		return expenseAccountMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除报销单方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		expenseAccountMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找报销单列表方法
	 * @param expenseAccount ExpenseAccount 实体对象（查询条件）
	 * @return List<ExpenseAccount> 实体对象的list
	 */
	public List<ExpenseAccount> listByCondition(ExpenseAccount expenseAccount){
		return expenseAccountMapper.listByCondition(expenseAccount);
	}
	
	/**
	 * 修改报销单方法
	 * @param expenseAccount ExpenseAccount 实体对象
	 */	
	public ResultMessage update(ExpenseAccount expenseAccount){
		expenseAccountMapper.update(expenseAccount);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 批量审核报销单
	 * @param expenseAccount ExpenseAccount：实体对象
	 */
	public void auditByKey(ExpenseAccount expenseAccount) {
		expenseAccountMapper.auditByKey(expenseAccount);
	}

	/**
	 * 按员工统计信息
	 */
	public List<ExpenseAccount> listByEmployee(ExpenseAccount expenseAccount) {
		// TODO Auto-generated method stub
		return expenseAccountMapper.listByEmployee(expenseAccount);
	}

	/**
	 * 业务统计列表展示
	 * @return: 统计数据map
	 */
	public List<ExpenseAccount> sumAllExpense(ExpenseAccount expenseAccount) {
		return expenseAccountMapper.sumAllExpense(expenseAccount);
	}

	/**
	 * 子列表展示
	 * @return: 统计数据map
	 */
	public List<ExpenseAccount> subgridlist(ExpenseAccount expenseAccount,String startDate,String endDate) {
		 expenseAccount.setStartDate(startDate);
		 expenseAccount.setEndDate(endDate);
		 return expenseAccountMapper.subgridlist(expenseAccount);
	}

	/**
	 * 发放金额
	 */
	public void assignByKey(ExpenseAccount expenseAccount) {
		expenseAccountMapper.assignByKey(expenseAccount);
	}

	/**
	 * 获取某员工的报销数据
	 */
	public ExpenseAccount getMyExpense(String userId,String startDate) {
		ExpenseAccount expenseAccount = new ExpenseAccount();
		expenseAccount.setEnterPid(userId);
		expenseAccount.setStartDate(startDate);
		return expenseAccountMapper.getMyExpense(expenseAccount);
	}

	public List<ExpenseAccount> getStatistical(ExpenseAccount expenseAccount) {
		return expenseAccountMapper.staticExpenseAccount(expenseAccount);
	}
}