package com.dongnao.workbench.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;


/**
 * 描述：org模块controller类，负责页面分发与跳转
 * 
 * @author yao.su
 * @version 1.0 2016-03-21
 */
 
@Controller
@RequestMapping("org")
public class OrgController{
	@Resource
	private OrgService orgService;
	
	
	/**
	 * 设置service
	 * @param orgService OrgService
	 */
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddOrg")
	public ModelAndView toAdd(String parentOrgId){
 		ModelAndView mv=new ModelAndView("WEB-INF/jsp/basic/org/addOrg");
 		Map<String,String> orgParent = FormatEntity.getObjectValue(orgService.getByPrimaryKey(parentOrgId));
 		mv.addObject("orgParent",orgParent);
		return mv;
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowOrg")
	public ModelAndView toShow(String key){
		Org entity = orgService.getByPrimaryKey(key);
		Map<String,String> org = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/basic/org/showOrg","org",org );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param org Org:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addOrg")
	public void add(Org org,HttpServletRequest request,HttpServletResponse response){
	    org.setCreatedby(Utils.getLoginUser(request));
	    org.setCreated(DateUtil.now());
	    org.setId(Utils.generateUniqueID());
		org.setIsActive(Constant.ISACTIVE_1);
		AjaxUtils.sendAjaxForObjectStr(response,orgService.add(org));
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteOrg")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			orgService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListOrg")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/basic/org/listOrg");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param org Org：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listOrg")
	public void listByCondition(Org org,HttpServletRequest request,
			HttpServletResponse response, Page page){
		org.setPage(page);	
		List<Org> list = orgService.listByCondition(org);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	@RequestMapping("/initOrgTree")
	public void initOrgTree(Org org,HttpServletRequest request, HttpServletResponse response){
		AjaxUtils.sendAjaxForObject(response,orgService.initOrgTree(org.getOrgName(), Utils.getLoginUserInfo(request)));
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditOrg")
	public ModelAndView toEdit(String key){
		ModelAndView mv=new ModelAndView("WEB-INF/jsp/basic/org/editOrg");
		Org entity = orgService.getByPrimaryKey(key);
		Map<String,String> org = FormatEntity.getObjectValue(entity);
		mv.addObject("org",org);
		return mv;
	}
	
	/**
	 * 修改方法
	 * @param org Org：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateOrg")
	public void update(Org org,HttpServletResponse response,HttpServletRequest request){
		org.setUpdated(DateUtil.now());
		org.setUpdatedby(Utils.getLoginUser(request));
		AjaxUtils.sendAjaxForObjectStr(response,orgService.update(org));
	}
	
}