package com.dongnao.workbench.school.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.school.dao.EmpSalaryMapper;
import com.dongnao.workbench.school.model.EmpSalary;
import com.dongnao.workbench.school.model.EmpSocialSecurity;
import com.dongnao.workbench.school.service.EmpSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnao.workbench.account.model.ExpenseAccount;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：员工工资表模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-12-14
 */
@Service("empSalaryService")
public class EmpSalaryServiceImpl implements EmpSalaryService{
        @Resource
	private EmpSalaryMapper empSalaryMapper;
	
 
	/**
	 * 新增员工工资表方法
	 * @param empSalary:实体类
	 */	
	public ResultMessage add(EmpSalary empSalary){
		empSalaryMapper.add(empSalary);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找员工工资表实体方法
	 * @param key String 实体主键
	 * @return EmpSalary 实体对象
	 */
	public EmpSalary getByPrimaryKey(String key){
		return empSalaryMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除员工工资表方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		empSalaryMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找员工工资表列表方法
	 * @param empSalary EmpSalary 实体对象（查询条件）
	 * @return List<EmpSalary> 实体对象的list
	 */
	public List<EmpSalary> listByCondition(EmpSalary empSalary){
		return empSalaryMapper.listByCondition(empSalary);
	}
	
	public List<EmpSalary> listEmpSocialSecurity(EmpSalary empSalary){
		return empSalaryMapper.listEmpSocialSecurity(empSalary);
	}
	
	/**
	 * 修改员工工资表方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage update(EmpSalary empSalary){
		empSalaryMapper.update(empSalary);
		return AjaxUtils.getSuccessMessage();
	}
	/**
	 * 按月份统计员工实发工资方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public EmpSalary getEmpSalaryStatistic(EmpSalary empSalary){
		return empSalaryMapper.getEmpSalaryStatistic(empSalary);
	}
	/**
	 * 自动生成当月员工工资条方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage autoAdd(EmpSalary empSalary){
		empSalaryMapper.autoAdd(empSalary);
		return AjaxUtils.getSuccessMessage();
	};
	/**
	 * 审核员工工资表方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage audit(EmpSalary empSalary){
		empSalaryMapper.audit(empSalary);
		return AjaxUtils.getSuccessMessage();
	};
	/**
	 * 发送员工工资表方法
	 * @param empSalary EmpSalary 实体对象
	 */	
	public ResultMessage send(EmpSalary empSalary) {
		empSalaryMapper.send(empSalary);
		return AjaxUtils.getSuccessMessage();
	}

	/**
	 * 已生成流水，删除词条工资记录
	 * @param empSalary EmpSalary 实体对象
	 */	
	@Override
	public ResultMessage assign(EmpSalary empSalary) {
		empSalaryMapper.assign(empSalary);
		return AjaxUtils.getSuccessMessage();
	}

	/* (non-Javadoc)
	 * @see com.dongnao.workbench.school.service.EmpSalaryService#addBatch(java.util.List)
	 */
	@Override
	public ResultMessage addBatch(List<EmpSalary> emp) {
		empSalaryMapper.addBatch(emp);
		return AjaxUtils.getSuccessMessage();
	}

	@Override
	public List<EmpSalary> listEmpCost(EmpSalary empSalary) {
		return empSalaryMapper.listEmpCost(empSalary);
	}

	@Override
	public ResultMessage updateEmpCost(EmpSalary empSalary) {
		empSalaryMapper.updateEmpCost(empSalary);
		return AjaxUtils.getSuccessMessage();
	}

	@Override
	public ResultMessage addEmpCost(EmpSalary empSalary) {
		empSalaryMapper.addEmpCost(empSalary);
		return AjaxUtils.getSuccessMessage();
	}

	@Override
	public void updateEmpCostBonus(EmpSalary empSalary) {
		empSalaryMapper.updateEmpCostBonus(empSalary);
	}
	

	
}