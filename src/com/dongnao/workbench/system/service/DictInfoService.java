package com.dongnao.workbench.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.system.model.DictInfo;



/**
 * 描述：数据字典信息模块service接口，提供controller操作所需方法
 * @author zhou.zheng
 *
 * @version 1.0 2013-11-04
 */
public interface DictInfoService  {
	/**
	 * 组件bean
	 */
	String BEAN_ID = "com.dongnao.workbench.dict.service.DictInfoService";


	/**
	 * 新增方法
	 * @param dictInfo DictInfo:实体类
	 * @param request 请求
	 * @return int 
	 */
	int add(DictInfo dictInfo, HttpServletRequest request);
	
	/**
	 * 删除方法
	 * @param key 多个由“，”分割开的id字符串
	 * @param request 请求
	 * @return int
	 */
	int deleteByKey(String key, HttpServletRequest request);
	
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键
	 * @return DictInfo: 实体对象
	 */
	DictInfo getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param dictInfo 实体对象（查询条件）
	 * @return List<DictInfo> 
	 */
	List<DictInfo> listByCondition(DictInfo dictInfo);
	
	/**
	 * 修改方法
	 * @param dictInfo DictInfo：实体对象
	 * @param request 请求
	 * @return int 
	 */	
	int update(DictInfo dictInfo, HttpServletRequest request);
	
	/**
	 * 根据类别获取字典信息
	 * @param type String
	 * @return List<String>
	 */
	List<String> getDictInfoByType(String type);
	
	/**
	 * 根据类别获取字典信息
	 * 
	 * @param type
	 *            String 字典类别
	 * @return List<DictInfo>
	 */
	public List<DictInfo> getDictInfoListByType(String type);
	
	
	/**
	 * 根据字典类型编码查询字典数据
     * @param dictTypeCode String
	 * @return List<String>
	 */
	String getDictByTypeCode(String dictTypeCode);
	
	/**
	 * 根据字典类型编码查询字典数据(radio & checkbox )
	 * @param dictTypeCode String
	 * @return List<String> 
	 */
	List<String> getDictForRadio(String dictTypeCode);

	/**
	 * 通过编码查询字典信息
	 * @param dictCode
	 * @return
	 */
	List<DictInfo> getByDictCode(DictInfo dictInfo);
	
	/**
	 * 获取所有字典信息Map，Key为字典类型编码，value为字典信息字符串
	 * @return Map<String,String> 
	 */
	Map<String,String> getAllDictInfo();
	
	
}