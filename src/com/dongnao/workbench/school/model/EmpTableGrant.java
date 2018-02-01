package com.dongnao.workbench.school.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：员工晚餐补助申请表模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2017-01-08
 */
public class EmpTableGrant extends Model{
	
	
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
	                 * 申请员工昵称
	                 **/
				   			private String empNickName;
				   	/**
	                 * 申请员工部门
	                 **/
				   			private String empDept;
		   			/**
	                 * 部门负责人
	                 **/
				   			private String deptLeader;		
		            /**
	                 * 申请理由
	                 **/
				   			private String reason;
		   		
		            /**
	                 * 填表日期
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
							private Date createTime;
					 /**
	                 * 补助日期起
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date startDate;
					 /**
	                 * 补助日期止
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date endDate;
		
		            /**
	                 * 补助日期日起
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd") 
							private Date grantDateStart;
		
		            /**
	                 * 补助日期日止
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd") 
							private Date grantDateEnd;
		
		            /**
	                 * 补助次数
	                 **/
				   			private Integer grantTime;
		   		
		            /**
	                 * 餐补合计
	                 **/
				   			private String tableMoney;
		   		
		            /**
	                 * 审核人
	                 **/
				   			private String checkId;
		   			/**
	                 * 审核者姓名
	                 **/
						   	private String checkName;
		   		
		            /**
	                 * 审核日期
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

			public String getDeptLeader() {
				return deptLeader;
			}

			public void setDeptLeader(String deptLeader) {
				this.deptLeader = deptLeader;
			}

			public String getCheckName() {
				return checkName;
			}

			public void setCheckName(String checkName) {
				this.checkName = checkName;
			}

					/**
			 * 获取 申请理由
			 * @return String this.reason
			 */
			public String getReason(){
				return this.reason;
			}
			
			/**
			 * 设置 申请理由
			 * @param String reason 
			 */
			public void setReason(String reason){
				this.reason = reason;
			}
		   		
		
		
			
								/**
			 * 获取 填表日期
			 * @return Date this.createTime
			 */
			public Date getCreateTime(){
				return this.createTime;
			}
			
			/**
			 * 设置 填表日期
			 * @param Date createTime 
			 */
			public void setCreateTime(Date createTime){
				this.createTime = createTime;
			}
								/**
			 * 获取 补助日期日起
			 * @return Date this.grantDateStart
			 */
			public Date getGrantDateStart(){
				return this.grantDateStart;
			}
			
			/**
			 * 设置 补助日期日起
			 * @param Date grantDateStart 
			 */
			public void setGrantDateStart(Date grantDateStart){
				this.grantDateStart = grantDateStart;
			}
								/**
			 * 获取 补助日期日止
			 * @return Date this.grantDateEnd
			 */
			public Date getGrantDateEnd(){
				return this.grantDateEnd;
			}
			
			/**
			 * 设置 补助日期日止
			 * @param Date grantDateEnd 
			 */
			public void setGrantDateEnd(Date grantDateEnd){
				this.grantDateEnd = grantDateEnd;
			}
						
		  			/**
			 * 获取 补助次数
			 * @return Integer this.grantTime
			 */
			public Integer getGrantTime(){
				return this.grantTime;
			}
			
			/**
			 * 设置 补助次数
			 * @param BigDecimal grantTime 
			 */
			public void setGrantTime(Integer grantTime){
				this.grantTime = grantTime;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 餐补合计
			 * @return String this.tableMoney
			 */
			public String getTableMoney(){
				return this.tableMoney;
			}
			
			/**
			 * 设置 餐补合计
			 * @param String tableMoney 
			 */
			public void setTableMoney(String tableMoney){
				this.tableMoney = tableMoney;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 审核人
			 * @return String this.checkId
			 */
			public String getCheckId(){
				return this.checkId;
			}
			
			/**
			 * 设置 审核人
			 * @param String checkId 
			 */
			public void setCheckId(String checkId){
				this.checkId = checkId;
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

			public Date getStartDate() {
				return startDate;
			}

			public void setStartDate(Date startDate) {
				this.startDate = startDate;
			}

			public Date getEndDate() {
				return endDate;
			}

			public void setEndDate(Date endDate) {
				this.endDate = endDate;
			}

	}