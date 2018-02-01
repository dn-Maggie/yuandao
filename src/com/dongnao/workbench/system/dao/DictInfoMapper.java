package com.dongnao.workbench.system.dao;

import java.util.List;
import java.util.Map;

import com.dongnao.workbench.system.model.DictInfo;

/**
 * 描述：dictInfo模块dao接口，提供数据库操作方法
 * @author zhou.zheng
 *
 * @version 1.0 2013-11-04
 */
public interface DictInfoMapper  {


	/**
	 * 新增方法
	 * @param dictInfo DictInfo:实体类
	 * @return int
	 */
	int add(DictInfo dictInfo);
	
	/**
	 * 删除方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	
	/**
	 * 根据主键查找实体方法
	 * @param key String：实体主键（查询条件）
	 * @return DictInfo: 实体
	 */
	DictInfo getByPrimaryKey(String key);
	
	
	/**
	 * 根据条件查找列表方法
	 * @param dictInfo DictInfo：实体对象（查询条件）
	 * @return List<DictInfo>: 实体对象的list
	 */
	List<DictInfo>  listByCondition(DictInfo dictInfo);
	
	/**
	 * 修改方法
	 * @param dictInfo 实体对象
	 * @return int 
	 */	
	int update(DictInfo dictInfo);
	
	/**
	 * 查询人员角色
	 * @param userId String 人员id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getUserRole(String userId);
	
	/**
	 * 查询发布人，完成人角色
	 * @param map Map<String,String>
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getPublishRole(Map<String,String> map);
	
	/**
	 * 查询人员角色
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getOverUserRole(String plateId);
	
	/**
	 * 查询生产线
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getBeltline(String plateId);

	/**
	 * 查询生产线
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getHmBeltline();
	
	/**
	 * 查询定检级别
	 * @param plateId String 板块id
	 * @return List<String>
	 */
	List<Map<Object,Object>> getCheckLeve(String plateId);

	/**
	 * 查询飞机号
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getPlaneNo(String plateId);
	
	/**
	 * 查询定检类的飞机号
	 * @param serMap Map<String,List<String>> 条件MAP
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getHmPlaneNo(Map<String,List<String>> serMap);

	/**
	 * 查询定检级别
	 * @param serMap Map<String,List<String>> 条件MAP
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getHmCheckLeve(Map<String,List<String>> serMap);
	/**
	 * 查询客户
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getCustomer(String plateId);

	/**
	 * 查询客户
	 * @param serMap Map<String,List<String>> 条件MAP
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getLmCustomer(Map<String,List<String>> serMap);
	/**
	 * 查询机型
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getPlaneType(String plateId);

	/**
	 * 查询航线类的机型
	 * @param serMap Map<String,List<String>> 条件MAP
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getLmPlaneType(Map<String,List<String>> serMap);
	
	
	/**
	 * 航线板块联动查询机号
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getPlaneNoForLm(String plateId);

	/**
	 * 查询航线类的飞机号
	 * @param serMap Map<String,List<String>> 条件MAP
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getLmPlaneNoForLm(Map<String,List<String>> serMap);
	
	
	/**
	 * 查询车间
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getWorkshop(String plateId);
	
	/**
	 * 查询车间
	 * @param serMap Map<String,List<String>> 条件MAP
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getShopWorkshop(Map<String,List<String>> serMap);
	
	/**
	 * 查询件号
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getPartNo(String plateId);
	
	/**
	 * 查询工卡指令号
	 * @param plateId String 板块id
	 * @return List<Map<Object,Object>>
	 */
	List<Map<Object,Object>> getJobCardNo(String plateId);
	
	
	/**
	 * 根据字典类型编码查询字典数据
	 * @param dictTypeCode String
	 * @return List<DictInfo>
	 */
	List<DictInfo> getDictByTypeCode(String dictTypeCode);

	/**
     * 根据飞机号查询对应的客户及机型
     * @param condition Map<String,Object> 条件MAP
     * @return List<Map<String, String>> 
     */
	List<Map<String, String>> getCustomAndAcType(Map<String, Object> condition);

	/**
	 * 通过字典编码查询
	 * @param dictCode
	 * @return
	 */
	List<DictInfo> getByDictCode(DictInfo dictInfo);
}