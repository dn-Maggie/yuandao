/**   
 * @Title: RoleType.java 
 * @Package org.k12py.common.enums  
 * @author yaobenan
 * @date 2015年12月12日 下午14:17:56
 * @version V1.0   
 */
package com.dongnao.workbench.common.enums;

/**
 * @ClassName: ResultStatus
 * @Description: 返回状态枚举
 * @author fan.yang
 * @date 2015年12月12日 下午14:17:56
 * 
 */

public enum ResultStatus{
    
    /**
     * 
     */
    SUCCESS(1,"成功"),
    /**
     *  
     */
    FAIL(0,"失败");
 
    private Integer value;
	
    private String name;
    
    private String descript;
    
    private int sort;
    
    private ResultStatus(Integer _value,String _name){
    	value = _value;
        name = _name;
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
