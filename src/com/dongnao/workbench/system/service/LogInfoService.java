package com.dongnao.workbench.system.service;

import java.util.List;

import com.dongnao.workbench.system.model.LogInfo;

/**
 * 描述：日志管理(logInfo)模块service接口，提供controller操作所需方法
 * @author wff
 * @version 1.0  2016-03-20
 */
public interface LogInfoService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.system.service.LogInfoService";
	/**
	 * 新增方法
	 * @param logInfo 实体类
	 */
	void add(LogInfo logInfo);
	
	/**
	 * 删除方法
	 * @param id 多个由“，”分割开的id字符串
	 */
	void deleteByKey(String id);
	
	/**
	 * 根据主键查找实体方法
	 * @param id 实体主键
	 * @return 实体对象
	 */
	LogInfo getByPrimaryKey(String id);
	
	/**
	 * 根据条件查找列表方法
	 * @param logInfo 实体对象（查询条件）
	 * @return 实体对象的list
	 */
	List<LogInfo> listByCondition(LogInfo logInfo);
	
	/**
	 * 修改方法
	 * @param logInfo 实体对象
	 */	
	void update(LogInfo logInfo);
}