package com.dongnao.workbench.basic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.basic.dao.DutyMapper;
import com.dongnao.workbench.basic.model.Org;
import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.basic.service.OrgService;
import com.dongnao.workbench.basic.service.UserInfoService;
import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.controller.BaseController;
import com.dongnao.workbench.common.enums.UserInfoType;
import com.dongnao.workbench.common.excel.ExcelExpUtils;
import com.dongnao.workbench.common.excel.ExpParamBean;
import com.dongnao.workbench.common.excel.IValueFormatter;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.MD5Encryption;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.model.Personrole;
import com.dongnao.workbench.system.model.Role;
import com.dongnao.workbench.system.service.DictInfoService;
import com.dongnao.workbench.system.service.PersonroleService;
import com.dongnao.workbench.system.service.RoleService;


/**
 * 描述：userInfo模块controller类，负责页面分发与跳转
 * 
 * @author yao.su
 * @version 1.0 2016-03-13
 */

@Controller
@RequestMapping("userInfo")
public class UserInfoController extends BaseController{
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private PersonroleService personroleService;
	@Resource
	private DutyMapper dutyMapper;
	@Resource
	private RoleService roleService;
	@Resource
	private DictInfoService dictInfoService;
	@Resource
	private OrgService orgService;

	/**
	 * 进入新增页面
	 * 
	 * @return ModelAndView 返回到新增页面
	 */
	@RequestMapping("/toAddUserInfo")
	public ModelAndView toAdd(String orgId) {
		if(orgId.equals("002")){
			return getWarningView("投资人只能在注册页面进行注册！");
		}
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/basic/userInfo/addUserInfo");
		List<Role> roleList = roleService.listByCondition(new Role());
		dictInfoService.getDictInfoByType("");
		mv.addObject("roleList", roleList);
		mv.addObject("basicOrg", orgService.getByPrimaryKey(orgId));
		
		Org org = new Org();
 		org.setParentOrgId(orgId);
 		List<Org> list = orgService.listByCondition(org);
 		mv.addObject("orgList",list);
		
		return mv;
	}

	
	/**
	 * 进入兼职班主任新增页面
	 * 
	 * @return ModelAndView 返回到新增页面
	 */
	@RequestMapping("/toAddNewUserInfo")
	public ModelAndView toAddNew(String orgId) {
		if(orgId.equals("002")){
			return getWarningView("投资人只能在注册页面进行注册！");
		}
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/basic/userInfo/addNewUserInfo");
		List<Role> roleList = roleService.listByCondition(new Role());
		dictInfoService.getDictInfoByType("");
		mv.addObject("roleList", roleList);
		mv.addObject("basicOrg", orgService.getByPrimaryKey(orgId));
		return mv;
	}
	
	/**
	 * 进入查看页面方法
	 * 
	 * @param key
	 *            String：实体id
	 * @return ModelAndView: 查询实体
	 */
	@RequestMapping("/toShowUserInfo")
	public ModelAndView toShow(String key) {
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/basic/userInfo/showUserInfo");
		UserInfo entity = userInfoService.getByPrimaryKey(key);
		Map<String, String> userInfo = FormatEntity.getObjectValue(entity);
		Personrole personrole = getPersonrole(entity.getId());
		if (personrole != null) {
			userInfo.put("roleId", personrole.getRoleId());
		}
		List<Role> roleList = roleService.listByCondition(new Role());
		dictInfoService.getDictInfoByType("");
		mv.addObject("roleList", roleList);
		mv.addObject("userInfo", userInfo);
		return mv;
	}

