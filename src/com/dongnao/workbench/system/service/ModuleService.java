package com.dongnao.workbench.system.service;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.system.model.Module;
import com.dongnao.workbench.system.model.ModuleLocation;

/**
 * 描述：菜单模块service接口，提供service操作所需方法
 * 
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public interface ModuleService {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.system.service.ModuleService";

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
	 *            String：实体主键
	 * @return 实体对象
	 */
	Module getByPrimaryKey(String key);

	/**
	 * 根据条件查找列表方法
	 * 
	 * @param module
	 *            Module：实体对象（查询条件）
	 * @return 实体对象的list
	 */
	List<Module> listByCondition(Module module);

	/**
	 * 修改方法
	 * 
	 * @param module
	 *            Module：实体对象
	 */
	void update(Module module);

	List<Module> getMenuListForAdmin();
	 List<Module> getMenuListByPcode(String pcode);

	/**
	 * 获取系统管理的菜单JSON数据
	 * 
	 * @return String JSON字符串
	 */
	String getMenuJsonForAdmin();

	/**
	 * 根据URL查询对应的模块路径集合
	 * 
	 * @return List<ModuleLocation>: 实体对象的list
	 */
	List<ModuleLocation> listModuleLocationList();
	/**
	 * 根据URL查询对应的模块路径（如果没有传，默认返回首页）
	 * 
	 * @param murl
	 * @return
	 */
	String getModuleLocationStrByUrl(String murl);

	/**
	 * 根据人员ID获取菜单JSON数据
	 * 
	 * @param pcode
	 *            String：实体对象（查询条件）
	 * @return String JSON字符串
	 */
	String getMenuJsonByPcode(String pcode);
}