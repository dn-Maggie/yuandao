package com.dongnao.workbench.school.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：员工绩效信息表模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-07-24
 */
public class EmpPerformance extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		   		
		   			/**
	                 * 参与岗位
	                 **/
						   	private String position;
		            /**
	                 * 员工ID
	                 **/
				   			private String employeeId;
		   			/**
	                 * 员工部门
	                 **/
				   			private String empDept;
		   		
		            /**
	                 * 员工姓名
	                 **/
				   			private String employeeName;
		   		
		            /**
	                 * 应付学费
	                 **/
				   			private String shouldPay;
		   		
		            /**
	                 * 实付学费
	                 **/
				   			private String actualPay;
		   		
		            /**
	                 * 流量来源
	                 **/
				   			private String comSource;
		   		
		            /**
	                 * 来源分类
	                 **/
				   			private String sourceSubclass;
		   		
		            /**
	                 * 绩效奖金
	                 **/
				   			private double performance;
		   		
		   			/**
		   			 * 发生时间
		   			 **/
		   			@DateTimeFormat(pattern = "yyyy-MM-dd")
		   			private Date createDate;
		   			
		   			/**
		   			 * 发生时间起始
		   			 **/
		   			@DateTimeFormat(pattern = "yyyy-MM-dd")
		   			private String startDate;
		   			
		   			/**
		   			 * 发生时间截止
		   			 **/
		   			@DateTimeFormat(pattern = "yyyy-MM-dd")
		   			private String endDate;
				   			
				   	/**
			         * 备注
			         **/
				   	private String note;
				
				   	/**
			         *是否审核
			         **/
				   	private String isPass;
						
				   	/**
			         *参与岗位
			         **/
				   	private String positionName;
				   	
					private Double sum;
					/**
			         *关联学生id
			         **/
					private String stuId;
					private String stuName;
					private String nickName;
					private String stuQq;
					private String stuSubject;
					private String stuCourse;
					private String stuShouldPay;
					private String stuActualPay;
					private String stuJoinTime;
					private String newRate;
						   	
						   	
					public String getNewRate() {
						return newRate;
					}

					public void setNewRate(String newRate) {
						this.newRate = newRate;
					}

			/**
			 * 获取 主键
			 * @return Integer this.id
			 */
			public String getId(){
				return this.id;
			}
			
			/**
			 * 设置 主键
			 * @param BigDecimal id 
			 */
			public void setId(String id){
				this.id = id;
			}
		  					
						
		  			/**
			 * 获取 员工ID
			 * @return String this.employeeId
			 */
			public String getEmployeeId(){
				return this.employeeId;
			}
			
			/**
			 * 设置 员工ID
			 * @param String employeeId 
			 */
			public void setEmployeeId(String employeeId){
				this.employeeId = employeeId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 员工姓名
			 * @return String this.employeeName
			 */
			public String getEmployeeName(){
				return this.employeeName;
			}
			
			/**
			 * 设置 员工姓名
			 * @param String employeeName 
			 */
			public void setEmployeeName(String employeeName){
				this.employeeName = employeeName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 应付学费
			 * @return String this.shouldPay
			 */
			public String getShouldPay(){
				return this.shouldPay;
			}
			
			/**
			 * 设置 应付学费
			 * @param String shouldPay 
			 */
			public void setShouldPay(String shouldPay){
				this.shouldPay = shouldPay;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 实付学费
			 * @return String this.actualPay
			 */
			public String getActualPay(){
				return this.actualPay;
			}
			
			/**
			 * 设置 实付学费
			 * @param String actualPay 
			 */
			public void setActualPay(String actualPay){
				this.actualPay = actualPay;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 流量来源
			 * @return String this.comSource
			 */
			public String getComSource(){
				return this.comSource;
			}
			
			/**
			 * 设置 流量来源
			 * @param String comSource 
			 */
			public void setComSource(String comSource){
				this.comSource = comSource;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 来源分类
			 * @return String this.sourceSubclass
			 */
			public String getSourceSubclass(){
				return this.sourceSubclass;
			}
			
			/**
			 * 设置 来源分类
			 * @param String sourceSubclass 
			 */
			public void setSourceSubclass(String sourceSubclass){
				this.sourceSubclass = sourceSubclass;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 绩效奖金
			 * @return String this.performance
			 */

		   		
		

			/**
			 * 获取 发生时间
			 * 
			 * @return Date this.createDate
			 */
			public Date getCreateDate() {
				return this.createDate;
			}

			public double getPerformance() {
				return performance;
			}

			public void setPerformance(double performance) {
				this.performance = performance;
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

			public String getNote() {
				return note;
			}

			public void setNote(String note) {
				this.note = note;
			}

			public String getPosition() {
				return position;
			}

			public void setPosition(String position) {
				this.position = position;
			}

			public String getPositionName() {
				return positionName;
			}

			public void setPositionName(String positionName) {
				this.positionName = positionName;
			}

			public Double getSum() {
				return sum;
			}

			public void setSum(Double sum) {
				this.sum = sum;
			}

			public String getIsPass() {
				return isPass;
			}

			public void setIsPass(String isPass) {
				this.isPass = isPass;
			}

			public String getStuId() {
				return stuId;
			}

			public void setStuId(String stuId) {
				this.stuId = stuId;
			}

			public String getStuName() {
				return stuName;
			}

			public void setStuName(String stuName) {
				this.stuName = stuName;
			}

			public String getStuQq() {
				return stuQq;
			}

			public void setStuQq(String stuQq) {
				this.stuQq = stuQq;
			}

			public String getStuSubject() {
				return stuSubject;
			}

			public void setStuSubject(String stuSubject) {
				this.stuSubject = stuSubject;
			}

			public String getStuShouldPay() {
				return stuShouldPay;
			}

			public void setStuShouldPay(String stuShouldPay) {
				this.stuShouldPay = stuShouldPay;
			}

			public String getStuActualPay() {
				return stuActualPay;
			}

			public void setStuActualPay(String stuActualPay) {
				this.stuActualPay = stuActualPay;
			}

			public String getStuJoinTime() {
				return stuJoinTime;
			}

			public void setStuJoinTime(String stuJoinTime) {
				this.stuJoinTime = stuJoinTime;
			}

			public String getStuCourse() {
				return stuCourse;
			}

			public void setStuCourse(String stuCourse) {
				this.stuCourse = stuCourse;
			}

			public void setStartDate(String startDate) {
				this.startDate = startDate;
			}
			public void setEndDate(String endDate) {
				this.endDate = endDate;
			}

			public String getNickName() {
				return nickName;
			}

			public void setNickName(String nickName) {
				this.nickName = nickName;
			}

			public String getEmpDept() {
				return empDept;
			}

			public void setEmpDept(String empDept) {
				this.empDept = empDept;
			}
			
}