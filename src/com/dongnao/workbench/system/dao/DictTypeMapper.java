package com.dongnao.workbench.system.dao;

import java.util.List;

import com.dongnao.workbench.system.model.DictType;

/**
 * 描述：dictType模块dao接口，提供数据库操作方法
 * @author zhou.zheng
 *
 * @version 1.0 2013-11-07
 */
public interface DictTypeMapper  {


	/**
	 * 新增方法
	 * @param dictType DictType:实体类
	 * @return int 
	 */
	int add(DictType dictType);
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键（查询条件）
	 * @return DictType: 实体
	 */
	DictType getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param dictType DictType：实体对象（查询条件）
	 * @return List<DictType>: 实体对象的list
	 */
	List<DictType>  listByCondition(DictType dictType);
	
	/**
	 * 修改方法
	 * @param dictType DictType：实体对象
	 * @return int 
	 */	
	int update(DictType dictType);

	/**
	 * 通过唯一类型获取
	 * @param typeCode 主键
	 * @return
	 */
	DictType getByTypeCode(String typeCode);

	/**
	 * 通过typeCode更新
	 * @param dictType 实体
	 * @return int
	 */
	int updateByTypeCode(DictType dictType);
	
}