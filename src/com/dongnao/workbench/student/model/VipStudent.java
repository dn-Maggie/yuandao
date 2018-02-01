package com.dongnao.workbench.student.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：会员信息管理表模块实体类，负责页面与后台数据传输功能
 *
 * @author cheng.mo
 * @version 1.0 2016-05-02
 */
public class VipStudent extends Model{
	
	
		            /**
	                 * 主键
	                 **/
				   			private String id;
		   		
		            /**
	                 * 学员姓名
	                 **/
				   			private String name;
		   		
		            /**
	                 * QQ昵称
	                 **/
				   			private String age;
		   		
		            /**
	                 * 手机号码
	                 **/
				   			private String phone;
		   		
		            /**
	                 * QQ
	                 **/
				   			private String qq;
		   		
		            /**
	                 * QQ昵称
	                 **/
				   			private String qqNick;
		   		
		            /**
	                 * 付款方姓名
	                 **/
				   			private String payerName;
		   		
		            /**
	                 * 付款账号
	                 **/
				   			private String payAccount;
		   		
		            /**
	                 * 付款方式
	                 **/
				   			private String payType;
		   		
		            /**
	                 * 到款账号
	                 **/
				   			private String receiveAccount;
		   		
		            /**
	                 * 交易号或流水号
	                 **/
				   			private String serialNo;
		   		
		            /**
	                 * 报名学科id
	                 **/
				   			private String subjectId;

				    /**
			        * 报名学科
			       **/
				   			private String subjectName;

				   /**
			        * 报名课程id
			        **/
						   	private String courseId;
				   	/**
					 * 报名课程
					 **/
				   			private String courseName;					   			
		            /**
	                 * 听课讲师
	                 **/
				   			private String teacherId;
				   			
				   	/**
			         * 班主任
			         **/
						    private String tutorId;
							/**
					         * 录入人ID
					         **/
						    private String enterEmp;
						    /**
					         * 录入人姓名
					         **/
						    private String enterEmpname;
		   		
				   	/**
			        * 转化人类型
			        **/
				   			private String followerType;
				   						   			
				   /**
					* 转化人岗位
					 **/
				   			private String followerPosition;
				   			
		            /**
	                 * 转化人id
	                 **/
				   		 private String followerId;
				   			
				     /**
			           * 转化人姓名
			          **/
						  private String followerName;

					 /**
					  * 转化人业绩比例
					  **/
						  private String followerRate;
				   		
		   		
		            /**
	                 * 应缴学费
	                 **/
				   			private Double shouldPay;
		   		
		            /**
	                 * 实缴学费
	                 **/
				   			private Double actualPay;
		   		
		            /**
	                 * 欠缴学费
	                 **/
				   			private Double owePay;
				   			/**
			                 * 欠缴学费
			                 **/
						   			private Double minOwePay;
				   	/**
			         * 学费补款
			         **/
						   	private Double contPay;
					/**
					 * 学费退款
					**/
							private Double refund;
						   	
		            /**
	                 * 能否上课
	                 **/
				   			private Integer isGoclass;
		   		
		            /**
	                 * 备注
	                 **/
				   			private String notes;
		   		
		            /**
	                 * 报名时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd") 
							private Date joinTime;
							private String jointime;
							private String joinStartDate;
				   			private String joinEndDate;
					 /**
	                 * 流量来源
	                 **/
				   			private String comSource;
		   		
				   			/**
			                 * 是否分配业绩
			                 **/
						   	private String isCount;
				            /**
			                 * 来源分类
			                 **/
				   			private String comSourceName;
				   			/**
			                 * 来源分类子分类
			                 **/
				   			private String sourceSubclass;
				   			
				   			/**
						        * 目前缴费状态
						        **/
				   			private String currStatus;
				   			/**
						        * 报名课程数量
						        **/				   			
				   			private int courses;
				   /**
			        * 学员最高学历
			        **/
						   	private String eduBack;			
					/**
					 * 学员目前所在公司
					 **/
							private String currCompany;						   	
					/**
					 * 学员目前职务
					 **/
							private String currPosition;			
					/**
					 * 学员工作年限
					 **/
							private String workingYear;
					/**
					 * 学员目前薪酬水平
					 **/
							private String salary;	
							/**
							 * 按科目统计人数
							 **/	
							private int subjectCnt;	
							/**
							 * 按课程统计人数
							 **/	
							private int courseCnt;	
							/**
							 * 分类统计要素
							 **/	
							private String groupBy;	
							
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
			 * 获取 学员姓名
			 * @return String this.name
			 */
			public String getName(){
				return this.name;
			}
			
