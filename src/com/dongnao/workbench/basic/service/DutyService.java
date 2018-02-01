package com.dongnao.workbench.basic.service;

import java.util.List;

import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.basic.model.DutyLevel;
import com.dongnao.workbench.common.bean.ResultMessage;


/**
 * 描述：岗位信息模块service接口，提供controller操作所需方法
 *
 * @author yao.su
 * @version 1.0 2016-03-13
 */
public interface DutyService  {

	/**
	 * 新增岗位信息方法
	 * @param duty Duty:实体类
	 * @return 
	 */
	public ResultMessage add(Duty duty);
	
	/**
	 * 删除岗位信息方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找岗位信息实体方法
	 * @param key String：实体主键
	 * @return duty Duty 实体对象
	 */
	public Duty getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找岗位信息列表方法
	 * @param duty Duty 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<Duty> listByCondition(Duty duty);
	
	/**
	 * 修改岗位信息方法
	 * @param duty Duty 实体对象
	 */	
	public void update(Duty duty);
	
	/**
	 * 初始化岗位树
	 * @param duty
	 * @return
	 */
	public String initDutyTree(Duty duty);

}