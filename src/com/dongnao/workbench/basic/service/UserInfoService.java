package com.dongnao.workbench.basic.service;

import java.util.HashMap;
import java.util.List;

import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.system.model.Personrole;


/**
 * 描述：用户信息模块service接口，提供controller操作所需方法
 *
 * @author yao.su
 * @version 1.0 2016-03-13
 */
public interface UserInfoService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.basic.service.UserInfoService";

	/**
	 * 新增用户和权限信息
	 * @param userInfo 用户信息
	 * @param roleId 权限ID
	 * @param loginUserInfo UserInfo
	 * @return true成功，false失败
	 */
	ResultMessage add(UserInfo userInfo,String roleId,UserInfo loginUserInfo);
	
	/**
	 * 删除方法
	 * @param key 信息ID，多个以“，”分割
	 * @param request HttpServletRequest
	 * @return 操作信息
	 */
	String deleteByKey(String key,UserInfo loginUserInfo);
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键
	 * @return userInfo UserInfo 实体对象
	 */
	UserInfo getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找列表方法
	 * @param userInfo UserInfo 实体对象（查询条件）
	 * @return 实体对象的list
	 */
	List<UserInfo> listByCondition(UserInfo userInfo);
	
	/**
	 * 修改方法
	 * @param userInfo UserInfo 实体对象
	 * @param role 权限ID
	 */	
	ResultMessage update(UserInfo userInfo, String roleId,UserInfo loginUserInfo);
	
	/**
	 * 更新最后登录信息
	 * @param userInfo 用户实体
	 */
	void updateLastLoginInfo(UserInfo userInfo);

	
	/**
	 * 更新角色id
	 * @param personrole Personrole：实体对象
	 */	
	void updateroleid(UserInfo userInfo);
	/**
	 * 根据登录账号查找实体方法
	 * @param userName 用户登录名
	 * @return userInfo实体
	 */
	public  UserInfo getByUserAccount(String userName);
	
	/**
	 * 重置用户密码
	 * @param info 用户实体
	 */
	void updatePassword(UserInfo info);
	
	/**
	 * 根据条件获取用户信息
	 * @param params 条件集合
	 * @return 根据条件查询LIST
	 */
	List<HashMap<String, String>> listUserByRid(HashMap<String, Object> params);
	
	/**
	 * 查询用户信息
	 * @param userInfo 实体
	 * @return List<UserInfo>
	 */
	List<UserInfo> getByUserInfo(UserInfo userInfo);
	
}