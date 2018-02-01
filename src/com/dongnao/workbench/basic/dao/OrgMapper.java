package com.dongnao.workbench.basic.dao;

import java.util.List;

import com.dongnao.workbench.basic.model.Org;
/**
 * 描述：组织机构模块dao接口，提供数据库操作方法
 *
 * @author yao.su
 * @version 1.0 2016-03-21
 */
public interface OrgMapper  {

	/**
	 * 新增组织机构方法
	 * @param org Org:实体类
	 */
	int add(Org org);
	
	/**
	 * 删除组织机构方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找组织机构实体方法
	 * @param key String：实体主键（查询条件）
	 * @return Org: 实体
	 */
	public Org getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找组织机构列表方法
	 * @param Org org：实体对象（查询条件）
	 * @return List<Org>: 实体对象的list
	 */
	public List<Org>  listByCondition(Org org);
	
	/**
	 * 根据条件查找组织机构列表方法
	 * @param Org org：实体对象（查询条件）
	 * @return List<Org>: 实体对象的list
	 */
	public List<Org>  listJoinStockOrgs(Org org);
	
	/**
	 * 修改组织机构方法
	 * @param org Org：实体对象
	 */	
	public int update(Org org);
}