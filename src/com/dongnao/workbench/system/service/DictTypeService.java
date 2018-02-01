package com.dongnao.workbench.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dongnao.workbench.system.model.DictType;



/**
 * 描述：数据字典类别模块service接口，提供controller操作所需方法
 * @author zhou.zheng
 *
 * @version 1.0 2013-11-07
 */
public interface DictTypeService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.dict.service.DictTypeService";

	/**
	 * 新增方法
	 * @param dictType DictType 实体类
	 * @param request 请求
	 * @return int 
	 */
	int add(DictType dictType, HttpServletRequest request);
	
	/**
	 * 删除方法
	 * @param typeCode :多个由“，”分割开的id字符串
	 * @param request 请求
	 * @return int 
	 */
	int deleteByTypeCode(String typeCode, HttpServletRequest request);
	
	/**
	 * 根据主键查找实体方法
	 * @param key ：实体主键
	 * @return DictType: 实体对象
	 */
	DictType getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param dictType DictType：实体对象（查询条件）
	 * @return List<DictType>: 实体对象的list
	 */
	List<DictType> listByCondition(DictType dictType);
	
	/**
	 * 修改方法
	 * @param dictType DictType：实体对象
	 * @param request 请求
	 * @return int 
	 */	
	int update(DictType dictType, HttpServletRequest request);
	
	/**
	 * 为页面下拉框提供字典类别信息
	 * @param dictType DictType
	 * @return List<String>，元素为name:XX,value:XX 的字符串
	 */
	List<String> getDictTypeForSelect(DictType dictType);

	/**
	 * 初始化树
	 * @param typeName 类型名
	 * @return String 
	 */
	String initDictTypeTree(String typeName);

	/**
	 * 通过类型编码查询
	 * @param typeCode 编码
	 * @return
	 */
	DictType getByTypeCode(String typeCode);
}