package com.dongnao.workbench.basic.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：用户信息模块实体类，负责页面与后台数据传输功能
 *
 * @author fan.yang
 * @version 1.0 2016-04-29
 */
public class UserInfo extends Model{
	
	
		            /**
	                 * 用户ID
	                 **/
				   			private String id;
		   		
		            /**
	                 * 登录账号
	                 **/
				   			private String userAccount;
		   		
		            /**
	                 * Md5加密
	                 **/
				   			private String password;
		   		
		            /**
	                 * 用户姓名
	                 **/
				   			private String fullName;
		   			/**
	                 * 用户年龄
	                 **/
				   			private String dutyName;
		   		
		            /**
	                 * 用户类型(1公司员工，2公众投资人)
	                 **/
				   			private Integer userType;
		   		
		            /**
	                 * 用户类型名称
	                 **/
				   			private String userTypeName;
		   		
		            /**
	                 * 所属组织机构ID
	                 **/
				   			private String orgId;
		   		
		            /**
	                 * 所属组织机构名称
	                 **/
				   			private String orgName;
		   		
		            /**
	                 * 所属组织类型1集团,2省公司,3分公司
	                 **/
				   			private Integer orgClass;
		   		
		   		
		            /**
	                 * 最后登录时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
						private Date lastLoginTime;
		
		            /**
	                 * 最后一次登录ip
	                 **/
				   			private String lastLoginIp;
		   		
		            /**
	                 * 状态（1启用，0停用）
	                 **/
				   			private String states;
		   		
		            /**
	                 * 备注
	                 **/
				   			private String memo;
		   		
		            /**
	                 * 开通开始日期
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
			private Date enableStateDate;
		
		            /**
	                 * 开通结束日期
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
			private Date enableEndDate;
		
		            /**
	                 * 修改时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
			private Date updated;
		
		            /**
	                 * 修改人ID
	                 **/
				   			private String updatedby;
		   		
		            /**
	                 * 创建人ID
	                 **/
				   			private String createdby;
		   		
		            /**
	                 * 创建时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
			private Date created;
		
		            /**
	                 * 1是可用未删除，0是不可能已删除
	                 **/
				   			private Integer isActive;
		   		
		            /**
	                 * 岗位ID
	                 **/
				   			private String dutyId;
		            /**
	                 * 核定工资
	                 **/
				   			private String roleId;
		   		
	
				
		  			/**
			 * 获取 用户ID
			 * @return String this.id
			 */
			public String getId(){
				return this.id;
			}
			
