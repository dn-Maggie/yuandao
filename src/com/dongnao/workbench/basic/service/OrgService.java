package com.dongnao.workbench.basic.service;

import java.util.List;

import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.bean.ResultMessage;


/**
 * 描述：组织机构模块service接口，提供controller操作所需方法
 * 
 * @author yao.su
 * @version 1.0 2016-03-21
 */
public interface OrgService {

	/**
	 * 新增组织机构方法
	 * 
	 * @param org
	 *            Org:实体类
	 */
	public ResultMessage add(Org org);

	/**
	 * 删除组织机构方法
	 * 
	 * @param key
	 *            :多个由“，”分割开的id字符串
	 */
	public void deleteByKey(String key);

	/**
	 * 根据主键查找组织机构实体方法
	 * 
	 * @param key
	 *            String：实体主键
	 * @return org Org 实体对象
	 */
	public Org getByPrimaryKey(String key);

	/**
	 * 根据条件查找组织机构列表方法
	 * 
	 * @param org
	 *            Org 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<Org> listByCondition(Org org);

	/**
	 * 根据条件查找组织机构列表方法
	 * 
	 * @param org
	 *            Org 实体对象（查询条件）
	 * @return: 实体对象的list
	 */
	public List<Org> listJoinStockOrgs(Org org);

	/**
	 * 修改组织机构方法
	 * 
	 * @param org
	 *            Org 实体对象
	 */
	public ResultMessage update(Org org);

	/**
	 * 初始化树
	 * 
	 * @param orgName
	 *            机构名
	 * @return String
	 */
	String initOrgTree(String orgName, UserInfo userInfo);
	
}