	/**
	 * 新增方法
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param userInfo
	 *            UserInfo:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addUserInfo")
	public void add(String roleId, UserInfo userInfo,
			HttpServletRequest request, HttpServletResponse response)throws IOException{
		// 设置普通员工
		userInfo.setUserType(UserInfoType.STAFF.getValue());
		AjaxUtils.sendAjaxForObjectStr(
				response,
				userInfoService.add(userInfo, roleId,
						Utils.getLoginUserInfo(request)));
	}

	/**
	 * ajax验证用户名是否存在
	 * 
	 * @param userAccount
	 *            用户登录名
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping("/ajaxGetUserInfoByUserAccount")
	public void ajaxGetUserInfoByUserAccount(String userAccount, String userId,
			HttpServletRequest request, HttpServletResponse response) {
		UserInfo userInfo = userInfoService.getByUserAccount(userAccount);
		boolean b = false;
		if (userInfo != null && !userInfo.getId().equals(userId)) {
			b = true;
		}
		AjaxUtils.sendAjaxForObject(response, b);
	}

	/**
	 * 删除方法
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param key
	 *            String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteUserInfo")
	public void deleteByKey(String key, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg",
				userInfoService.deleteByKey(key,
						Utils.getLoginUserInfo(request)));
		AjaxUtils.sendAjaxForMap(response, map);
	}

	/**
	 * 进入列表页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/toListUserInfo")
	public ModelAndView toList() {
		ModelAndView m = new ModelAndView(
				"WEB-INF/jsp/basic/userInfo/listUserInfo");
		m.addObject("statusData", Utils.getDictInfo("status", true));
		m.addObject("userTypeData", Utils.getDictInfo("userType", true));
		return m;
	}
	
	/**
	 * 进入兼职班主任查看列表页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/toListNewUserInfo")
	public ModelAndView toListNew(HttpServletRequest request) {
		ModelAndView m = new ModelAndView(
				"WEB-INF/jsp/basic/userInfo/listNewUserInfo");
		m.addObject("statusData", Utils.getDictInfo("status", true));
		m.addObject("userTypeData", Utils.getDictInfo("userType", true));
		m.addObject("createdby", Utils.getLoginUserInfo(request).getCreatedby());
		return m;
	}
	
	
	
	/**
	 * 进入列表弹出选择页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/toListDlg")
	public ModelAndView toListDlg() {
		ModelAndView m = new ModelAndView(
				"WEB-INF/jsp/basic/userInfo/listUserInfoDlg");
		m.addObject("statusData", Utils.getDictInfo("status", true));
		m.addObject("userTypeData", Utils.getDictInfo("userType", true));
		return m;
	}

	/**
	 * 根据条件查找列表方法
	 * 
	 * @param userInfo
	 *            UserInfo：实体对象（查询条件）
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param page
	 *            Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listUserInfo")
	public void listByCondition(UserInfo userInfo, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		userInfo.setPage(page);
		userInfo.setIsActive(Constant.ISDELETE_FALSE);
		List<UserInfo> list = userInfoService.listByCondition(userInfo);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}

	/**
	 * 进入修改页面方法
	 * 
	 * @param key
	 *            String：实体id
	 * @return ModelAndView: 查询实体
	 */
	@RequestMapping("/toEditUserInfo")
	public ModelAndView toEdit(String key, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(
				"WEB-INF/jsp/basic/userInfo/editUserInfo");
		UserInfo entity = userInfoService.getByPrimaryKey(key);
		Map<String, String> userInfo = FormatEntity.getObjectValue(entity);
		Personrole personrole = getPersonrole(entity.getId());
		if (personrole != null) {
			userInfo.put("roleId", personrole.getRoleId());
		}
		List<Role> roleList = roleService.listByCondition(new Role());
		dictInfoService.getDictInfoByType("");
		mv.addObject("roleList", roleList);
		mv.addObject("userInfo", userInfo);
		return mv;
	}

