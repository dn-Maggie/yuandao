package com.dongnao.workbench.school.model;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：员工签卡模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-12-12
 */
public class EmpAbsence extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		   		
		            /**
	                 * 员工id
	                 **/
				   			private String empId;
				   			private String empNo;
				   			private String empNickName;
				   			private String empDept;
				   			private String deptLeader;
		   		
		            /**
	                 * 申请内容
	                 **/
				   			private String content;
			   		 /**
	                 * 补签类型
	                 **/
				   			private Integer absenceType;
		   		
		            /**
	                 * 补签日期
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
							private Date absenceDate;
		
		            /**
	                 * 申请时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
							private Date createDate;
					/**
	                 * 审核者ID
	                 **/
					private String checkId;
					private String checkName;
					/**
	                 * 审核标识
	                 **/
					private Integer checkFlag;
					/**
	                 * 审核日期
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
							private Date checkDate;
					/**
	                 * 直接负责人
	                 **/
					private String straightLeader;
					private String straightLeaderName;
				
  			/**
			 * 获取 
			 * @return String this.id
			 */
			public String getId(){
				return this.id;
			}
			
			/**
			 * 设置 
			 * @param String id 
			 */
			public void setId(String id){
				this.id = id;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 
			 * @return String this.empId
			 */
			public String getEmpId(){
				return this.empId;
			}
			
			/**
			 * 设置 
			 * @param String empId 
			 */
			public void setEmpId(String empId){
				this.empId = empId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 
			 * @return String this.content
			 */
			public String getContent(){
				return this.content;
			}
			
			/**
			 * 设置 
			 * @param String content 
			 */
			public void setContent(String content){
				this.content = content;
			}
		   		
		
		
			
								/**
			 * 获取 
			 * @return Date this.absenceDate
			 */
			public Date getAbsenceDate(){
				return this.absenceDate;
			}
			
			/**
			 * 设置 
			 * @param Date absenceDate 
			 */
			public void setAbsenceDate(Date absenceDate){
				this.absenceDate = absenceDate;
			}
								/**
			 * 获取 
			 * @return Date this.createDate
			 */
			public Date getCreateDate(){
				return this.createDate;
			}
			
			/**
			 * 设置 
			 * @param Date createDate 
			 */
			public void setCreateDate(Date createDate){
				this.createDate = createDate;
			}

			public Integer getAbsenceType() {
				return absenceType;
			}

			public void setAbsenceType(Integer absenceType) {
				this.absenceType = absenceType;
			}

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

			public String getCheckId() {
				return checkId;
			}

			public void setCheckId(String checkId) {
				this.checkId = checkId;
			}

			public Integer getCheckFlag() {
				return checkFlag;
			}

			public void setCheckFlag(Integer checkFlag) {
				this.checkFlag = checkFlag;
			}

			public String getStraightLeader() {
				return straightLeader;
			}

			public void setStraightLeader(String straightLeader) {
				this.straightLeader = straightLeader;
			}

			public String getDeptLeader() {
				return deptLeader;
			}

			public void setDeptLeader(String deptLeader) {
				this.deptLeader = deptLeader;
			}

			public String getStraightLeaderName() {
				return straightLeaderName;
			}

			public void setStraightLeaderName(String straightLeaderName) {
				this.straightLeaderName = straightLeaderName;
			}

			public String getCheckName() {
				return checkName;
			}

			
			public void setCheckName(String checkName) {
				this.checkName = checkName;
			}
			
			public Date getCheckDate() {
				return checkDate;
			}

			public void setCheckDate(Date checkDate) {
				this.checkDate = checkDate;
			}

	}