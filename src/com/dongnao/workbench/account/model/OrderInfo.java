package com.dongnao.workbench.account.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：订单信息模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-08-30
 */
public class OrderInfo extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		            /**
	                 * 1.学费收入 2.学费补款 3.学费退款 4.学费调增 5.学费调减
	                 **/
				   			private Integer orderType;
		            /**
	                 * 学生ID
	                 **/
				   			private String stuId;
				   	/**
			         * 学生姓名
			         **/
						   	private String stuName;	
					/**
			         * 微信号
			         **/
						   	private String wangwang;	
		            /**
	                 * 报名学科
	                 **/
				   			private String subjectName;
		   		
		            /**
	                 * 报名课程
	                 **/
				   			private String courseName;
		   		
		            /**
	                 * 应缴学费
	                 **/
				   			private String shouldPay;
		   		
		            /**
	                 * 实缴学费
	                 **/
				   			private String actualPay;
				   			
				   	/**
			         * 学员人数
			         **/
						   private Integer cnt;
					/**
					 * 学员人数
					 **/
						   private String groupBy;
		   		
		            /**
	                 * 创建日期
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd") 
					private Date createTime;
					private Double shouldxfsr;
					private Double actualxfsr;
					private Double xfbk;
					private Double xftz;
					private Double xftk;
	
				
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
			 * 获取 1.学费收入 2.学费补款 3.学费退款 4.学费调增 5.学费调减
			 * @return Integer this.orderType
			 */
			public Integer getOrderType(){
				return this.orderType;
			}
			
			/**
			 * 设置 1.学费收入 2.学费补款 3.学费退款 4.学费调增 5.学费调减
			 * @param BigDecimal orderType 
			 */
			public void setOrderType(Integer orderType){
				this.orderType = orderType;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 学生ID
			 * @return String this.stuId
			 */
			public String getStuId(){
				return this.stuId;
			}
			
			/**
			 * 设置 学生ID
			 * @param String stuId 
			 */
			public void setStuId(String stuId){
				this.stuId = stuId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 报名学科
			 * @return String this.subjectName
			 */
			public String getSubjectName(){
				return this.subjectName;
			}
			
			/**
			 * 设置 报名学科
			 * @param String subjectName 
			 */
			public void setSubjectName(String subjectName){
				this.subjectName = subjectName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 报名课程
			 * @return String this.courseName
			 */
			public String getCourseName(){
				return this.courseName;
			}
			
			/**
			 * 设置 报名课程
			 * @param String courseName 
			 */
			public void setCourseName(String courseName){
				this.courseName = courseName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 应缴学费
			 * @return String this.shouldPay
			 */
			public String getShouldPay(){
				return this.shouldPay;
			}
			
			/**
			 * 设置 应缴学费
			 * @param String shouldPay 
			 */
			public void setShouldPay(String shouldPay){
				this.shouldPay = shouldPay;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 实缴学费
			 * @return String this.actualPay
			 */
			public String getActualPay(){
				return this.actualPay;
			}
			
			/**
			 * 设置 实缴学费
			 * @param String actualPay 
			 */
			public void setActualPay(String actualPay){
				this.actualPay = actualPay;
			}
		   		
		
		
			
								/**
			 * 获取 创建日期
			 * @return Date this.createTime
			 */
			public Date getCreateTime(){
				return this.createTime;
			}
			
			/**
			 * 设置 创建日期
			 * @param Date createTime 
			 */
			public void setCreateTime(Date createTime){
				this.createTime = createTime;
			}

			public String getStuName() {
				return stuName;
			}

			public void setStuName(String stuName) {
				this.stuName = stuName;
			}

			public Integer getCnt() {
				return cnt;
			}

			public void setCnt(Integer cnt) {
				this.cnt = cnt;
			}

			public String getGroupBy() {
				return groupBy;
			}

			public void setGroupBy(String groupBy) {
				this.groupBy = groupBy;
			}

			public Double getShouldxfsr() {
				return shouldxfsr;
			}

			public void setShouldxfsr(Double shouldxfsr) {
				this.shouldxfsr = shouldxfsr;
			}

			public Double getActualxfsr() {
				return actualxfsr;
			}

			public void setActualxfsr(Double actualxfsr) {
				this.actualxfsr = actualxfsr;
			}

			public Double getXfbk() {
				return xfbk;
			}

			public void setXfbk(Double xfbk) {
				this.xfbk = xfbk;
			}

			public Double getXftz() {
				return xftz;
			}

			public void setXftz(Double xftz) {
				this.xftz = xftz;
			}

			public Double getXftk() {
				return xftk;
			}

			public void setXftk(Double xftk) {
				this.xftk = xftk;
			}

			public String getWangwang() {
				return wangwang;
			}

			public void setWangwang(String wangwang) {
				this.wangwang = wangwang;
			}
			}