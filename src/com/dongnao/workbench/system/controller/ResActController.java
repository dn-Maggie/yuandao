package com.dongnao.workbench.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.model.ResAct;
import com.dongnao.workbench.system.service.ResActService;

/**
 * 描述：资源操作模块controller类，负责页面分发与跳转
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
 
@Controller
@RequestMapping("resAct")
public class ResActController{

	
	private ResActService resActService;
	
	/**
	 * 设置service
	 * @param resActService ResActService
	 */
	@Autowired
	public void setResActService(ResActService resActService) {
		this.resActService = resActService;
	}
 
 	/**
 	* 进入新增页面
	* @param key String：资源ID
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddResAct")
	public ModelAndView toAdd(String key){
		return new ModelAndView("WEB-INF/jsp/system/resAct/addResAct","resuuid",key);
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowResAct")
	public ModelAndView toShow(String key){
		ResAct entity = resActService.getByPrimaryKey(key);
		Map<String,String> resAct = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/resAct/showResAct","resAct",resAct );
	}
	
	/**
	 * 新增方法
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param resAct ResAct:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addResAct")
	public void add(ResAct resAct,HttpServletRequest request,HttpServletResponse response){
		String userId = Utils.getLoginUser(request);
		resAct.setCreateId(userId);
		resAct.setUpdateId(userId);
		resActService.add(resAct);
		AjaxUtils.sendAjaxSuccessMessage(response);
		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteResAct")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			resActService.deleteByKey(str[i]);
		}
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListResAct")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/system/resAct/listResAct");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param resAct ResAct：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listResAct")
	public void listByCondition(ResAct resAct,HttpServletRequest request,
			HttpServletResponse response, Page page){
		resAct.setSortFild(page.getOrderFields());
		resAct.setSortBy(page.getOrder());
		resAct.setPage(page);
		List<ResAct> list = resActService.listByCondition(resAct);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toUpdateResAct")
	public ModelAndView toUpdate(String key){
		ResAct entity = resActService.getByPrimaryKey(key);
		Map<String,String> resAct = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/system/resAct/updateResAct","resAct",resAct );
	}
	
	/**
	 * 修改方法
	 * @param resAct ResAct：实体对象
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateResAct")
	public void update(ResAct resAct,HttpServletRequest request,HttpServletResponse response){
		String userId = Utils.getLoginUser(request);
		resAct.setUpdateId(userId);
		resActService.update(resAct);
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
}