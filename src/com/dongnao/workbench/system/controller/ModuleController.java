package com.dongnao.workbench.system.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;




import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.model.Module;
import com.dongnao.workbench.system.service.ModuleService;



/**
 * 描述：菜单模块controller类，负责页面分发与跳转
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
 
@Controller
@RequestMapping("module")
public class ModuleController{

	
	private ModuleService moduleService;
	


	
	/**
	 * 设置service
	 * @param moduleService ModuleService
	 */
	@Autowired
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddModule")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/system/module/addModule");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowModule")
	public ModelAndView toShow(String key){
		Module entity = moduleService.getByPrimaryKey(key);
		Map<String,String> module = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/module/showModule","module",module );
	}
	
	/**
	 * 新增方法
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param module Module:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addModule")
	public void add(Module module,HttpServletRequest request,HttpServletResponse response){
		String userId = Utils.getLoginUser(request);
		module.setCreateId(userId);
		module.setUpdateId(userId);
		moduleService.add(module);
		AjaxUtils.sendAjaxSuccessMessage(response);
		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteModule")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			moduleService.deleteByKey(str[i]);
		}
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListModule")
	public ModelAndView toList(){
		ModelAndView m = new ModelAndView("WEB-INF/jsp/system/module/listModule");
		m.addObject("statusData", Utils.getDictInfo("status", true));
		m.addObject("yn_numData", Utils.getDictInfo("yn_num", true));
		return m;
		
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param module Module：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listModule")
	public void listByCondition(Module module,HttpServletRequest request,
			HttpServletResponse response, Page page){
		module.setSortFild(page.getOrderFields());
		module.setSortBy(page.getOrder());
		module.setPage(page);
		List<Module> list = moduleService.listByCondition(module);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toUpdateModule")
	public ModelAndView toUpdate(String key){
		Module entity = moduleService.getByPrimaryKey(key);
		Map<String,String> module = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/system/module/updateModule","module",module );
	}
	
	/**
	 * 修改方法
	 * @param module Module：实体对象
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateModule")
	public void update(Module module,HttpServletRequest request,HttpServletResponse response){
		String userId = Utils.getLoginUser(request);
		module.setUpdateId(userId);
		moduleService.update(module);
		AjaxUtils.sendAjaxSuccessMessage(response);
	}

	/**
	 * 进入菜单资源管理
	 * @param key String：菜单ID
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toListModuleRes")
	public ModelAndView toListModuleRes(String key){
		return new ModelAndView("WEB-INF/jsp/system/module/listModuleRes","muuid",key );
	}
	
	/**
	 * 进入菜单资源管理选择界面
	 * @param key String：菜单ID
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toSelectModuleRes")
	public ModelAndView toSelectModuleRes(String key){
		return new ModelAndView("WEB-INF/jsp/system/module/selectModuleRes","muuid",key);
	}
	
	/**
	 * 获取菜单数据
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/getMenus")
	public void getMenus(HttpServletRequest request,HttpServletResponse response){
        String menus = null;
        String pcode = Utils.getLoginUserInfo(request).getId().toString();
        String loginUser = Utils.getLoginUser(request);
//        String webSysAdmin = request.getSession().getServletContext().getAttribute(Constant.WEB_SYS_ADMIN).toString();
		String webSysAdmin = Utils.getConfigValue(Constant.WEB_SYS_ADMIN);
		String[] adminArr = webSysAdmin.split(",");
		for(int i=0,l=adminArr.length;i<l;i++){
			if(adminArr[i].equals(loginUser)){
				webSysAdmin = adminArr[i];
				break;
			}
		}
        if (webSysAdmin.equals(loginUser)) {
			menus = moduleService.getMenuJsonForAdmin();
		}else {
			menus = moduleService.getMenuJsonByPcode(pcode);
		}
		AjaxUtils.sendAjaxForObject(response, menus);
	}
	
	
	

	
}