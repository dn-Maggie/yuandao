package com.dongnao.workbench.system.service;

import java.util.List;

import com.dongnao.workbench.system.model.SettingInfo;

/**
 * 描述：系统配置管理(settingInfo)模块service接口，提供controller操作所需方法
 * @author wff
 * @version 1.0  2013-11-21
 */
public interface SettingInfoService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.system.service.SettingInfoService";

	/**
	 * 新增方法
	 * @param settingInfo 实体类
	 */
	void add(SettingInfo settingInfo);
	
	/**
	 * 删除方法
	 * @param id 多个由“，”分割开的id字符串
	 */
	void deleteByKey(String id);
	
	/**
	 * 根据主键查找实体方法
	 * @param id 实体主键
	 * @return  实体对象
	 */
	SettingInfo getByPrimaryKey(String id);
	
	/**
	 * 根据条件查找列表方法
	 * @param settingInfo 实体对象（查询条件）
	 * @return 实体对象的list
	 */
	List<SettingInfo> listByCondition(SettingInfo settingInfo);
	
	/**
	 * 修改方法
	 * @param settingInfo 实体对象
	 */	
	void update(SettingInfo settingInfo);
	
	/**
	 * 新增操作时，判断功能名称是否已存在
	 * @param variableName 功能名称
	 * @return boolean
	 */
	boolean getCheckByName(String variableName);
	
	/**
	 * 获取系统配置
	 * @return List<SettingInfo>
	 */
	List<SettingInfo> getSettingValue();
	
	/**
	 * 用户自定义session失效时间
	 * @param sessionValue session值
	 * @param userId 用户id
	 */
	void updateSessionValueByName(String sessionValue, String userId);
	
	/**
	 * 根据用户id获取session时间
	 * @param userId 用户id
	 * @return String session值
	 */
	String getSessionValueByUserId(String userId);
}