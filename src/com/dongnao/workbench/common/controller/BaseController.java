package com.dongnao.workbench.common.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.student.service.VipStudentService;


@Controller
@RequestMapping("/base/")
public class BaseController {
	@Resource
	private VipStudentService vipStudentService;
	/**
	 * 获取警告视图
	 * 
	 * @param message
	 * @return
	 */
	public ModelAndView getWarningView(String message) {
		ModelAndView m = new ModelAndView("WEB-INF/jsp/common/warning");
		m.addObject("message", message);
		return m;
	}
	
}
