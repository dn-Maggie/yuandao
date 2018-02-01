package com.dongnao.workbench.basic.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.basic.service.SubjectService;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.common.util.FormatEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * 描述：学科表模块controller类，负责页面分发与跳转
 * 
 * @author fan.yang
 * @version 1.0 2016-05-01
 */
 
@Controller
@RequestMapping("subject")
public class SubjectController{
         @Resource
	private SubjectService subjectService;
	 
 	/**
 	* 进入新增页面
 	* @return ModelAndView 返回到新增页面
 	*/
 	@RequestMapping("/toAddSubject")
	public ModelAndView toAdd(){
		return new ModelAndView("WEB-INF/jsp/basic/subject/addSubject");
	}
	
	/**
	 * 进入查看页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toShowSubject")
	public ModelAndView toShow(String key){
		Subject entity = subjectService.getByPrimaryKey(key);
		Map<String,String> subject = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/basic/subject/showSubject","subject",subject );
	}
	
	/**
	 * 新增方法
	 * @param response HttpServletResponse
	 * @param subject Subject:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addSubject")
	public void add(Subject subject,HttpServletRequest request,HttpServletResponse response){
	subject.setId(Utils.generateUniqueID());
	AjaxUtils.sendAjaxForObjectStr(
				response,subjectService.add(subject));		
	}
	
	/**
	 * 删除方法
	 * @param response HttpServletResponse
	 * @param key String:多个由“，”分割开的id字符串
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/deleteSubject")
	public void deleteByKey(String key,HttpServletResponse response){
		String[] str = key.split(",");
		for(int i=0;i<str.length;i++){
			subjectService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}
	
	/**
	 * 进入列表页面
	 * @return ModelAndView
	 */
	@RequestMapping("/toListSubject")
	public ModelAndView toList(){
		return new ModelAndView("WEB-INF/jsp/basic/subject/listSubject");
	}
	
	/**
	 * 根据条件查找列表方法
	 * @param subject Subject：实体对象（查询条件）
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param page Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listSubject")
	public void listByCondition(Subject subject,HttpServletRequest request,
			HttpServletResponse response, Page page){
		subject.setPage(page);	
		List<Subject> list = subjectService.listByCondition(subject);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}
	
	/**
	 * 进入修改页面方法
	 * @param key String：实体id
	 * @return ModelAndView: 查询实体
	 */	
	@RequestMapping("/toEditSubject")
	public ModelAndView toEdit(String key){
		Subject entity = subjectService.getByPrimaryKey(key);
		Map<String,String> subject = FormatEntity.getObjectValue(entity);
		return new ModelAndView("WEB-INF/jsp/basic/subject/editSubject","subject",subject );
	}
	
	/**
	 * 修改方法
	 * @param subject Subject：实体对象
	 * @param response HttpServletResponse
	 * @return: ajax输入json字符串
	 */	
	@RequestMapping("/updateSubject")
	public void update(Subject subject,HttpServletRequest request,HttpServletResponse response){
		AjaxUtils.sendAjaxForObjectStr(
				response,subjectService.update(subject));	
	}
	
}