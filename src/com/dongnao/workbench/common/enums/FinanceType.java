package com.dongnao.workbench.common.enums;

public enum FinanceType {

	LEND(1,"借[收入]"),
	LOAN(2,"贷[支出]");
	
	private Integer value;
	
    private String name;
    
    private String descript;
    
    private int sort;
    
    private FinanceType(Integer _value,String _name){
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
