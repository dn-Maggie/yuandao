package com.dongnao.workbench.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.dongnao.workbench.basic.model.UserInfo;
import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.interceptor.TokenHandler;
import com.dongnao.workbench.system.model.ModuleLocation;
import com.dongnao.workbench.system.service.RoleRightService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




/**
 * 
 * 工具类
 * 
 * @author joan.xiong
 * @version 1.0.0
 */
public class Utils {
	private static Logger logger = LoggerFactory.getLogger(Utils.class);

	/**
	 * 从Request对象获取当前登录账号
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String 登录账号
	 */
	public static String getLoginUser(HttpServletRequest request) {
		UserInfo loginUserInfo = getLoginUserInfo(request);
		if (loginUserInfo != null) {
			return loginUserInfo.getUserAccount();
		}
		return null;
	}

	/**
	 * 从Request对象获取当前登陆账户的userType
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 用户类型
	 */
	public static String getLoginUserType(HttpServletRequest request) {
		UserInfo loginUserInfo = getLoginUserInfo(request);
		if (loginUserInfo != null) {
			return loginUserInfo.getOrder();
		}
		return null;
	}

	/**
	 * 从Request对象获取当前登陆用户的所属单位
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 所属单位ID
	 */
	public static String getLoginUserUnitId(HttpServletRequest request) {
		UserInfo loginUserInfo = getLoginUserInfo(request);
		if (loginUserInfo != null) {
			return loginUserInfo.getOrgId();
		}
		return null;
	}

	/**
	 * 从Request对象获取当前登录用户对象
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return UserInfo 登录用户对象
	 */
	public static UserInfo getLoginUserInfo(HttpServletRequest request) {
		return (UserInfo) WebUtils.getSessionAttribute(request,
				Constant.LOGIN_USER_KEY);
	}




	/**
	 * 从Request对象获取当前登录用户对象ID
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return String 登录用户对象ID
	 */
	public static String getLoginUserInfoId(HttpServletRequest request) {
		UserInfo loginUserInfo = getLoginUserInfo(request);
		if (loginUserInfo != null) {
			return loginUserInfo.getId();
		}
		return null;
	}

	/**
	 * 判断当前登陆对象是否为系统管理员
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true是系统管理员false为一般用户
	 */
	public static boolean isSuperAdmin(HttpServletRequest request) {
		UserInfo info = getLoginUserInfo(request);
		boolean isWebAdmin = false;
		if(info.getUserAccount().equals(Constant.SUPER_ADMIN)){//如果是超级管理员
			isWebAdmin = true;
		}
		request.setAttribute("isAdmin", isWebAdmin);
		return isWebAdmin;
	}
	/**
	 * 判断当前登陆对象是否为系统管理员
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return true是系统管理员false为一般用户
	 */
	public static boolean isSuperAdmin(UserInfo info) {
		boolean isWebAdmin = false;
		if(info.getUserAccount().equals(Constant.SUPER_ADMIN)){//如果是超级管理员
			isWebAdmin = true;
		}
		return isWebAdmin;
	}

	/**
	 * 获取客户端访问真实IP
	 * 
	 * @param req
	 *            请求
	 * @return 真实路径
	 */
	public static String getRemortIP(HttpServletRequest req) {
		String currUserIp = req.getHeader("x-forwarded-for");// req.getRemoteAddr();
		if (StringUtils.isEmpty(currUserIp)) {
			currUserIp = req.getRemoteAddr();
		} else {
			if (currUserIp.indexOf(",") > -1) {
				currUserIp = currUserIp.substring(0, currUserIp.indexOf(","));
			}
		}
		return currUserIp;

	}

	/**
	 * 生成UUID
	 * 
	 * @author heyin
	 * @return 生成的UUID
	 */
	public static String generateUniqueID() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 生成UUID
	 * 
	 * @author heyin
	 * @return 生成的UUID
	 */
	public static String generateVersion() {
		return UUID.randomUUID().toString();
	}

	

	/**
	 * 处理页面显示的br标签
	 * 
	 * @author wff
	 * @param oldValue
	 *            String
	 * @return String
	 */
	public static String replaceBr(String oldValue) {
		String strValue = oldValue;
		if (oldValue != null) {
			if (strValue.contains("\\r<br/>")) {
				strValue = strValue.replaceAll("<br/>", "");
			}
			if (strValue.contains("<br/>")) {
				strValue = strValue.replaceAll("<br/>", "\\\\r");
			}
			if (strValue.contains("<br>")) {
				strValue = strValue.replaceAll("<br>", "\\\\r");
			}
		} else {
			oldValue = "";
		}
		return strValue;
	}

