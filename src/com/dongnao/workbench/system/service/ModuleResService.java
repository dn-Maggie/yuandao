package com.dongnao.workbench.system.service;

import java.util.List;

import com.dongnao.workbench.system.model.ModuleRes;

/**
 * 描述：菜单资源模块service接口，提供Service操作所需方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public interface ModuleResService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.system.service.ModuleResService";


	/**
	 * 新增方法
	 * @param moduleRes ModuleRes:实体类
	 */
	void add(ModuleRes moduleRes);
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键
	 * @return ModuleRes 实体对象
	 */
	ModuleRes getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param moduleRes ModuleRes：实体对象（查询条件）
	 * @return List<ModuleRes> 实体对象的list
	 */
	List<ModuleRes> listByCondition(ModuleRes moduleRes);
	
	/**
	 * 修改方法
	 * @param moduleRes ModuleRes：实体对象
	 */	
	void update(ModuleRes moduleRes);
	

	/**
	 * 更新  资源所在的菜单数据
	 * @param resuuids 资源IDS
	 * @param muuid 菜单ID
	 */
	void updateMrMuuid(String resuuids, String muuid);
}