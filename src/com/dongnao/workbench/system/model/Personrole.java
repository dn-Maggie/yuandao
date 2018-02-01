package com.dongnao.workbench.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：用户角色关联模块实体类，负责页面与后台数据传输功能
 * 
 * @author yang.bin
 * @version 1.0 2016-03-06
 */
public class Personrole extends Model {
	private String roleId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String userId;

	/**
	 * 获取角色ID
	 * 
	 * @return roleId
	 */
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * 设置角色ID
	 * 
	 * @param roleId 角色ID
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取用户UUID
	 * 
	 * @return userId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 设置用户UUID
	 * 
	 * @param userId 用户UUID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}