	/**
	 * 将字符串中的\r\n或\n替换成<br/>
	 * 修正程序，含有换行的Json数据导致界面无法显示
	 * 
	 * @author Joanxiong
	 * @param strValue
	 *            String
	 * @return strValue替换后的值
	 */
	public static String replaceStr(String strValue) {
		if (strValue != null) {
			return strValue.replace("<", "&lt;").replace(">", "&gt;")
					.replace("\r\n", "<br/>").replace("\n", "<br/>")
					.replace("\\n", "<br/>");
		} else {
			return null;
		}
	}

	/**
	 * 判断字符串是否数字
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean isNumber(String str) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern
				.compile("[0-9]*");
		java.util.regex.Matcher match = pattern.matcher(str);
		if (match.matches() == false) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 判断字符串是否是日期格式（格式：yyyy-MM-dd）
	 * 
	 * @param str
	 *            String
	 * @return boolean
	 */
	public static boolean isValidDate(String str) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
		try {
			dateFormat.parse(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取当前工作路径
	 * 
	 * @return String
	 */
	public static String getWorkspacePath() {
		return Thread.currentThread().getContextClassLoader().getResource("")
				.getPath().substring(1)
				.replace("WebRoot/images/bigicon", "src");
	}

	/**
	 * 格式化日期时间对象
	 * 
	 * @param d
	 *            Date
	 * @return Date
	 */
	public static Date parseTime(Date d) {
		try {
			if (d != null) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						Constant.DATETIME_FORMAT);
				String str = dateFormat.format(d);
				return dateFormat.parse(str);
			}
		} catch (ParseException e) {
			return null;
		}
		return d;
	}
	/**
	 * 获取国际化信息
	 * 
	 * @param key
	 *            String ：传入的国际化key
	 * @param obj
	 *            Object ：传入的国际化参数
	 * @return String 返回国际化信息
	 */
	public static String getI18n(String key, Object[] obj) {
		WebApplicationContext applicationContext = (WebApplicationContext) SpringInit.getApplicationContext();
		Locale locale = Locale.getDefault();
		String menuName = applicationContext.getMessage(key, obj, "", locale);
		return menuName;
	}


	/**
	 * 利用Spring上下文实例化Bean对象
	 * 
	 * @param beanName
	 *            String
	 * @return Object
	 */
	public static Object getBean(String beanName) {
		WebApplicationContext applicationContext = (WebApplicationContext) SpringInit
				.getApplicationContext();
		return applicationContext.getBean(beanName);
	}

	/**
	 * 获取上下文中的配置变量值
	 * 
	 * @param variableName
	 *            String
	 * @return String
	 */
	public static String getConfigValue(String variableName) {
		WebApplicationContext applicationContext = (WebApplicationContext) SpringInit
				.getApplicationContext();
		return applicationContext.getServletContext()
				.getAttribute(variableName).toString();
	}

	/**
	 * 获取页面定位URL的MAP结果集
	 * 
	 * @return
	 */
	public static Map<String, ModuleLocation> getModuleLocationMap(String type) {
		WebApplicationContext applicationContext = (WebApplicationContext) SpringInit
				.getApplicationContext();
		if (type.equals("mid")) {
			return (Map<String, ModuleLocation>) applicationContext.getServletContext().getAttribute(Constant.MODULE_LOCATION_MID_MAP);
		} else {
			return (Map<String, ModuleLocation>) applicationContext.getServletContext().getAttribute(Constant.MODULE_LOCATION_URL_MAP);
		}
	}

