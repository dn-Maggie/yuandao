package com.dongnao.workbench.student.service;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.student.dao.VipRefundMapper;
import com.dongnao.workbench.student.model.VipRefund;
 
/**
 * 描述：VIP学员退款信息模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-08-01
 */
@Service("vipRefundService")
public class VipRefundServiceImpl implements VipRefundService{
        @Resource
	private VipRefundMapper vipRefundMapper;
	
 
	/**
	 * 新增VIP学员退款信息方法
	 * @param vipRefund:实体类
	 */	
	public ResultMessage add(VipRefund vipRefund){
		vipRefundMapper.add(vipRefund);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找VIP学员退款信息实体方法
	 * @param key String 实体主键
	 * @return VipRefund 实体对象
	 */
	public VipRefund getByPrimaryKey(String key){
		return vipRefundMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除VIP学员退款信息方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		vipRefundMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找VIP学员退款信息列表方法
	 * @param vipRefund VipRefund 实体对象（查询条件）
	 * @return List<VipRefund> 实体对象的list
	 */
	public List<VipRefund> listByCondition(VipRefund vipRefund){
		return vipRefundMapper.listByCondition(vipRefund);
	}
	
	/**
	 * 修改VIP学员退款信息方法
	 * @param vipRefund VipRefund 实体对象
	 */	
	public ResultMessage update(VipRefund vipRefund){
		vipRefundMapper.update(vipRefund);
		return AjaxUtils.getSuccessMessage();
	}

	public List<VipRefund> getStatistical(VipRefund vipRefund) {
		return vipRefundMapper.getStatistical(vipRefund);
	}
}