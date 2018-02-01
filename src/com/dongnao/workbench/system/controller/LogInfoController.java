package com.dongnao.workbench.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.EnumConstant;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.system.model.LogInfo;
import com.dongnao.workbench.system.service.LogInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述：日志管理(logInfo)模块controller类，负责页面分发与跳转
 * 主要实现：日志的增删改查
 * @author wff
 * @version 1.0  2016-03-20
 */
@Controller
@RequestMapping("logInfo")
public class LogInfoController{
	private LogInfoService logInfoService;
	
	/**
	 * 设置service
	 * @param logInfoService LogInfoService
	 */
	@Autowired
	public void setLogInfoService(LogInfoService logInfoService) {
		this.logInfoService = logInfoService;
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddLogInfo")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/system/logInfo/addLogInfo");
	}
	
	/**
	 * 进入查看页面方法
	 * @param id 实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowLogInfo")
	public ModelAndView toShow(String id){
		LogInfo entity = logInfoService.getByPrimaryKey(id);
		Map<String,String> logInfo = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/logInfo/showLogInfo","logInfo",logInfo );
	}
	

	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param ids String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteLogInfo")
	public void deleteByKey(String ids,HttpServletResponse response){
		String[] str = ids.split(",");
		for(int i=0;i<str.length;i++){
			logInfoService.deleteByKey(str[i]);
		}		
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListLogInfo")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/system/logInfo/listLogInfo");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param logInfo LogInfo：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listLogInfo")
	public void listByCondition(LogInfo logInfo,HttpServletRequest request,
			HttpServletResponse response, Page page){
		logInfo.setOrderBy(page.getOrderFields());
		logInfo.setOrderType(page.getOrder());
		logInfo.setPage(page);
		List<LogInfo> list = logInfoService.listByCondition(logInfo);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toUpdateLogInfo")
	public ModelAndView toUpdate(String key){
		LogInfo entity = logInfoService.getByPrimaryKey(key);
		Map<String,String> logInfo = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/system/logInfo/updateLogInfo","logInfo",logInfo );
	}
	

	
	/**
	 * 获取枚举中定义的操作类型常量
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/getActTypeByEnum")
	public void getActTypeByEnum(HttpServletResponse response){
		String option = "";
		List<String> enumList = new ArrayList<String>();
		for (EnumConstant e : EnumConstant.values()) {
			option = "\"name\":\""+e.name()+"\",\"value\":\""+e.name()+"\""; 
			enumList.add(option);
		}
		AjaxUtils.sendAjaxForSelect(response, enumList);
	}
	
	
}