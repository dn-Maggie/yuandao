package com.dongnao.workbench.common.enums;

/**
 * 委托交易状态
 * 
 * @author Administrator
 * 
 */
public enum CommissionDealStatus {
	/**
	 * 1未成交
	 */
	NO_DEAL(1, "未成交", "未成交"),
	/**
	 * 2部分成交
	 */
	PART_DEAL(2, "部分成交", "部分成交"),
	/**
	 * 3成交
	 */
	DEAL(3, "成交", "成交"),
	/**
	 * 4已撤单
	 */
	REVOKE(4, "已撤单", "已撤单");

	private Integer value;

	private String name;

	private String descript;

	private int sort;

	private CommissionDealStatus(Integer _value, String _name, String _desc) {
		value = _value;
		name = _name;
		descript = _desc;
		sort = this.ordinal();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
