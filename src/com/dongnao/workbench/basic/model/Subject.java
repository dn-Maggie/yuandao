package com.dongnao.workbench.basic.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：学科表模块实体类，负责页面与后台数据传输功能
 *
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
public class Subject extends Model{
	
	
		            /**
	                 * 
	                 **/
				   			private String id;
		   		
		            /**
	                 * 名称
	                 **/
				   			private String name;
		   		
		            /**
	                 * 创建时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
			private Date creatdate;
		
					/**
	                 * 月度业绩目标
	                 **/
					private int perfTarget;
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
			 * 获取 名称
			 * @return String this.name
			 */
			public String getName(){
				return this.name;
			}
			
			/**
			 * 设置 名称
			 * @param String name 
			 */
			public void setName(String name){
				this.name = name;
			}
		   		
		
		
			
								/**
			 * 获取 创建时间
			 * @return Date this.creatdate
			 */
			public Date getCreatdate(){
				return this.creatdate;
			}
			
			/**
			 * 设置 创建时间
			 * @param Date creatdate 
			 */
			public void setCreatdate(Date creatdate){
				this.creatdate = creatdate;
			}

			public int getPerfTarget() {
				return perfTarget;
			}

			public void setPerfTarget(int perfTarget) {
				this.perfTarget = perfTarget;
			}
			
			
			}