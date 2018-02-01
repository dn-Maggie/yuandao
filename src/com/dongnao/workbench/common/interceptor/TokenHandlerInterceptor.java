package com.dongnao.workbench.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述：重复提交拦截器，负责生成Token值，并将值存储到缓存服务器
 * @author joan.xiong
 * @version 1.0 2015-5-26
 */
public class TokenHandlerInterceptor implements HandlerInterceptor{
	 
	 
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }
 
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2, ModelAndView arg3) throws Exception {
        TokenHandler.setToken(request);
    }
 
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2) throws Exception {
        return true;
    }
 
}

