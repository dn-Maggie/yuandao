package com.dongnao.workbench.system.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：菜单模块实体类，负责页面与后台数据传输功能
 * 
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public class Module extends Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7130229301896403736L;
	private String moduleMemo;// 备注
	private String navurl;// 菜单URL
	private String id;// 主键
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;// 创建时间
	private String enabled;// 状态
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;// 更新时间
	private String needRight;// 是否需要权限控制
	private String moduleName;// 菜单名称
	private String updateId;// 更新人ID
	private String serialIndex;// 序号
	private String moduleCode;// 菜单编码
	private String createId;// 创建人ID
	private String resourceId;// 图标资源ID
	private String resourceFilePath;// 图标资源路径

	private String sortBy;// 排序方式
	private String sortFild;// 排序字段

	private List<ModuleRes> listModuleRes;// 包含的子集菜单

	/**
	 * 获得备注
	 * 
	 * @return moduleMemo
	 */
	public String getModuleMemo() {
		return this.moduleMemo;
	}

	/**
	 * 设置备注
	 * 
	 * @param moduleMemo
	 *            备注
	 */
	public void setModuleMemo(String moduleMemo) {
		this.moduleMemo = moduleMemo;

	}

	/**
	 * 获得菜单URL
	 * 
	 * @return navurl
	 */
	public String getNavurl() {
		return this.navurl;
	}

	/**
	 * 设置菜单URL
	 * 
	 * @param navurl
	 *            菜单URL
	 */
	public void setNavurl(String navurl) {
		this.navurl = navurl;
	}

	/**
	 * 获得主键
	 * 
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * 获得创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;

	}

	/**
	 * 获得状态
	 * 
	 * @return enabled
	 */
	public String getEnabled() {
		return this.enabled;
	}

	/**
	 * 设置状态
	 * 
	 * @param enabled
	 *            状态
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;

	}

	/**
	 * 获得更新时间
	 * 
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;

	}

	/**
	 * 获得是否需要权限
	 * 
	 * @return needRight
	 */
	public String getNeedRight() {
		return this.needRight;
	}

	/**
	 * 设置是否需要权限
	 * 
	 * @param needRight
	 *            是否需要权限
	 */
	public void setNeedRight(String needRight) {
		this.needRight = needRight;

	}

	/**
	 * 获得菜单名称
	 * 
	 * @return moduleName
	 */
	public String getModuleName() {
		return this.moduleName;
	}

	/**
	 * 设置菜单名称
	 * 
	 * @param moduleName
	 *            菜单名称
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;

	}

	/**
	 * 获得更新人ID
	 * 
	 * @return updateId
	 */
	public String getUpdateId() {
		return this.updateId;
	}

	/**
	 * 设置更新人ID
	 * 
	 * @param updateId
	 *            更新人ID
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;

	}

	/**
	 * 获得序号
	 * 
	 * @return serialIndex
	 */
	public String getSerialIndex() {
		return this.serialIndex;
	}

	/**
	 * 设置序号
	 * 
	 * @param serialIndex
	 *            序号
	 */
	public void setSerialIndex(String serialIndex) {
		this.serialIndex = serialIndex;

	}

	/**
	 * 获得菜单编码
	 * 
	 * @return moduleCode
	 */
	public String getModuleCode() {
		return this.moduleCode;
	}

	/**
	 * 设置菜单编码
	 * 
	 * @param moduleCode
	 *            菜单编码
	 */
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;

	}

	/**
	 * 获得创建人ID
	 * 
	 * @return createId
	 */
	public String getCreateId() {
		return this.createId;
	}

	/**
	 * 设置创建人ID
	 * 
	 * @param createId
	 *            创建人ID
	 */
	public void setCreateId(String createId) {
		this.createId = createId;

	}

	/**
	 * 获得排序方式
	 * 
	 * @return sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * 设置排序方式
	 * 
	 * @param sortBy
	 *            排序方式
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * 获得排序字段
	 * 
	 * @return sortFild
	 */
	public String getSortFild() {
		return sortFild;
	}

	/**
	 * 设置排序字段
	 * 
	 * @param sortFild
	 *            排序字段
	 */
	public void setSortFild(String sortFild) {
		this.sortFild = sortFild;
	}

	/**
	 * 获得图标资源ID
	 * 
	 * @return resourceId
	 */
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * 设置图标资源ID
	 * 
	 * @param resourceId
	 *            图标资源ID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 获得图标资源路径
	 * 
	 * @return resourceFilePath
	 */
	public String getResourceFilePath() {
		return resourceFilePath;
	}

	/**
	 * 设置图标资源路径
	 * 
	 * @param resourceId
	 *            图标资源路径
	 */
	public void setResourceFilePath(String resourceFilePath) {
		this.resourceFilePath = resourceFilePath;
	}

	public List<ModuleRes> getListModuleRes() {
		return listModuleRes;
	}

	public void setListModuleRes(List<ModuleRes> listModuleRes) {
		this.listModuleRes = listModuleRes;
	}

}