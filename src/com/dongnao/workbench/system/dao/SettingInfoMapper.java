package com.dongnao.workbench.system.dao;

import java.util.List;
import java.util.Map;

import com.dongnao.workbench.system.model.SettingInfo;
/**
 * 描述：settingInfo模块dao接口，提供数据库操作方法
 * @author wff
 * @version 1.0  2013-11-21
 */
public interface SettingInfoMapper  {
	
	/**
	 * 新增方法
	 * @param settingInfo SettingInfo:实体类
	 */
	void add(SettingInfo settingInfo);
	
	/**
	 * 删除方法
	 * @param id String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String id);
	
	/**
	 * 根据主键查找实体方法
	 * @param id String：实体主键（查询条件）
	 * @return SettingInfo: 实体
	 */
	SettingInfo getByPrimaryKey(String id);
	
	/**
	 * 根据条件查找列表方法
	 * @param settingInfo 实体对象（查询条件）
	 * @return List<SettingInfo>: 实体对象的list
	 */
	List<SettingInfo> listByCondition(SettingInfo settingInfo);
	
	/**
	 * 修改方法
	 * @param settingInfo SettingInfo：实体对象
	 */	
	void update(SettingInfo settingInfo);
	
	/**
	 * 新增操作时，判断功能名称是否已存在
	 * @param variableName 功能名称
	 * @return List<SettingInfo> 实体对象list
	 */
	List<SettingInfo> getCheckByName(String variableName);
	
	/**
	 * 用户自定义session失效时间
	 * @param map 存放用户id和session失效值
	 */
	void updateSessionValueByName(Map<String, String> map);
	
	/**
	 * 用户第一次设置session时间时，新增一条记录
	 * @param sessionMap 存放用户id和session值以及主键id(和表结构对应)
	 */
	void addSessionInfo(Map<String, String> sessionMap);
	
	/**
	 * 获取系统配置
	 * @return List<SettingInfo>
	 */
	List<SettingInfo> getSettingValue();
	
	/**
	 * 根据id获取sessiob时间
	 * @param userId 用户id
	 * @return String session值
	 */
	String getSessionValueByUserId(String userId);
}