package com.dongnao.workbench.common.bean;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Survey {
	private int id;
	private String ip;
	private String content;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date createDate;
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
