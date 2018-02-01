package com.dongnao.workbench.basic.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.basic.model.Course;
import com.dongnao.workbench.basic.model.Subject;
import com.dongnao.workbench.basic.service.CourseService;
import com.dongnao.workbench.basic.service.SubjectService;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.common.util.FormatEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述：课程表模块controller类，负责页面分发与跳转
 * 
 * @author cheng.mo
 * @version 1.0 2016-05-01
 */

@Controller
@RequestMapping("course")
public class CourseController {
	@Resource
	private CourseService courseService;
	@Resource
	private SubjectService subjectService;
	/**
	 * 进入新增页面
	 * 
	 * @return ModelAndView 返回到新增页面
	 */
	@RequestMapping("/toAddCourse")
	public ModelAndView toAdd() {
		ModelAndView m=new ModelAndView("WEB-INF/jsp/basic/course/addCourse");
		Subject s=new Subject();
		List<Subject> subList=subjectService.listByCondition(s);
 		m.addObject("subList",subList);
		return m;
	}

	/**
	 * 进入查看页面方法
	 * 
	 * @param key
	 *            String：实体id
	 * @return ModelAndView: 查询实体
	 */
	@RequestMapping("/toShowCourse")
	public ModelAndView toShow(String key) {
		Course entity = courseService.getByPrimaryKey(key);
		Map<String, String> course = FormatEntity.getObjectValue(entity);
		ModelAndView m=new ModelAndView("WEB-INF/jsp/basic/course/showCourse", "course", course);
		Subject s=new Subject();
		List<Subject> subList=subjectService.listByCondition(s);
 		m.addObject("subList",subList);
		return m;
	}

	/**
	 * 新增方法
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param course
	 *            Course:实体类
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/addCourse")
	public void add(Course course, HttpServletRequest request, HttpServletResponse response) {
		course.setId(Utils.generateUniqueID());
		AjaxUtils.sendAjaxForObjectStr(response, courseService.add(course) );
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
	@RequestMapping("/deleteCourse")
	public void deleteByKey(String key, HttpServletResponse response) {
		String[] str = key.split(",");
		for (int i = 0; i < str.length; i++) {
			courseService.deleteByKey(str[i]);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", "成功");
		AjaxUtils.sendAjaxForMap(response, map);
	}

	/**
	 * 进入列表页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping("/toListCourse")
	public ModelAndView toList() {
		ModelAndView m=new ModelAndView("WEB-INF/jsp/basic/course/listCourse");
		Subject s=new Subject();
		List<Subject> subList=subjectService.listByCondition(s);
		Course c=new Course();
		List<Course> couList=courseService.listByCondition(c);
 		m.addObject("subList",subList);
 		m.addObject("couList",couList);
		return m;
	}

	/**
	 * 根据条件查找列表方法
	 * 
	 * @param course
	 *            Course：实体对象（查询条件）
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param page
	 *            Page:分页对象
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/listCourse")
	public void listByCondition(Course course, HttpServletRequest request, HttpServletResponse response, Page page) {
		course.setPage(page);
		List<Course> list = courseService.listByCondition(course);
		AjaxUtils.sendAjaxForPage(request, response, page, list);
	}

	/**
	 * 进入修改页面方法
	 * 
	 * @param key
	 *            String：实体id
	 * @return ModelAndView: 查询实体
	 */
	@RequestMapping("/toEditCourse")
	public ModelAndView toEdit(String key) {
		Course entity = courseService.getByPrimaryKey(key);
		Map<String, String> course = FormatEntity.getObjectValue(entity);
		ModelAndView m=new ModelAndView("WEB-INF/jsp/basic/course/editCourse", "course", course);
		Subject s=new Subject();
		List<Subject> subList=subjectService.listByCondition(s);
 		m.addObject("subList",subList);
		return m;
	}

	/**
	 * 修改方法
	 * 
	 * @param course
	 *            Course：实体对象
	 * @param response
	 *            HttpServletResponse
	 * @return: ajax输入json字符串
	 */
	@RequestMapping("/updateCourse")
	public void update(Course course, HttpServletRequest request, HttpServletResponse response) {
		AjaxUtils.sendAjaxForObjectStr(response, courseService.update(course));
	}

}