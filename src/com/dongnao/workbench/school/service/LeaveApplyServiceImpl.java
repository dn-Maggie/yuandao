package com.dongnao.workbench.school.service;
import javax.annotation.Resource;

import java.util.Date;
import java.util.List;
import com.dongnao.workbench.school.dao.LeaveApplyMapper;
import com.dongnao.workbench.school.model.LeaveApply;
import com.dongnao.workbench.school.service.LeaveApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.util.AjaxUtils;
 
/**
 * 描述：员工请假管理模块service接口实现类，实现service接口方法
 *
 * @author maggie
 * @version 1.0 2016-09-12
 */
@Service("leaveApplyService")
public class LeaveApplyServiceImpl implements LeaveApplyService{
        @Resource
	private LeaveApplyMapper leaveApplyMapper;
	
 
	/**
	 * 新增员工请假管理方法
	 * @param leaveApply:实体类
	 */	
	public ResultMessage add(LeaveApply leaveApply){
		leaveApplyMapper.add(leaveApply);
		return AjaxUtils.getSuccessMessage();
	}
	
	/**
	 * 根据主键查找员工请假管理实体方法
	 * @param key String 实体主键
	 * @return LeaveApply 实体对象
	 */
	public LeaveApply getByPrimaryKey(String key){
		return leaveApplyMapper.getByPrimaryKey(key);
	}
	
	/**
	 * 删除员工请假管理方法
	 * @param key String 多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key){
		leaveApplyMapper.deleteByKey(key);
	}
	
	/**
	 * 根据条件查找员工请假管理列表方法
	 * @param leaveApply LeaveApply 实体对象（查询条件）
	 * @return List<LeaveApply> 实体对象的list
	 */
	public List<LeaveApply> listByCondition(LeaveApply leaveApply){
		return leaveApplyMapper.listByCondition(leaveApply);
	}
	
	/**
	 * 修改员工请假管理方法
	 * @param leaveApply LeaveApply 实体对象
	 */	
	public ResultMessage update(LeaveApply leaveApply){
		leaveApplyMapper.update(leaveApply);
		return AjaxUtils.getSuccessMessage();
	}

	public List<LeaveApply> sumAllLeave(LeaveApply leaveApply) {
		return leaveApplyMapper.sumAllLeave(leaveApply);
	}

	public List<LeaveApply> subgridlist(LeaveApply entity, Date startDate, Date endDate) {
		entity.setEndDate(endDate);
		entity.setStartDate(startDate);
		return leaveApplyMapper.subgridlist(entity);
	}

	/**
	 * 获取所有直接负责人列表
	 * @param leaveApply LeaveApply 实体对象
	 */	
	public List<LeaveApply> getStraightLeader() {
		return leaveApplyMapper.getStraightLeader();
	}
}