package com.dongnao.workbench.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;


/**
 * 描述：数据字典信息模块实体类，负责页面与后台数据传输功能
 * 
 * @author zhou.zheng
 * 
 * @version 1.0 2013-11-04
 */
public class DictInfo extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String isDelete;

	private String id;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	private String orderNo;

	private String memo;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	private String dictCode;

	private String dictTypeCode;

	private String dictTypeName;
	
	private String creatorId;

	private String states;

	private String dictName;

	private String updaterId;

	//排序顺序
	private String sortBy;
	//排序字段
	private String sortFild;
	
	/**
	 * 获得 是否删除
	 * @return isDelete
	 */
	public String getIsDelete() {
		return this.isDelete;
	}

	/**
	 * 设置是否删除
	 * @param isDelete 是否删除
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;

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
	 * 获得序号
	 * @return orderNo
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 设置序号
	 * @param orderNo 序号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;

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
	 * 获得数据字典编码
	 * @return dictCode
	 */
	public String getDictCode() {
		return this.dictCode;
	}

	/**
	 * 设置数据字典编码
	 * @param dictCode 数据字典编码
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;

	}

	/**
	 * 获得类型编码
	 * @return dictTypeCode
	 */
	public String getDictTypeCode() {
		return this.dictTypeCode;
	}

	/**
	 * 设置类型编码
	 * @param dictTypeCode 类型编码
	 */
	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;

	}

	/**
	 * 获取字典类型名称 
	 * @return 字典类型名称
	 */
	public String getDictTypeName() {
		return dictTypeName;
	}

	/**
	 * 设置字典类型名称
	 * @param dictTypeName 字典类型名称
	 */
	public void setDictTypeName(String dictTypeName) {
		this.dictTypeName = dictTypeName;
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
	 * 获得数据字典名称
	 * @return dictName
	 */
	public String getDictName() {
		return this.dictName;
	}

	/**
	 * 设置数据字典名称
	 * @param dictName 数据字典名称
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;

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