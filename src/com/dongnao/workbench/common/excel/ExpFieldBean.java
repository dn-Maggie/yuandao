package com.dongnao.workbench.common.excel;

import java.io.Serializable;

public class ExpFieldBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String label;
	private Integer width;
	private Integer rowspan;
	private Integer colspan;
	private IValueFormatter valFmt;

	public IValueFormatter getValFmt() {
		return valFmt;
	}

	public void setValFmt(IValueFormatter valFmt) {
		this.valFmt = valFmt;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	private String dateFormat;

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getRowspan() {
		return this.rowspan;
	}

	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}

	public Integer getColspan() {
		return this.colspan;
	}

	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}
}
