package com.dongnao.workbench.school.model;

import java.sql.Date;

import com.dongnao.workbench.common.bean.Model;

/**
 * @author Michael
 * @Time   2017年8月28日下午2:47:25
 */
public class RecentTwoMonthEmpPerf extends Model{
	private String id;
	private String empName;
	private String position;
	private double money;
	private String beginDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}


	
}
