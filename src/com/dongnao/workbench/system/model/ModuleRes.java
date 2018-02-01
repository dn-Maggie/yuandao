package com.dongnao.workbench.system.model;

import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：菜单资源模块实体类，负责页面与后台数据传输功能
 * 
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public class ModuleRes extends Model {

	private String id;//主键
	private String resourcecode;//资源编码
	private String needright;//是否需要权限控制
	private String muuid;//菜单ID
	private String memo;//备注
	private String status;//状态
	private String serialindex;//序号
	private String resourceName;//资源名称
	private String resparentuuid;//父资源
	private String resurl;//资源URL
	private String resType;//资源类型
	private String sortBy;//排序方式
	private String sortFild;//排序字段
	private String resTypeName;//资源类型名称
	private String roleStatus;//角色状态  是否被禁用
	/**
	 * 获得主键
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置主键
	 * @param id 主键
	 */
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * 获取 角色状态
	 * @return
	 */
	public String getRoleStatus() {
		return roleStatus;
	}
	/**
	 * 设置  角色状态
	 * @param states
	 */


	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
	
	/**
	 * 获得资源编码
	 * @return resourcecode
	 */
	public String getResourcecode() {
		return this.resourcecode;
	}
	

	/**
	 * 设置资源编码
	 * @param resourcecode 资源编码
	 */
	public void setResourcecode(String resourcecode) {
		this.resourcecode = resourcecode;

	}

	/**
	 * 获得是否需要权限控制
	 * @return needright
	 */
	public String getNeedright() {
		return this.needright;
	}

	/**
	 * 设置是否需要权限控制
	 * @param needright 是否需要权限控制
	 */
	public void setNeedright(String needright) {
		this.needright = needright;

	}

	/**
	 * 获得菜单ID
	 * @return muuid
	 */
	public String getMuuid() {
		return this.muuid;
	}

	/**
	 * 设置菜单ID
	 * @param muuid 菜单ID
	 */
	public void setMuuid(String muuid) {
		this.muuid = muuid;

	}
	
	/**
	 * 获得备注
	 * @return memo
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * 设置备注
	 * @param memo 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;

	}

	/**
	 * 获得状态
	 * @return status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * 设置状态
	 * @param status 状态
	 */
	public void setStatus(String status) {
		this.status = status;

	}

	/**
	 * 获得序号
	 * @return serialindex
	 */
	public String getSerialindex() {
		return this.serialindex;
	}

	/**
	 * 设置序号
	 * @param serialindex 序号
	 */
	public void setSerialindex(String serialindex) {
		this.serialindex = serialindex;

	}

	/**
	 * 获得资源名称
	 * @return resourceName
	 */
	public String getResourceName() {
		return this.resourceName;
	}

	/**
	 * 设置资源名称
	 * @param resourceName 资源名称
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;

	}

	/**
	 * 获得父资源ID
	 * @return resparentuuid
	 */
	public String getResparentuuid() {
		return this.resparentuuid;
	}

	/**
	 * 设置父资源ID
	 * @param resparentuuid 父资源ID
	 */
	public void setResparentuuid(String resparentuuid) {
		this.resparentuuid = resparentuuid;

	}

	/**
	 * 获得资源URL
	 * @return resurl
	 */
	public String getResurl() {
		return this.resurl;
	}
	
	/**
	 * 设置资源URL
	 * @param resurl 资源URL
	 */
	public void setResurl(String resurl) {
		this.resurl = resurl;

	}
	
	/**
	 * 获得排序方式
	 * @return sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * 设置排序方式
	 * @param sortBy 排序方式
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * 获得排序字段
	 * @return sortFild
	 */
	public String getSortFild() {
		return sortFild;
	}

	/**
	 * 设置排序字段
	 * @param sortFild 排序字段
	 */
	public void setSortFild(String sortFild) {
		this.sortFild = sortFild;
	}

	/**
	 * 获取资源类型
	 * @return resType
	 */
	public String getResType() {
		return resType;
	}

	/**
	 * 设置资源类型
	 * @param resType 资源类型
	 */
	public void setResType(String resType) {
		this.resType = resType;
	}

	/**
	 * 获取资源类型映射名称
	 * @return
	 */
	public String getResTypeName() {
		return resTypeName;
	}

	/**
	 * 设置资源类型映射名称
	 * @param resTypeName 资源类型映射名称
	 */
	public void setResTypeName(String resTypeName) {
		this.resTypeName = resTypeName;
	}
	

}