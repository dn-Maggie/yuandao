/**   
 * @Title: RoleType.java 
 * @Package org.k12py.common.enums  
 * @author yaobenan
 * @date 2015年12月12日 下午14:17:56
 * @version V1.0   
 */
package com.dongnao.workbench.common.enums;

/**
 * @ClassName: OrgClass
 * @Description: 机构级别枚举
 * @author fan.yang
 * @date 2015年12月12日 下午14:17:56
 * 
 */

public enum OrgClass{
    
    /**
     * 1集团
     */
	GROUP(1,"集团","集团"),
	/**
     *  2省公司
     */
	PROVINCIAL(2,"省公司","省公司"),
	/**
     *  3分公司
     */
	BRANCH(3,"分公司","分公司");
 
	private Integer value;
    
    private String name;
    
    private String descript;
    
    private int sort;
    
    private OrgClass(Integer _value,String _name,String _desc){
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
