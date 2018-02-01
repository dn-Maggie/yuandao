package com.dongnao.workbench.school.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：员工模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-07-15
 */
public class Employee extends Model{
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
				   			
		   			/**
	                 * 工号
	                 **/
				   			private String empNo;
				   			private String deptLeader;
				   			private String notDining;
		            /**
	                 * 姓名
	                 **/
				   			private String name;
		   		
		            /**
	                 * 性别
	                 **/
				   			private String sex;
		   		
		            /**
	                 * 年龄
	                 **/
				   			private Integer age;
		   		
		            /**
	                 * 籍贯
	                 **/
				   			private String nativePlace;
		   		
		            /**
	                 * 民族
	                 **/
				   			private String nation;
				   			private String nationname;
		   		
		            /**
	                 * 身份证号码
	                 **/
				   			private String idCard;
		   			/**
	                 * 户口类别
	                 **/
				   			private String nodeType;
   		
		            /**
	                 * 婚姻状况
	                 **/
				   			private String maritalStatus;
		   		
		            /**
	                 * 目前住址
	                 **/
				   			private String currentAddress;
		   		
		            /**
	                 * 手机号码
	                 **/
				   			private String mobile;
		   		
		            /**
	                 * 通讯地址
	                 **/
				   			private String mailAddress;
		   		
		            /**
	                 * 邮政编码
	                 **/
				   			private String postcode;
		   		
		            /**
	                 * 最高学历
	                 **/
				   			private String educationBackground;
		   		
		            /**
	                 * 专业
	                 **/
				   			private String profession;
		   		
		            /**
	                 * 外语水平
	                 **/
				   			private String foreignLanguage;
		   		
		            /**
	                 * 职业资格
	                 **/
				   			private String professionalQualification;
		   		
		            /**
	                 * 教育经历
	                 **/
				   			private String educationExperience;
		   		
		            /**
	                 * 工作经历
	                 **/
				   			private String workExperience;
		   		
		            /**
	                 * 家庭成员
	                 **/
				   			private String familyMember;
		   		
		            /**
	                 * 紧急联络人
	                 **/
				   			private String emergencyContact;
		   		
		            /**
	                 * 入职日期
	                 **/
						@DateTimeFormat(pattern="yyyy-MM-dd") 
						private Date entryDate;
					 /**
	                 * 转正日期
	                 **/
						@DateTimeFormat(pattern="yyyy-MM-dd") 
						private Date beFullDate;
					/**
	                 * 离职日期
	                 **/
						@DateTimeFormat(pattern="yyyy-MM-dd") 
						private Date leaveDate;
					/**
	                 * 出生日期
	                 **/
						@DateTimeFormat(pattern="yyyy-MM-dd") 
						private Date birthDate;
					

					/**
	                 * 职位
	                 **/
			   			private String position;
			   			private String dutyId;
		   		
		            /**
	                 * 薪级工资
	                 **/
			   			private Double salary;
				   	/**
			         * 基本工资
			         **/
					   	private Double salValue;
					/**
					* 股份配额	
					**/
						private Integer stockNum;	
				   	/**
			         * 职工昵称
			         **/
			   			private String nickName;
				    /**
			         * 部门编码
			         **/
					   	private String deptNo;				   		
		   		
		            /**
	                 * 部门
	                 **/
			   			private String dept;
		   		
		            /**
	                 * 银行卡号
	                 **/
			   			private String bankCard;
		   		
		            /**
	                 * 银行名称
	                 **/
			   			private String bankName;
				   			
				    /**
			          * 备注
			           **/
					    private String note;
					    
					    private Integer isAssess;/*是否被考核*/
					    private String checkName;/*考核人*/
					    private Integer checkStanderd;/*考核标准*/
						   		
