package com.dongnao.workbench.school.dao;

import java.util.List;
import com.dongnao.workbench.school.model.EmpPerformance;
import com.dongnao.workbench.school.model.PerformanceStiData;
import com.dongnao.workbench.school.model.RecentTwoMonthEmpPerf;
/**
 * 描述：员工绩效信息表模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-07-24
 */
public interface EmpPerformanceMapper  {

	/**
	 * 新增员工绩效信息表方法
	 * @param empPerformance EmpPerformance:实体类
	 */
	int add(EmpPerformance empPerformance);
	
	/**
	 * 删除员工绩效信息表方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工绩效信息表实体方法
	 * @param key String：实体主键（查询条件）
	 * @return EmpPerformance: 实体
	 */
	public EmpPerformance getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工绩效信息表列表方法
	 * @param EmpPerformance empPerformance：实体对象（查询条件）
	 * @return List<EmpPerformance>: 实体对象的list
	 */
	public List<EmpPerformance>  listByCondition(EmpPerformance empPerformance);
	
	/**
	 * 修改员工绩效信息表方法
	 * @param empPerformance EmpPerformance：实体对象
	 */	
	public void update(EmpPerformance empPerformance);

	
	/**
	 * 按员工统计信息
	 */
	public List<EmpPerformance> listByEmployee(EmpPerformance empPerformance);

	/**
	 * 员工奖金总额统计，按员工统计信息
	 */
	public List<EmpPerformance> listBonusByEmployee(EmpPerformance empPerformance);
	
	void deleteByStuId(EmpPerformance empPerformance);
	
	/**
	 * 清算员工绩效信息表方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	void settleByKey(String key);
	
	/**
	 * 获取某员工业绩
	 */
	public EmpPerformance getMyPerformance(EmpPerformance empPerformance);
	
	/**
	 * 查询最近两个月每个员工的不同岗位（转化、推广、讲师授课、客服等）的营收总额
	 */
	public List<RecentTwoMonthEmpPerf> recentTwoMonthEmpRevenue(EmpPerformance str);



	/**
	 * @param key
	 */
	void deleteNewByKey(String key);

	
	/*每个月月初更新上一个月的业绩到业绩统计表*/
	public void updatePerformance(List<PerformanceStiData> performanceStiData);
	
	/*按条件查询业绩统计数据*/
	public List<PerformanceStiData>  perSticlistByCondition(PerformanceStiData performanceStiData);
	
	/*更新实际获得的奖金金额（贡献绩效小于成本时实际获得奖金金额为原来的三分之一）*/
	public void updateActualBonus(PerformanceStiData performanceStiData);

	/*获取讲师成本&业绩*/
	public List<EmpPerformance> listEmpBonusCost(EmpPerformance empPerformance);
}