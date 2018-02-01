package com.dongnao.workbench.school.model;

import java.util.Date;

import com.dongnao.workbench.common.bean.Model;

/**
 * @author Michael
 * @Time   2017年10月18日下午4:02:02
 */
public class PerformanceStiData extends Model {
	
	private String id;
	private String EmpId;
	private String month;
	private double money;
	private double cost;
	private double bonus;
	private double actualBonus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpId() {
		return EmpId;
	}
	public void setEmpId(String empId) {
		EmpId = empId;
	}
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getBonus() {
		return bonus;
	}
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	public double getActualBonus() {
		return actualBonus;
	}
	public void setActualBonus(double actualBonus) {
		this.actualBonus = actualBonus;
	}
	
	
}
