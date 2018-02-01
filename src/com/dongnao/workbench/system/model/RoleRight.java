package com.dongnao.workbench.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：角色权限模块实体类，负责页面与后台数据传输功能
 * @author joan.xiong
 * @version 1.0 2016-03-22
 */
public class RoleRight extends Model {

	private String id;//权限主键
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	private String actcodes;//资源操作按钮
	private String roleid;//角色ID
	private String resuuid;//资源ID

	/**
	 * 获得权限主键
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置权限主键
	 * @param id 权限主键
	 */
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * 获得创建时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;

	}

	/**
	 * 获得资源操作按钮
	 * @return actcodes
	 */
	public String getActcodes() {
		return this.actcodes;
	}

	/**
	 * 设置资源操作按钮
	 * @param actcodes 资源操作按钮
	 */
	public void setActcodes(String actcodes) {
		this.actcodes = actcodes;

	}

	/**
	 * 获得角色ID
	 * @return roleid
	 */
	public String getRoleid() {
		return this.roleid;
	}

	/**
	 * 设置角色ID
	 * @param roleid 角色ID
	 */
	public void setRoleid(String roleid) {
		this.roleid = roleid;

	}

	/**
	 * 获得资源ID
	 * @return resuuid
	 */
	public String getResuuid() {
		return this.resuuid;
	}

	/**
	 * 设置资源ID
	 * @param resuuid 资源ID
	 */
	public void setResuuid(String resuuid) {
		this.resuuid = resuuid;

	}

}