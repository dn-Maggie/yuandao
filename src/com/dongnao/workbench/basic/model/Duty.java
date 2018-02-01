package com.dongnao.workbench.basic.model;
import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：系统职务表模块实体类，负责页面与后台数据传输功能
 * 
 * @author yao.su
 * @version 1.0 2016-04-04
 */
public class Duty extends Model {
	/**
	 * 主键ID
	 **/
	private String id;
	/**
	 * 所属部门ID
	 **/
	private String orgId;
	/**
	 * 职务类型
	 **/
	private String dutyType;

	/**
	 * 职务类型名称（来自数据字典）
	 **/
	private String dutyTypeName;

	/**
	 * 职务名称
	 **/
	private String dutyName;

	/**
	 * 职务描述
	 **/
	private String dutyDesc;

	/**
	 * 职务级别：1:集团级；2：省级；3：分公司级
	 **/
	private String dutySort;

	/**
	 * 上级职务
	 **/
	private String pid;

	/**
	 * 获取 主键ID
	 * 
	 * @return String this.id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 主键ID
	 * 
	 * @param String
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	/**
	 * 获取 所属部门ID
	 * 
	 * @return String this.orgId
	 */
	public String getOrgId() {
		return this.orgId;
	}

	/**
	 * 设置 所属部门ID
	 * 
	 * @param String
	 *            orgId
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}	
	
	/**
	 * 获取 职务类型
	 * 
	 * @return String this.dutyType
	 */
	public String getDutyType() {
		return this.dutyType;
	}

	/**
	 * 设置 职务类型
	 * 
	 * @param String
	 *            dutyType
	 */
	public void setDutyType(String dutyType) {
		this.dutyType = dutyType;
	}

	/**
	 * 获取 职务名称
	 * 
	 * @return String this.dutyName
	 */
	public String getDutyName() {
		return this.dutyName;
	}

	/**
	 * 设置 职务名称
	 * 
	 * @param String
	 *            dutyName
	 */
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	/**
	 * 获取 职务描述
	 * 
	 * @return String this.dutyDesc
	 */
	public String getDutyDesc() {
		return this.dutyDesc;
	}

	/**
	 * 设置 职务描述
	 * 
	 * @param String
	 *            dutyDesc
	 */
	public void setDutyDesc(String dutyDesc) {
		this.dutyDesc = dutyDesc;
	}

	/**
	 * 获取 职务级别：1:集团级；2：省级；3：分公司级
	 * 
	 * @return String this.dutySort
	 */
	public String getDutySort() {
		return this.dutySort;
	}

	/**
	 * 设置 职务级别：1:集团级；2：省级；3：分公司级
	 * 
	 * @param String
	 *            dutySort
	 */
	public void setDutySort(String dutySort) {
		this.dutySort = dutySort;
	}

	/**
	 * 获取 上级职务
	 * 
	 * @return String this.pid
	 */
	public String getPid() {
		return this.pid;
	}

	/**
	 * 设置 上级职务
	 * 
	 * @param String
	 *            pid
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDutyTypeName() {
		return dutyTypeName;
	}

	public void setDutyTypeName(String dutyTypeName) {
		this.dutyTypeName = dutyTypeName;
	}


}