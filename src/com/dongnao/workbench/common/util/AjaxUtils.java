package com.dongnao.workbench.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.log4j.Logger;

import com.dongnao.workbench.common.Constant;
import com.dongnao.workbench.common.bean.ResultMessage;
import com.dongnao.workbench.common.page.Page;
import com.dongnao.workbench.common.page.PageUtil;

/**
 * 描述：ajax传送对象公共类，负责将数据传输到前台
 * 
 * @author zhou.zheng
 * @version 1.0 2016-03-22
 */
public class AjaxUtils {
	static Logger log = Logger.getLogger(AjaxUtils.class);

	/**
	 * 将map对象传到前台
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param map
	 *            Map<?, ?>
	 */
	public static void sendAjaxForMap(HttpServletResponse response,
			Map<?, ?> map) {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			/** 采用JSON解析结果集内容---start */
			JsonConfig config = new JsonConfig();
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			config.registerJsonValueProcessor(java.sql.Timestamp.class,
					new DateJsonValueProcessor(Constant.DATETIME_FORMAT));
			config.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor(Constant.DATETIME_FORMAT));
			out.print(JSONObject.fromObject(map, config).toString());
		} catch (IOException e) {
			log.error("ajax传送map对象出错", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 将Object对象传到前台
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param obj
	 *            Object
	 */
	public static void sendAjaxForListStr(HttpServletResponse response,
			List list) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			/** 采用JSON解析结果集内容---start */
			JsonConfig config = new JsonConfig();
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			config.registerJsonValueProcessor(java.sql.Timestamp.class,
					new DateJsonValueProcessor(Constant.DATETIME_FORMAT));
			config.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor(Constant.DATETIME_FORMAT));
			out.print(JSONArray.fromObject(list, config).toString());
		} catch (IOException e) {
			log.error("ajax传送Object对象出错", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 将Object对象传到前台
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param obj
	 *            Object
	 */
	public static void sendAjaxForObjectStr(HttpServletResponse response,
			Object obj) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			/** 采用JSON解析结果集内容---start */
			JsonConfig config = new JsonConfig();
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			config.registerJsonValueProcessor(java.sql.Timestamp.class,
					new DateJsonValueProcessor(Constant.DATETIME_FORMAT));
			config.registerJsonValueProcessor(java.util.Date.class,
					new DateJsonValueProcessor(Constant.DATETIME_FORMAT));
			out.print(JSONObject.fromObject(obj, config).toString());
		} catch (IOException e) {
			log.error("ajax传送Object对象出错", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 将Object对象传到前台
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param obj
	 *            Object
	 */
	public static void sendAjaxForObject(HttpServletResponse response,
			Object obj) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(obj);
		} catch (IOException e) {
			log.error("ajax传送Object对象出错", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 将list对象传到前台
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param list
	 *            List<String>
	 */
	public static void sendAjaxForSelect(HttpServletResponse response,
			List<String> list) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			String str = "";
			if (list != null && list.size() > 0) {
				for (String str1 : list) {
					sb.append("{").append(str1).append("}").append(",");
				}
				str = sb.toString()
						.substring(0, sb.toString().lastIndexOf(","));
			} else {
				str = "[{\"name\":\"\",\"value\":\"\"}";
			}
			// log.info(str);
			out.print(str + "]");

		} catch (IOException e) {
			log.error("ajax传送map对象出错", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 为complete控件传送对象
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param list
	 *            List<String>
	 */
	@Deprecated
	public static void sendAjaxForComplete(HttpServletResponse response,
			List<String> list) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("[");
			for (String str : list) {
				sb.append("\"").append(str).append("\"").append(",");
			}
			String str = "";
			if (sb.indexOf(",") > -1) {
				str = sb.substring(0, sb.lastIndexOf(","));
			} else {
				str = sb.toString();
			}
			response.getWriter().print(str + "]");

		} catch (IOException e) {
			log.error("ajax传送map对象出错", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 将page对象传送到前台
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param page
	 *            Page
	 * @param list
	 *            List<?>
	 */
	public static void sendAjaxForPage(HttpServletRequest request,
			HttpServletResponse response, Page page, List<?> list) {
		page.setResult(list);
		RenderJson r = new RenderJson(PageUtil.toJson4JqGrid(page, true));
		r.render(request, response);
	}

	/**
	 * 将page对象传送到前台
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @param page
	 *            Page
	 * @param list
	 *            List<?>
	 */
	public static void sendAjaxForPage(HttpServletRequest request,
			HttpServletResponse response, Page page) {
		RenderJson r = new RenderJson(PageUtil.toJson4JqGrid(page, true));
		r.render(request, response);
	}

	public static ResultMessage getFailureMessage(String msg) {
		return getResultMessage(Integer.valueOf(0), msg);
	}

	public static ResultMessage getResultMessage(Integer status, String msg) {
		ResultMessage rm = new ResultMessage();
		rm.setStatus(status);
		rm.setMessage(msg);
		return rm;
	}

	public static ResultMessage getSuccessMessage() {
		return getResultMessage(Integer.valueOf(1), "操作成功");
	}

	public static ResultMessage getSuccessMessage(String msg) {
		return getResultMessage(Integer.valueOf(1), msg);
	}

	/**
	 * 将成功消息对象传到前台
	 * 
	 * @param response
	 *            HttpServletResponse
	 */
	public static void sendAjaxSuccessMessage(HttpServletResponse response) {
		sendAjaxForObjectStr(response, getSuccessMessage());
	}
	/**
	 * 将成功消息对象传到前台
	 * 
	 * @param response
	 *            HttpServletResponse
	 */
	public static void sendAjaxFailureMessage(HttpServletResponse response) {
		sendAjaxForObjectStr(response, getFailureMessage("处理失败"));
	}

}
