package com.dongnao.workbench.school.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：员工订餐表模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-11-24
 */
public class EmpDining extends Model{
	
	
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
		   			/**
	                 * 录入人id
	                 **/
		   			private String enterId;
		            /**
	                 * 预订时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
					private Date orderTime;
		
		            /**
	                 * 预订类型（1.中饭，2.晚饭）
	                 **/
		   			private String orderType;
		   		
		            /**
	                 * 吃饭时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
					private Date diningTime;
					private String note;
	
				
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
					 * 获取 员工id
					 * @return String this.empId
					 */
					public String getEmpId(){
						return this.empId;
					}
					
					/**
					 * 设置 员工id
					 * @param String empId 
					 */
					public void setEmpId(String empId){
						this.empId = empId;
					}
				   		
		
		
			
					/**
					 * 获取 预订时间
					 * @return Date this.orderTime
					 */
					public Date getOrderTime(){
						return this.orderTime;
					}
					
					/**
					 * 设置 预订时间
					 * @param Date orderTime 
					 */
					public void setOrderTime(Date orderTime){
						this.orderTime = orderTime;
					}
								
				  			/**
					 * 获取 预订类型（1.中饭，2.晚饭）
					 * @return String this.orderType
					 */
					public String getOrderType(){
						return this.orderType;
					}
					
					/**
					 * 设置 预订类型（1.中饭，2.晚饭）
					 * @param String orderType 
					 */
					public void setOrderType(String orderType){
						this.orderType = orderType;
					}
		   		
		
		
			
								/**
					 * 获取 吃饭时间
					 * @return Date this.diningTime
					 */
					public Date getDiningTime(){
						return this.diningTime;
					}
					
					/**
					 * 设置 吃饭时间
					 * @param Date diningTime 
					 */
					public void setDiningTime(Date diningTime){
						this.diningTime = diningTime;
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

					public String getNote() {
						return note;
					}

					public void setNote(String note) {
						this.note = note;
					}

					public String getEmpNo() {
						return empNo;
					}

					public void setEmpNo(String empNo) {
						this.empNo = empNo;
					}

					public String getEnterId() {
						return enterId;
					}

					public void setEnterId(String enterId) {
						this.enterId = enterId;
					}
			}