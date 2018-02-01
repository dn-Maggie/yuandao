package com.dongnao.workbench.system.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：菜单模块实体类，负责页面与后台数据传输功能
 * 
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
public class ModuleLocation  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2734403265137733742L;
	private String mid;// 上级id
	private String pid;// 上级id
	private Integer mtype;// 类型1一级菜单，2二级菜单，3按钮菜单
	private String mname;// 名称
	private String murl;// url

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getMtype() {
		return mtype;
	}

	public void setMtype(Integer mtype) {
		this.mtype = mtype;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMurl() {
		return murl;
	}

	public void setMurl(String murl) {
		this.murl = murl;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

}