package com.dongnao.workbench.system.dao;

import java.util.List;
import com.dongnao.workbench.system.model.Personrole;
/**
 * 描述：用户角色关联模块dao接口，提供数据库操作方法
 *
 * @author yang.bin
 * @version 1.0 2016-03-06
 */
public interface PersonroleMapper  {

	/**
	 * 新增方法
	 * @param personrole Personrole:实体类
	 */
	void add(Personrole personrole);
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键（查询条件）
	 * @return Personrole: 实体
	 */
	Personrole getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找列表方法
	 * @param Personrole personrole：实体对象（查询条件）
	 * @return List<Personrole>: 实体对象的list
	 */
	List<Personrole>  listByCondition(Personrole personrole);
	
	/**
	 * 修改方法
	 * @param personrole Personrole：实体对象
	 */	
	void update(Personrole personrole);
}