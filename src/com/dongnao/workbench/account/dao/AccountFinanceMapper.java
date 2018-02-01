package com.dongnao.workbench.account.dao;

import java.util.List;
import com.dongnao.workbench.account.model.AccountFinance;
/**
 * 描述：财务科目模块dao接口，提供数据库操作方法
 *
 * @author cjw
 * @version 1.0 2016-05-01
 */
public interface AccountFinanceMapper  {

	/**
	 * 新增财务科目方法
	 * @param accountFinance AccountFinance:实体类
	 */
	void add(AccountFinance accountFinance);
	
	/**
	 * 删除财务科目方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找财务科目实体方法
	 * @param key String：实体主键（查询条件）
	 * @return AccountFinance: 实体
	 */
	public AccountFinance getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找财务科目列表方法
	 * @param AccountFinance accountFinance：实体对象（查询条件）
	 * @return List<AccountFinance>: 实体对象的list
	 */
	public List<AccountFinance>  listByCondition(AccountFinance accountFinance);
	
	/**
	 * 修改财务科目方法
	 * @param accountFinance AccountFinance：实体对象
	 */	
	public void update(AccountFinance accountFinance);
}