	/**
	 * 修改方法
	 * 
	 * @param userInfo
	 *            UserInfo：实体对象
	 * @param response
	 *            HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/updateUserInfo")
	public void update(String roleId, UserInfo userInfo,
			HttpServletRequest request, HttpServletResponse response)throws IOException{
		// 设置普通员工
		userInfo.setUserType(UserInfoType.STAFF.getValue());
		AjaxUtils.sendAjaxForObjectStr(
				response,
				userInfoService.update(userInfo, roleId,
					Utils.getLoginUserInfo(request)));
	}

	/**
	 * 根据条件获取用户列表
	 * 
	 * @param roleRid
	 *            角色ID
	 * @param fullName
	 *            用户名称
	 * @param userAccount
	 *            用户登录名
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param page
	 *            分页信息
	 */
	@RequestMapping("/listUserByRid")
	public void listUserByRid(String roleRid, String fullName,
			String userAccount, HttpServletRequest request,
			HttpServletResponse response, Page page) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleRid);
		params.put("fullName", fullName);
		params.put("userAccount", userAccount);
		params.put("page", page);
		List<HashMap<String, String>> list = userInfoService.listUserByRid(params);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}

	
	/**
	 * 修改登陆用户密码
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping("/toEditPassWord")
	public ModelAndView toEditPassWord(HttpServletRequest request){
		UserInfo userInfo=Utils.getLoginUserInfo(request);
		if(userInfo!=null){
			return new ModelAndView("WEB-INF/jsp/common/editPassWord", "userInfo",userInfo);
		}else{
			request.getSession().removeAttribute(Constant.LOGIN_USER_KEY);
			return new ModelAndView("WEB-INF/jsp/common/login");
		}
	}
	
	/**
	 * 修改密码
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param oldPassword 旧密码
	 * @param newPassWord 新密码
	 */
	@RequestMapping("/changePassWord")
	public void updatePassWord(HttpServletRequest request,HttpServletResponse response,String oldPassword,String newPassWord){
		UserInfo userInfo=Utils.getLoginUserInfo(request);
		Map<String, String> map = new HashMap<String, String>();
		if(userInfo.getPassword().equals(MD5Encryption.MD5(oldPassword))){
			userInfo.setPassword(newPassWord);
			userInfo.setId(userInfo.getId());
			userInfoService.updatePassword(userInfo);
			map.put("rs", "true");
		}else{
			map.put("rs", "false");
		}
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 重置为初始密码
	 * 
	 * @param key
	 *            当前用户ID
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	@RequestMapping("/passwordReset")
	public void passwordReset(String key, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		if (key != null && !"".equals(key)) {
			UserInfo info = userInfoService.getByPrimaryKey(key);
			if (info != null) {
				String userId = Utils.getLoginUser(request);
				info.setPassword(Utils
						.getConfigValue(Constant.INITIAL_PASSWORD));
				info.setUpdatedby(userId);
				info.setUpdated(DateUtil.now());
				userInfoService.updatePassword(info);
				map.put("rs", "true");
			}
		}
		AjaxUtils.sendAjaxForMap(response, map);
	}

	/**
	 * 根据用户ID获取角色用户关联实体
	 * 
	 * @param userInfoId
	 *            用户ID
	 * @return 角色用户关联实体
	 */
	private Personrole getPersonrole(String userInfoId) {
		Personrole personrole = new Personrole();
		personrole.setUserId(userInfoId);
		List<Personrole> personroles = personroleService
				.listByCondition(personrole);
		if (personroles != null && personroles.size() > 0) {
			return personroles.get(0);
		} else {
			return null;
		}
	}

	@RequestMapping("/exportExcel")
	public void exportExcel(UserInfo userInfo, ExpParamBean epb,
			HttpServletRequest request, HttpServletResponse response, Page page)
			throws Exception {
		ExcelExpUtils.setExpFieldBeanValFmt(epb.getFieldlist(),
				new IValueFormatter() {
					public Object formatter(Object v) {
						if (v == null) {
							return "";
						}
						return (v.toString()=="1" ? "开启" : "停用");
					}
				}, "states");
		int expType = Integer.parseInt(request.getParameter("expType"));
		if (expType == 1) {
			userInfo.setPage(page);
		}
		List<UserInfo> list = userInfoService.listByCondition(userInfo);
		ExcelExpUtils.exportListToExcel(list, response, epb.getFieldlist(),
				"用户基础信息列表", "用户基础信息列表");
	}

	
	/**
	 * 进入员工资源管理选择界面
	 */	
	@RequestMapping("/toSelectEmployee")
	public ModelAndView toSelectEmployee(){
		return new ModelAndView("WEB-INF/jsp/basic/userInfo/selectEmployee");
	}
	
}