					/**
					 * 岗位级别
					 **/
					    private String dutyRank;	   			
				    /**
					 * 员工状态
					 **/						    
					    private String currState;
				    /**
					 * 企业邮箱
					 **/						    
					    private String foxmail;
				    /**
					 * 试用期
					 **/						    
					    private Integer probation;
				    /**
					 * 员工总数（统计用）
					 **/	    
					    private Integer empCount;
				    /**
					 * 请假日期（统计用）
					 **/	
					    private Double leaveDays;
					    
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
					 * 获取 姓名
					 * @return String this.name
					 */
						public String getName(){
							return this.name;
						}
				
					/**
					 * 设置 姓名
					 * @param String name 
					 */
						public void setName(String name){
							this.name = name;
						}
			   		
			
			
				
							
		  			public Integer getIsAssess() {
						return isAssess;
					}

					public void setIsAssess(Integer isAssess) {
						this.isAssess = isAssess;
					}

					public String getCheckName() {
						return checkName;
					}

					public void setCheckName(String checkName) {
						this.checkName = checkName;
					}

					/**
					 * 获取 性别
					 * @return Integer this.sex
					 */
						public String getSex(){
							return this.sex;
						}
				
					/**
					 * 设置 性别
					 * @param BigDecimal sex 
					 */
					public void setSex(String sex){
						this.sex = sex;
					}
			  		
			
			
				
							
		  			/**
					 * 获取 年龄
					 * @return Integer this.age
					 */
					public Integer getAge(){
						return this.age;
					}
				
					/**
					 * 设置 年龄
					 * @param BigDecimal age 
					 */
					public void setAge(Integer age){
						this.age = age;
					}
				  		
			
			
				
							
		  			/**
					 * 获取 籍贯
					 * @return String this.nativePlace
					 */
					public String getNativePlace(){
						return this.nativePlace;
					}
				
					/**
					 * 设置 籍贯
					 * @param String nativePlace 
					 */
					public void setNativePlace(String nativePlace){
						this.nativePlace = nativePlace;
					}
				   		
			
			
				
							
		  			/**
					 * 获取 民族
					 * @return String this.nation
					 */
					public String getNation(){
						return this.nation;
					}
				
					/**
					 * 设置 民族
					 * @param String nation 
					 */
					public void setNation(String nation){
						this.nation = nation;
					}
			   		
			
			
				
							
		  			/**
					 * 获取 身份证号码
					 * @return String this.idCard
					 */
					public String getIdCard(){
						return this.idCard;
					}
				
					/**
					 * 设置 身份证号码
					 * @param String idCard 
					 */
					public void setIdCard(String idCard){
						this.idCard = idCard;
					}
				   		
			
			
				
							
		  			/**
					 * 获取 婚姻状况
					 * @return String this.maritalStatus
					 */
					public String getMaritalStatus(){
						return this.maritalStatus;
					}
				
					/**
					 * 设置 婚姻状况
					 * @param String maritalStatus 
					 */
					public void setMaritalStatus(String maritalStatus){
						this.maritalStatus = maritalStatus;
					}
			   		
			
			
				
							
		  			/**
					 * 获取 目前住址
					 * @return String this.currentAddress
					 */
					public String getCurrentAddress(){
						return this.currentAddress;
					}
				
					/**
					 * 设置 目前住址
					 * @param String currentAddress 
					 */
					public void setCurrentAddress(String currentAddress){
						this.currentAddress = currentAddress;
					}
				   		
			
			
				
							
		  			/**
					 * 获取 手机号码
					 * @return String this.mobile
					 */
					public String getMobile(){
						return this.mobile;
					}
				
					/**
					 * 设置 手机号码
					 * @param String mobile 
					 */
					public void setMobile(String mobile){
						this.mobile = mobile;
					}
				   		
			
			
				
						
		  			/**
					 * 获取 通讯地址
					 * @return String this.mailAddress
					 */
					public String getMailAddress(){
						return this.mailAddress;
					}
					
					/**
					 * 设置 通讯地址
					 * @param String mailAddress 
					 */
					public void setMailAddress(String mailAddress){
						this.mailAddress = mailAddress;
					}
				   		
			
			
				
							
			  			/**
				 * 获取 邮政编码
				 * @return String this.postcode
				 */
				public String getPostcode(){
					return this.postcode;
				}
				
