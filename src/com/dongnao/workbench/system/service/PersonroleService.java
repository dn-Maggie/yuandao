package com.dongnao.workbench.system.service;

import java.util.List;
import com.dongnao.workbench.system.model.Personrole;

/**
 * 描述：用户角色模块service接口，提供controller操作所需方法
 *
 * @author yang.bin
 * @version 1.0 2016-03-06
 */
public interface PersonroleService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.system.service.PersonroleService";

	/**
	 * 新增方法
	 * @param personrole Personrole:实体类
	 */
	void add(Personrole personrole);
	
	/**
	 * 删除方法
	 * @param key 删除ID，多个ID以","分隔
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键
	 * @return personrole Personrole 实体对象
	 */
	Personrole getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找列表方法
	 * @param personrole 实体对象（查询条件）
	 * @return 实体对象的list
	 */
	List<Personrole> listByCondition(Personrole personrole);
	
	/**
	 * 修改方法
	 * @param personrole Personrole 实体对象
	 */	
	void update(Personrole personrole);
}