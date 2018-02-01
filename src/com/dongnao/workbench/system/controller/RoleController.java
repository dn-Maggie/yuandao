package com.dongnao.workbench.system.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;













import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.basic.service.UserInfoService;
import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.model.Role;
import com.dongnao.workbench.system.service.RoleService;

import net.sf.json.JSONArray;


/**
 * 描述：角色模块controller类，负责页面分发与跳转
 * @author joan.xiong
 * @version 1.0 2013-11-11
 */
 
@Controller
@RequestMapping("role")
public class RoleController{

	private RoleService roleService;
	
	private UserInfoService userInfoService;
	
	/**
	 * 设置userInfoService
	 * @param userInfoService
	 */
	@Autowired
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}
	/**
	 * 设置service
	 * @param roleService RoleService
	 */
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddRole")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/system/role/addRole");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowRole")
	public ModelAndView toShow(String key){
		Role entity = roleService.getByPrimaryKey(key);
		Map<String,String> role = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/role/showRole","role",role );
	}
	
	/**
	 * 新增方法
	 * @param role 角色实体
	 * @param request HttpServletRequest
	 * @param response HttpServletRequest
	 */
	@RequestMapping("/addRole")
	public void add(Role role,HttpServletRequest request,HttpServletResponse response){
		role.setRoleId(Utils.generateUniqueID());
		String userId=Utils.getLoginUser(request);
		role.setCreatorId(userId);
		role.setUpdaterId(userId);
		AjaxUtils.sendAjaxForObjectStr(response, roleService.add(role));
	}
	
	/**
	 * 删除方法
	 * @param key 数据ID，多个ID以","分隔
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/deleteRole")
	public void deleteByKey(String key,HttpServletRequest request,HttpServletResponse response){
		//通过角色id验证角色下是否有用户
		HashMap<String, Object>params=new HashMap<String, Object>();
		params.put("roleId", key);
		List<HashMap<String, String>> list = userInfoService.listUserByRid(params);
		if ( list.size()>0) {
		AjaxUtils.sendAjaxForObject(response, "0");
		}
		else {
		roleService.deleteByKey(key,Utils.getLoginUser(request));
		AjaxUtils.sendAjaxForObject(response, "1");
		}
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListRole")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/system/role/listRole");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param role Role：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listRole")
	public void listByCondition(Role role,HttpServletRequest request,
			HttpServletResponse response, Page page){
		role.setPage(page);	
		List<Role> list = roleService.listByCondition(role);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditRole")
	public ModelAndView toEdit(String key){
		Role entity = roleService.getByPrimaryKey(key);
		Map<String,String> role = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/system/role/editRole","role",role );
	}
	
	/**
	 * 修改方法
	 * @param role 角色实体
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/updateRole")
	public void update(Role role,HttpServletRequest request,HttpServletResponse response){
		role.setUpdaterId(Utils.getLoginUser(request));
		AjaxUtils.sendAjaxForObjectStr(response, roleService.update(role));
	}

	/**
	 * 获取菜单树所有菜单JSON字符串
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/getAllmenuTreeJson")
	public void getAllmenuTreeJson(String type,HttpServletRequest request,HttpServletResponse response){
		String resType="";
		if(type!=null&&!"".equals(type)){
			resType=type;
		}
		String jsonData = roleService.getAllmenuTreeJson(resType);
		AjaxUtils.sendAjaxForObject(response, jsonData);
	}
	
	/**
	 * 根据角色ID获取资源树 
	 * @param roleRid 角色ID
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/getMenuTreeJsonByRole")
	public void getMenuTreeJsonByRole(String roleRid,HttpServletRequest request,HttpServletResponse response){
		String jsonData = roleService.getMenuTreeJsonByRole(roleRid);
		AjaxUtils.sendAjaxForObject(response, jsonData);
	}
	
	/**
	 * 获取指定角色的菜单树JSON字符串
	 * @param roleRid String: 角色ID
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/getMenuTreeIdsByRoleId")
	public void getMenuTreeIdsByRoleId(String roleRid,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObject(response,roleService.getMenuTreeIdsByRoleId(roleRid));
	}
	
	/**
	 * 保存角色权限分配信息
	 * @param roleRid  角色ID
	 * @param objectJson  权限JSON字符串
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/portionRight")
	public void portionRight(String roleRid,String objectJson,HttpServletRequest request,HttpServletResponse response){
		String jsonData = roleService.saveRoleRight(roleRid, objectJson);
		AjaxUtils.sendAjaxForObject(response, jsonData);
	}

	/**
	 * 进入角色人员列表页面
	 * @param roleRid  角色ID
	 * @return ModelAndView
	 */
	@RequestMapping("/toShowRoleUser")
	public ModelAndView toShowRoleUser(String roleRid){
		return new ModelAndView("WEB-INF/jsp/system/role/listRoleUser","roleRid",roleRid);
	}
	
	/**
	 * 为页面获取角色下拉列表
	 * @param userKey 用户ID
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/getRoleListForSelect")
	public void getRoleListForSelectByUserType(String userKey,HttpServletRequest request,HttpServletResponse response){
		Role role=new Role();
		role.setStates(Constant.ENABLE_VALUE);
		List<String> roles =new ArrayList<String>();
		roles=roleService.getRoleListForSelect(role);
		AjaxUtils.sendAjaxForSelect(response, roles);
	}

}