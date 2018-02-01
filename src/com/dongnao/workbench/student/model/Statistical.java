package com.dongnao.workbench.student.model;

import java.math.BigDecimal;

public class Statistical {
	//学生人数
	private BigDecimal vipcnt;
	//订单总数
	private BigDecimal cnt;
	//应缴总额
	private BigDecimal allturnover;
	//应缴学费
	private BigDecimal shouldPay;
	//实缴总额
	private BigDecimal actualPay;
	//欠费总额
	private BigDecimal owePay;
	//学费收入
	private BigDecimal xfsr;
	//学费补款
	private BigDecimal xfbk;
	//退款总额
	private BigDecimal xftk;

	public BigDecimal getShouldPay() {
		return shouldPay;
	}
	public void setShouldPay(BigDecimal shouldPay) {
		this.shouldPay = shouldPay;
	}

	public BigDecimal getCnt() {
		return cnt;
	}

	public void setCnt(BigDecimal cnt) {
		this.cnt = cnt;
	}


	public BigDecimal getActualPay() {
		return actualPay;
	}

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}


	public BigDecimal getAllturnover() {
		return allturnover;
	}

	public void setAllturnover(BigDecimal allturnover) {
		this.allturnover = allturnover;
	}

	public BigDecimal getVipcnt() {
		return vipcnt;
	}

	public void setVipcnt(BigDecimal vipcnt) {
		this.vipcnt = vipcnt;
	}

	public BigDecimal getOwePay() {
		return owePay;
	}

	public void setOwePay(BigDecimal owePay) {
		this.owePay = owePay;
	}
	public BigDecimal getXfsr() {
		return xfsr;
	}
	public void setXfsr(BigDecimal xfsr) {
		this.xfsr = xfsr;
	}
	public BigDecimal getXfbk() {
		return xfbk;
	}
	public void setXfbk(BigDecimal xfbk) {
		this.xfbk = xfbk;
	}
	public BigDecimal getXftk() {
		return xftk;
	}
	public void setXftk(BigDecimal xftk) {
		this.xftk = xftk;
	}
	
}
