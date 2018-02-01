package com.dongnao.workbench.common.excel;

import java.io.Serializable;
import java.util.List;



public class ExpParamBean extends ExpSeptPageBean implements Serializable {
	/**
	 * 标题格式及内容输出
	 */
	private List<List<ExpFieldBean>> fieldlist;
	/**
	 * 合计（包括小计和总计）格式及内容输出
	 */
	private List<List<ExpFieldBean>> totallist;


	public List<List<ExpFieldBean>> getFieldlist() {
		return this.fieldlist;
	}

	public void setFieldlist(List<List<ExpFieldBean>> fieldlist) {
		this.fieldlist = fieldlist;
	}

	public List<List<ExpFieldBean>> getTotallist() {
		return totallist;
	}

	public void setTotallist(List<List<ExpFieldBean>> totallist) {
		this.totallist = totallist;
	}
	
}
