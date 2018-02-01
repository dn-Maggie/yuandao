package com.dongnao.workbench.basic.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.basic.model.DutyLevel;

/**
 * 描述：岗位级别表模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-12-20
 */
public interface DutyLevelService  {

	/**
	 * 新增岗位级别表方法
	 * @param dutyLevel DutyLevel:实体类
	 */
	public ResultMessage add(DutyLevel dutyLevel);
	
	/**
	 * 删除岗位级别表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找岗位级别表实体方法
	 * @param key String：实体主键
	 * @return dutyLevel DutyLevel 实体对象
	 */
	public DutyLevel getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找岗位级别表列表方法
	 * @param dutyLevel DutyLevel 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<DutyLevel> listByCondition(DutyLevel dutyLevel);
	
	/**
	 * 修改岗位级别表方法
	 * @param dutyLevel DutyLevel 实体对象
	 */	
	public ResultMessage update(DutyLevel dutyLevel);
}