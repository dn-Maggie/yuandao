package com.dongnao.workbench.school.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.EmpAbsence;

/**
 * 描述：员工签卡模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-12-12
 */
public interface EmpAbsenceService  {

	/**
	 * 新增员工签卡方法
	 * @param empAbsence EmpAbsence:实体类
	 */
	public ResultMessage add(EmpAbsence empAbsence);
	
	/**
	 * 删除员工签卡方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工签卡实体方法
	 * @param key String：实体主键
	 * @return empAbsence EmpAbsence 实体对象
	 */
	public EmpAbsence getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工签卡列表方法
	 * @param empAbsence EmpAbsence 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<EmpAbsence> listByCondition(EmpAbsence empAbsence);
	
	/**
	 * 修改员工签卡方法
	 * @param empAbsence EmpAbsence 实体对象
	 */	
	public ResultMessage update(EmpAbsence empAbsence);
	/**
	 * 从emp_absence表中获取所有直接负责人方法
	 * @param empAbsence EmpAbsence 实体对象
	 */	
	public List<EmpAbsence> getStraightLeader();
}