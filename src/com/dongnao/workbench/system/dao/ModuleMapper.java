package com.dongnao.workbench.system.dao;

import java.util.List;

import com.dongnao.workbench.system.model.Module;
import com.dongnao.workbench.system.model.ModuleLocation;

/**
 * 描述：菜单模块dao接口，提供数据库操作方法
 * 
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public interface ModuleMapper {

	/**
	 * 新增方法
	 * 
	 * @param module
	 *            Module:实体类
	 */
	void add(Module module);

	/**
	 * 删除方法
	 * 
	 * @param key
	 *            String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);

	/**
	 * 根据主键查找实体方法
	 * 
	 * @param key
	 *            String：实体主键（查询条件）
	 * @return Module: 实体
	 */
	Module getByPrimaryKey(String key);

	/**
	 * 根据条件查找列表方法
	 * 
	 * @param module
	 *            Module：实体对象（查询条件）
	 * @return List<Module>: 实体对象的list
	 */
	List<Module> listByCondition(Module module);

	/**
	 * 修改方法
	 * 
	 * @param module
	 *            Module：实体对象
	 */
	void update(Module module);

	/**
	 * 根据人员ID获取菜单数据
	 * 
	 * @param pcode
	 *            String:人员ID
	 * @return List<Module>: 实体对象的list
	 */
	List<Module> queryForMenuByPcode(String pcode);

	/**
	 * 根据URL查询对应的模块路径集合
	 * 
	 * @return List<ModuleLocation>: 实体对象的list
	 */
	public List<ModuleLocation> listModuleLocationList();

	/**
	 * 获取系统管理的菜单数据
	 * 
	 * @return List<Module>: 实体对象的list
	 */
	List<Module> queryForMenuByAdmin();

}