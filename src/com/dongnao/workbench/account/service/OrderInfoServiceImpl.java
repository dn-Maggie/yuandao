package com.dongnao.workbench.account.service;
import javax.annotation.Resource;
import java.util.List;
import com.dongnao.workbench.account.dao.OrderInfoMapper;
import com.dongnao.workbench.account.model.OrderInfo;
import com.dongnao.workbench.account.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.student.model.Statistical;
 
/**
 * 描述：订单信息模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-08-30
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService{
        @Resource
	private OrderInfoMapper orderInfoMapper;
	
 
	/**
	 * 新增订单信息方法
	 * @param orderInfo:实体类
	 */	
	public ResultMessage add(OrderInfo orderInfo){
		orderInfoMapper.add(orderInfo);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找订单信息实体方法
	 * @param key String 实体主键
	 * @return OrderInfo 实体对象
	 */
	public OrderInfo getByPrimaryKey(String key){
		return orderInfoMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除订单信息方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		orderInfoMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找订单信息列表方法
	 * @param orderInfo OrderInfo 实体对象（查询条件）
	 * @return List<OrderInfo> 实体对象的list
	 */
	public List<OrderInfo> listByCondition(OrderInfo orderInfo){
		return orderInfoMapper.listByCondition(orderInfo);
	}
	
	/**
	 * 修改订单信息方法
	 * @param orderInfo OrderInfo 实体对象
	 */	
	public ResultMessage update(OrderInfo orderInfo){
		orderInfoMapper.update(orderInfo);
		return AjaxUtils.getSuccessMessage();
	}

	/**
	 * 业务列表展示
	 * @return: 统计数据map
	 */
	public List<OrderInfo> sumOrder(OrderInfo orderInfo) {
		return orderInfoMapper.sumOrder(orderInfo);
	}

	/**
	 * 子列表展示
	 * @return: 统计数据map
	 */
	public List<OrderInfo> subgridlist(OrderInfo orderInfo) {
		return orderInfoMapper.subgridlist(orderInfo);
	}

	/**
	 * 获得统计数据
	 * @return: 统计数据map
	 */
	public OrderInfo sumAllOrder(String month){
		return orderInfoMapper.sumAllOrder(month);
	}
}