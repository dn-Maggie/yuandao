package com.dongnao.workbench.account.service;
import javax.annotation.Resource;

import java.util.List;

import com.dongnao.workbench.account.dao.AccountFlowMapper;
import com.dongnao.workbench.account.model.AccountFlow;
import com.dongnao.workbench.account.model.FinStatements;
import com.dongnao.workbench.account.service.AccountFlowService;
import com.dongnao.workbench.basic.service.SubjectService;

import org.springframework.stereotype.Service;

import com.dongnao.workbench.common.bean.ReportQuerycondition;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.bean.ResultMoney;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.school.model.RecentlyThirtyDayData;
import com.dongnao.workbench.school.model.RecentlyThreeMonthData;
 
/**
 * 描述：财务流水模块service接口实现类，实现service接口方法
 *
 * @author cjw
 * @version 1.0 2016-05-01
 */
@Service("accountFlowService")
public class AccountFlowServiceImpl implements AccountFlowService{
        @Resource
	private AccountFlowMapper accountFlowMapper;
	
 
	/**
	 * 新增财务流水方法
	 * @param accountFlow:实体类
	 */	
	public ResultMessage add(AccountFlow accountFlow){
		accountFlowMapper.add(accountFlow);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找财务流水实体方法
	 * @param key String 实体主键
	 * @return AccountFlow 实体对象
	 */
	public AccountFlow getByPrimaryKey(String key){
		return accountFlowMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除财务流水方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		accountFlowMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找财务流水列表方法
	 * @param accountFlow AccountFlow 实体对象（查询条件）
	 * @return List<AccountFlow> 实体对象的list
	 */
	public List<AccountFlow> listByCondition(AccountFlow accountFlow){
		return accountFlowMapper.listByCondition(accountFlow);
	}
	
	/**
	 * 修改财务流水方法
	 * @param accountFlow AccountFlow 实体对象
	 */	
	public ResultMessage update(AccountFlow accountFlow){
		accountFlowMapper.update(accountFlow);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据月份统计财务流水列表方法
	 * @param String month（查询条件）
	 * @return: 实体对象的list
	 */
	public ResultMoney getCountMoney(AccountFlow accountFlow) {
		return accountFlowMapper.countMoney(accountFlow);
	}
	
	/**
	 * 根据月份查找与学费相关的科目名称的财务流水列表方法
	 * @param String month（查询条件）
	 * @return: 实体对象的list
	 */
	public ResultMoney getXFMoney(AccountFlow accountFlow) {
		return accountFlowMapper.countXF(accountFlow);
	}

	/**
	 * 根据月份查找每个科目名称的财务流水列表方法
	 * @param String month（查询条件）
	 * @return: 实体对象的list
	 */
	public List<AccountFlow> listByAccountName(String month) {
		return accountFlowMapper.listByAccountName(month);
	}

	/**
	 * 根据月份列出每个月财务流水消费支出列表方法
	 * @param accountFlow AccountFlow 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<AccountFlow> listCostByCondition(AccountFlow accountFlow) {
		return accountFlowMapper.listCostByCondition(accountFlow);
	}
	
	/**
	 * 统计图数据
	 * @param String month（查询条件）
	 * @return: 实体对象的list
	 */
	public ResultMoney getBarStatistic(AccountFlow accountFlow) {
		return accountFlowMapper.getBarStatistic(accountFlow);
	}

	/**
	 * 根据条件查询总业绩方法
	 * @param ReportQuerycondition rqc 实体对象（查询条件）
	 * @return List<DeptReport>: 实体对象的DeptReport
	 */
	public List<FinStatements> reportlistByzyj(ReportQuerycondition rqc) {
		return accountFlowMapper.reportlistByzyj(rqc);
	}

	public void timedupdatecostprofitr(List<ReportQuerycondition> rqc) {
		accountFlowMapper.timedupdatecostprofitr(rqc);
	}

	public void timedupdatecostprofitc(List<ReportQuerycondition> rqc) {
		accountFlowMapper.timedupdatecostprofitc(rqc);
	}

	public void timedupdatecostprofit(ReportQuerycondition rqc) {
		accountFlowMapper.timedupdatecostprofitdelete(rqc);
	}

	public List<RecentlyThirtyDayData> getRecentlyThirtyDayData(String subjectName) {
		return accountFlowMapper.getRecentlyThirtyDayData(subjectName);
	}

	public List<RecentlyThreeMonthData> getRecentlyThreeMonthData(RecentlyThreeMonthData rt) {
		return accountFlowMapper.getRecentlyThreeMonthData(rt);
	}

}