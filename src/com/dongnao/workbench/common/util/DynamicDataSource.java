package com.dongnao.workbench.common.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 描述：动态数据源
 * @author zhou.zheng
 * @version 1.0 2016-03-22
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	

	/**
	 * 获取当前使用的数据源键值
	 * @return String 数据源名称
	 */
	public static String getCurrentLookupKey() {
		return (String) contextHolder.get();
	}

	/**
	 * 设置数据源键值
	 * @param currentLookupKey 数据源键值
	 */
	public static void setCurrentLookupKey(String currentLookupKey) {
		contextHolder.set(currentLookupKey);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return getCurrentLookupKey();
	}

}