package com.dongnao.workbench.system.model;

import com.dongnao.workbench.common.bean.Model;


/**
 * 描述：settingInfo模块实体类，负责页面与后台数据传输功能
 * 
 * @author wff
 * @version 1.0  2013-11-21
 */
public class SettingInfo extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String variableValue;
	private String variableName;
	private String varNameCn;
	//排序字段
	private String orderBy;
	//排序类型
	private String orderType;
	/**
	 * 所属局id
	 */
	private String bureauId;


        /**
	 * 获取 所属局 id
	 * @return
	 */
	public String getBureauId() {
		return bureauId;
	}

	/**
	 * 设置  所属局id
	 * @param bureauId
	 */
	public void setBureauId(String bureauId) {
		this.bureauId = bureauId;
	}
	/**
	 * 获得主键id
	 * @return 主键id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置主键id
	 * @param id 主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获得功能值
	 * @return variableValue
	 */
	public String getVariableValue() {
		return this.variableValue;
	}

	/**
	 * 设置功能值
	 * @param variableValue 功能值
	 */
	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	/**
	 * 获得功能名称
	 * @return variableName
	 */
	public String getVariableName() {
		return this.variableName;
	}

	/**
	 * 设置功能名称
	 * @param variableName 功能名称
	 */
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	/**
	 * 获得功能名称中文显示名称
	 * @return 中文显示名称
	 */
	public String getVarNameCn() {
		return varNameCn;
	}

	/**
	 * 设置功能名称中文显示名称
	 * @param varNameCn 中文显示名称
	 */
	public void setVarNameCn(String varNameCn) {
		this.varNameCn = varNameCn;
	}

	/**
	 * 获得排序字段
	 * @return orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段
	 * @param orderBy 排序字段
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 获得排序方式
	 * @return orderType
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * 设置排序方式
	 * @param orderType 排序方式
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}