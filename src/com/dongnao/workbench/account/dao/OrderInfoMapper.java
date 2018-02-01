package com.dongnao.workbench.account.dao;

import java.util.List;
import com.dongnao.workbench.account.model.OrderInfo;
import com.dongnao.workbench.student.model.Statistical;
/**
 * 描述：订单信息模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-08-30
 */
public interface OrderInfoMapper  {

	/**
	 * 新增订单信息方法
	 * @param orderInfo OrderInfo:实体类
	 */
	void add(OrderInfo orderInfo);
	
	/**
	 * 删除订单信息方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找订单信息实体方法
	 * @param key String：实体主键（查询条件）
	 * @return OrderInfo: 实体
	 */
	public OrderInfo getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找订单信息列表方法
	 * @param OrderInfo orderInfo：实体对象（查询条件）
	 * @return List<OrderInfo>: 实体对象的list
	 */
	public List<OrderInfo>  listByCondition(OrderInfo orderInfo);
	
	/**
	 * 修改订单信息方法
	 * @param orderInfo OrderInfo：实体对象
	 */	
	public void update(OrderInfo orderInfo);

	public List<OrderInfo> sumOrder(OrderInfo orderInfo);

	public List<OrderInfo> subgridlist(OrderInfo orderInfo);

	public OrderInfo sumAllOrder(String month);
}