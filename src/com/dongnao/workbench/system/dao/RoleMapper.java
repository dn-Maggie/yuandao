package com.dongnao.workbench.system.dao;

import java.util.List;
import java.util.Map;

import com.dongnao.workbench.system.model.Role;
/**
 * 描述：角色模块dao接口，提供数据库操作方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public interface RoleMapper  {

	/**
	 * 新增方法
	 * @param role Role:实体类
	 */
	void add(Role role);
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键（查询条件）
	 * @return Role: 实体
	 */
	Role getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找列表方法
	 * @param role 实体对象（查询条件）
	 * @return List<Role>: 实体对象的list
	 */
	List<Role>  listByCondition(Role role);
	
	/**
	 * 修改方法
	 * @param role Role：实体对象
	 */	
	void update(Role role);
	
	/**
	 * 获取指定角色的菜单树List
	 * @param roleRid 角色ID
	 * @return List
	 */
	List<Map<String, Object>> getForRoleTree(String roleRid);
	/**
	 * 添加前验证名称是否存在
	 * @param name
	 * @return
	 */
	public String verifyNameAdd(String name);
	
	/**
	 * 修改前验证名称是否存在
	 * @param name
	 * @return
	 */
	public String verifyNameUpdate(Role	role);
	
	/**
	 * 添加前验证名称是否存在
	 * @param name
	 * @return
	 */
	public String verifyCodeAdd(String code);
	
	/**
	 * 修改前验证名称是否存在
	 * @param name
	 * @return
	 */
	public String verifyCodeUpdate(Role	role);

}