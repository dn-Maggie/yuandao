package com.dongnao.workbench.account.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.account.model.AccountSubject;

/**
 * 描述：会计科目信息模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-08-10
 */
public interface AccountSubjectService  {

	/**
	 * 新增会计科目信息方法
	 * @param accountSubject AccountSubject:实体类
	 */
	public ResultMessage add(AccountSubject accountSubject);
	
	/**
	 * 删除会计科目信息方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找会计科目信息实体方法
	 * @param key String：实体主键
	 * @return accountSubject AccountSubject 实体对象
	 */
	public AccountSubject getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找会计科目信息列表方法
	 * @param accountSubject AccountSubject 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<AccountSubject> listByCondition(AccountSubject accountSubject);
	
	/**
	 * 修改会计科目信息方法
	 * @param accountSubject AccountSubject 实体对象
	 */	
	public ResultMessage update(AccountSubject accountSubject);
}