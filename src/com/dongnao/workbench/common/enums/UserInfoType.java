
package com.dongnao.workbench.common.enums;

/**
 * @ClassName: UserInfoType
 * @Description: 机构级别枚举
 * @author fan.yang
 * @date 2015年12月12日 下午14:17:56
 * 
 */

public enum UserInfoType{

    /**
     * 1公司员工
     */
	STAFF(1, "公司员工", "公司员工"),
	/**
     * 2公众投资人
     */
	INVESTORS(2,"投资人","投资人");
	
 
	private Integer value;
    
    private String name;
    
    private String descript;
    
    private int sort;
    
    private UserInfoType(Integer _value,String _name,String _descript){
    	value = _value;
        name = _name;
        descript = _descript;
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
