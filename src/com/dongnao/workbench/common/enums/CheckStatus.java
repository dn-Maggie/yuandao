/**   
 * @Title: RoleType.java 
 * @Package org.k12py.common.enums  
 * @author yaobenan
 * @date 2015年12月12日 下午14:17:56
 * @version V1.0   
 */
package com.dongnao.workbench.common.enums;

/**
 * @ClassName: CheckStatus
 * @Description:  审核状态枚举
 * @author fan.yang
 * @date 2015年12月12日 下午14:17:56
 * 
 */

public enum CheckStatus{
    
    /**
     * 1待审核
     */
	PENDING(0,"待审核","待审核"),
	/**
     *  2已审核
     */
	SUCCESS(1,"审核通过","审核通过"),
	/**
     *  3审核未通过
     */
	FAILURE(2,"审核未通过","审核未通过");
 
	private Integer value;
    
    private String name;
    
    private String descript;
    
    private int sort;
    
    private CheckStatus(Integer _value,String _name,String _desc){
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
