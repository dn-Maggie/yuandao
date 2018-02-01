package com.dongnao.workbench.system.dao;

import java.util.List;
import java.util.Map;

import com.dongnao.workbench.system.model.ModuleRes;
/**
 * 描述：菜单资源模块dao接口，提供数据库操作方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public interface ModuleResMapper  {

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
	 * @param key String：实体主键（查询条件）
	 * @return ModuleRes: 实体
	 */
	ModuleRes getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param moduleRes ModuleRes：实体对象（查询条件）
	 * @return List<ModuleRes>: 实体对象的list
	 */
	List<ModuleRes>  listByCondition(ModuleRes moduleRes);
	
	/**
	 * 修改方法
	 * @param moduleRes ModuleRes：实体对象
	 */	
	void update(ModuleRes moduleRes);

	/**
	 * 更新资源的菜单数据
	 * @param map Map<String,Object>:资源ID和菜单ID MAP
	 */
	void updateMrMuuid(Map<String,Object> map);

	/**
	 * 查询系统管理的菜单数据
	 * @return List<ModuleRes>: 实体对象的list
	 */
	List<ModuleRes> queryForMenuByAdmin();

	/**
	 * 根据人员ID查询菜单数据
	 * @param pcode String：人员ID
	 * @return List<ModuleRes>: 实体对象的list
	 */
	List<ModuleRes> queryForMenuByPcode(String pcode);

	/**
	 * 根据条件查找列表方法(排除系统资源）
	 * @param moduleRes ModuleRes：实体对象（查询条件）
	 * @return List<ModuleRes>: 实体对象的list
	 */
	List<ModuleRes> listWithoutSysRes(ModuleRes moduleRes);
	/**
	 * 根据人员id查询角色状态
	 * @param pcode 人员id
	 * @return
	 */
	ModuleRes queryFoRoleStatesByPcode(String pcode);
	
	
}