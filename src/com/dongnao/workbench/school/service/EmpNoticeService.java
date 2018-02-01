package com.dongnao.workbench.school.service;

import java.util.List;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.school.model.EmpNotice;

/**
 * 描述：员工公告通知模块service接口，提供controller操作所需方法
 *
 * @author maggie
 * @version 1.0 2016-12-22
 */
public interface EmpNoticeService  {

	/**
	 * 新增员工公告通知方法
	 * @param empNotice EmpNotice:实体类
	 */
	public ResultMessage add(EmpNotice empNotice);
	
	/**
	 * 删除员工公告通知方法
	 * @param key:多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);
	
	/**
	 * 根据主键查找员工公告通知实体方法
	 * @param key String：实体主键
	 * @return empNotice EmpNotice 实体对象
	 */
	public EmpNotice getByPrimaryKey(String key);
	
	/**
	 * 根据条件查找员工公告通知列表方法
	 * @param empNotice EmpNotice 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<EmpNotice> listByCondition(EmpNotice empNotice);
	
	/**
	 * 修改员工公告通知方法
	 * @param empNotice EmpNotice 实体对象
	 */	
	public ResultMessage update(EmpNotice empNotice);
}