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
 * @Description:  申购状态枚举
 * @author fan.yang
 * @date 2015年12月12日 下午14:17:56
 * 
 */

public enum PurchaseStatus{
    
    /**
     * 1待处理
     */
	PENDING(0,"待处理","待处理"),
	/**
     *  2申购成功
     */
	SUCCESS(1,"申购成功","申购成功"),
	/**
     *  3申购失败
     */
	FAILURE(2,"申购失败","申购失败");
 
	private Integer value;
    
    private String name;
    
    private String descript;
    
    private int sort;
    
    private PurchaseStatus(Integer _value,String _name,String _desc){
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
