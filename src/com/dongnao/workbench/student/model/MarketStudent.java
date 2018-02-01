package com.dongnao.workbench.student.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：录入学员信息模块实体类，负责页面与后台数据传输功能
 *
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
public class MarketStudent extends Model{
	
	
	            /**
                 * 
                 **/
			   			private String id;
	   		
	            /**
                 * QQ号码
                 **/
			   			private String qq;
	   		
	            /**
                 * 微信号
                 **/
			   			private String subjectId;

	            /**
                 * 录入人
                 **/
			   			private String userId;
	   		
	            /**
                 * 时间
                 **/
				@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
				private Date time;
				
	   			private String createdby;
	            public String getCreatedby() {
					return createdby;
				}
				public void setCreatedby(String createdby) {
					this.createdby = createdby;
				}
				
				/**
                 * 店铺名称
                 **/
	   			private String notes;
	   			/**
                 * 学科名称
                 **/
	   			private String subjectname;
	   			/**
                 * 课程名称
                 **/
	   			private String coursename;
	   			/**
                 * 添加者
                 **/
	   			private String fullname;
	   			/**
                 * 意向学生总数
                 **/
	   			private Integer markStuCount;
	   			/**
                 * 图片地址
                 **/
	   			private String fileUrl;
			   			
			
	  			public String getFullname() {
							return fullname;
						}

						public void setFullname(String fullname) {
							this.fullname = fullname;
						}

				public String getSubjectname() {
							return subjectname;
				}

				public void setSubjectname(String subjectname) {
					this.subjectname = subjectname;
				}

				public String getCoursename() {
					return coursename;
				}

				public void setCoursename(String coursename) {
					this.coursename = coursename;
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
				 * 获取 QQ号码
				 * @return String this.qq
				 */
				public String getQq(){
					return this.qq;
				}
				
				/**
				 * 设置 QQ号码
				 * @param String qq 
				 */
				public void setQq(String qq){
					this.qq = qq;
				}
	  			/**
				 * 获取 学科id
				 * @return String this.subjectId
				 */
				public String getSubjectId(){
					return this.subjectId;
				}
				
				/**
				 * 设置 学科id
				 * @param String subjectId 
				 */
				public void setSubjectId(String subjectId){
					this.subjectId = subjectId;
				}
							
	  			/**
				 * 获取 录入人
				 * @return String this.userId
				 */
				public String getUserId(){
					return this.userId;
				}
				
				/**
				 * 设置 录入人
				 * @param String userId 
				 */
				public void setUserId(String userId){
					this.userId = userId;
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
			   		
	
				public Integer getMarkStuCount() {
					return markStuCount;
				}
	
				public void setMarkStuCount(Integer markStuCount) {
					this.markStuCount = markStuCount;
				}
	
			
				public String getFileUrl() {
					return fileUrl;
				}
	
				public void setFileUrl(String fileUrl) {
					this.fileUrl = fileUrl;
				}

			
			}