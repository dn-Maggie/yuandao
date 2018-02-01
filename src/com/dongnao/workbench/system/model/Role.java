package com.dongnao.workbench.system.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;


/**
 * 描述：角色模块实体类，负责页面与后台数据传输功能
 * 
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public class Role extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	private String roleDesc;
	private String roleId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String memo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	private String creatorId;
	private String states;
	private String roleName;
	private String roleCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private String updaterId;

	private String statesName;

	private String unitId;

	private String key;

	/**
	 * 是否删除
	 */
	private String isDelete;
	/**
	 * 所属局id
	 */
	private String bureauId;
	
	/**
	 * 角色首页查看业绩标识
	 */
	private int hpvp;

	/**
	 * 获取开始时间
	 * 
	 * @return Date this.startTime
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * 设置开始时间
	 * 
	 * @param startTime
	 *            开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获取角色描述
	 * 
	 * @return String this.roleDesc
	 */
	public String getRoleDesc() {
		return this.roleDesc;
	}

	/**
	 * 设置角色描述
	 * 
	 * @param roleDesc
	 *            角色描述
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	/**
	 * 获取角色UUID
	 * 
	 * @return String this.roleId
	 */
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * 设置角色UUID
	 * 
	 * @param roleId
	 *            角色UUID
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return Date this.createTime
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
	 * 获取备注
	 * 
	 * @return String this.memo
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * 设置备注
	 * 
	 * @param memo
	 *            备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return Date this.updateTime
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
	 * 获取创建人UUID
	 * 
	 * @return String this.creatorId
	 */
	public String getCreatorId() {
		return this.creatorId;
	}

	/**
	 * 设置创建人UUID
	 * 
	 * @param creatorId
	 *            创建人UUID
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 获取状态
	 * 
	 * @return String this.states
	 */
	public String getStates() {
		return this.states;
	}

	/**
	 * 设置状态
	 * 
	 * @param states
	 *            状态
	 */
	public void setStates(String states) {
		this.states = states;
	}

	/**
	 * 获取角色名称
	 * 
	 * @return String this.roleName
	 */
	public String getRoleName() {
		return this.roleName;
	}

	/**
	 * 设置角色名称
	 * 
	 * @param roleName
	 *            角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 获取角色编码
	 * 
	 * @return String this.roleCode
	 */
	public String getRoleCode() {
		return this.roleCode;
	}

	/**
	 * 设置角色编码
	 * 
	 * @param roleCode
	 *            角色编码
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * 获取结束时间
	 * 
	 * @return Date this.endTime
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * 设置结束时间
	 * 
	 * @param endTime
	 *            结束数据
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获取更新人UUID
	 * 
	 * @return String this.updaterId
	 */
	public String getUpdaterId() {
		return this.updaterId;
	}

	/**
	 * 设置更新人UUID
	 * 
	 * @param updaterId
	 *            更新人UUID
	 */
	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	/**
	 * 获取状态映射名称
	 * 
	 * @return statesName
	 */
	public String getStatesName() {
		return statesName;
	}

	/**
	 * 获取单位ID
	 * 
	 * @return 获取单位ID
	 */
	public String getUnitId() {
		return unitId;
	}

	/**
	 * 设置单位ID
	 * 
	 * @param unitId
	 *            单位ID
	 */
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	/**
	 * 获取权限关联ID
	 * 
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置权限关联ID
	 * 
	 * @param key
	 *            用户ID
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 设置状态显示名称
	 * 
	 * @param statesName
	 *            状态显示名称
	 */
	public void setStatesName(String statesName) {
		this.statesName = statesName;
	}

	/**
	 * 获取 是否删除
	 * 
	 * @return
	 */
	public String getIsDelete() {
		return isDelete;
	}

	/**
	 * 设置 是否删除
	 * 
	 * @param isDelete
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 获取 所属局 id
	 * 
	 * @return
	 */
	public String getBureauId() {
		return bureauId;
	}

	/**
	 * 设置 所属局id
	 * 
	 * @param bureauId
	 */
	public void setBureauId(String bureauId) {
		this.bureauId = bureauId;
	}

	public int getHpvp() {
		return hpvp;
	}

	public void setHpvp(int hpvp) {
		this.hpvp = hpvp;
	}
	

}