			/**
			 * 设置 用户ID
			 * @param String id 
			 */
			public void setId(String id){
				this.id = id;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 登录账号
			 * @return String this.userAccount
			 */
			public String getUserAccount(){
				return this.userAccount;
			}
			
			/**
			 * 设置 登录账号
			 * @param String userAccount 
			 */
			public void setUserAccount(String userAccount){
				this.userAccount = userAccount;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 Md5加密
			 * @return String this.password
			 */
			public String getPassword(){
				return this.password;
			}
			
			/**
			 * 设置 Md5加密
			 * @param String password 
			 */
			public void setPassword(String password){
				this.password = password;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 用户姓名
			 * @return String this.fullName
			 */
			public String getFullName(){
				return this.fullName;
			}
			
			/**
			 * 设置 用户姓名
			 * @param String fullName 
			 */
			public void setFullName(String fullName){
				this.fullName = fullName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 用户类型(1公司员工，2公众投资人)
			 * @return Integer this.userType
			 */
			public Integer getUserType(){
				return this.userType;
			}
			
			/**
			 * 设置 用户类型(1公司员工，2公众投资人)
			 * @param BigDecimal userType 
			 */
			public void setUserType(Integer userType){
				this.userType = userType;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 用户类型名称
			 * @return String this.userTypeName
			 */
			public String getUserTypeName(){
				return this.userTypeName;
			}
			
			/**
			 * 设置 用户类型名称
			 * @param String userTypeName 
			 */
			public void setUserTypeName(String userTypeName){
				this.userTypeName = userTypeName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 所属组织机构ID
			 * @return String this.orgId
			 */
			public String getOrgId(){
				return this.orgId;
			}
			
			/**
			 * 设置 所属组织机构ID
			 * @param String orgId 
			 */
			public void setOrgId(String orgId){
				this.orgId = orgId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 所属组织机构名称
			 * @return String this.orgName
			 */
			public String getOrgName(){
				return this.orgName;
			}
			
			/**
			 * 设置 所属组织机构名称
			 * @param String orgName 
			 */
			public void setOrgName(String orgName){
				this.orgName = orgName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 所属组织类型1集团,2省公司,3分公司
			 * @return Integer this.orgClass
			 */
			public Integer getOrgClass(){
				return this.orgClass;
			}
			
			/**
			 * 设置 所属组织类型1集团,2省公司,3分公司
			 * @param BigDecimal orgClass 
			 */
			public void setOrgClass(Integer orgClass){
				this.orgClass = orgClass;
			}
		  		
		
		
			
						
		
		
			
						
		
		
			
		
			
								/**
			 * 获取 最后登录时间
			 * @return Date this.lastLoginTime
			 */
			public Date getLastLoginTime(){
				return this.lastLoginTime;
			}
			
			/**
			 * 设置 最后登录时间
			 * @param Date lastLoginTime 
			 */
			public void setLastLoginTime(Date lastLoginTime){
				this.lastLoginTime = lastLoginTime;
			}
						
		  			/**
			 * 获取 最后一次登录ip
			 * @return String this.lastLoginIp
			 */
			public String getLastLoginIp(){
				return this.lastLoginIp;
			}
			
			/**
			 * 设置 最后一次登录ip
			 * @param String lastLoginIp 
			 */
			public void setLastLoginIp(String lastLoginIp){
				this.lastLoginIp = lastLoginIp;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 状态（1启用，0停用）
			 * @return String this.states
			 */
			public String getStates(){
				return this.states;
			}
			
			/**
			 * 设置 状态（1启用，0停用）
			 * @param String states 
			 */
			public void setStates(String states){
				this.states = states;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 备注
			 * @return String this.memo
			 */
			public String getMemo(){
				return this.memo;
			}
			
			/**
			 * 设置 备注
			 * @param String memo 
			 */
			public void setMemo(String memo){
				this.memo = memo;
			}
		   		
		
		
			
								/**
			 * 获取 开通开始日期
			 * @return Date this.enableStateDate
			 */
			public Date getEnableStateDate(){
				return this.enableStateDate;
			}
			
			/**
			 * 设置 开通开始日期
			 * @param Date enableStateDate 
			 */
			public void setEnableStateDate(Date enableStateDate){
				this.enableStateDate = enableStateDate;
			}
								/**
			 * 获取 开通结束日期
			 * @return Date this.enableEndDate
			 */
			public Date getEnableEndDate(){
				return this.enableEndDate;
			}
			
			/**
			 * 设置 开通结束日期
			 * @param Date enableEndDate 
			 */
			public void setEnableEndDate(Date enableEndDate){
				this.enableEndDate = enableEndDate;
			}
								/**
			 * 获取 修改时间
			 * @return Date this.updated
			 */
			public Date getUpdated(){
				return this.updated;
			}
			
			/**
			 * 设置 修改时间
			 * @param Date updated 
			 */
			public void setUpdated(Date updated){
				this.updated = updated;
			}
						
		  			/**
			 * 获取 修改人ID
			 * @return String this.updatedby
			 */
			public String getUpdatedby(){
				return this.updatedby;
			}
			
			/**
			 * 设置 修改人ID
			 * @param String updatedby 
			 */
			public void setUpdatedby(String updatedby){
				this.updatedby = updatedby;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 创建人ID
			 * @return String this.createdby
			 */
			public String getCreatedby(){
				return this.createdby;
			}
			
			/**
			 * 设置 创建人ID
			 * @param String createdby 
			 */
			public void setCreatedby(String createdby){
				this.createdby = createdby;
			}
		   		
		
		
			
								/**
			 * 获取 创建时间
			 * @return Date this.created
			 */
			public Date getCreated(){
				return this.created;
			}
			
			/**
			 * 设置 创建时间
			 * @param Date created 
			 */
			public void setCreated(Date created){
				this.created = created;
			}
						
		  			/**
			 * 获取 1是可用未删除，0是不可能已删除
			 * @return Integer this.isActive
			 */
			public Integer getIsActive(){
				return this.isActive;
			}
			
			/**
			 * 设置 1是可用未删除，0是不可能已删除
			 * @param BigDecimal isActive 
			 */
			public void setIsActive(Integer isActive){
				this.isActive = isActive;
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
		   		
		

			public String getDutyName() {
				return dutyName;
			}

			public void setDutyName(String dutyName) {
				this.dutyName = dutyName;
			}

			public String getRoleId() {
				return roleId;
			}

			public void setRoleId(String roleId) {
				this.roleId = roleId;
			}
		  		
		
		
			
			}