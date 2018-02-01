package com.dongnao.workbench.common.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.page.Page;

/**
 * 描述：实体类，负责分页数据传输功能
 * 
 * @author joan.xiong
 * @version 1.0 2014-05-12
 */
public class Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 分页对象
	private Page page;
	/**
	 * 排序字段
	 */
	private String orderFields;
	/**
	 * 排序方式
	 */
	private String order;

	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updated;
	/**
	 * 修改人ID
	 */
	private String updatedby;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date created;
	/**
	 * 创建人ID
	 */
	private String createdby;
	/**
	 * 业务组织机构ID
	 */
	private String orgId;

	/**
	 * 所属组织机构名称
	 **/
	private String orgName;
	/**
	 * 1是可用未删除，0是不可能已删除
	 */
	private Integer isActive;
	private Map<String,Object> propsMap= new HashMap<String,Object>();
	
	public Map<String, Object> getPropsMap() {
		return propsMap;
	}

	public void setPropsMap(Map<String, Object> propsMap) {
		this.propsMap = propsMap;
	}

	/**
	 * 获得分页对象
	 * 
	 * @return page
	 */
	public Page getPage() {
		return this.page;
	}

	/**
	 * 设置分页对象
	 * 
	 * @param page
	 *            分页对象
	 */
	public void setPage(Page page) {
		this.page = page;
		this.order = page.getOrder();
		this.orderFields = page.getOrderFields();
	}

	/**
	 * 获取排序字段名称
	 * 
	 * @return 排序字段名称
	 */
	public String getOrderFields() {
		return this.orderFields;
	}

	/**
	 * 获取排序方式
	 * 
	 * @return order
	 */
	public String getOrder() {
		return this.order;
	}

	/**
	 * 设置排序字段
	 * 
	 * @param orderFields
	 *            排序字段
	 */
	public void setOrderFields(String orderFields) {
		this.orderFields = orderFields;
	}

	/**
	 * 设置排序方式
	 * 
	 * @param order
	 *            排序方式
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	/**
	 * 获取 所属组织机构名称
	 * 
	 * @return String this.orgName
	 */
	public String getOrgName() {
		return this.orgName;
	}

	/**
	 * 设置 所属组织机构名称
	 * 
	 * @param String
	 *            orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