	/**
	 * 子计划是否需要审核标记
	 * 
	 * @return
	 */
	public static boolean getPlanItemCheckFlag() {
		String v = getConfigValue("PlanItem_checkFlag");
		if (v != null) {
			if (v.equals("1")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 主计划是否需要审核标记
	 * 
	 * @return
	 */
	public static boolean getPlanMainCheckFlag() {
		String v = getConfigValue("PlanMain_checkFlag");
		if (v != null) {
			if (v.equals("1")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 块计划是否需要审核标记
	 * 
	 * @return
	 */
	public static boolean getPlanPieceCheckFlag() {
		String v = getConfigValue("PlanPiece_checkFlag");
		if (v != null) {
			if (v.equals("1")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 跨局计划是否需要审核标记
	 * 
	 * @return
	 */
	public static boolean getReplaceCheckFlag() {
		String v = getConfigValue("Replace_checkFlag");
		if (v != null) {
			if (v.equals("1")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 设置上下文中的配置变量值
	 * 
	 * @param variableName
	 *            String
	 * @return String
	 */
	public static void setConfigValue(String variableName, String variableValue) {
		WebApplicationContext applicationContext = (WebApplicationContext) SpringInit
				.getApplicationContext();
		applicationContext.getServletContext().setAttribute(variableName,
				variableValue);
	}

	/**
	 * 获取上下文中的字典信息
	 * 
	 * @param variableName
	 *            String
	 * @return String
	 */
	public static String getDictInfo(String dictTypeCode, boolean isForSelect) {
		WebApplicationContext applicationContext = (WebApplicationContext) SpringInit
				.getApplicationContext();
		Map<String, String> dictMap = (Map<String, String>) applicationContext
				.getServletContext().getAttribute(Constant.APP_DICT_KEY);
		if(dictMap==null){
			return null;
		}
		String dictStr = dictMap.get(dictTypeCode);
		if (!isForSelect) {
			JSONObject infoObj = null;
			JSONArray infoArr = JSONArray.fromObject(dictStr);
			Iterator<JSONObject> it = infoArr.iterator();
			while (it.hasNext()) {
				infoObj = it.next();
				infoObj.put("label", infoObj.get("name"));
				// infoObj.remove("name");
				infoObj.put("name", dictTypeCode);
			}
			dictStr = infoArr.toString();
		}
		return dictStr;
	}

	/**
	 * 更新上下文中的页面权限控制的URL列表
	 */
	public static void updateRightUrl() {
		WebApplicationContext applicationContext = (WebApplicationContext) SpringInit
				.getApplicationContext();
		RoleRightService roleRightService = (RoleRightService) applicationContext
				.getBean("roleRightService");
		List<String> allRightUrl = roleRightService.getAllRightUrl();
		applicationContext.getServletContext().setAttribute(
				Constant.APP_RIGHT_KEY, allRightUrl);
	}

	/**
	 * 获取应用的基础路径
	 * 
	 * @param req
	 *            HttpServletRequest
	 * @return String
	 */
	public static String getBasePath(HttpServletRequest req) {
		return req.getScheme() + "://" + req.getServerName() + ":"
				+ req.getServerPort() + req.getContextPath();
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return 返回文件相对路径 \2015\02\15\*.
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile file) throws IOException {
		Calendar c = DateUtil.nowCalendar();// 日期
		String filename = file.getOriginalFilename();
		String suffix = StringUtils.substring(filename,
				filename.lastIndexOf(".") + 1);
		String realName = String.valueOf(System.currentTimeMillis()
				+ ((int) (Math.random() * 900) + 100));// 新文件名
		String destPath = "";// 相对路劲
		String fileRootPath = getConfigValue(Constant.FILE_ROOT_PATH);// 根目录

		InputStream is = null;
		OutputStream os = null;
		try {
			File dir = null;
			dir = new File(fileRootPath);
			dir.mkdir();// 创建根目录
			destPath = File.separator + c.get(Calendar.YEAR);
			dir = new File(fileRootPath + destPath);
			dir.mkdir();// 创建年份文件夹
			destPath += File.separator + (c.get(Calendar.MONTH) + 1);
			dir = new File(fileRootPath + destPath);
			dir.mkdir();// 创建月份文件夹
			destPath += File.separator + c.get(Calendar.DAY_OF_MONTH);
			dir = new File(fileRootPath + destPath);
			dir.mkdir();// 创建日文件夹

			File df = new File(fileRootPath + destPath + File.separator
					+ realName + "." + suffix);
			if (!df.exists()) {// 如果不存在先创建
				df.createNewFile();
			}

			is = file.getInputStream();
			os = new FileOutputStream(df);// 写入服务器
			int bt = 0;
			byte[] bf = new byte[8192];
			while ((bt = is.read(bf, 0, 8192)) != -1) {
				os.write(bf, 0, bt);
			}
		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
		}

		return fileRootPath + destPath + File.separator + realName + "."
				+ suffix;
	}

	/**
	 * 文件上传_菜单图片上传
	 * 
	 * @param file
	 * @return 返回文件相对路径 \2015\02\15\*.
	 * @throws Exception
	 */
	public static String uploadFile4menu(MultipartFile file,
			HttpServletRequest req) throws IOException {
		Calendar c = DateUtil.nowCalendar();// 日期
		String filename = file.getOriginalFilename();
		String suffix = StringUtils.substring(filename,
				filename.lastIndexOf(".") + 1);
		String realName = String.valueOf(System.currentTimeMillis()
				+ ((int) (Math.random() * 900) + 100));// 新文件名
		String destPath = "";// 相对路径
		String fileRootPath = req.getSession().getServletContext()
				.getRealPath("/")
				+ "images" + File.separator + "bigicon";// 根目录

		InputStream is = null;
		OutputStream os = null;
		try {
			File dir = null;
			dir = new File(fileRootPath);
			dir.mkdir();// 创建根目录
			destPath = File.separator + c.get(Calendar.YEAR);
			dir = new File(fileRootPath + destPath);
			dir.mkdir();// 创建年份文件夹
			destPath += File.separator + (c.get(Calendar.MONTH) + 1);
			dir = new File(fileRootPath + destPath);
			dir.mkdir();// 创建月份文件夹
			destPath += File.separator + c.get(Calendar.DAY_OF_MONTH);
			dir = new File(fileRootPath + destPath);
			dir.mkdir();// 创建日文件夹

			File df = new File(fileRootPath + destPath + File.separator
					+ realName + "." + suffix);
			if (!df.exists()) {// 如果不存在先创建
				df.createNewFile();
			}

			is = file.getInputStream();
			os = new FileOutputStream(df);// 写入服务器
			int bt = 0;
			byte[] bf = new byte[8192];
			while ((bt = is.read(bf, 0, 8192)) != -1) {
				os.write(bf, 0, bt);
			}
		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
		}

		return fileRootPath + destPath + File.separator + realName + "."
				+ suffix;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            如\2014\02\1\132.jpg
	 */
	public static boolean removeFile(String filePath) {
		String fileRootPath = getConfigValue(Constant.FILE_ROOT_PATH);// 根目录
		File file = new File(fileRootPath + filePath);
		if (file.isFile()) {
			return file.delete();
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            如D:root\2014\02\1\132.jpg
	 */
	public static boolean removeFileByRootPath(String filePath) {
		File file = new File(filePath);
		if (file.isFile()) {
			return file.delete();
		} else {
			return false;
		}
	}

	/**
	 * 通用验证
	 * 
	 * @param object
	 *            实体对象
	 * @param validateType
	 *            验证类型 ：1-唯一性 2-有效性
	 * @return boolean true 验证成功 false 验证失败
	 */
	public static boolean validate(Object object, String validateType) {
		boolean flag = false;
		if (Constant.VALIDATE_TYPE_UNIQUE.equals(validateType)) {// 唯一性验证
			flag = object == null ? true : false;
		}
		if (Constant.VALIDATE_TYPE_VALID.equals(validateType)) {// 有效性验证
			flag = object == null ? false : true;
		}
		return flag;
	}

	public static boolean validFileSize(long fileSize) {
		boolean flag = false;
		long validSize = Long.parseLong(Utils
				.getConfigValue(Constant.FILE_UPLOAD_MAX_SIZE)) * 1024 * 1024;
		if (fileSize > validSize) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 字符串替换
	 * 
	 * @param reg
	 *            正则表达式
	 * @param str
	 *            待替换字符
	 * @param beStr
	 *            替换成该字符串
	 * @return String
	 */
	public static String replaceAll(String reg, String str, String beStr) {
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		return m.replaceAll(beStr).trim();
	}

	/**
	 * 超出长度以其他字符代替
	 * 
	 * @param str
	 *            待处理字符串
	 * @param length
	 *            长度
	 * @return String
	 */
	public static String replaceStr(String str, int length, String replaceStr) {
		if (str.length() > length) {
			str = str.substring(0, length) + replaceStr;
		}
		return str;
	}

	/**
	 * 向请求中添加Token，并将Token值放入Map中返回到请求
	 * 
	 * @param request
	 * @param map
	 */
	public static void handleToken(HttpServletRequest request,
			Map<String, Object> map) {
		String[] token = TokenHandler.setToken(request);
		JSONObject tokenJson = new JSONObject();
		tokenJson.put("key", token[0]);
		tokenJson.put("value", token[1]);
		map.put("token", tokenJson);
	}

	/** 
	 * 设置cookie到response中 
	 * @param response 
	 * @param name  cookie名字 
	 * @param value cookie值 
	 * @param maxAge cookie生命周期， 以分钟为单位 
	 */  
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge){  
	    Cookie cookie = new Cookie(name, value);  
	    cookie.setPath("/");  
	    if(maxAge>0){  
	        cookie.setMaxAge(maxAge*60);  
	    }  
	    response.addCookie(cookie);  
	}  
	/** 
	 * 根据名字从request中获取cookie的值 
	 * @param request 
	 * @param name cookie名字 
	 * @return 
	 */  
	public static String getCookieByName(HttpServletRequest request, String name){  
	    Cookie[] cookies = request.getCookies();//获取cookie数组  
	    for(Cookie cookie : cookies){  
	        if(cookie.getName().equals(name)){  
	            return cookie.getValue();  
	        }  
	    }  
	    return null;  
	}  
}
