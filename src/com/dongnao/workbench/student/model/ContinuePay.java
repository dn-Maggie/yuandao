package com.dongnao.workbench.student.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：补款管理模块实体类，负责页面与后台数据传输功能
 *
 * @author yang.liu
 * @version 1.0 2016-05-02
 */
public class ContinuePay extends Model{
	
	
		            /**
	                 * 
	                 **/
				   			private String id;
		   		
		            /**
	                 * VIP学员id
	                 **/
				   			private String stuId;
		   		
		            /**
	                 * 补款学员姓名
	                 **/
				   			private String stuName;
		   		
		            /**
	                 * 补款金额
	                 **/
				   			private Double money;
		   		
		            /**
	                 * 录入人
	                 **/
				   			private String operator;
		   		
		            /**
	                 * 时间
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
			                 * 学科Id
			                 **/
						    private String subjectId;
						    /**
			                 * 课程Id
			                 **/
						    private String courseId;
						    
				   			private String qq;
				   			private String subjectName;
				   			private String stuname;
				   			private String fullname;
				   			private String subname;
				   			private String stuJointime;
				   			
				   			private String joinStartDate;
				   			private String joinEndDate;
				   			private String startDate;
				   			private String endDate;
				
				   			public String getSubname() {
								return subname;
							}

							public void setSubname(String subname) {
								this.subname = subname;
							}

							public String getQq() {
								return qq;
							}

							public void setQq(String qq) {
								this.qq = qq;
							}

							public String getStuname() {
								return stuname;
							}

							public void setStuname(String stuname) {
								this.stuname = stuname;
							}

							public String getFullname() {
								return fullname;
							}

							public void setFullname(String fullname) {
								this.fullname = fullname;
							}

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
			 * 获取 VIP学员id
			 * @return String this.stuId
			 */
			public String getStuId(){
				return this.stuId;
			}
			
			/**
			 * 设置 VIP学员id
			 * @param String stuId 
			 */
			public void setStuId(String stuId){
				this.stuId = stuId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 补款学员姓名
			 * @return String this.stuName
			 */
			public String getStuName(){
				return this.stuName;
			}
			
			/**
			 * 设置 补款学员姓名
			 * @param String stuName 
			 */
			public void setStuName(String stuName){
				this.stuName = stuName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 补款金额
			 * @return Double this.money
			 */
			public Double getMoney(){
				return this.money;
			}
			
			/**
			 * 设置 补款金额
			 * @param BigDecimal money 
			 */
			public void setMoney(Double money){
				this.money = money;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 催款人
			 * @return String this.operator
			 */
			public String getOperator(){
				return this.operator;
			}
			
			/**
			 * 设置 催款人
			 * @param String operator 
			 */
			public void setOperator(String operator){
				this.operator = operator;
			}
		   		
		
		
			
								/**
			 * 获取 时间
			 * @return Date this.time
			 */
			public Date getTime(){
				return this.time;
			}
			
			/**
			 * 设置 时间
			 * @param Date time 
			 */
			public void setTime(Date time){
				this.time = time;
			}
						
		  			/**
			 * 获取 备注
			 * @return String this.notes
			 */
			public String getNotes(){
				return this.notes;
			}
			
			/**
			 * 设置 备注
			 * @param String notes 
			 */
			public void setNotes(String notes){
				this.notes = notes;
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

			public String getSubjectId() {
				return subjectId;
			}

			public void setSubjectId(String subjectId) {
				this.subjectId = subjectId;
			}

			public String getCourseId() {
				return courseId;
			}

			public void setCourseId(String courseId) {
				this.courseId = courseId;
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