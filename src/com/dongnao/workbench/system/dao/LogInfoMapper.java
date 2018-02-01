package com.dongnao.workbench.system.dao;

import java.util.List;

import com.dongnao.workbench.system.model.LogInfo;
/**
 * 描述：logInfo模块dao接口，提供数据库操作方法
 * @author wff
 * @version 1.0  2016-03-20
 */
public interface LogInfoMapper  {
	/**
	 * 新增方法
	 * @param logInfo LogInfo:实体类
	 */
	void add(LogInfo logInfo);
	
	/**
	 * 删除方法
	 * @param id String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String id);
	
	/**
	 * 根据主键查找实体方法
	 * @param id String：实体主键（查询条件）
	 * @return LogInfo: 实体
	 */
	LogInfo getByPrimaryKey(String id);
	
	/**
	 * 根据条件查找列表方法
	 * @param logInfo logInfo：实体对象（查询条件）
	 * @return List<LogInfo>: 实体对象的list
	 */
	List<LogInfo>  listByCondition(LogInfo logInfo);
	
	/**
	 * 修改方法
	 * @param logInfo LogInfo：实体对象
	 */	
	void update(LogInfo logInfo);
}