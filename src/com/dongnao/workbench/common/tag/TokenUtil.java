package com.dongnao.workbench.common.tag;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dongnao.workbench.common.interceptor.TokenHandler;
import com.dongnao.workbench.common.util.StringUtil;


/**
 * 描述：重复提交Token工具类
 * @author joan.xiong
 * @version 1.0 2015-9-18
 */
public class TokenUtil {
	private static final Logger log = Logger.getLogger(TokenUtil.class);
	/**
	 * 页面持有token名称的字段名
	 */
	public static final String TOKEN_NAME_FIELD = "icss.token.name";
	/**
	 * session中存储token的对象名
	 */
	public static final String TOKEN_NAME_LIST = "icssTokens";
	private static final Random RANDOM = new Random();

	public static String generateGUID() {
		return new BigInteger(165, RANDOM).toString(36).toUpperCase();
	}
	
	/**
	 * 使用随机字串作为token,保存token到Session，并返回token用于向页面写隐藏域
	 * 
	 * @param request
	 * @return token
	 */
	public static String setToken(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		List<String> tokens = (ArrayList<String>)session.getAttribute(TOKEN_NAME_LIST);
		if(tokens == null){
			tokens = new ArrayList<String>();
		}
		String token = TokenHandler.generateGUID();
		tokens.add(token);
		session.setAttribute(TOKEN_NAME_LIST, tokens);
		return token;
	}
	
	/**
	 * 验证token，并返回结果
	 * 
	 * @param request
	 * @return token
	 */
	public static boolean validToken(HttpServletRequest request) {
		boolean flag = false;
		String reqToken = getTokenName(request);
		if(StringUtil.isEmptyOrNull(reqToken)){//请求中无Token参数则验证不成功
			return flag;
		}
		HttpSession session = request.getSession();
		if(session !=null){//不存在Session，验证不成功
			@SuppressWarnings("unchecked")
			List<String> tokens = (ArrayList<String>)session.getAttribute(TOKEN_NAME_LIST);
			if(tokens !=null && tokens.size()>0){//不存在Token存储对象，验证不成功
				for(String token:tokens){
					/**
					 * 只有session中的Token和request请求中的Token一致，才验证通过
					 * 同时删除Seesion中的Token值，更新到Session对象中
					 */
					if(token.equals(reqToken)){
						flag = true;
						tokens.remove(token);
						session.setAttribute(TOKEN_NAME_LIST, tokens);
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * 从请求参数中获取token值
	 * 
	 * @param request
	 * @return token
	 */
	public static String getTokenName(HttpServletRequest request) {
		Map<?, ?> params = request.getParameterMap();
		if (!params.containsKey(TOKEN_NAME_FIELD)) {
			log.warn("Could not find token name in params.");
			return null;
		}
		String[] tokenArr = (String[]) params.get(TOKEN_NAME_FIELD);
		if(tokenArr !=null && tokenArr.length>0){
			return tokenArr[0];
		}
		return null;
	}
	
}
