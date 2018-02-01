package com.dongnao.workbench.student.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.student.model.MarketStudent;

/**
 * 描述：录入学员信息模块service接口，提供controller操作所需方法
 *
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
public interface MarketStudentService  {

	/**
	 * 新增录入学员信息方法
	 * @param marketStudent MarketStudent:实体类
	 */
	public ResultMessage add(MarketStudent marketStudent);
	
	/**
	 * 删除录入学员信息方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找录入学员信息实体方法
	 * @param key String：实体主键
	 * @return marketStudent MarketStudent 实体对象
	 */
	public MarketStudent getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找录入学员信息列表方法
	 * @param marketStudent MarketStudent 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<MarketStudent> listByCondition(MarketStudent marketStudent);
	
	/**
	 * 修改录入学员信息方法
	 * @param marketStudent MarketStudent 实体对象
	 */	
	public ResultMessage update(MarketStudent marketStudent);

	public MarketStudent getMarkStuCount(String month);
	
	/**
	 * 获取录入信息
	 * @param marketStudent MarketStudent 实体对象
	 */	
	public List<MarketStudent> getMarketStatistic(MarketStudent ms);
	
	
}