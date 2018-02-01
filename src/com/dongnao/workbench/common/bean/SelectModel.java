package com.dongnao.workbench.common.bean;

/**
 * 下拉框实体类
 * @author CYL
 *
 */
public class SelectModel extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 下拉框选项名称
	 */
	private String name;
	
	/**
	 * 下拉框选项值
	 */
	private String value;
	
	/**
	 * 上级下拉框值
	 */
	private String first;

	/**
	 * 获取   下拉框选项名称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置  下拉框选项名称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取  下拉框选项值
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置   下拉框选项值
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取 上级下拉框值
	 * @return
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * 设置  上级下拉框值
	 * @param first
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	
	
	
}
