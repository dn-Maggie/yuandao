package com.dongnao.workbench.common.bean;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：省市区地域模块实体类，负责页面与后台数据传输功能
 *
 * @author fan.yang
 * @version 1.0 2016-05-10
 */
public class ChinaArea extends Model{
	
	
        /**
         * 
         **/
	   			private Integer id;
	
        /**
         * 
         **/
	   			private String name;
	
        /**
         * 
         **/
	   			private Integer pid;
		   		
	
				
		  			/**
			 * 获取 
			 * @return Integer this.id
			 */
			public Integer getId(){
				return this.id;
			}
			
			/**
			 * 设置 
			 * @param BigDecimal id 
			 */
			public void setId(Integer id){
				this.id = id;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 
			 * @return String this.name
			 */
			public String getName(){
				return this.name;
			}
			
			/**
			 * 设置 
			 * @param String name 
			 */
			public void setName(String name){
				this.name = name;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 
			 * @return Integer this.pid
			 */
			public Integer getPid(){
				return this.pid;
			}
			
			/**
			 * 设置 
			 * @param BigDecimal pid 
			 */
			public void setPid(Integer pid){
				this.pid = pid;
			}
		  		
		
		
			
			}