package com.dongnao.workbench.school.model;

import com.dongnao.workbench.common.bean.Model;

/**
 * @author Michael
 * @Time   2017年8月1日上午9:51:24
 */
public class RecentlyThirtyDayData extends Model {
	
	private String id;
	private String date;
	private Double money;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}

}
