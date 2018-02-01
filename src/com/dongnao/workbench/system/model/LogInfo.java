package com.dongnao.workbench.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;


/**
 * 描述：logInfo模块实体类，负责页面与后台数据传输功能
 * 
 * @author wff
 * @version 1.0  2016-03-20
 */
public class LogInfo extends Model{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String actModule;
	private String actObj;
	private String actResult;
	private String userId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date actTime;
	private String actMessage;
	private String actAction;
	private String actorName;
	private String actType;

	//新增属性   结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
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
	 * @return id
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
	 * 获得操作模块
	 * @return actModule
	 */
	public String getActModule() {
		return this.actModule;
	}

	/**
	 * 设置操作模块
	 * @param actModule 操作模块
	 */
	public void setActModule(String actModule) {
		this.actModule = actModule;
	}

	/**
	 * 获得操作对象
	 * @return actObj
	 */
	public String getActObj() {
		return this.actObj;
	}

	/**
	 * 设置操作对象
	 * @param actObj 操作对象
	 */
	public void setActObj(String actObj) {
		this.actObj = actObj;
	}

	/**
	 * 获得操作结果
	 * @return actResult
	 */
	public String getActResult() {
		return this.actResult;
	}

	/**
	 * 设置操作结果
	 * @param actResult 操作结果
	 */
	public void setActResult(String actResult) {
		this.actResult = actResult;
	}

	/**
	 * 获得操作用户id
	 * @return userId
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 设置操作用户id
	 * @param userId 操作用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获得操作时间
	 * @return actTime
	 */
	public Date getActTime() {
		return this.actTime;
	}

	/**
	 * 设置操作时间
	 * @param actTime 操作时间
	 */
	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	/**
	 * 获得操作信息
	 * @return actMessage
	 */
	public String getActMessage() {
		return this.actMessage;
	}

	/**
	 * 设置操作信息
	 * @param actMessage 操作信息
	 */
	public void setActMessage(String actMessage) {
		this.actMessage = actMessage;
	}

	/**
	 * 获得操作方法
	 * @return actAction
	 */
	public String getActAction() {
		return this.actAction;
	}

	/**
	 * 设置操作方法 
	 * @param actAction 操作方法
	 */
	public void setActAction(String actAction) {
		this.actAction = actAction;
	}

	/**
	 * 获得操作名称
	 * @return actorName
	 */
	public String getActorName() {
		return this.actorName;
	}

	/**
	 * 设置操作人名称
	 * @param actorName 操作人名称
	 */
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	/**
	 * 获得操作类型 
	 * @return actType
	 */
	public String getActType() {
		return this.actType;
	}

	/**
	 * 设置操作类型
	 * @param actType 操作类型
	 */
	public void setActType(String actType) {
		this.actType = actType;
	}

	/**
	 * 获得结束时间
	 * @return endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 设置结束时间
	 * @param endTime 结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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