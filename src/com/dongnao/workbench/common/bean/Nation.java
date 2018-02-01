package com.dongnao.workbench.common.bean;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：民族模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-07-17
 */
public class Nation extends Model{
	
	
		            /**
	                 * 
	                 **/
				   			private String id;
		   		
		            /**
	                 * 
	                 **/
				   			private String nationName;
		   		
	
				
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
			 * 获取 
			 * @return String this.nationName
			 */
			public String getNationName(){
				return this.nationName;
			}
			
			/**
			 * 设置 
			 * @param String nationName 
			 */
			public void setNationName(String nationName){
				this.nationName = nationName;
			}
		   		
		
		
			
			}