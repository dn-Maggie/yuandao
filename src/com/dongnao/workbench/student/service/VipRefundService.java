package com.dongnao.workbench.student.service;

import java.util.List;

import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.student.model.VipRefund;

/**
 * 描述：VIP学员退款信息模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-08-01
 */
public interface VipRefundService  {

	/**
	 * 新增VIP学员退款信息方法
	 * @param vipRefund VipRefund:实体类
	 */
	public ResultMessage add(VipRefund vipRefund);
	
	/**
	 * 删除VIP学员退款信息方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找VIP学员退款信息实体方法
	 * @param key String：实体主键
	 * @return vipRefund VipRefund 实体对象
	 */
	public VipRefund getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找VIP学员退款信息列表方法
	 * @param vipRefund VipRefund 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<VipRefund> listByCondition(VipRefund vipRefund);
	
	/**
	 * 修改VIP学员退款信息方法
	 * @param vipRefund VipRefund 实体对象
	 */	
	public ResultMessage update(VipRefund vipRefund);

	public List<VipRefund> getStatistical(VipRefund vipRefund);
}