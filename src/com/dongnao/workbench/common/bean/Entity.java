package com.dongnao.workbench.common.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Entity implements IEntity {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private String queryFilters = null;
	static Class a;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public String getQueryFilters() {
		return this.queryFilters;
	}

	public void setQueryFilters(String queryFilters) {
		this.queryFilters = queryFilters;
	}

	

}