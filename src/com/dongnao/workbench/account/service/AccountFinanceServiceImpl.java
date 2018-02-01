package com.dongnao.workbench.account.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.account.dao.AccountFinanceMapper;
import com.dongnao.workbench.account.model.AccountFinance;
import com.dongnao.workbench.account.service.AccountFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：财务科目模块service接口实现类，实现service接口方法
 *
 * @author cjw
 * @version 1.0 2016-05-01
 */
@Service("accountFinanceService")
public class AccountFinanceServiceImpl implements AccountFinanceService{
        @Resource
	private AccountFinanceMapper accountFinanceMapper;
	
 
	/**
	 * 新增财务科目方法
	 * @param accountFinance:实体类
	 */	
	public ResultMessage add(AccountFinance accountFinance){
		accountFinanceMapper.add(accountFinance);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找财务科目实体方法
	 * @param key String 实体主键
	 * @return AccountFinance 实体对象
	 */
	public AccountFinance getByPrimaryKey(String key){
		return accountFinanceMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除财务科目方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		accountFinanceMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找财务科目列表方法
	 * @param accountFinance AccountFinance 实体对象（查询条件）
	 * @return List<AccountFinance> 实体对象的list
	 */
	public List<AccountFinance> listByCondition(AccountFinance accountFinance){
		return accountFinanceMapper.listByCondition(accountFinance);
	}
	
	/**
	 * 修改财务科目方法
	 * @param accountFinance AccountFinance 实体对象
	 */	
	public ResultMessage update(AccountFinance accountFinance){
		accountFinanceMapper.update(accountFinance);
		return AjaxUtils.getSuccessMessage();
	}
}