package com.dongnao.workbench.common.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongnao.workbench.common.bean.Render;
import com.dongnao.workbench.common.exceptions.UnexpectedException;

/**
 * 描述：ajax方式传输json对象
 * @author zhou.zheng
 * @version 1.0 2016-03-18
 */
public class RenderJson extends Render {

	String a;
	
	
	
	/**
	 * 构造函数，参数为字符串
	 * @param s String
	 */
	public RenderJson(String s) {
		a = s;
	}
	
	/**
	 * 构造函数，参数为字符串、状态
	 * @param s String
	 * @param i int
	 */
	public RenderJson(String s, int i) {
		a = s;
		status = i;
		setStauts = true;
	}
	
	/**
	 * JSON与对象转换
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	public void render(HttpServletRequest request, HttpServletResponse response) {
		setContentType(response, "application/json; charset=utf-8");
		try {
			if (setStauts){
				response.setStatus(status);
			}
			response.getOutputStream().write(a.getBytes("utf-8"));
		} catch (Exception exception) {
			logger.error("render json error.{}", a, exception);
			throw new UnexpectedException(StringUtil.format(
					"render json error. {0}", new String[] { a }), exception,
					true);
		}
	}

	
}
