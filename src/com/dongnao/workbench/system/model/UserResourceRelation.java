package com.dongnao.workbench.system.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.dongnao.workbench.common.bean.Model;

/**
 * 描述：学生账户资源关联实体类，负责页面与后台数据传输功能
 * 
 * @author liux
 * @version 1.0 2016-03-03
 */
public class UserResourceRelation extends Model implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7990792245339058907L;
	private String resourceRelationId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String resourceId;
	private String studentId;
	private String userId;
	
	//额外增加的需要在页面显示的字段及条件字段
	private String studentCode;		//学号
	private String studentName;	//姓名
	private String resourceName;	//资源名称
	private String resourceUrl;		//资源地址
	private String publishStatus;	//发布状态
	private String schoolId;			//学校ID
	private String fileName;			//文件全名
	private String isDisplayImg;		//是否显示
	private String filePath;				//文件存储路径
	private String publishStatusName;
	
	/**
	 * 获取图片是否显示1-显示图片 0-不显示图片
	 * @return  String 图片是否显示
	 */
	public String getIsDisplayImg() {
		return isDisplayImg;
	}

	/**
	 * 设置图片是否显示1-显示图片 0-不显示图片
	 * @param isDisplayImg 图片是否显示
	 */
	public void setIsDisplayImg(String isDisplayImg) {
		this.isDisplayImg = isDisplayImg;
	}

	/**
	 * 获取文件存储路径
	 * @return String 文件存储路径
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * 设置文件存储路径
	 * @param filePath 文件存储路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 获取文件全名
	 * @return String 文件全名
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * 设置文件全名
	 * @param fileName 文件全名
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 获取学校ID
	 * @return String 学校ID
	 */
	public String getSchoolId() {
		return schoolId;
	}

	/**
	 * 设置学校ID
	 * @param schoolId 学校ID
	 */
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * 获取学号
	 * @return String 学号
	 */
	public String getStudentCode() {
		return studentCode;
	}

	/**
	 * 设置学号
	 * @param studentCode 学号
	 */
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	/**
	 * 获取姓名
	 * @return String 姓名
	 */
	public String getStudentName() {
		return studentName;
	}

	/**
	 * 设置姓名
	 * @param studentName 姓名
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * 获取资源名称
	 * @return String 资源名称
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * 设置资源名称
	 * @param resourceName 资源名称
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * 获取资源地址
	 * @return String 资源地址
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}

	/**
	 * 设置资源地址
	 * @param resourceUrl 资源地址
	 */
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	/**
	 * 获取发布状态
	 * @return String 发布状态
	 */
	public String getPublishStatus() {
		return publishStatus;
	}

	/**
	 * 设置发布状态
	 * @param publishStatus 发布状态
	 */
	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	/**
	 * 获取资源关联ID
	 * @return String 资源关联ID
	 */
	public String getResourceRelationId() {
		return this.resourceRelationId;
	}

	/**
	 * 设置资源关联ID
	 * @param resourceRelationId 资源关联ID
	 */
	public void setResourceRelationId(String resourceRelationId) {
		this.resourceRelationId = resourceRelationId;
	}

	/**
	 * 获取创建时间
	 * @return Date 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取资源ID
	 * @return String 资源ID
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * 设置资源ID
	 * @param resourceId 资源ID
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * 获取学生ID
	 * @return String 学生ID
	 */
	public String getStudentId() {
		return this.studentId;
	}

	/**
	 * 设置学生ID
	 * @param studentId 学生ID
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * 获取用户ID
	 * @return String 用户ID
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 设置用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取发布状态的名称
	 * @return String this.publishStatusName 
	 */
	public String getPublishStatusName(){
		return this.publishStatusName;
	}
	/**
	 * 设置发布状态的名称
	 * @return this.publishStatusName 发布状态的名称
	 */
	public void getPublishStatusName(String publishStatusName){
		
	}
}