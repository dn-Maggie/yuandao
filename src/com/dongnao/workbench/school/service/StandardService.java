package com.dongnao.workbench.school.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.Standard;
import com.dongnao.workbench.student.model.Statistical;

/**
 * 描述：考核标准表模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-07-18
 */
public interface StandardService  {

	/**
	 * 新增考核标准表方法
	 * @param standard Standard:实体类
	 */
	public ResultMessage add(Standard standard);
	
	/**
	 * 删除考核标准表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找考核标准表实体方法
	 * @param key String：实体主键
	 * @return standard Standard 实体对象
	 */
	public Standard getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找考核标准表列表方法
	 * @param standard Standard 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<Standard> listByCondition(Standard standard);
	
	/**
	 * 修改考核标准表方法
	 * @param standard Standard 实体对象
	 */	
	public ResultMessage update(Standard standard);

	/**
	 * 查询所有流量来源方法
	 * @param standard Standard 实体对象
	 */	
	public List<Standard> getAllResourceId();

	/**
	 * 查询所有转化人类型方法
	 * @param standard Standard 实体对象
	 */	
	public List<Standard> getAllFollowerId();

	/**
	 * @return
	 */
	public List<Standard> getAllSubResourceId();
}