				/**
				 * 设置 邮政编码
				 * @param String postcode 
				 */
				public void setPostcode(String postcode){
					this.postcode = postcode;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 最高学历
				 * @return String this.educationBackground
				 */
				public String getEducationBackground(){
					return this.educationBackground;
				}
				
				/**
				 * 设置 最高学历
				 * @param String educationBackground 
				 */
				public void setEducationBackground(String educationBackground){
					this.educationBackground = educationBackground;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 专业
				 * @return String this.profession
				 */
				public String getProfession(){
					return this.profession;
				}
				
				/**
				 * 设置 专业
				 * @param String profession 
				 */
				public void setProfession(String profession){
					this.profession = profession;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 外语水平
				 * @return String this.foreignLanguage
				 */
				public String getForeignLanguage(){
					return this.foreignLanguage;
				}
				
				/**
				 * 设置 外语水平
				 * @param String foreignLanguage 
				 */
				public void setForeignLanguage(String foreignLanguage){
					this.foreignLanguage = foreignLanguage;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 职业资格
				 * @return String this.professionalQualification
				 */
				public String getProfessionalQualification(){
					return this.professionalQualification;
				}
				
				/**
				 * 设置 职业资格
				 * @param String professionalQualification 
				 */
				public void setProfessionalQualification(String professionalQualification){
					this.professionalQualification = professionalQualification;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 教育经历
				 * @return String this.educationExperience
				 */
				public String getEducationExperience(){
					return this.educationExperience;
				}
				
				/**
				 * 设置 教育经历
				 * @param String educationExperience 
				 */
				public void setEducationExperience(String educationExperience){
					this.educationExperience = educationExperience;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 工作经历
				 * @return String this.workExperience
				 */
				public String getWorkExperience(){
					return this.workExperience;
				}
				
				/**
				 * 设置 工作经历
				 * @param String workExperience 
				 */
				public void setWorkExperience(String workExperience){
					this.workExperience = workExperience;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 家庭成员
				 * @return String this.familyMember
				 */
				public String getFamilyMember(){
					return this.familyMember;
				}
				
				/**
				 * 设置 家庭成员
				 * @param String familyMember 
				 */
				public void setFamilyMember(String familyMember){
					this.familyMember = familyMember;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 紧急联络人
				 * @return String this.emergencyContact
				 */
				public String getEmergencyContact(){
					return this.emergencyContact;
				}
				
				/**
				 * 设置 紧急联络人
				 * @param String emergencyContact 
				 */
				public void setEmergencyContact(String emergencyContact){
					this.emergencyContact = emergencyContact;
				}
			   		
			
			
				
				/**
				 * 获取 入职日期
				 * @return Date this.entryDate
				 */
				public Date getEntryDate(){
					return this.entryDate;
				}
				
				/**
				 * 设置 入职日期
				 * @param Date entryDate 
				 */
				public void setEntryDate(Date entryDate){
					this.entryDate = entryDate;
				}
				
				/**
				 * 获取 出生日期
				 * @return Date this.entryDate
				 */
				public Date getBirthDate(){
					return this.birthDate;
				}
				
				/**
				 * 设置 出生日期
				 * @param Date entryDate 
				 */
				public void setBirthDate(Date birthDate){
					this.birthDate = birthDate;
				}
							
			  	/**
				 * 获取 职位
				 * @return String this.position
				 */
				public String getPosition(){
					return this.position;
				}
				
				/**
				 * 设置 职位
				 * @param String position 
				 */
				public void setPosition(String position){
					this.position = position;
				}
							
			  	/**
				 * 获取 工资水平
				 * @return String this.salary
				 */
				public Double getSalary(){
					return this.salary;
				}
				
				/**
				 * 设置 工资水平
				 * @param String salary 
				 */
				public void setSalary(Double salary){
					this.salary = salary;
				}
			   		
							
			  			/**
				 * 获取 职工昵称
				 * @return String this.nickName
				 */
				public String getNickName(){
					return this.nickName;
				}
				
				/**
				 * 设置 职工昵称
				 * @param String nickName 
				 */
				public void setNickName(String nickName){
					this.nickName = nickName;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 部门
				 * @return String this.dept
				 */
				public String getDept(){
					return this.dept;
				}
				
				/**
				 * 设置 部门
				 * @param String dept 
				 */
				public void setDept(String dept){
					this.dept = dept;
				}
			   		
			
			
				
							
			  			/**
				 * 获取 银行卡号
				 * @return String this.bankCard
				 */
				public String getBankCard(){
					return this.bankCard;
				}
				
				/**
				 * 设置 银行卡号
				 * @param String bankCard 
				 */
				public void setBankCard(String bankCard){
					this.bankCard = bankCard;
				}			
							
			  			/**
				 * 获取 银行名称
				 * @return String this.bankName
				 */
				public String getBankName(){
					return this.bankName;
				}
				
				/**
				 * 设置 银行名称
				 * @param String bankName 
				 */
				public void setBankName(String bankName){
					this.bankName = bankName;
				}
	
				public String getEmpNo() {
					return empNo;
				}
	
				public void setEmpNo(String empNo) {
					this.empNo = empNo;
				}
	
				public String getNodeType() {
					return nodeType;
				}
	
				public void setNodeType(String nodeType) {
					this.nodeType = nodeType;
				}
	
				public String getNote() {
					return note;
				}
	
				public void setNote(String note) {
					this.note = note;
				}
	
				public String getNationname() {
					return nationname;
				}
	
				public void setNationname(String nationname) {
					this.nationname = nationname;
				}
	
				public Integer getEmpCount() {
					return empCount;
				}
	
				public void setEmpCount(Integer empCount) {
					this.empCount = empCount;
				}
	
				public Double getSalValue() {
					return salValue;
				}
	
				public void setSalValue(Double salValue) {
					this.salValue = salValue;
				}
	
				public Integer getStockNum() {
					return stockNum;
				}
	
				public void setStockNum(Integer stockNum) {
					this.stockNum = stockNum;
				}
	
				public String getDutyRank() {
					return dutyRank;
				}
	
				public void setDutyRank(String dutyRank) {
					this.dutyRank = dutyRank;
				}
	
				public String getCurrState() {
					return currState;
				}
	
				public void setCurrState(String currState) {
					this.currState = currState;
				}
	
				public String getDeptNo() {
					return deptNo;
				}
	
				public void setDeptNo(String deptNo) {
					this.deptNo = deptNo;
				}
	
				public Integer getProbation() {
					return probation;
				}
	
				public void setProbation(Integer probation) {
					this.probation = probation;
				}
	
				public String getFoxmail() {
					return foxmail;
				}
	
				public void setFoxmail(String foxmail) {
					this.foxmail = foxmail;
				}

				public String getDeptLeader() {
					return deptLeader;
				}

				public void setDeptLeader(String deptLeader) {
					this.deptLeader = deptLeader;
				}

				public String getDutyId() {
					return dutyId;
				}

				public void setDutyId(String dutyId) {
					this.dutyId = dutyId;
				}

				/**
                 * 转正日期
                 **/
	            public Date getBeFullDate() {
					return beFullDate;
				}
	            /**
                 * 转正日期
                 **/
				public void setBeFullDate(Date beFullDate) {
					this.beFullDate = beFullDate;
				}

				public Double getLeaveDays() {
					return leaveDays;
				}

				public void setLeaveDays(Double leaveDays) {
					this.leaveDays = leaveDays;
				}

				public Date getLeaveDate() {
					return leaveDate;
				}

				public void setLeaveDate(Date leaveDate) {
					this.leaveDate = leaveDate;
				}

				public String getNotDining() {
					return notDining;
				}

				public void setNotDining(String notDining) {
					this.notDining = notDining;
				}

				public Integer getCheckStanderd() {
					return checkStanderd;
				}

				public void setCheckStanderd(Integer checkStanderd) {
					this.checkStanderd = checkStanderd;
				}
		
			
			}