package com.dongnao.workbench.school.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：员工公告通知模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-12-22
 */
public class EmpNotice extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		   		
		            /**
	                 * 创建时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
					private Date createTime;
		
		            /**
	                 * 创建者ID
	                 **/
				   			private String createId;
				   			private String createName;
		   			/**
	                 * 修改者ID
	                 **/
				   			private String editId;
							private String editName;
		   			/**
	                 * 最后一次修改时间
	                 **/
				   			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
				   			private Date editTime;
		            /**
	                 * 公告标题
	                 **/
				   			private String noticeTitle;
		   		
		            /**
	                 * 公告内容
	                 **/
				   			private String noticeContent;
		   		
				   			private String fileSize;
				   			private String fileSuffix;
				   			private String fileUrl;
				   			private String fileName;
				
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
			 * 获取修改时间
			 * @return Date this.createTime
			 */
			public Date getEditTime() {
				return editTime;
			}
			/**
			 * 设置修改时间
			 * @return Date this.createTime
			 */
			public void setEditTime(Date editTime) {
				this.editTime = editTime;
			}
			
			 /**
			 * 获取 创建时间
			 * @return Date this.createTime
			 */
			public Date getCreateTime(){
				return this.createTime;
			}
			
			/**
			 * 设置 创建时间
			 * @param Date createTime 
			 */
			public void setCreateTime(Date createTime){
				this.createTime = createTime;
			}
						
		  			/**
			 * 获取 创建者ID
			 * @return String this.createId
			 */
			public String getCreateId(){
				return this.createId;
			}
			
			/**
			 * 设置 创建者ID
			 * @param String createId 
			 */
			public void setCreateId(String createId){
				this.createId = createId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 公告标题
			 * @return String this.noticeTitle
			 */
			public String getNoticeTitle(){
				return this.noticeTitle;
			}
			
			/**
			 * 设置 公告标题
			 * @param String noticeTitle 
			 */
			public void setNoticeTitle(String noticeTitle){
				this.noticeTitle = noticeTitle;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 公告内容
			 * @return String this.noticeContent
			 */
			public String getNoticeContent(){
				return this.noticeContent;
			}
			
			/**
			 * 设置 公告内容
			 * @param String noticeContent 
			 */
			public void setNoticeContent(String noticeContent){
				this.noticeContent = noticeContent;
			}

			public String getCreateName() {
				return createName;
			}

			public void setCreateName(String createName) {
				this.createName = createName;
			}

			public String getEditName() {
				return editName;
			}

			public void setEditName(String editName) {
				this.editName = editName;
			}

			public String getEditId() {
				return editId;
			}

			public void setEditId(String editId) {
				this.editId = editId;
			}

			public String getFileSize() {
				return fileSize;
			}

			public void setFileSize(String fileSize) {
				this.fileSize = fileSize;
			}

			public String getFileSuffix() {
				return fileSuffix;
			}

			public void setFileSuffix(String fileSuffix) {
				this.fileSuffix = fileSuffix;
			}

			public String getFileUrl() {
				return fileUrl;
			}

			public void setFileUrl(String fileUrl) {
				this.fileUrl = fileUrl;
			}

			public String getFileName() {
				return fileName;
			}

			public void setFileName(String fileName) {
				this.fileName = fileName;
			}


		
		
			
			}