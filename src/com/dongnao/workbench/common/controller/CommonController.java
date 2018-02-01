package com.dongnao.workbench.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dongnao.workbench.common.util.AjaxUtils;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.common.util.msg.SMSCodeUtils;


/**
 * 描述：公共模块controller类，负责公共获取
 * 
 * @author fan.yang
 * @version 1.0 2016-03-27
 */

@Controller
@RequestMapping("/common/")
public class CommonController {
	/**
	 * 进入首页页面
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return ModelAndView 返回到首页页面
	 */
	@RequestMapping("/sendSmsCode")
	public void sendSmsCode(HttpServletRequest request,HttpServletResponse response) {
		AjaxUtils.sendAjaxForObjectStr(response, SMSCodeUtils.newMsgCode(request, Utils.getLoginUserInfo(request)));;
	}

	/**
	 * 进入首页页面
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return ModelAndView 返回到首页页面
	 */
	@RequestMapping("/patternSmsCode")
	public void patternSmsCode(String smsCode,HttpServletRequest request,HttpServletResponse response) {
		if(SMSCodeUtils.checkMsgCode(request, smsCode)){
			AjaxUtils.sendAjaxForObject(response, Boolean.TRUE);
		}else{
			AjaxUtils.sendAjaxForObject(response, Boolean.FALSE);
		}
	}
}
