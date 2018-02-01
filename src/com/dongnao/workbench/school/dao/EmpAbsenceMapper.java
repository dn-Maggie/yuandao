package com.dongnao.workbench.school.dao;

import java.util.List;
import com.dongnao.workbench.school.model.EmpAbsence;
/**
 * 描述：员工签卡模块dao接口，提供数据库操作方法
 *
 * @author maggie
 * @version 1.0 2016-12-12
 */
public interface EmpAbsenceMapper  {

	/**
	 * 新增员工签卡方法
	 * @param empAbsence EmpAbsence:实体类
	 */
	void add(EmpAbsence empAbsence);
	
	/**
	 * 删除员工签卡方法
	 * @param key String:多个由“，”分割开的id字符串
	 */
	void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工签卡实体方法
	 * @param key String：实体主键（查询条件）
	 * @return EmpAbsence: 实体
	 */
	public EmpAbsence getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工签卡列表方法
	 * @param EmpAbsence empAbsence：实体对象（查询条件）
	 * @return List<EmpAbsence>: 实体对象的list
	 */
	public List<EmpAbsence>  listByCondition(EmpAbsence empAbsence);
	
	/**
	 * 修改员工签卡方法
	 * @param empAbsence EmpAbsence：实体对象
	 */	
	public void update(EmpAbsence empAbsence);
	/**
	 * 获取所有直接负责人列表
	 * @param empAbsence EmpAbsence 实体对象
	 */	
	public List<EmpAbsence> getStraightLeader();
}