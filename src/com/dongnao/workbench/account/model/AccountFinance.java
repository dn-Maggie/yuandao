package com.dongnao.workbench.account.model;

import java.util.Date;
import java.math.BigDecimal;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：财务科目模块实体类，负责页面与后台数据传输功能
 *
 * @author cjw
 * @version 1.0 2016-05-01
 */
public class AccountFinance extends Model {

	/**
	 * 财务科目id
	 **/
	private String id;

	/**
	 * 科目编号
	 **/
	private String accountNo;

	/**
	 * 科目名称
	 **/
	private String accountName;

	/**
	 * 科目类型
	 **/
	private Integer accountType;

	/**
	 * 科目说明
	 **/
	private String intro;
	
	private String label;

	/**
	 * 获取 财务科目id
	 * 
	 * @return String this.id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 财务科目id
	 * 
	 * @param String
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 科目编号
	 * 
	 * @return String this.accountNo
	 */
	public String getAccountNo() {
		return this.accountNo;
	}

	/**
	 * 设置 科目编号
	 * 
	 * @param String
	 *            accountNo
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * 获取 科目名称
	 * 
	 * @return String this.accountName
	 */
	public String getAccountName() {
		return this.accountName;
	}

	/**
	 * 设置 科目名称
	 * 
	 * @param String
	 *            accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * 获取 科目类型
	 * 
	 * @return Integer this.accountType
	 */
	public Integer getAccountType() {
		return this.accountType;
	}

	/**
	 * 设置 科目类型
	 * 
	 * @param BigDecimal
	 *            accountType
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	/**
	 * 获取 科目说明
	 * 
	 * @return String this.intro
	 */
	public String getIntro() {
		return this.intro;
	}

	/**
	 * 设置 科目说明
	 * 
	 * @param String
	 *            intro
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public String getLabel(){
		return this.toString();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.accountNo+"-"+this.accountName;
	}

}