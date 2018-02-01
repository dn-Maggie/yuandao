package com.dongnao.workbench.student.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：VIP学员退款信息模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-08-01
 */
public class VipRefund extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		   		
		            /**
	                 * 退款学员ID
	                 **/
				   			private String stuId;
		   		
		            /**
	                 * 退款学员姓名
	                 **/
				   			private String stuName;
		   		
		            /**
	                 * 退款金额
	                 **/
				   			private String refund;
		   		
		            /**
	                 * 退款原因
	                 **/
				   			private String reason;
		   		
		            /**
	                 * 退款时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
			private Date time;
		
					 /**
	                 * 备注
	                 **/
				    private String notes;
				    
				    /**
	                 * 是否分配业绩
	                 **/
				    private String isCount;
				    /**
	                 * 录入人
	                 **/
				    private String operator;
					private String fullname;
					private String subjectName;
					private String courseName;
					private String qq;
					private String stuJointime;
					
					private String joinStartDate;
		   			private String joinEndDate;
		   			private String startDate;
		   			private String endDate;
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
			 * 获取 退款学员ID
			 * @return String this.stuId
			 */
			public String getStuId(){
				return this.stuId;
			}
			
			/**
			 * 设置 退款学员ID
			 * @param String stuId 
			 */
			public void setStuId(String stuId){
				this.stuId = stuId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 退款学员姓名
			 * @return String this.stuName
			 */
			public String getStuName(){
				return this.stuName;
			}
			
			/**
			 * 设置 退款学员姓名
			 * @param String stuName 
			 */
			public void setStuName(String stuName){
				this.stuName = stuName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 退款金额
			 * @return String this.refund
			 */
			public String getRefund(){
				return this.refund;
			}
			
			/**
			 * 设置 退款金额
			 * @param String refund 
			 */
			public void setRefund(String refund){
				this.refund = refund;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 退款原因
			 * @return String this.reason
			 */
			public String getReason(){
				return this.reason;
			}
			
			/**
			 * 设置 退款原因
			 * @param String reason 
			 */
			public void setReason(String reason){
				this.reason = reason;
			}
		   		
		
		
			
								/**
			 * 获取 退款时间
			 * @return Date this.time
			 */
			public Date getTime(){
				return this.time;
			}
			
			/**
			 * 设置 退款时间
			 * @param Date time 
			 */
			public void setTime(Date time){
				this.time = time;
			}

			public String getNotes() {
				return notes;
			}

			public void setNotes(String notes) {
				this.notes = notes;
			}

			public String getOperator() {
				return operator;
			}

			public void setOperator(String operator) {
				this.operator = operator;
			}

			public String getFullname() {
				return fullname;
			}

			public void setFullname(String fullname) {
				this.fullname = fullname;
			}

			public String getCourseName() {
				return courseName;
			}

			public void setCourseName(String courseName) {
				this.courseName = courseName;
			}

			public String getQq() {
				return qq;
			}

			public void setQq(String qq) {
				this.qq = qq;
			}

			public String getStuJointime() {
				return stuJointime;
			}

			public void setStuJointime(String stuJointime) {
				this.stuJointime = stuJointime;
			}

			public String getIsCount() {
				return isCount;
			}

			public void setIsCount(String isCount) {
				this.isCount = isCount;
			}


			public String getSubjectName() {
				return subjectName;
			}

			public void setSubjectName(String subjectName) {
				this.subjectName = subjectName;
			}

			public String getJoinStartDate() {
				return joinStartDate;
			}

			public void setJoinStartDate(String joinStartDate) {
				this.joinStartDate = joinStartDate;
			}

			public String getJoinEndDate() {
				return joinEndDate;
			}

			public void setJoinEndDate(String joinEndDate) {
				this.joinEndDate = joinEndDate;
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