package com.dongnao.workbench.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.tag.TokenUtil;
import com.dongnao.workbench.common.util.AjaxUtils;

/**
 * 描述：重复提交拦截器，负责验证Token，检查缓存服务器中是否有相关Token，无则不通过
 * @author joan.xiong
 * @version 1.0 2015-5-26
 */
public class TokenValidInterceptor implements HandlerInterceptor{
	 
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object arg2, Exception arg3)
            throws Exception {
    }
 
    /**
     * 在controller的方法执行之后，在DispatcherServlet类导向到view进行render之前依次执行
     * 因为它有ModelAndView 传进来，那么我们就可以在render view之前往view中添加额外的model对象，或者对view的去处进行修改
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2, ModelAndView arg3) throws Exception {
    }
    
    /**
     * 在Controller的方法执行前会被调用，可以使用这个方法来中断或者继续执行链的处理，
     * 当返回true时，处理执行链会继续，当返回false时，则不会去执行Controller的方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object arg2) throws Exception {
    	String uri = request.getRequestURI();
    	//排除资源上传请求，因为1、资源上传是采用Include的方式嵌到其它页面,2、若转置合作关系则直接进入对应页面，不需要跳转
    	if(uri.indexOf("/addResource.do")>-1 || uri.indexOf("/updateResource.do")>-1
    			|| uri.indexOf("/adResource/addAdResource.do")>-1){
    		return true;
    	}
        if(!TokenUtil.validToken(request)){
			response.setCharacterEncoding("UTF-8");	
			JSONObject result = new JSONObject();
			result.put("rs", Constant.FLAG_FAILURE);
			result.put("msg", "您的请求已提交，请勿频繁操作！");
			AjaxUtils.sendAjaxForObject(response, result);
            return false;
        }
    return true;
    }
 
}

