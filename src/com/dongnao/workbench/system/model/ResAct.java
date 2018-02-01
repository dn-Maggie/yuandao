package com.dongnao.workbench.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：资源操作模块实体类，负责页面与后台数据传输功能
 * 
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public class ResAct extends Model {

	private String resuuId;//资源ID
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	private String memo;//备注
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//更新时间
	private String needRight;//是否需要权限控制
	private String actCode;//操作编码
	private String updateId;//更新人ID
	private String states;//状态
	private String id;//操作主键
	private String actUrl;//操作URL
	private String actName;//操作名称
	private String createId;//创建人ID
	
	private String sortBy;//排序方式
	private String sortFild;//排序字段
	
	/**
	 * 获得资源ID
	 * @return resuuId
	 */
	public String getResuuId() {
		return resuuId;
	}
	
	/**
	 * 设置资源ID
	 * @param resuuId 资源ID
	 */
	public void setResuuId(String resuuId) {
		this.resuuId = resuuId;
	}
	
	/**
	 * 获得创建时间
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获得备注
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}
	
	/**
	 * 设置备注
	 * @param memo 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 获得更新时间
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 获得是否需要权限控制
	 * @return needRight
	 */
	public String getNeedRight() {
		return needRight;
	}
	
	/**
	 * 设置是否需要权限控制
	 * @param needRight 是否需要权限控制
	 */
	public void setNeedRight(String needRight) {
		this.needRight = needRight;
	}
	
	/**
	 * 获得操作编码
	 * @return actCode
	 */
	public String getActCode() {
		return actCode;
	}
	
	/**
	 * 设置操作编码
	 * @param actCode 操作编码
	 */
	public void setActCode(String actCode) {
		this.actCode = actCode;
	}
	
	/**
	 * 获得更新人ID
	 * @return updateId
	 */
	public String getUpdateId() {
		return updateId;
	}
	
	/**
	 * 设置更新人ID
	 * @param updateId 更新人ID
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	/**
	 * 获得状态
	 * @return states
	 */
	public String getStates() {
		return states;
	}
	
	/**
	 * 设置状态
	 * @param states 状态
	 */
	public void setStates(String states) {
		this.states = states;
	}
	
	/**
	 * 获得操作主键
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 设置操作主键
	 * @param id 操作主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 获得操作URL
	 * @return actUrl
	 */
	public String getActUrl() {
		return actUrl;
	}
	
	/**
	 * 设置操作URL
	 * @param actUrl 操作URL
	 */
	public void setActUrl(String actUrl) {
		this.actUrl = actUrl;
	}
	
	/**
	 * 获得操作名称
	 * @return actName
	 */
	public String getActName() {
		return actName;
	}
	
	/**
	 * 设置操作名称
	 * @param actName 操作名称
	 */
	public void setActName(String actName) {
		this.actName = actName;
	}
	
	/**
	 * 获得创建人ID
	 * @return createId
	 */
	public String getCreateId() {
		return createId;
	}
	
	/**
	 * 设置创建人ID
	 * @param createId 创建人ID
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
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
}