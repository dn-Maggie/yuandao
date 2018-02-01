package com.dongnao.workbench.student.dao;

import java.util.List;

import com.dongnao.workbench.student.model.ContinuePay;
import com.dongnao.workbench.student.model.StatisticalCP;
/**
 * 描述：补款管理模块dao接口，提供数据库操作方法
 *
 * @author yang.liu
 * @version 1.0 2016-05-02
 */
public interface ContinuePayMapper  {

	/**
	 * 新增补款管理方法
	 * @param continuePay ContinuePay:实体类
	 */
	void add(ContinuePay continuePay);
	
	/**
	 * 删除补款管理方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找补款管理实体方法
	 * @param key String：实体主键（查询条件）
	 * @return ContinuePay: 实体
	 */
	public ContinuePay getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找补款管理列表方法
	 * @param ContinuePay continuePay：实体对象（查询条件）
	 * @return List<ContinuePay>: 实体对象的list
	 */
	public List<ContinuePay>  listByCondition(ContinuePay continuePay);
	
	/**
	 * 修改补款管理方法
	 * @param continuePay ContinuePay：实体对象
	 */	
	public void update(ContinuePay continuePay);
	
	/**
	 * 查询所有补款学员方法
	 * @param ContinuePay continuePay：实体对象（查询条件）
	 * @return List<ContinuePay>: 实体对象的list
	 */
	public List<ContinuePay>  listAllContinuePayStu();

	/**
	 * 删除退款学生补款记录
	 * @return: 统计数据map
	 */
	public void deleteByStu(ContinuePay continuepay);

	public List<ContinuePay> getStatistical(ContinuePay continuePay);
}