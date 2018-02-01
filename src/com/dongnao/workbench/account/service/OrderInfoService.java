package com.dongnao.workbench.account.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.student.model.Statistical;
import com.dongnao.workbench.account.model.OrderInfo;

/**
 * 描述：订单信息模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-08-30
 */
public interface OrderInfoService  {

	/**
	 * 新增订单信息方法
	 * @param orderInfo OrderInfo:实体类
	 */
	public ResultMessage add(OrderInfo orderInfo);
	
	/**
	 * 删除订单信息方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找订单信息实体方法
	 * @param key String：实体主键
	 * @return orderInfo OrderInfo 实体对象
	 */
	public OrderInfo getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找订单信息列表方法
	 * @param orderInfo OrderInfo 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<OrderInfo> listByCondition(OrderInfo orderInfo);
	
	/**
	 * 修改订单信息方法
	 * @param orderInfo OrderInfo 实体对象
	 */	
	public ResultMessage update(OrderInfo orderInfo);

	public List<OrderInfo> sumOrder(OrderInfo orderInfo);

	public List<OrderInfo> subgridlist(OrderInfo orderInfo);
	/**
	 * 获得统计数据
	 * @return: 统计数据map
	 */
	public OrderInfo sumAllOrder(String month);
}