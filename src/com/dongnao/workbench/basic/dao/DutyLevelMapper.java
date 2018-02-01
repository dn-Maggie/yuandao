package com.dongnao.workbench.basic.dao;

import java.util.List;
import com.dongnao.workbench.basic.model.DutyLevel;
/**
 * 描述：岗位级别表模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-12-20
 */
public interface DutyLevelMapper  {

	/**
	 * 新增岗位级别表方法
	 * @param dutyLevel DutyLevel:实体类
	 */
	void add(DutyLevel dutyLevel);
	
	/**
	 * 删除岗位级别表方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找岗位级别表实体方法
	 * @param key String：实体主键（查询条件）
	 * @return DutyLevel: 实体
	 */
	public DutyLevel getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找岗位级别表列表方法
	 * @param DutyLevel dutyLevel：实体对象（查询条件）
	 * @return List<DutyLevel>: 实体对象的list
	 */
	public List<DutyLevel>  listByCondition(DutyLevel dutyLevel);
	
	/**
	 * 修改岗位级别表方法
	 * @param dutyLevel DutyLevel：实体对象
	 */	
	public void update(DutyLevel dutyLevel);
}