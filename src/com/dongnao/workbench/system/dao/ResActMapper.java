package com.dongnao.workbench.system.dao;

import java.util.List;

import com.dongnao.workbench.system.model.ResAct;
/**
 * 描述：资源操作模块dao接口，提供数据库操作方法
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public interface ResActMapper  {

	/**
	 * 新增方法
	 * @param resAct ResAct:实体类
	 */
	void add(ResAct resAct);
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键（查询条件）
	 * @return ResAct: 实体
	 */
	ResAct getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param resAct ResAct：实体对象（查询条件）
	 * @return List<ResAct>: 实体对象的list
	 */
	List<ResAct>  listByCondition(ResAct resAct);
	
	/**
	 * 修改方法
	 * @param resAct ResAct：实体对象
	 */	
	void update(ResAct resAct);
	
	
	
	
}