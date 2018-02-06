package com.dongnao.workbench.school.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

public class EmpCost  extends Model implements java.io.Serializable{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private String empStatus;
		private String empId;
		private String empNickName;
		private String empDeptNo;
		private String empDept;
		private String id;
		
		@DateTimeFormat(pattern="yyyy-MM-dd") 
		private Date month;
		
		private Double costMoney;
		private int costType;
		private String costContent;
		private int status;
		public String getEmpStatus() {
			return empStatus;
		}
		public void setEmpStatus(String empStatus) {
			this.empStatus = empStatus;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getEmpNickName() {
			return empNickName;
		}
		public void setEmpNickName(String empNickName) {
			this.empNickName = empNickName;
		}
		public String getEmpDeptNo() {
			return empDeptNo;
		}
		public void setEmpDeptNo(String empDeptNo) {
			this.empDeptNo = empDeptNo;
		}
		public String getEmpDept() {
			return empDept;
		}
		public void setEmpDept(String empDept) {
			this.empDept = empDept;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Date getMonth() {
			return month;
		}
		public void setMonth(Date month) {
			this.month = month;
		}
		public Double getCostMoney() {
			return costMoney;
		}
		public void setCostMoney(Double costMoney) {
			this.costMoney = costMoney;
		}
		public int getCostType() {
			return costType;
		}
		public void setCostType(int costType) {
			this.costType = costType;
		}
		public String getCostContent() {
			return costContent;
		}
		public void setCostContent(String costContent) {
			this.costContent = costContent;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
	
		
}
