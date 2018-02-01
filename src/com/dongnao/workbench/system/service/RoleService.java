package com.dongnao.workbench.system.service;

import java.util.List;
import java.util.Map;

import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.system.model.Role;

/**
 * 描述：role模块service接口，提供service操作所需方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public interface RoleService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.system.service.RoleService";

	/**
	 * 新增方法
	 * @param role Role:实体类
	 * @return 是否成功
	 */
	ResultMessage add(Role role);
	
	/**
	 * 删除方法
	 * @param key 多个由“，”分割开的id字符串
	 * @param updaterId 更新人UUID
	 */
	void deleteByKey(String key,String updaterId);
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键
	 * @return role Role 实体对象
	 */
	Role getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找列表方法
	 * @param role Role 实体对象（查询条件）
	 * @return 实体对象的list
	 */
	List<Role> listByCondition(Role role);
	
	/**
	 * 修改方法
	 * @param role Role 实体对象
	 */	
	ResultMessage update(Role role);

	/**
	 * 获取菜单树所有菜单JSON字符串
	 * @param resType 资源类型
	 * @return String JSON字符串
	 */
	String getAllmenuTreeJson(String resType);

	/**
	 * 根据角色ID获取已有权限树
	 * @param roleRid 角色ID
	 * @return json字符串
	 */
	String getMenuTreeJsonByRole(String roleRid);
	/**
	 * 获取指定角色的菜单JSON字符串
	 * @param roleRid 角色ID
	 * @return JSON字符串
	 */
	String getMenuTreeIdsByRoleId(String roleRid);

	/**
	 * 保存角色权限分配信息
	 * @param roleRid  角色ID
	 * @param objectJson  权限JSON字符串
	 * @return String 结果
	 */
	String saveRoleRight(String roleRid, String objectJson);
	
	/**
	 * 为页面下拉框获取角色列表
	 * @param role 角色条件
	 * @return List<String>，元素为name:XX,value:XX 的字符串
	 */
	List<String> getRoleListForSelect(Role role);
	
	
}