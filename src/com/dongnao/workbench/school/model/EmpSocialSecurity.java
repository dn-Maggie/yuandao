package com.dongnao.workbench.school.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

/**
 * @author Maggie
 * @Time   2017年10月12日下午5:22:40
 */
public class EmpSocialSecurity  extends Model implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     **/
		private String id;
	
	    /**
	     * 申请人ID
	     **/
		private String empId;
		private String empNo;
		private String empName;
		private String empNickName;
		private String empDept;
		private String empEntryDate;
		private String empBeFullDate;
		private String empStatus;
		  /**
         * 社保扣款
         **/
		private Double socialSecurity;
		/**
         * 创建时间
         **/
		@DateTimeFormat(pattern="yyyy-MM-dd") 
		private Date createDate;
		
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getEmpNo() {
			return empNo;
		}
		public void setEmpNo(String empNo) {
			this.empNo = empNo;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getEmpNickName() {
			return empNickName;
		}
		public void setEmpNickName(String empNickName) {
			this.empNickName = empNickName;
		}
		public String getEmpDept() {
			return empDept;
		}
		public void setEmpDept(String empDept) {
			this.empDept = empDept;
		}
		public String getEmpEntryDate() {
			return empEntryDate;
		}
		public void setEmpEntryDate(String empEntryDate) {
			this.empEntryDate = empEntryDate;
		}
		public String getEmpBeFullDate() {
			return empBeFullDate;
		}
		public void setEmpBeFullDate(String empBeFullDate) {
			this.empBeFullDate = empBeFullDate;
		}
		public String getEmpStatus() {
			return empStatus;
		}
		public void setEmpStatus(String empStatus) {
			this.empStatus = empStatus;
		}
		public Double getSocialSecurity() {
			return socialSecurity;
		}
		public void setSocialSecurity(Double socialSecurity) {
			this.socialSecurity = socialSecurity;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
}
