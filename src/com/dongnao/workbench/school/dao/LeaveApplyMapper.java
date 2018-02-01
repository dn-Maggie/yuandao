package com.dongnao.workbench.school.dao;

import java.util.List;
import com.dongnao.workbench.school.model.LeaveApply;
/**
 * 描述：员工请假管理模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-09-12
 */
public interface LeaveApplyMapper  {

	/**
	 * 新增员工请假管理方法
	 * @param leaveApply LeaveApply:实体类
	 */
	void add(LeaveApply leaveApply);
	
	/**
	 * 删除员工请假管理方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工请假管理实体方法
	 * @param key String：实体主键（查询条件）
	 * @return LeaveApply: 实体
	 */
	public LeaveApply getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工请假管理列表方法
	 * @param LeaveApply leaveApply：实体对象（查询条件）
	 * @return List<LeaveApply>: 实体对象的list
	 */
	public List<LeaveApply>  listByCondition(LeaveApply leaveApply);
	
	/**
	 * 修改员工请假管理方法
	 * @param leaveApply LeaveApply：实体对象
	 */	
	public void update(LeaveApply leaveApply);

	public List<LeaveApply> subgridlist(LeaveApply entity);

	public List<LeaveApply> sumAllLeave(LeaveApply leaveApply);
	/**
	 * 获取所有直接负责人列表
	 * @param leaveApply LeaveApply 实体对象
	 */	
	public List<LeaveApply> getStraightLeader();
}