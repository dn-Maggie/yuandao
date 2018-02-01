package com.dongnao.workbench.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;


/**
 * 描述：数据字典类型模块实体类，负责页面与后台数据传输功能
 * 
 * @author zhou.zheng
 * 
 * @version 1.0 2013-11-07
 */
public class DictType extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String typeName;

	private String id;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	private String memo;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private String creatorId;

	private String states;

	private String typeCode;

	private String updaterId;

	private String isSystem;

	private String sortBy;
	private String sortFild;


	/**
	 * 获得类型名称
	 * @return typeName
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * 设置类型名称
	 * @param typeName 类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;

	}

	/**
	 * 获得ID
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置ID
	 * @param id ID
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
	 * 获得更新时间
	 * @return updateTime
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;

	}

	/**
	 * 获得创建人ID
	 * @return creatorId
	 */
	public String getCreatorId() {
		return this.creatorId;
	}

	/**
	 * 设置创建人ID
	 * @param creatorId 创建人ID
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;

	}

	/**
	 * 获得状态
	 * @return states
	 */
	public String getStates() {
		return this.states;
	}

	/**
	 * 设置状态
	 * @param states 状态
	 */
	public void setStates(String states) {
		this.states = states;

	}

	/**
	 * 获得类型编码
	 * @return typeCode
	 */
	public String getTypeCode() {
		return this.typeCode;
	}

	/**
	 * 设置类型编码
	 * @param typeCode 类型编码
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;

	}

	/**
	 * 获得更新人ID
	 * @return updaterId
	 */
	public String getUpdaterId() {
		return this.updaterId;
	}

	/**
	 * 设置更新人ID
	 * @param updaterId 更新人ID
	 */
	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;

	}

	/**
	 * 获得是否系统字典
	 * @return isSystem
	 */
	public String getIsSystem() {
		return this.isSystem;
	}

	/**
	 * 设置是否系统字典
	 * @param isSystem 是否系统字典
	 */
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;

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