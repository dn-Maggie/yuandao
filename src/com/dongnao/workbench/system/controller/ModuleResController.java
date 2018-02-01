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
import com.dongnao.workbench.system.model.ModuleRes;
import com.dongnao.workbench.system.service.ModuleResService;

/**
 * 描述：菜单资源模块controller类，负责页面分发与跳转
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
 
@Controller
@RequestMapping("moduleRes")
public class ModuleResController{

	
	private ModuleResService moduleResService;
	
	/**
	 * 设置service
	 * @param moduleResService ModuleResService
	 */
	@Autowired
	public void setModuleResService(ModuleResService moduleResService) {
		this.moduleResService = moduleResService;
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddModuleRes")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/system/moduleRes/addModuleRes");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowModuleRes")
	public ModelAndView toShow(String key){
		ModuleRes entity = moduleResService.getByPrimaryKey(key);
		Map<String,String> moduleRes = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/moduleRes/showModuleRes","moduleRes",moduleRes );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param moduleRes ModuleRes:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addModuleRes")
	public void add(ModuleRes moduleRes,HttpServletResponse response){
		moduleResService.add(moduleRes);
		Map<String, String> map = new HashMap<String, String>();
		AjaxUtils.sendAjaxSuccessMessage(response);
		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteModuleRes")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			moduleResService.deleteByKey(str[i]);
		}
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListModuleRes")
	public ModelAndView toList(){
		ModelAndView m = new ModelAndView("WEB-INF/jsp/system/moduleRes/listModuleRes");
		m.addObject("statusData", Utils.getDictInfo("status", true));
		m.addObject("yn_numData", Utils.getDictInfo("yn_num", true));
		return m;
		
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param moduleRes ModuleRes：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listModuleRes")
	public void listByCondition(ModuleRes moduleRes,HttpServletRequest request,
			HttpServletResponse response, Page page){
		moduleRes.setSortFild(page.getOrderFields());
		moduleRes.setSortBy(page.getOrder());
		moduleRes.setPage(page);
		List<ModuleRes> list = moduleResService.listByCondition(moduleRes);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toUpdateModuleRes")
	public ModelAndView toUpdate(String key){
		ModuleRes entity = moduleResService.getByPrimaryKey(key);
		Map<String,String> moduleRes = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/system/moduleRes/updateModuleRes","moduleRes",moduleRes );
	}
	
	/**
	 * 修改方法
	 * @param moduleRes ModuleRes：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateModuleRes")
	public void update(ModuleRes moduleRes,HttpServletResponse response){
		moduleResService.update(moduleRes);
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
	/**
	 * 更新  资源所在的菜单数据
	 * @param resuuids String：资源IDS,多个由“，”分割开的id字符串
	 * @param muuid String：菜单ID
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/changeMrMuuid")
	public void changeMrMuuid(String resuuids,String muuid,HttpServletResponse response){
		moduleResService.updateMrMuuid(resuuids,muuid);
		AjaxUtils.sendAjaxSuccessMessage(response);
	}

	/**
	 * 进入资源操作管理
	 * @param key String：资源ID
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toListResAct")
	public ModelAndView toListModuleRes(String key){
		return new ModelAndView("WEB-INF/jsp/system/resAct/listResAct","resuuid",key );
	}
	
}