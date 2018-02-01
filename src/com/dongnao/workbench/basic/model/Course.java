package com.dongnao.workbench.basic.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：课程表模块实体类，负责页面与后台数据传输功能
 *
 * @author cheng.mo
 * @version 1.0 2016-05-01
 */
public class Course extends Model{
	
	
		            /**
	                 * 
	                 **/
				   			private String id;
		   		
		            /**
	                 * 课程名称
	                 **/
				   			private String name;
		   		
		           

					/**
	                 * 所属学科ID
	                 **/
				   			private String subjectId;
				   /**
			         * 所属学科名
			         **/
						   	private String subjectName;
				
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
			 * 获取 课程名称
			 * @return String this.name
			 */
			public String getName(){
				return this.name;
			}
			
			/**
			 * 设置 课程名称
			 * @param String name 
			 */
			public void setName(String name){
				this.name = name;
			}
		   		
		
		
			
						
  			/**
			 * 获取 所属学科Id
			 * @return String this.subjectId
			 */
			public String getSubjectId(){
				return this.subjectId;
			}
			
			/**
			 * 设置 所属学科Id
			 * @param String subjectId 
			 */
			public void setSubjectId(String subjectId){
				this.subjectId = subjectId;
			}
			
			/**
			 * 获取 所属学科名
			 * @return String this.subjectId
			 */
			public String getSubjectName() {
				return subjectName;
			}

			/**
			 * 设置 所属学科名
			 * @param String subjectId 
			 */
			public void setSubjectName(String subjectName) {
				this.subjectName = subjectName;
			}
		}