package com.dongnao.workbench.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.model.Personrole;
import com.dongnao.workbench.system.service.PersonroleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：用户角色关联模块controller类，负责页面分发与跳转
 * 
 * @author yang.bin
 * @version 1.0 2016-03-06
 */
 
@Controller
@RequestMapping("personrole")
public class PersonroleController{

	private PersonroleService personroleService;
	
	/**
	 * 设置service
	 * @param personroleService PersonroleService
	 */
	@Autowired
	public void setPersonroleService(PersonroleService personroleService) {
		this.personroleService = personroleService;
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddPersonrole")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/system/personrole/addPersonrole");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowPersonrole")
	public ModelAndView toShow(String key){
		Personrole entity = personroleService.getByPrimaryKey(key);
		Map<String,String> personrole = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/personrole/showPersonrole","personrole",personrole );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param personrole Personrole:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addPersonrole")
	public void add(Personrole personrole,HttpServletResponse response){
		personroleService.add(personrole);
		AjaxUtils.sendAjaxSuccessMessage(response);
		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deletePersonrole")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			personroleService.deleteByKey(str[i]);
		}
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListPersonrole")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/system/personrole/listPersonrole");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param personrole Personrole：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listPersonrole")
	public void listByCondition(Personrole personrole,HttpServletRequest request,
			HttpServletResponse response, Page page){
		personrole.setPage(page);	
		List<Personrole> list = personroleService.listByCondition(personrole);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditPersonrole")
	public ModelAndView toEdit(String key){
		Personrole entity = personroleService.getByPrimaryKey(key);
		Map<String,String> personrole = FormatEntity.getObjectValue(entity);
		
		return new ModelAndView("WEB-INF/jsp/system/personrole/editPersonrole","personrole",personrole );
	}
	
	/**
	 * 修改方法
	 * @param personrole Personrole：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updatePersonrole")
	public void update(Personrole personrole,HttpServletResponse response){
		personroleService.update(personrole);
		AjaxUtils.sendAjaxSuccessMessage(response);
	}
	
}