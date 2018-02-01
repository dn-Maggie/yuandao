package com.dongnao.workbench.school.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：员工请假管理模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-09-12
 */
public class LeaveApply extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		   		
		            /**
	                 * 申请员工ID
	                 **/
				   			private String empId;
				   			/**
			                 * 申请员工姓名
			                 **/
						   	private String empName;
						   	/**
			                 * 申请员工部门
			                 **/
						   	private String empDept;
		   		
		            /**
	                 * 审核者ID
	                 **/
				   			private String checkId;
		   			/**
	                 * 审核者姓名
	                 **/
						   	private String checkName;
		   		
		            /**
	                 * 请假事由
	                 **/
				   			private String content;
		   		
		            /**
	                 * 请假日期始
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date startDate;
		
		            /**
	                 * 请假日期止
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date endDate;
		
		            /**
	                 * 请假天数
	                 **/
			   			private Double leaveDate;
		   		
		            /**
	                 * 申请日期
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date createDate;
		
		            /**
	                 * 审核日期
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date checkDate;
		
		            /**
	                 * 审核标志
	                 **/
			   			private Integer checkFlag;
		   		
		            /**
	                 * 请假扣款
	                 **/
			   			private String costSalary;
		   		
		            /**
	                 * 扣款标志
	                 **/
			   			private Integer isCost;
		   		
		            /**
	                 * 请假类型
	                 **/
		   			private Integer leaveType;
		   			/**
	                 * 部门负责人
	                 **/
		   			private String deptLeader;
		   			/**
	                 * 直接负责人
	                 **/
		   			private String straightLeader;
		   			private String straightLeaderName;
		   			/**
	                 * 总经理
	                 **/
		   			private String headLeader;
		   		
	
				
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
			 * 获取 申请员工ID
			 * @return String this.empId
			 */
			public String getEmpId(){
				return this.empId;
			}
			
			/**
			 * 设置 申请员工ID
			 * @param String empId 
			 */
			public void setEmpId(String empId){
				this.empId = empId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 审核者ID
			 * @return String this.checkId
			 */
			public String getCheckId(){
				return this.checkId;
			}
			
			/**
			 * 设置 审核者ID
			 * @param String checkId 
			 */
			public void setCheckId(String checkId){
				this.checkId = checkId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 请假事由
			 * @return String this.content
			 */
			public String getContent(){
				return this.content;
			}
			
			/**
			 * 设置 请假事由
			 * @param String content 
			 */
			public void setContent(String content){
				this.content = content;
			}
		   		
		
		
			
								/**
			 * 获取 请假日期始
			 * @return Date this.startDate
			 */
			public Date getStartDate(){
				return this.startDate;
			}
			
			/**
			 * 设置 请假日期始
			 * @param Date startDate 
			 */
			public void setStartDate(Date startDate){
				this.startDate = startDate;
			}
			
			/**
			 * 获取 请假日期止
			 * @return Date this.endDate
			 */
			public Date getEndDate(){
				return this.endDate;
			}
			
			/**
			 * 设置 请假日期止
			 * @param Date endDate 
			 */
			public void setEndDate(Date endDate){
				this.endDate = endDate;
			}
						
		  	/**
			 * 获取 请假天数
			 * @return Integer this.leaveDate
			 */
			public Double getLeaveDate(){
				return this.leaveDate;
			}
			
			/**
			 * 设置 请假天数
			 * @param BigDecimal leaveDate 
			 */
			public void setLeaveDate(Double leaveDate){
				this.leaveDate = leaveDate;
			}
			
			/**
			 * 获取 申请日期
			 * @return Date this.createDate
			 */
			public Date getCreateDate(){
				return this.createDate;
			}
			
			/**
			 * 设置 申请日期
			 * @param Date createDate 
			 */
			public void setCreateDate(Date createDate){
				this.createDate = createDate;
			}
			
			/**
			 * 获取 审核日期
			 * @return Date this.checkDate
			 */
			public Date getCheckDate(){
				return this.checkDate;
			}
			
			/**
			 * 设置 审核日期
			 * @param Date checkDate 
			 */
			public void setCheckDate(Date checkDate){
				this.checkDate = checkDate;
			}
						
		  			/**
			 * 获取 审核标志
			 * @return Integer this.checkFlag
			 */
			public Integer getCheckFlag(){
				return this.checkFlag;
			}
			
			/**
			 * 设置 审核标志
			 * @param BigDecimal checkFlag 
			 */
			public void setCheckFlag(Integer checkFlag){
				this.checkFlag = checkFlag;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 请假扣款
			 * @return String this.costSalary
			 */
			public String getCostSalary(){
				return this.costSalary;
			}
			
			/**
			 * 设置 请假扣款
			 * @param String costSalary 
			 */
			public void setCostSalary(String costSalary){
				this.costSalary = costSalary;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 扣款标志
			 * @return Integer this.isCost
			 */
			public Integer getIsCost(){
				return this.isCost;
			}
			
			/**
			 * 设置 扣款标志
			 * @param BigDecimal isCost 
			 */
			public void setIsCost(Integer isCost){
				this.isCost = isCost;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 请假类型
			 * @return Integer this.leaveType
			 */
			public Integer getLeaveType(){
				return this.leaveType;
			}
			
			/**
			 * 设置 请假类型
			 * @param BigDecimal leaveType 
			 */
			public void setLeaveType(Integer leaveType){
				this.leaveType = leaveType;
			}

			public String getEmpName() {
				return empName;
			}

			public void setEmpName(String empName) {
				this.empName = empName;
			}

			public String getCheckName() {
				return checkName;
			}

			public void setCheckName(String checkName) {
				this.checkName = checkName;
			}

			public String getDeptLeader() {
				return deptLeader;
			}

			public void setDeptLeader(String deptLeader) {
				this.deptLeader = deptLeader;
			}

			public String getEmpDept() {
				return empDept;
			}

			public void setEmpDept(String empDept) {
				this.empDept = empDept;
			}

			public String getStraightLeader() {
				return straightLeader;
			}

			public void setStraightLeader(String straightLeader) {
				this.straightLeader = straightLeader;
			}

			public String getHeadLeader() {
				return headLeader;
			}

			public void setHeadLeader(String headLeader) {
				this.headLeader = headLeader;
			}

			public String getStraightLeaderName() {
				return straightLeaderName;
			}

			public void setStraightLeaderName(String straightLeaderName) {
				this.straightLeaderName = straightLeaderName;
			}
		  		
		
		
			
			}