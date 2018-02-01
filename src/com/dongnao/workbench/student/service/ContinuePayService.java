package com.dongnao.workbench.student.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.student.model.ContinuePay;
import com.dongnao.workbench.student.model.StatisticalCP;

/**
 * 描述：补款管理模块service接口，提供controller操作所需方法
 *
 * @author yang.liu
 * @version 1.0 2016-05-02
 */
public interface ContinuePayService  {

	/**
	 * 新增补款管理方法
	 * @param continuePay ContinuePay:实体类
	 */
	public ResultMessage add(ContinuePay continuePay);
	
	/**
	 * 删除补款管理方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找补款管理实体方法
	 * @param key String：实体主键
	 * @return continuePay ContinuePay 实体对象
	 */
	public ContinuePay getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找补款管理列表方法
	 * @param continuePay ContinuePay 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<ContinuePay> listByCondition(ContinuePay continuePay);
	
	/**
	 * 修改补款管理方法
	 * @param continuePay ContinuePay 实体对象
	 */	
	public ResultMessage update(ContinuePay continuePay);

	/**
	 * 获得统计数据
	 * @return: 统计数据map
	 */
	public List<ContinuePay> getStatistical(ContinuePay continuePay);
	
	
}