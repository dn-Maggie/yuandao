package com.dongnao.workbench.account.model;

import java.util.Date;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import com.dongnao.workbench.common.bean.Model;
/**
 * 描述：报销单模块实体类，负责页面与后台数据传输功能
 *
 * @author maggie
 * @version 1.0 2016-08-18
 */
public class ExpenseAccount extends Model{
	
	
		            /**
	                 * 
	                 **/
				   			private String id;
		   		
		            /**
	                 * 报销单类型
	                 **/
				   			private String expenseType;
		   		
		            /**
	                 * 报销方式
	                 **/
				   			private String expenseWay;
		   		
		            /**
	                 * 报销金额
	                 **/
				   			private String expenseMoney;
		   		
		            /**
	                 * 报销事由
	                 **/
				   			private String content;
		   		
		            /**
	                 * 部门编号
	                 **/
				   			private String deptNo;
				   	/**
			         * 部门名称
			         **/
						   	private String deptName;
		   		
		            /**
	                 * 报销申请人
	                 **/
				   			private String enterPid;
				   	 /**
				   	  * 报销申请人姓名
			          **/
						   private String enterName;
		   		
		            /**
	                 * 申请审核人
	                 **/
				   			private String checkPid;
				   	/**
			         * 申请审核人姓名
			         **/
						    private String checkName;
		   		
		            /**
	                 * 申请时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
					private Date enterDate;
		
		            /**
	                 * 审核时间
	                 **/
					@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
					private Date checkDate;
		
		            /**
	                 * 附单据数
	                 **/
				   			private Integer docAttach;
		   		
		            /**
	                 * 审核标识
	                 **/
				   			private Integer checkFlag;
				    /**
			         * 金额发放标识
			        **/
						    private Integer assignFlag;		   		
						    private String startDate;
						    private String endDate;	
						    
						    /**
			                 * 图片地址
			                 **/
				   			private String fileUrl;
				   		 /**
			                 * 审核批注截图
			                 **/
				   			private String checkPizhu;
				
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
			 * 获取 报销单类型
			 * @return String this.expenseType
			 */
			public String getExpenseType(){
				return this.expenseType;
			}
			
			/**
			 * 设置 报销单类型
			 * @param String expenseType 
			 */
			public void setExpenseType(String expenseType){
				this.expenseType = expenseType;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 报销方式
			 * @return String this.expenseWay
			 */
			public String getExpenseWay(){
				return this.expenseWay;
			}
			
			/**
			 * 设置 报销方式
			 * @param String expenseWay 
			 */
			public void setExpenseWay(String expenseWay){
				this.expenseWay = expenseWay;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 报销金额
			 * @return String this.expenseMoney
			 */
			public String getExpenseMoney(){
				return this.expenseMoney;
			}
			
			/**
			 * 设置 报销金额
			 * @param String expenseMoney 
			 */
			public void setExpenseMoney(String expenseMoney){
				this.expenseMoney = expenseMoney;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 报销事由
			 * @return String this.content
			 */
			public String getContent(){
				return this.content;
			}
			
			/**
			 * 设置 报销事由
			 * @param String content 
			 */
			public void setContent(String content){
				this.content = content;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 部门编号
			 * @return String this.deptNo
			 */
			public String getDeptNo(){
				return this.deptNo;
			}
			
			/**
			 * 设置 部门编号
			 * @param String deptNo 
			 */
			public void setDeptNo(String deptNo){
				this.deptNo = deptNo;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 报销申请人
			 * @return String this.enterPid
			 */
			public String getEnterPid(){
				return this.enterPid;
			}
			
			/**
			 * 设置 报销申请人
			 * @param String enterPid 
			 */
			public void setEnterPid(String enterPid){
				this.enterPid = enterPid;
			}
		   		
		
		
			
						
		  			/**
			 * 获取 申请审核人
			 * @return String this.checkPid
			 */
			public String getCheckPid(){
				return this.checkPid;
			}
			
			/**
			 * 设置 申请审核人
			 * @param String checkPid 
			 */
			public void setCheckPid(String checkPid){
				this.checkPid = checkPid;
			}
		   		
		
		
			
								/**
			 * 获取 申请时间
			 * @return Date this.enterDate
			 */
			public Date getEnterDate(){
				return this.enterDate;
			}
			
			/**
			 * 设置 申请时间
			 * @param Date enterDate 
			 */
			public void setEnterDate(Date enterDate){
				this.enterDate = enterDate;
			}
								/**
			 * 获取 审核时间
			 * @return Date this.checkDate
			 */
			public Date getCheckDate(){
				return this.checkDate;
			}
			
			/**
			 * 设置 审核时间
			 * @param Date checkDate 
			 */
			public void setCheckDate(Date checkDate){
				this.checkDate = checkDate;
			}
						
		  			/**
			 * 获取 附单据数
			 * @return Integer this.docAttach
			 */
			public Integer getDocAttach(){
				return this.docAttach;
			}
			
			/**
			 * 设置 附单据数
			 * @param BigDecimal docAttach 
			 */
			public void setDocAttach(Integer docAttach){
				this.docAttach = docAttach;
			}
		  		
		
		
			
						
		  			/**
			 * 获取 审核标识
			 * @return Integer this.checkFlag
			 */


			public String getEnterName() {
				return enterName;
			}

			public Integer getCheckFlag() {
						return checkFlag;
					}

					public void setCheckFlag(Integer checkFlag) {
						this.checkFlag = checkFlag;
					}

			public void setEnterName(String enterName) {
				this.enterName = enterName;
			}

			public String getCheckName() {
				return checkName;
			}

			public void setCheckName(String checkName) {
				this.checkName = checkName;
			}

			public String getDeptName() {
				return deptName;
			}

			public void setDeptName(String deptName) {
				this.deptName = deptName;
			}

			public Integer getAssignFlag() {
				return assignFlag;
			}

			public void setAssignFlag(Integer assignFlag) {
				this.assignFlag = assignFlag;
			}

			public String getStartDate() {
				return startDate;
			}

			public void setStartDate(String startDate) {
				this.startDate = startDate;
			}

			public String getEndDate() {
				return endDate;
			}

			public void setEndDate(String endDate) {
				this.endDate = endDate;
			}

			public String getFileUrl() {
				return fileUrl;
			}

			public void setFileUrl(String fileUrl) {
				this.fileUrl = fileUrl;
			}

			public String getCheckPizhu() {
				return checkPizhu;
			}

			public void setCheckPizhu(String checkPizhu) {
				this.checkPizhu = checkPizhu;
			}
		  		
			}