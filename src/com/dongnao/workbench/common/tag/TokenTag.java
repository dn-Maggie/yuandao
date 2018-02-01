package com.dongnao.workbench.common.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 描述：重复提交标签类，负责生成页面的重复提交Token隐藏表单
 * @author joan.xiong
 * @version 1.0 2015-5-26
 * @version 1.1 2015-6-16 修改为不从Request中查找Token，直接产生Token
 * @version 1.2 2015-9-18 修改Token保存到Session
 */
public class TokenTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {  
		HttpServletRequest request = (HttpServletRequest)((PageContext)getJspContext()).getRequest();
		/**
		 * 修改Token保存到Session
		 */
		String token = TokenUtil.setToken(request);
		
		StringBuffer tokenStr = new StringBuffer();
    	tokenStr.append("<input type=\"hidden\" name=\"").append(TokenUtil.TOKEN_NAME_FIELD).append("\" value=\"").append(token).append("\"/>");
		getJspContext().getOut().write(tokenStr.toString());
		super.doTag();
	}

}
