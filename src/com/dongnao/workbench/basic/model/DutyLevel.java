package com.dongnao.workbench.basic.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：岗位级别表模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-12-20
 */
public class DutyLevel extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		   		
		            /**
	                 * 岗位ID
	                 **/
				   			private String dutyId;
				   			private String dutyName;
		   		
		            /**
	                 * 等级类型（P/T/D/J）
	                 **/
				   			private String levelType;
		   		
		            /**
	                 * 评定登记级别
	                 **/
				   			private Integer levelName;
		   		
		            /**
	                 * 薪级工资
	                 **/
				   			private String levelSal;
		   		
	
				
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
			 * 获取 岗位ID
			 * @return String this.dutyId
			 */
			public String getDutyId(){
				return this.dutyId;
			}
			
			/**
			 * 设置 岗位ID
			 * @param String dutyId 
			 */
			public void setDutyId(String dutyId){
				this.dutyId = dutyId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 等级类型（P/T/D/J）
			 * @return String this.levelType
			 */
			public String getLevelType(){
				return this.levelType;
			}
			
			/**
			 * 设置 等级类型（P/T/D/J）
			 * @param String levelType 
			 */
			public void setLevelType(String levelType){
				this.levelType = levelType;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 评定登记级别
			 * @return String this.levelName
			 */
			public Integer getLevelName(){
				return this.levelName;
			}
			
			/**
			 * 设置 评定登记级别
			 * @param String levelName 
			 */
			public void setLevelName(Integer levelName){
				this.levelName = levelName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 薪级工资
			 * @return String this.levelSal
			 */
			public String getLevelSal(){
				return this.levelSal;
			}
			
			/**
			 * 设置 薪级工资
			 * @param String levelSal 
			 */
			public void setLevelSal(String levelSal){
				this.levelSal = levelSal;
			}

			public String getDutyName() {
				return dutyName;
			}

			public void setDutyName(String dutyName) {
				this.dutyName = dutyName;
			}
		   		
		
		
			
			}