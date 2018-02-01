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

public enum TransType{
    
    /**
     * 1申购
     */
	APPLY_FOR_THE_PURCHASE(1,"申购","申购"),
	/**
     *  2赎回
     */
	REDEEM(2,"赎回","赎回"),
	/**
     *  3买入
     */
	BUY_IN(3,"买入","买入"),
	/**
     *  4卖出
     */
	SELL_OUT(4,"卖出","卖出");
 
	private Integer value;
    
    private String name;
    
    private String descript;
    
    private int sort;
    
    private TransType(Integer _value,String _name,String _desc){
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
    
	public static TransType valueOf(int value){
		switch(value){
			case 1:
				return TransType.APPLY_FOR_THE_PURCHASE;
			case 2:
				return TransType.REDEEM;
			case 3:
				return TransType.BUY_IN;
			case 4:
				return TransType.SELL_OUT;
			default:
				return null;
		}
	}
}
