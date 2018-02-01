package com.dongnao.workbench.school.service;

import java.util.Date;
import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.LeaveApply;

/**
 * 描述：员工请假管理模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-09-12
 */
public interface LeaveApplyService  {

	/**
	 * 新增员工请假管理方法
	 * @param leaveApply LeaveApply:实体类
	 */
	public ResultMessage add(LeaveApply leaveApply);
	
	/**
	 * 删除员工请假管理方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工请假管理实体方法
	 * @param key String：实体主键
	 * @return leaveApply LeaveApply 实体对象
	 */
	public LeaveApply getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工请假管理列表方法
	 * @param leaveApply LeaveApply 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<LeaveApply> listByCondition(LeaveApply leaveApply);
	
	/**
	 * 修改员工请假管理方法
	 * @param leaveApply LeaveApply 实体对象
	 */	
	public ResultMessage update(LeaveApply leaveApply);

	public List<LeaveApply> sumAllLeave(LeaveApply leaveApply);

	public List<LeaveApply> subgridlist(LeaveApply entity, Date startDate, Date endDate);
	/**
	 * 获取所有直接负责人列表
	 * @param leaveApply LeaveApply 实体对象
	 */	
	public List<LeaveApply> getStraightLeader();
}