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
import com.dongnao.workbench.system.model.RoleRight;
import com.dongnao.workbench.system.service.RoleRightService;

/**
 * 描述：角色权限模块controller类，负责页面分发与跳转
 * @author joan.xiong
 * @version 1.0 2016-03-11
 */
 
@Controller
@RequestMapping("roleRight")
public class RoleRightController{

	
	/**
	 * 定义 角色权限模块service
	 */
	private RoleRightService roleRightService;
	
	/**
	 * 设置service
	 * @param roleRightService RoleRightService
	 */
	@Autowired
	public void setRoleRightService(RoleRightService roleRightService) {
		this.roleRightService = roleRightService;
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddRoleRight")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/system/addRoleRight");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowRoleRight")
	public ModelAndView toShow(String key){
		RoleRight entity = roleRightService.getByPrimaryKey(key);
		Map<String,String> roleRight = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/showRoleRight","roleRight",roleRight );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param roleRight RoleRight:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addRoleRight")
	public void add(RoleRight roleRight,HttpServletResponse response){
		roleRightService.add(roleRight);
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteRoleRight")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			roleRightService.deleteByKey(str[i]);
		}
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListRoleRight")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/system/listRoleRight");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param roleRight RoleRight：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listRoleRight")
	public void listByCondition(RoleRight roleRight,HttpServletRequest request,
			HttpServletResponse response, Page page){
		roleRight.setPage(page);
		List<RoleRight> list = roleRightService.listByCondition(roleRight);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toUpdateRoleRight")
	public ModelAndView toUpdate(String key){
		RoleRight entity = roleRightService.getByPrimaryKey(key);
		Map<String,String> roleRight = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/system/updateRoleRight","roleRight",roleRight );
	}
	
	/**
	 * 修改方法
	 * @param roleRight RoleRight：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateRoleRight")
	
	public void update(RoleRight roleRight,HttpServletResponse response){
		roleRightService.update(roleRight);
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
}