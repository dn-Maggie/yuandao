package com.dongnao.workbench.school.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.EmpPerformance;
import com.dongnao.workbench.school.model.RecentTwoMonthEmpPerf;

/**
 * 描述：员工绩效信息表模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-07-24
 */
public interface EmpPerformanceService  {

	/**
	 * 新增员工绩效信息表方法
	 * @param empPerformance EmpPerformance:实体类
	 */
	public ResultMessage add(EmpPerformance empPerformance);
	
	/**
	 * 删除员工绩效信息表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工绩效信息表实体方法
	 * @param key String：实体主键
	 * @return empPerformance EmpPerformance 实体对象
	 */
	public EmpPerformance getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工绩效信息表列表方法
	 * @param empPerformance EmpPerformance 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<EmpPerformance> listByCondition(EmpPerformance empPerformance);
	
	/**
	 * 修改员工绩效信息表方法
	 * @param empPerformance EmpPerformance 实体对象
	 */	
	public ResultMessage update(EmpPerformance empPerformance);

	
	/**
	 * 按员工统计信息
	 */
	public List<EmpPerformance> listByEmployee(EmpPerformance empPerformance);
	
	
	/**
	 * 员工奖金总额统计，按员工统计信息
	 */
	public List<EmpPerformance> listBonusByEmployee(EmpPerformance empPerformance);
	
	/**
	 * 根据stuid删除员工绩效信息表方法
	 * @param stuid:stuid的多个字符串
	 */
	public void deleteByStuId(EmpPerformance empPerformance);

	/**
	 * 清算员工绩效信息表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void settleByKey(String key);
	/**
	 * 获取某员工的业绩信息
	 * @param month 
	 * @param string 
	 */
	public EmpPerformance getMyPerformance(String userId, String startDate);
	
	/**
	 * 查询最近两个月每个员工的不同岗位（转化、推广、讲师授课、客服等）的营收总额
	 */
	public List<RecentTwoMonthEmpPerf> recentTwoMonthEmpRevenue(EmpPerformance str);

	/**
	 * 获取讲师成本&业绩，在讲师个人业绩页面展示
	 */
	public List<EmpPerformance> listEmpBonusCost(EmpPerformance empPerformance);
	
}