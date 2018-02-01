package com.dongnao.workbench.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;






import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.util.StringUtil;
import com.dongnao.workbench.common.util.Utils;
import com.dongnao.workbench.system.service.RoleRightService;

/**
 * 描述：权限过滤类，负责对用户的访问权限进行过滤
 * @author joan.xiong
 * @version 1.0 2016-03-10
 */
public class RightFilter implements Filter {

	private static Logger log = Logger.getLogger(RightFilter.class);
    private static List<String> uriList;

	/**
	 * 设置不被拦截的uri地址
	 * @param uriList 不被拦截的地址，在spring_mvc.xml中配置
	 */
	public void setUriList(List<String> uriList) {
		RightFilter.uriList = uriList;
	}
	
	@Override
	public void destroy() {
	}

	/**
	 * 过滤实现
	 * @param request ServletRequest
	 * @param response ServletResponse
	 * @param chain FilterChain
	 * @throws IOException if has error
	 * @throws ServletException if has error
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
		throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse rs = (HttpServletResponse) response;
		String uri = hreq.getRequestURI();
		//当前账号访问IP
		String currUserIp = hreq.getHeader("x-forwarded-for");//req.getRemoteAddr();
		if(StringUtil.isEmptyOrNull(currUserIp)){
			currUserIp = hreq.getRemoteAddr();
		}else{
			log.debug("------------------x-forwarded-for:"+currUserIp);
			if(currUserIp.indexOf(",")>-1){
				currUserIp = currUserIp.substring(0, currUserIp.indexOf(","));
			}
		}

		UserInfo user = Utils.getLoginUserInfo(hreq);
		String userName= "";
		if(user != null){
			userName = user.getUserAccount();
		}
		//log.debug("------------------x-real-ip:"+hreq.getHeader("x-real-ip"));
		log.debug("当前会话:"+hreq.getSession().getId()+"\t 当前访问IP："+currUserIp+"\t 登录账号："+userName+"\t 当前访问地址："+uri);
		
		uri = uri.replace(hreq.getContextPath(), "");
		if ("/".equals(uri) || RightFilter.uriList.contains(uri)) {
			chain.doFilter(request, response);
			return;
		}
		
		//账号未登录
		if(user == null){
 			rs.sendRedirect(hreq.getContextPath() +"/tologin.do");
        	return;
 		}
		
		//取得请求路径
		List<String> urlList = (List<String>)hreq.getSession().getServletContext().getAttribute(Constant.APP_RIGHT_KEY);
		if(urlList !=null && urlList.contains(uri)){
			boolean isHaveRight = false;
			//验证用户权限，同时获取并设置页面权限
			RoleRightService roleRightService = (RoleRightService) Utils.getBean("roleRightService");
			isHaveRight = roleRightService.checkAndSetRight(hreq);
			if(!isHaveRight){
				rs.sendRedirect(hreq.getContextPath()+"/showError.do?mKey=msg.noRight");
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
	}

}
