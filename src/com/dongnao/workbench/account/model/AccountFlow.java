package com.dongnao.workbench.account.model;

import java.util.Date;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：财务流水模块实体类，负责页面与后台数据传输功能
 *
 * @author cjw
 * @version 1.0 2016-05-01
 */
public class AccountFlow extends Model {
	/**
	 * 财务流水id
	 **/
	private String id;
	/**
	 * 关联学生id
	 **/
	private String stuId;
	/**
	 * 关联员工id
	 **/
	private String empId;
	/**
	 * 科目id
	 **/
	private String accountId;

	/**
	 * 科目编号
	 **/
	private String accountNo;

	/**
	 * 科目类型
	 **/
	private Integer accountType;

	/**
	 * 科目名称
	 **/
	private String accountName;
	/**
	 * 不含科目名称
	 **/
	private String accountNameExcept;
	/**
	 * 包含科目名称
	 **/
	private String accountNameInclude;
	/**
	 * 科目名称+发生时间
	 **/
	private String createTime;
	
	/**
	 * 发生时间
	 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createDate;

	/**
	 * 发生金额
	 **/
	private String money;

	/**
	 * 备注
	 **/
	private String note;
	/**
	 * 是否计入凭证
	 **/
	private Integer isAccount;
	/**
	 * 科目名称
	 **/
	private String subjectName;
	/**
	 * 课程名称
	 **/
	private String courseName;
	/**
	 * 学生名称
	 **/
	private String stuName;
	private String startDate;
	private String endDate;
	
	/**
	 * 获取 财务流水id
	 * 
	 * @return String this.id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 财务流水id
	 * 
	 * @param String
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 科目id
	 * 
	 * @return String this.accountId
	 */
	public String getAccountId() {
		return this.accountId;
	}

	/**
	 * 设置 科目id
	 * 
	 * @param String
	 *            accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	/**
	 * 获取 科目编号
	 * 
	 * @return String this.accountNo
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * 设置 科目编号
	 * 
	 * @param String
	 *            accountNo
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 获取 科目类型
	 * 
	 * @return Integer this.accountType
	 */
	public Integer getAccountType() {
		return this.accountType;
	}

	/**
	 * 设置 科目类型
	 * 
	 * @param BigDecimal
	 *            accountType
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	/**
	 * 获取 科目名称
	 * 
	 * @return String this.accountName
	 */
	public String getAccountName() {
		return this.accountName;
	}

	/**
	 * 设置 科目名称
	 * 
	 * @param String
	 *            accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * 获取 发生时间
	 * 
	 * @return Date this.createDate
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置 发生时间
	 * 
	 * @param Date
	 *            createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取 发生金额
	 * 
	 * @return String this.money
	 */
	public String getMoney() {
		return this.money;
	}

	/**
	 * 设置 发生金额
	 * 
	 * @param String
	 *            money
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * 获取 备注
	 * 
	 * @return String this.note
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * 设置 备注
	 * 
	 * @param String
	 *            note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	public Integer getIsAccount() {
		return isAccount;
	}

	public void setIsAccount(Integer isAccount) {
		this.isAccount = isAccount;
	}

	public String getAccountNameExcept() {
		return accountNameExcept;
	}

	public void setAccountNameExcept(String accountNameExcept) {
		this.accountNameExcept = accountNameExcept;
	}

	public String getAccountNameInclude() {
		return accountNameInclude;
	}

	public void setAccountNameInclude(String accountNameInclude) {
		this.accountNameInclude = accountNameInclude;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


}