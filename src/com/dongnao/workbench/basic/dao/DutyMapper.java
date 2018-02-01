package com.dongnao.workbench.basic.dao;

import java.util.List;

import com.dongnao.workbench.basic.model.Duty;
import com.dongnao.workbench.basic.model.DutyLevel;
/**
 * 描述：岗位信息模块dao接口，提供数据库操作方法
 *
 * @author yao.su
 * @version 1.0 2016-03-13
 */
public interface DutyMapper  {

	/**
	 * 新增岗位信息方法
	 * @param duty Duty:实体类
	 */
	void add(Duty duty);
	
	/**
	 * 删除岗位信息方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找岗位信息实体方法
	 * @param key String：实体主键（查询条件）
	 * @return Duty: 实体
	 */
	public Duty getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找岗位信息列表方法
	 * @param Duty duty：实体对象（查询条件）
	 * @return List<Duty>: 实体对象的list
	 */
	public List<Duty>  listByCondition(Duty duty);
	
	/**
	 * 修改岗位信息方法
	 * @param duty Duty：实体对象
	 */	
	public void update(Duty duty);
	/**
	 * 按岗位id展开岗位级别
	 * @param dutylevel
	 * @return
	 */
	public List<DutyLevel> listDutyLevel(DutyLevel dutyLevel);
}