package com.dongnao.workbench.school.model;

import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：考核标准表模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-07-18
 */
public class Standard extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		   		
		            /**
	                 * 流量来源编号
	                 **/
				   			private String parentId;
		   		
		            /**
	                 * 流量来源名
	                 **/
				   			private String parentName;
		   		
		            /**
	                 * 来源分类编号
	                 **/
				   			private String subId;
		   		
		            /**
	                 * 来源分类
	                 **/
				   			private String subName;
		   		
				   	/**
			         * 岗位
			         **/
						    private String positionId;
				   		
		            /**
	                 * 岗位
	                 **/
				   			private String position;
		   		
		            /**
	                 * 备注
	                 **/
				   			private String note;
		   		
		            /**
	                 * 业绩比例
	                 **/
				   			private String rate;
		   		
				   			private String newRate;
				
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
			 * 获取 流量来源编号
			 * @return String this.parentId
			 */
			public String getParentId(){
				return this.parentId;
			}
			
			/**
			 * 设置 流量来源编号
			 * @param String parentId 
			 */
			public void setParentId(String parentId){
				this.parentId = parentId;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 流量来源名
			 * @return String this.parentName
			 */
			public String getParentName(){
				return this.parentName;
			}
			
			/**
			 * 设置 流量来源名
			 * @param String parentName 
			 */
			public void setParentName(String parentName){
				this.parentName = parentName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 来源分类编号
			 * @return String this.subId
			 */
			public String getSubId(){
				return this.subId;
			}
			
			/**
			 * 设置 来源分类编号
			 * @param String subId 
			 */
			public void setSubId(String subId){
				this.subId = subId;
			}
			
						
		  			/**
			 * 获取 来源分类
			 * @return String this.subName
			 */
			public String getSubName(){
				return this.subName;
			}
			
			/**
			 * 设置 来源分类
			 * @param String subName 
			 */
			public void setSubName(String subName){
				this.subName = subName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 岗位
			 * @return String this.position
			 */
			public String getPosition(){
				return this.position;
			}
			
			/**
			 * 设置 岗位
			 * @param String position 
			 */
			public void setPosition(String position){
				this.position = position;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 备注
			 * @return String this.note
			 */
			public String getNote(){
				return this.note;
			}
			
			/**
			 * 设置 备注
			 * @param String note 
			 */
			public void setNote(String note){
				this.note = note;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 业绩比例
			 * @return String this.rate
			 */
			public String getRate(){
				return this.rate;
			}
			
			/**
			 * 设置 业绩比例
			 * @param String rate 
			 */
			public void setRate(String rate){
				this.rate = rate;
			}

			
			public String getPositionId() {
				return positionId;
			}

			public void setPositionId(String positionId) {
				this.positionId = positionId;
			}

			/**
			 * @return the newRate
			 */
			public String getNewRate() {
				return newRate;
			}

			/**
			 * @param newRate the newRate to set
			 */
			public void setNewRate(String newRate) {
				this.newRate = newRate;
			}
		   		
		
		
			
	 }