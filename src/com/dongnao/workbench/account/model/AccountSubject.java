package com.dongnao.workbench.account.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：会计科目信息模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-08-10
 */
public class AccountSubject extends Model{
	
	
		            /**
	                 * 序号
	                 **/
				   			private String id;
		   		
		            /**
	                 * 会计科目分类编号
	                 **/
				   			private Integer parentId;
		   		
		            /**
	                 * 会计科目分类名称
	                 **/
				   			private String parentName;
		   		
		            /**
	                 * 会计科目编号
	                 **/
				   			private Integer accountId;
		   		
		            /**
	                 * 会计科目名称
	                 **/
				   			private String accountName;
		   		
		            /**
	                 * 描述
	                 **/
				   			private String note;
		   		
	
				
		  			/**
			 * 获取 序号
			 * @return Integer this.id
			 */
			public String getId(){
				return this.id;
			}
			
			/**
			 * 设置 序号
			 * @param String id 
			 */
			public void setId(String id){
				this.id = id;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 会计科目分类编号
			 * @return Integer this.parentId
			 */
			public Integer getParentId(){
				return this.parentId;
			}
			
			/**
			 * 设置 会计科目分类编号
			 * @param BigDecimal parentId 
			 */
			public void setParentId(Integer parentId){
				this.parentId = parentId;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 会计科目分类名称
			 * @return String this.parentName
			 */
			public String getParentName(){
				return this.parentName;
			}
			
			/**
			 * 设置 会计科目分类名称
			 * @param String parentName 
			 */
			public void setParentName(String parentName){
				this.parentName = parentName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 会计科目编号
			 * @return Integer this.accountId
			 */
			public Integer getAccountId(){
				return this.accountId;
			}
			
			/**
			 * 设置 会计科目编号
			 * @param BigDecimal accountId 
			 */
			public void setAccountId(Integer accountId){
				this.accountId = accountId;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 会计科目名称
			 * @return String this.accountName
			 */
			public String getAccountName(){
				return this.accountName;
			}
			
			/**
			 * 设置 会计科目名称
			 * @param String accountName 
			 */
			public void setAccountName(String accountName){
				this.accountName = accountName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 描述
			 * @return String this.note
			 */
			public String getDesc(){
				return this.note;
			}
			
			/**
			 * 设置 描述
			 * @param String note 
			 */
			public void setDesc(String note){
				this.note = note;
			}
		   		
		
		
			
			}