			/**
			 * 设置 学员姓名
			 * @param String name 
			 */
			public void setName(String name){
				this.name = name;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 年龄
			 * @return String this.age
			 */
			public String getAge(){
				return this.age;
			}
			
			/**
			 * 设置 年龄
			 * @param String age 
			 */
			public void setAge(String age){
				this.age = age;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 手机号码
			 * @return String this.phone
			 */
			public String getPhone(){
				return this.phone;
			}
			
			/**
			 * 设置 手机号码
			 * @param String phone 
			 */
			public void setPhone(String phone){
				this.phone = phone;
			}
						
		  	/**
			 * 获取 QQ
			 * @return String this.qq
			 */
			public String getQq(){
				return this.qq;
			}
			
			/**
			 * 设置 QQ
			 * @param String qq 
			 */
			public void setQq(String qq){
				this.qq = qq;
			}
						
		  	/**
			 * 获取 QQ昵称
			 * @return String this.qqNick
			 */
			public String getQqNick(){
				return this.qqNick;
			}
			
			/**
			 * 设置 QQ昵称
			 * @param String qqNick 
			 */
			public void setQqNick(String qqNick){
				this.qqNick = qqNick;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 付款方姓名
			 * @return String this.payerName
			 */
			public String getPayerName(){
				return this.payerName;
			}
			
			/**
			 * 设置 付款方姓名
			 * @param String payerName 
			 */
			public void setPayerName(String payerName){
				this.payerName = payerName;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 付款账号
			 * @return String this.payAccount
			 */
			public String getPayAccount(){
				return this.payAccount;
			}
			
			/**
			 * 设置 付款账号
			 * @param String payAccount 
			 */
			public void setPayAccount(String payAccount){
				this.payAccount = payAccount;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 付款方式
			 * @return String this.payType
			 */
			public String getPayType(){
				return this.payType;
			}
			
			/**
			 * 设置 付款方式
			 * @param String payType 
			 */
			public void setPayType(String payType){
				this.payType = payType;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 到款账号
			 * @return String this.receiveAccount
			 */
			public String getReceiveAccount(){
				return this.receiveAccount;
			}
			
			/**
			 * 设置 到款账号
			 * @param String receiveAccount 
			 */
			public void setReceiveAccount(String receiveAccount){
				this.receiveAccount = receiveAccount;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 交易号或流水号
			 * @return String this.serialNo
			 */
			public String getSerialNo(){
				return this.serialNo;
			}
			
			/**
			 * 设置 交易号或流水号
			 * @param String serialNo 
			 */
			public void setSerialNo(String serialNo){
				this.serialNo = serialNo;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 报名学科
			 * @return String this.subjectId
			 */
			public String getSubjectId(){
				return this.subjectId;
			}
			
			/**
			 * 设置 报名学科
			 * @param String subjectId 
			 */
			public void setSubjectId(String subjectId){
				this.subjectId = subjectId;
			}
		
			
						
		  	/**
			 * 获取 班主任
			 * @return String this.tutorId
			 */
			public String getTutorId(){
				return this.tutorId;
			}
			
			/**
			 * 设置 班主任
			 * @param String tutorId 
			 */
			public void setTutorId(String tutorId){
				this.tutorId = tutorId;
			}
		   		
			
			/**
			 * 获取 听课讲师
			 * @return String this.teacherId
			 */
			public String getTeacherId(){
				return this.teacherId;
			}
			
			/**
			 * 设置 听课讲师
			 * @param String teacherId 
			 */
			public void setTeacherId(String teacherId){
				this.teacherId = teacherId;
			}
		
			
						
		  	/**
			 * 获取 转化人
			 * @return String this.followerId
			 */
			public String getFollowerId(){
				return this.followerId;
			}
			
			/**
			 * 设置 转化人
			 * @param String followerId 
			 */
			public void setFollowerId(String followerId){
				this.followerId = followerId;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 应缴学费
			 * @return Double this.shouldPay
			 */
			public Double getShouldPay(){
				return this.shouldPay;
			}
			
			/**
			 * 设置 应缴学费
			 * @param BigDecimal shouldPay 
			 */
			public void setShouldPay(Double shouldPay){
				this.shouldPay = shouldPay;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 实缴学费
			 * @return Double this.actualPay
			 */
			public Double getActualPay(){
				return this.actualPay;
			}
			
			/**
			 * 设置 实缴学费
			 * @param BigDecimal actualPay 
			 */
			public void setActualPay(Double actualPay){
				this.actualPay = actualPay;
			}
						
		  			/**
			 * 获取 欠缴学费
			 * @return Double this.owePay
			 */
			public Double getOwePay(){
				return this.owePay;
			}
			
			/**
			 * 设置 欠缴学费
			 * @param BigDecimal owePay 
			 */
			public void setOwePay(Double owePay){
				this.owePay = owePay;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 能否上课
			 * @return Integer this.isGoclass
			 */
			public Integer getIsGoclass(){
				return this.isGoclass;
			}
			
			/**
			 * 设置 能否上课
			 * @param BigDecimal isGoclass 
			 */
			public void setIsGoclass(Integer isGoclass){
				this.isGoclass = isGoclass;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 备注
			 * @return String this.notes
			 */
			public String getNotes(){
				return this.notes;
			}
			
			/**
			 * 设置 备注
			 * @param String notes 
			 */
			public void setNotes(String notes){
				this.notes = notes;
			}
		   		
		
		
			
								/**
			 * 获取 报名时间
			 * @return Date this.joinTime
			 */
			public Date getJoinTime(){
				return this.joinTime;
			}
			
			/**
			 * 设置 报名时间
			 * @param Date joinTime 
			 */
			public void setJoinTime(Date joinTime){
				this.joinTime = joinTime;
			}
			
			/**
			 * 获取 流量来源
			 * @return String this.comSource
			 */
			public String getComSource(){
				return this.comSource;
			}
			
			/**
			 * 设置 流量来源
			 * @param String comSource 
			 */
			public void setComSource(String comSource){
				this.comSource = comSource;
			}
		   		
						
		  	/**
			 * 获取 来源分类
			 * @return String this.sourceSubclass
			 */
			public String getSourceSubclass(){
				return this.sourceSubclass;
			}
			
			/**
			 * 设置 来源分类
			 * @param String sourceSubclass 
			 */
			public void setSourceSubclass(String sourceSubclass){
				this.sourceSubclass = sourceSubclass;
			}
			/**
			 * 获取 转化人类型
			 * @return String this.followerType
			 */
			public String getFollowerType() {
				return followerType;
			}
			/**
			 * 设置  转化人类型
			 * @param String followerType 
			 */
			public void setFollowerType(String followerType) {
				this.followerType = followerType;
			}

			public String getSubjectName() {
				return subjectName;
			}

			public void setSubjectName(String subjectName) {
				this.subjectName = subjectName;
			}

			public String getCourseName() {
				return courseName;
			}

			public void setCourseName(String courseName) {
				this.courseName = courseName;
			}

			public String getFollowerName() {
				return followerName;
			}

			public void setFollowerName(String followerName) {
				this.followerName = followerName;
			}

			public String getComSourceName() {
				return comSourceName;
			}

			public void setComSourceName(String comSourceName) {
				this.comSourceName = comSourceName;
			}

			public String getFollowerPosition() {
				return followerPosition;
			}

			public void setFollowerPosition(String followerPosition) {
				this.followerPosition = followerPosition;
			}

			public String getCourseId() {
				return courseId;
			}

			public void setCourseId(String courseId) {
				this.courseId = courseId;
			}


			public String getCurrStatus() {
				return currStatus;
			}

			public void setCurrStatus(String currStatus) {
				this.currStatus = currStatus;
			}

			public String getIsCount() {
				return isCount;
			}

			public void setIsCount(String isCount) {
				this.isCount = isCount;
			}

			public String getFollowerRate() {
				return followerRate;
			}

			public void setFollowerRate(String followerRate) {
				this.followerRate = followerRate;
			}


			public String getEduBack() {
				return eduBack;
			}

			public void setEduBack(String eduBack) {
				this.eduBack = eduBack;
			}

			public String getCurrCompany() {
				return currCompany;
			}

			public void setCurrCompany(String currCompany) {
				this.currCompany = currCompany;
			}

			public String getCurrPosition() {
				return currPosition;
			}

			public void setCurrPosition(String currPosition) {
				this.currPosition = currPosition;
			}

			public String getEnterEmp() {
				return enterEmp;
			}

			public void setEnterEmp(String enterEmp) {
				this.enterEmp = enterEmp;
			}
			
			public String getWorkingYear() {
				return workingYear;
			}

			public void setWorkingYear(String workingYear) {
				this.workingYear = workingYear;
			}

			public String getSalary() {
				return salary;
			}

			public void setSalary(String salary) {
				this.salary = salary;
			}

			public int getCourses() {
				return courses;
			}

			public void setCourses(int courses) {
				this.courses = courses;
			}

			public int getSubjectCnt() {
				return subjectCnt;
			}

			public void setSubjectCnt(int subjectCnt) {
				this.subjectCnt = subjectCnt;
			}

			public int getCourseCnt() {
				return courseCnt;
			}

			public void setCourseCnt(int courseCnt) {
				this.courseCnt = courseCnt;
			}

			public String getGroupBy() {
				return groupBy;
			}

			public void setGroupBy(String groupBy) {
				this.groupBy = groupBy;
			}

			public Double getRefund() {
				return refund;
			}

			public void setRefund(Double refund) {
				this.refund = refund;
			}

			public Double getContPay() {
				return contPay;
			}

			public void setContPay(Double contPay) {
				this.contPay = contPay;
			}

			public Double getMinOwePay() {
				return minOwePay;
			}

			public void setMinOwePay(Double minOwePay) {
				this.minOwePay = minOwePay;
			}

			public String getEnterEmpname() {
				return enterEmpname;
			}

			public void setEnterEmpname(String enterEmpname) {
				this.enterEmpname = enterEmpname;
			}

			public String getJointime() {
				return jointime;
			}

			public void setJointime(String jointime) {
				this.jointime = jointime;
			}

			public String getJoinStartDate() {
				return joinStartDate;
			}

			public void setJoinStartDate(String joinStartDate) {
				this.joinStartDate = joinStartDate;
			}

			public String getJoinEndDate() {
				return joinEndDate;
			}

			public void setJoinEndDate(String joinEndDate) {
				this.joinEndDate = joinEndDate;
			}
}