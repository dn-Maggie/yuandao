package com.dongnao.workbench.system.dao;

import java.util.HashMap;
import java.util.List;

import com.dongnao.workbench.system.model.RoleRight;
/**
 * 描述：角色权限模块dao接口，提供数据库操作方法
 * @author joan.xiong
 * @version 1.0 2016-03-22
 */
public interface RoleRightMapper  {


	/**
	 * 新增方法
	 * @param roleRight RoleRight:实体类
	 */
	void add(RoleRight roleRight);
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键（查询条件）
	 * @return RoleRight: 实体
	 */
	RoleRight getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param roleRight RoleRight：实体对象（查询条件）
	 * @return List<RoleRight>: 实体对象的list
	 */
	List<RoleRight>  listByCondition(RoleRight roleRight);
	
	/**
	 * 修改方法
	 * @param roleRight RoleRight：实体对象
	 */	
	void update(RoleRight roleRight);

	/**
	 * 删除同一角色的授权数据
	 * @param roleid String：角色ID
	 */
	void deleteByRoleId(String roleid);

	/**
	 * 根据用户工号查询该用户所拥有的资源操作
	 * @param params HashMap 参数集合
	 * @return List<HashMap<String, String>> 资源操作集合
	 */
	List<HashMap<String, String>> getResactByUrlForUser(HashMap<String, String> params);

	/**
	 * 获取系统管理员在当前路径下所拥有的资源操作
	 * @param params HashMap 参数集合
	 * @return List<HashMap<String, String>> 资源操作集合
	 */
	List<HashMap<String, String>> getResactByUrlForAdmin(HashMap<String, String> params);

	/**
	 * 获取系统中需要权限过滤的URL资源
	 * @return List<String> 资源列表
	 */
	List<HashMap<String,String>> getAllRightUrl();
	
	/**
	 * 获取系统管理员在Request请求中的路径权限数据
	 * @param params 条件集合
	 * @return List<HashMap<String, String>> 结果集合
	 */
	List<HashMap<String, String>> checkRightForAdmin(HashMap<String, String> params);

	/**
	 * 获取Request请求中的路径权限数据
	 * @param params 条件集合
	 * @return List<HashMap<String, String>> 结果集合
	 */
	List<HashMap<String, String>> checkRightForUser(HashMap<String, String> params);
	
}