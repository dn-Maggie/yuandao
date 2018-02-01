package com.dongnao.workbench.basic.dao;

import java.util.HashMap;
import java.util.List;

import com.dongnao.workbench.basic.model.UserInfo;

/**
 * 描述：用户信息模块dao接口，提供数据库操作方法
 *
 * @author yao.su
 * @version 1.0 2016-03-13
 */
public interface UserInfoMapper  {
	/**
	 * 新增方法
	 * @param userInfo UserInfo:实体类
	 */
	void add(UserInfo userInfo);
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键（查询条件）
	 * @return UserInfo: 实体
	 */
	UserInfo getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找列表方法
	 * @param userInfo 实体对象（查询条件）
	 * @return List<UserInfo>: 实体对象的list
	 */
	List<UserInfo>  listByCondition(UserInfo userInfo);
	
	/**
	 * 修改方法
	 * @param userInfo UserInfo：实体对象
	 */	
	void update(UserInfo userInfo);
	
	/**
	 * 修改roleid方法
	 * @param userInfo UserInfo：实体对象
	 */	
	void updateRoleid(UserInfo userInfo);
	/**
	 * 修改最后登陆信息
	 * @param userInfo UserInfo：实体对象
	 */	
	void updateLastLoginInfo(UserInfo userInfo);
	/**
	 * 修改密码
	 * @param userInfo UserInfo：实体对象
	 */	
	void updatePassWord(UserInfo userInfo);
	
	
	
	/**
	 * 根据用户登录名获取用户实体
	 * @param userName 用户名
	 * @return 用户信息实体
	 */
	UserInfo getByUserAccount(String userName);
	
	/**
	 * 根据角色ID获取用户信息列表
	 * @param params 条件集合
	 * @return List<HashMap<String, String>> 用户信息列表
	 */
	List<HashMap<String, String>> listUserByRid(HashMap<String, Object> params);
	
	
	/**
	 * 根据userid 进行逻辑删除
	 * @param userId
	 */
	void logicDeleteByKey(String userId);
	

	

}