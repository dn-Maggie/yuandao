package com.dongnao.workbench.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dongnao.workbench.system.model.RoleRight;

/**
 * 描述：roleRight模块service接口，提供service操作所需方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public interface RoleRightService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.system.service.RoleRightService";

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
	 * @param key String：实体主键
	 * @return 实体对象
	 */
	RoleRight getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param roleRight RoleRight：实体对象（查询条件）
	 * @return List<RoleRight> 实体对象的list
	 */
	List<RoleRight> listByCondition(RoleRight roleRight);
	
	/**
	 * 修改方法
	 * @param roleRight RoleRight：实体对象
	 */	
	void update(RoleRight roleRight);

	/**
	 * 获取系统中需要权限过滤的URL资源
	 * @return List<String> URL资源列表
	 */
	List<String> getAllRightUrl();

	/**
	 * 验证用户权限，同时获取并设置页面权限
	 * @param hreq Request请求
	 * @return boolean 是否有权限
	 */
	boolean checkAndSetRight(HttpServletRequest hreq);
}