package com.dongnao.workbench.school.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：员工工资表模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-12-14
 */
public class EmpSalary extends Model implements java.io.Serializable{
		            /**
	 * 
	 */
	private static final long serialVersionUID = -5217032731630006972L;

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
		   			private String empBank;
		   			private String empBankCard;
		   			private String empEntryDate;
		   			private String empBeFullDate;
		   			private String empMobile;
		   			private String empStatus;
		            /**
	                 * 基本工资
	                 **/
		   			private Double basicSalary;
			   		 /**
	                 * 薪级工资
	                 **/
		   			private Double dutyLevelSalary;
		   		
		            /**
	                 * 应发工资
	                 **/
		   			private Double shouldSalary;
		   		
		            /**
	                 * 社保扣款
	                 **/
		   			private Double socialSecurity;
		   		
		   			/**
	                 * 社保总额
	                 **/
		   			private Double allSocialSecurity;
		   		
		            /**
	                 * 请假扣款
	                 **/
		   			private Double leaveDay;
		   			private Double leaveCost;
		   			private Double actualattendance;//实际出勤天数
		   			private Double  trafficsubsidies;//交通补助
		   			/**
	                 * 迟到早退扣款
	                 **/
		   			private Integer lateEarlyTime;
		   			private Double lateEarlyCost;
		   			/**
	                 * 打卡异常扣款
	                 **/
		   			private Integer attendanceAnomalyTime;
		   			private Double attendanceAnomalyCost;
		   		
		            /**
	                 * 餐补
	                 **/
		   			private Double tableMoney;
		   		
		            /**
	                 * 住房补贴
	                 **/
		   			private Double housingAllowance;
		   		
		            /**
	                 * 绩效奖金
	                 **/
		   			private Double meritRaise;
		   			/**
	                 * 提成
	                 **/
		   			private Double pushMoney;
		            /**
	                 * 其他
	                 **/
		   			private Double rests;
		   		
		            /**
	                 * 实发工资
	                 **/
		   			private Double actualSalary;
		   		
				   	/**
	                 * 发送Email标识
	                 **/
		   			private String sendFlag;
				   	/**
	                 * 审核人ID
	                 **/
		   			private String checkId;
		   			private String checkName;
				   	/**
	                 * 审核标识
	                 **/
		   			private String checkFlag;
		   			
			   			
					   	public String getEmpNo() {
							return empNo;
						}
	
