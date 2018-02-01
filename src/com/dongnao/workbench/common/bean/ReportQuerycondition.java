package com.dongnao.workbench.common.bean;

public class ReportQuerycondition extends Model{
	//报表查询条件实体类
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String year;//查询的年份
	private String deptname;//查询的部门
	private String classtype;//查询的报表类型（总业绩，总成本，总利润）
	
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	
}