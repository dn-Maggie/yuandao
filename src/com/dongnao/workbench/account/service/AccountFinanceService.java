package com.dongnao.workbench.account.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.account.model.AccountFinance;

/**
 * 描述：财务科目模块service接口，提供controller操作所需方法
 *
 * @author cjw
 * @version 1.0 2016-05-01
 */
public interface AccountFinanceService  {

	/**
	 * 新增财务科目方法
	 * @param accountFinance AccountFinance:实体类
	 */
	public ResultMessage add(AccountFinance accountFinance);
	
	/**
	 * 删除财务科目方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找财务科目实体方法
	 * @param key String：实体主键
	 * @return accountFinance AccountFinance 实体对象
	 */
	public AccountFinance getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找财务科目列表方法
	 * @param accountFinance AccountFinance 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<AccountFinance> listByCondition(AccountFinance accountFinance);
	
	/**
	 * 修改财务科目方法
	 * @param accountFinance AccountFinance 实体对象
	 */	
	public ResultMessage update(AccountFinance accountFinance);
}