package com.dongnao.workbench.student.model;

import java.math.BigDecimal;

public class StatisticalCP {
	//学生人数
	private BigDecimal count;

	//补交总额
	private BigDecimal allmoney;

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public BigDecimal getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(BigDecimal allmoney) {
		this.allmoney = allmoney;
	}
	
	
}
