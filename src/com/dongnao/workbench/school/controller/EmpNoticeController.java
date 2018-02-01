package com.dongnao.workbench.school.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.DateUtil;
import com.dongnao.workbench.common.util.FormatEntity;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.school.model.EmpNotice;
import com.dongnao.workbench.school.service.EmpNoticeService;
import com.dongnao.workbench.school.service.EmployeeService;


/**
 * 描述：员工公告通知模块controller类，负责页面分发与跳转
 * 
 * @author maggie
 * @version 1.0 2016-12-22
 */
 
@Controller
@RequestMapping("empNotice")
public class EmpNoticeController extends HttpServlet{
	@Resource
	private EmpNoticeService empNoticeService;
	@Resource
  	private EmployeeService employeeService;	
	 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddEmpNotice")
	public ModelAndView toAdd(HttpServletRequest request,HttpServletResponse response){
 		ModelAndView mv = new ModelAndView("WEB-INF/jsp/school/empNotice/addEmpNotice");
 		mv.addObject("user", employeeService.getByPrimaryKey(Utils.getLoginUserInfoId(request)));
 		return mv;
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowEmpNotice")
	public ModelAndView toShow(String key){
		EmpNotice entity = empNoticeService.getByPrimaryKey(key);
		Map<String,String> empNotice = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/school/empNotice/showEmpNotice","empNotice",empNotice );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param empNotice EmpNotice:实体类
	 * @return: ajax输入json字符串
	 * @throws FileUploadException 
	 */
	@RequestMapping("/addEmpNotice")
	public void add(EmpNotice empNotice,HttpServletRequest request,HttpServletResponse response) throws FileUploadException{
	empNotice.setId(Utils.generateUniqueID());
	empNotice.setCreateId(Utils.getLoginUserInfoId(request));
	empNotice.setCreateTime(DateUtil.now());
	if(empNotice.getFileUrl()!=null&&empNotice.getFileUrl()!=""){
		String file = empNotice.getFileUrl();
		file = file.substring(file.lastIndexOf("\\")+1);
		empNotice.setFileName(file);
	}
	AjaxUtils.sendAjaxForObjectStr(
		response,empNoticeService.add(empNotice));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteEmpNotice")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			empNoticeService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListEmpNotice")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/school/empNotice/listEmpNotice");
	}
	
	
	
	/**
	 * 根据条件查找列表方法
	 * @param empNotice EmpNotice：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listEmpNotice")
	public void listByCondition(EmpNotice empNotice,HttpServletRequest request,
			HttpServletResponse response, Page page){
		empNotice.setPage(page);	
		List<EmpNotice> list = empNoticeService.listByCondition(empNotice);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditEmpNotice")
	public ModelAndView toEdit(String key,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/school/empNotice/editEmpNotice");
		mv.addObject("empNotice",FormatEntity.getObjectValue(empNoticeService.getByPrimaryKey(key)));
		mv.addObject("user", employeeService.getByPrimaryKey(Utils.getLoginUserInfoId(request)));
		return mv;
	}
	
	/**
	 * 修改方法
	 * @param empNotice EmpNotice：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateEmpNotice")
	public void update(EmpNotice empNotice,HttpServletRequest request,HttpServletResponse response){
		if(empNotice.getEditTime()==null){
			empNotice.setEditId(Utils.getLoginUserInfoId(request));
			empNotice.setEditTime(DateUtil.now());
			AjaxUtils.sendAjaxForObjectStr(
					response,empNoticeService.update(empNotice));	
		}
		
		
	}
}