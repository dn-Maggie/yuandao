package com.dongnao.workbench.basic.service;

import java.util.List;

import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.common.bean.ResultMessage;

/**
 * 描述：学科表模块service接口，提供controller操作所需方法
 *
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
public interface SubjectService  {

	/**
	 * 新增学科表方法
	 * @param subject Subject:实体类
	 */
	public ResultMessage add(Subject subject);
	
	/**
	 * 删除学科表方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找学科表实体方法
	 * @param key String：实体主键
	 * @return subject Subject 实体对象
	 */
	public Subject getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找学科表列表方法
	 * @param subject Subject 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<Subject> listByCondition(Subject subject);
	
	/**
	 * 修改学科表方法
	 * @param subject Subject 实体对象
	 */	
	public ResultMessage update(Subject subject);
	
	/**
	 * 查询总业绩目标方法
	 * @param subject Subject 实体对象
	 */	
	public int queryPerfTarget();
}