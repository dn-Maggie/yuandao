/** 
 * CopyRright (c)2014-2015                       
 * Project:  sn     
 * Title: ExpSeptPageBean.java  
 * Package com.suframework.webbase.excel                                                                        
 * JDK version used:  <JDK1.5>   
 * Description: (用一句话描述该文件做什么)                                                        
 * Author: 307806767@qq.com                
 * Create date: Feb 28, 2014 2:08:25 PM 
 * Modified By：   <修改人中文名或拼音缩写>                                         
 * Modified Date:  <修改日期，格式:YYYY-MM-DD>                                    
 * Why & What is modified  <修改原因描述>    
 * Version V1.0                        
 */

package com.dongnao.workbench.common.excel;

import java.io.Serializable;


/**
 * 
 * @ClassName: ExpSeptPageBean
 * @Description: TODO(导出分页的BEAN)
 * @author suyao(QQ307806767)
 * @date Feb 28, 2014 2:08:53 PM
 * 
 */
public class ExpSeptPageBean implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 5571333092808590027L;
	/**
	 * 开始页
	 */
	private Integer startpage = Integer.valueOf(1);
	/**
	 * 结束页
	 */
	private Integer endpage = Integer.valueOf(1);

	/**
	 * 页大小
	 */
	private Integer pagesize = Integer.valueOf(20);
	private String order;
	/**
	 * 种类
	 */
	private String sort;

	public Integer getStartpage() {
		return startpage;
	}

	public void setStartpage(Integer startpage) {
		this.startpage = startpage;
	}

	public Integer getEndpage() {
		return endpage;
	}

	public void setEndpage(Integer endpage) {
		this.endpage = endpage;
	}

	public Integer getPagesize() {
		return pagesize;
	}

	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}
