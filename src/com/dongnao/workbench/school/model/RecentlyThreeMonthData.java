package com.dongnao.workbench.school.model;

import com.dongnao.workbench.common.bean.Model;

/**
 * @author Michael
 * @Time   2017年8月22日下午2:31:01
 */
public class RecentlyThreeMonthData extends Model {
	private String id;
	private String yearMonth;
	private String subjectName;
	private String yearMonthDay;
	private Double money;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getYearMonthDay() {
		return yearMonthDay;
	}
	public void setYearMonthDay(String yearMonthDay) {
		this.yearMonthDay = yearMonthDay;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
	
}