						public void setEmpNo(String empNo) {
							this.empNo = empNo;
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
	
						public String getSendFlag() {
							return sendFlag;
						}
	
						public void setSendFlag(String sendFlag) {
							this.sendFlag = sendFlag;
						}
	
						public String getCheckId() {
							return checkId;
						}
	
						public void setCheckId(String checkId) {
							this.checkId = checkId;
						}
	
						public String getCheckName() {
							return checkName;
						}
	
						public void setCheckName(String checkName) {
							this.checkName = checkName;
						}
	
						public String getCheckFlag() {
							return checkFlag;
						}
	
						public void setCheckFlag(String checkFlag) {
							this.checkFlag = checkFlag;
						}
	
						public String getAssignFlag() {
							return assignFlag;
						}
	
						public void setAssignFlag(String assignFlag) {
							this.assignFlag = assignFlag;
						}
	
						public Date getCreateDate() {
							return createDate;
						}
	
						public void setCreateDate(Date createDate) {
							this.createDate = createDate;
						}
	
						public Date getSendDate() {
							return sendDate;
						}
	
						public void setSendDate(Date sendDate) {
							this.sendDate = sendDate;
						}
	
						public Date getCheckDate() {
							return checkDate;
						}
	
						public void setCheckDate(Date checkDate) {
							this.checkDate = checkDate;
						}
	
						/*public void setBasicSalary(Double basicSalary) {
							this.basicSalary = basicSalary;
						}*/

					/**
	                 * 分发标识（财务结算）
	                 **/
				   			private String assignFlag; 
				   	/**
	                 * 备注
	                 **/
				   			private String note;
		   			/**
	                 * 创建时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd") 
						private Date createDate;
					
					/**
	                 * 发送时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date sendDate;
					
					/**
	                 * 审核时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date checkDate;

					
			/**
			 * 获取 主键
			 * @return String this.id
			 */
			public String getId(){
				return this.id;
			}
			
			/**
			 * 设置 主键
			 * @param String id 
			 */
			public void setId(String id){
				this.id = id;
			}
		   		
		
		
			
						
  			/**
			 * 获取 申请人ID
			 * @return String this.empId
			 */
			public String getEmpId(){
				return this.empId;
			}
			
			/**
			 * 设置 申请人ID
			 * @param String empId 
			 */
			public void setEmpId(String empId){
				this.empId = empId;
			}
		   		
		
		
			
						
  			/**
			 * 获取 基本工资
			 * @return String this.basicSalary
			 */
			public Double getBasicSalary(){
				return this.basicSalary;
			}
			
			/**
			 * 设置 基本工资
			 * @param String basicSalary 
			 */
			public void setBasicSalary(Double basicSalary){
				this.basicSalary = basicSalary;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 应发工资
			 * @return String this.shouldSalary
			 */
			public Double getShouldSalary(){
				return this.shouldSalary;
			}
			
			/**
			 * 设置 应发工资
			 * @param String shouldSalary 
			 */
			public void setShouldSalary(Double shouldSalary){
				this.shouldSalary = shouldSalary;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 社保扣款
			 * @return String this.socialSecurity
			 */
			public Double getSocialSecurity(){
				return this.socialSecurity;
			}
			
			/**
			 * 设置 社保扣款
			 * @param String socialSecurity 
			 */
			public void setSocialSecurity(Double socialSecurity){
				this.socialSecurity = socialSecurity;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 请假扣款
			 * @return String this.leaveCost
			 */
			public Double getLeaveCost(){
				return this.leaveCost;
			}
			
			/**
			 * 设置 请假扣款
			 * @param String leaveCost 
			 */
			public void setLeaveCost(Double leaveCost){
				this.leaveCost = leaveCost;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 餐补
			 * @return String this.tableMoney
			 */
			public Double getTableMoney(){
				return this.tableMoney;
			}
			
			/**
			 * 设置 餐补
			 * @param String tableMoney 
			 */
			public void setTableMoney(Double tableMoney){
				this.tableMoney = tableMoney;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 住房补贴
			 * @return String this.housingAllowance
			 */
			public Double getHousingAllowance(){
				return this.housingAllowance;
			}
			
			/**
			 * 设置 住房补贴
			 * @param String housingAllowance 
			 */
			public void setHousingAllowance(Double housingAllowance){
				this.housingAllowance = housingAllowance;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 绩效奖金
			 * @return String this.meritRaise
			 */
			public Double getMeritRaise(){
				return this.meritRaise;
			}
			
			/**
			 * 设置 绩效奖金
			 * @param String meritRaise 
			 */
			public void setMeritRaise(Double meritRaise){
				this.meritRaise = meritRaise;
			}
		   		
		
		
			
						
		  			public Double getPushMoney() {
				return pushMoney;
			}

			public void setPushMoney(Double pushMoney) {
				this.pushMoney = pushMoney;
			}

					/**
			 * 获取 其他
			 * @return String this.rests
			 */
			public Double getRests(){
				return this.rests;
			}
			
			/**
			 * 设置 其他
			 * @param String rests 
			 */
			public void setRests(Double rests){
				this.rests = rests;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 实发工资
			 * @return String this.actualSalary
			 */
			public Double getActualSalary(){
				return this.actualSalary;
			}
			
			/**
			 * 设置 实发工资
			 * @param String actualSalary 
			 */
			public void setActualSalary(Double actualSalary){
				this.actualSalary = actualSalary;
			}
		
		  			/**
			 * 获取 备注
			 * @return String this.note
			 */
			public String getNote(){
				return this.note;
			}
			
			/**
			 * 设置 备注
			 * @param String note 
			 */
			public void setNote(String note){
				this.note = note;
			}

			public Double getDutyLevelSalary() {
				return dutyLevelSalary;
			}

			public void setDutyLevelSalary(Double dutyLevelSalary) {
				this.dutyLevelSalary = dutyLevelSalary;
			}

			public String getEmpName() {
				return empName;
			}

			public void setEmpName(String empName) {
				this.empName = empName;
			}

			public String getEmpBankCard() {
				return empBankCard;
			}

			public void setEmpBankCard(String empBankCard) {
				this.empBankCard = empBankCard;
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

			public String getEmpMobile() {
				return empMobile;
			}

			public void setEmpMobile(String empMobile) {
				this.empMobile = empMobile;
			}


			public String getEmpStatus() {
				return empStatus;
			}

			public void setEmpStatus(String empStatus) {
				this.empStatus = empStatus;
			}

			public Double getLeaveDay() {
				return leaveDay;
			}

			public void setLeaveDay(Double leaveDay) {
				this.leaveDay = leaveDay;
			}

			public Integer getLateEarlyTime() {
				return lateEarlyTime;
			}

			public void setLateEarlyTime(Integer lateEarlyTime) {
				this.lateEarlyTime = lateEarlyTime;
			}

			public Double getLateEarlyCost() {
				return lateEarlyCost;
			}

			public void setLateEarlyCost(Double lateEarlyCost) {
				this.lateEarlyCost = lateEarlyCost;
			}

			public Integer getAttendanceAnomalyTime() {
				return attendanceAnomalyTime;
			}

			public void setAttendanceAnomalyTime(Integer attendanceAnomalyTime) {
				this.attendanceAnomalyTime = attendanceAnomalyTime;
			}

			public Double getAttendanceAnomalyCost() {
				return attendanceAnomalyCost;
			}

			public void setAttendanceAnomalyCost(Double attendanceAnomalyCost) {
				this.attendanceAnomalyCost = attendanceAnomalyCost;
			}

			public String getEmpBank() {
				return empBank;
			}

			public void setEmpBank(String empBank) {
				this.empBank = empBank;
			}

			public Double getActualattendance() {
				return actualattendance;
			}

			public void setActualattendance(Double actualattendance) {
				this.actualattendance = actualattendance;
			}

			public Double getTrafficsubsidies() {
				return trafficsubsidies;
			}

			public void setTrafficsubsidies(Double trafficsubsidies) {
				this.trafficsubsidies = trafficsubsidies;
			}

			public Double getAllSocialSecurity() {
				return allSocialSecurity;
			}

			public void setAllSocialSecurity(Double allSocialSecurity) {
				this.allSocialSecurity = allSocialSecurity;
			}



			
			
		}