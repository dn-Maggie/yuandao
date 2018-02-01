package com.dongnao.workbench.account.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.account.dao.AccountSubjectMapper;
import com.dongnao.workbench.account.model.AccountSubject;
import com.dongnao.workbench.account.service.AccountSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：会计科目信息模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-08-10
 */
@Service("accountSubjectService")
public class AccountSubjectServiceImpl implements AccountSubjectService{
        @Resource
	private AccountSubjectMapper accountSubjectMapper;
	
 
	/**
	 * 新增会计科目信息方法
	 * @param accountSubject:实体类
	 */	
	public ResultMessage add(AccountSubject accountSubject){
		accountSubjectMapper.add(accountSubject);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找会计科目信息实体方法
	 * @param key String 实体主键
	 * @return AccountSubject 实体对象
	 */
	public AccountSubject getByPrimaryKey(String key){
		return accountSubjectMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除会计科目信息方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		accountSubjectMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找会计科目信息列表方法
	 * @param accountSubject AccountSubject 实体对象（查询条件）
	 * @return List<AccountSubject> 实体对象的list
	 */
	public List<AccountSubject> listByCondition(AccountSubject accountSubject){
		return accountSubjectMapper.listByCondition(accountSubject);
	}
	
	/**
	 * 修改会计科目信息方法
	 * @param accountSubject AccountSubject 实体对象
	 */	
	public ResultMessage update(AccountSubject accountSubject){
		accountSubjectMapper.update(accountSubject);
		return AjaxUtils.getSuccessMessage();
	}
}