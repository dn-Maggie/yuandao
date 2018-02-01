package com.dongnao.workbench.common.page;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dongnao.workbench.common.Constants;
import com.dongnao.workbench.common.bean.Entity;
import com.dongnao.workbench.common.util.DateJsonValueProcessor;
import com.dongnao.workbench.common.util.ReflectUtil;
import com.dongnao.workbench.common.util.StringUtil;

/**
 * 描述：分页公共类
 * @author joan.xiong
 * @version 1.0 2016-03-21
 */
public class PageUtil {

	/**
	 * 构造方法，初始化常用参数
	 */
	public PageUtil() {
		b = "pageNo";
		c = "pageSize";
		d = "totalPages";
		e = "totalCount";
		f = "result";
		g = "start";
		h = null;
		i = "orderBy";
		j = "orderType";
		k = false;
	}

	/**
	 * 将分页对象转换成json字符串
	 * @param page Page
	 * @param booleanObject  boolean
	 * @param columns String
	 * @return 组装好的json字符串 
	 */
	public String toJson(Page page, boolean booleanObject, String columns) {
		if (page == null){
			return null;
		}
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("{");
		if (StringUtil.isNotBlank(b)){
			stringbuffer.append("\"" + b + "\":\"" + page.getPageNo() + "\",");
		}
		if (StringUtil.isNotBlank(c)){
			stringbuffer.append("\"" + c + "\":\"" + page.getPageSize() + "\",");
		}
		if (StringUtil.isNotBlank(d)){
			stringbuffer.append("\"" + d + "\":\"" + page.getTotalPages()
					+ "\",");
		}
		if (StringUtil.isNotBlank(e)){
			stringbuffer.append("\"" + e + "\":\"" + page.getTotalCount()
					+ "\",");
		}
		if (StringUtil.isNotBlank(g)){
			stringbuffer.append("\"" + g + "\":\"" + page.getFirst() + "\",");
		}
		if (StringUtil.isNotBlank(h)){
			stringbuffer.append("\"" + h + "\":\""
					+ (page.getStart() + page.getResult().size()) + "\",");
		}
		if (StringUtil.isNotBlank(i)){
			stringbuffer.append("\"" + i + "\":\"" + page.getOrderFields()
					+ "\",");
		}
		if (StringUtil.isNotBlank(j)){
			stringbuffer.append("\"" + j + "\":\"" + page.getOrder() + "\",");
		}
		stringbuffer.append("\"" + f + "\":");
		/**采用JSON解析结果集内容---start */
		JsonConfig config=new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		config.registerJsonValueProcessor(java.sql.Timestamp.class, new DateJsonValueProcessor(Constants.DATE_FORMATE));  
		config.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor(Constants.DATE_FORMATE));  
		JSONArray jsonArray=JSONArray.fromObject(page.getResult(),config);
		stringbuffer.append(jsonArray);
		/**采用JSON解析结果集内容---end */
		stringbuffer.append("}");
		
		return stringbuffer.toString();
		
	}
	
	/**
	 * Object对象转换为JSON格式
	 * 
	 * 例如List对象、JavaBean对象、JavaBean对象数组、Map对象、List Map对象
	 * 
	 * @param object
	 *            传入的Object对象
	 * @return object的JSON格式字符串
	 */
	public static String toJson(Object object) {

		// Jackson方式转换为Json
		MappingJsonFactory f = new MappingJsonFactory();
		StringWriter sw = new StringWriter();

		try {
			JsonGenerator generator = f.createJsonGenerator(sw);
			generator.writeObject(object);
			generator.close();

		} catch (Exception e) {
			return "";
		}
		return sw.toString();

	} 

	/**
	 * 实体转换成json字符串
	 * @param entity Entity
	 * @param booleanObject boolean
	 * @param columns String
	 * @return json格式的字符串 String
	 */
	public static String toJson4Entity(Entity entity, boolean booleanObject,
			String columns) {
		if (booleanObject){
			if (StringUtil.isBlank(columns)){
				return entity.toJson();
			}else{
				//return entity.toJson(columns);
			}
		}
		if (StringUtil.isBlank(columns)){
			//return entity.toJsonAsArray();
		}else{
			//return entity.toJsonAsArray(columns);
		}
		return null;
	}

	/**
	 * 将map对象转换成json格式的字符串
	 * @param mapEntity Map<?, ?>
	 * @param booleanObject boolean
	 * @param columns String
	 * @param booleanFormat boolean
	 * @return json格式的字符串 String
	 */
	public static String toJson4Map(Map<?, ?> mapEntity, boolean booleanObject,
			String columns, boolean booleanFormat) {
		StringBuffer stringbuffer = new StringBuffer();
		boolean flag = false;
		String s = "{";
		String s1 = "}";
		if (!booleanObject) {
			s = "[";
			s1 = "]";
		}
		stringbuffer.append(s);
		try {
			if (StringUtil.isBlank(columns)) {
				for (Iterator<?> iterator = mapEntity.entrySet().iterator(); iterator.hasNext();) {
					Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
					String s2 = entry.getKey().toString();
					if (booleanFormat){
						s2 = StringUtil.formatJavaName(s2.toLowerCase());
					}
					Object obj = entry.getValue();
					flag = ifTo4Json(stringbuffer, s2, obj, flag, booleanObject);
				}

			} else {
				String[] as = columns.split(",");
				for (int l = 0; l < as.length; l++) {
					String s3 = as[l];
					Object obj1 = mapEntity.get(s3);
					if (booleanFormat){
						s3 = StringUtil.formatJavaName(s3.toLowerCase());
					}
					flag = ifTo4Json(stringbuffer, s3, obj1, flag, booleanObject);
				}

			}
		} catch (Exception exception) {
			logger.error(
					"\u8F6C\u6362\u67E5\u8BE2\u7ED3\u679C\u4E3AJSON\u683C\u5F0F\u6570\u636E\u5931\u8D25\uFF1A",
					exception);
		}
		stringbuffer.append(s1);
		return stringbuffer.toString();
	}

	/**
	 * 将object转换成json字符串
	 * @param object Object
	 * @param booleanObject boolean
	 * @param columns String
	 * @return String 
	 */
	public static String toJson4Object(Object object, boolean booleanObject,
			String columns) {
		StringBuffer stringbuffer = new StringBuffer();
		boolean flag = false;
		String s = "{";
		String s1 = "}";
		if (!booleanObject) {
			s = "[";
			s1 = "]";
		}
		stringbuffer.append(s);
		try {
			if (StringUtil.isBlank(columns)) {
				Field[] afield = object.getClass().getDeclaredFields();
				for (int l = 0; l < afield.length; l++) {
					Field field = afield[l];
					field.setAccessible(true);
					String s3 = field.getName();
					Object obj;
					try {
						obj = ReflectUtil.getPropertityValue(object, s3);
					} catch (Exception exception1) {
						obj = field.get(object);
					}
					flag = ifTo4Json(stringbuffer, s3, obj, flag, booleanObject);
				}

			} else {
				String[] as = columns.split(",");
				for (int i1 = 0; i1 < as.length; i1++) {
					String s2 = as[i1];
					Field field1 = object.getClass().getDeclaredField(s2);
					Object obj1;
					try {
						obj1 = ReflectUtil.getPropertityValue(object, s2);
					} catch (Exception exception2) {
						obj1 = field1.get(object);
					}
					flag = ifTo4Json(stringbuffer, s2, obj1, flag, booleanObject);
				}

			}
		} catch (Exception exception) {
			logger.error(
					"\u8F6C\u6362\u67E5\u8BE2\u7ED3\u679C\u4E3AJSON\u683C\u5F0F\u6570\u636E\u5931\u8D25\uFF1A",
					exception);
		}
		stringbuffer.append(s1);
		return stringbuffer.toString();
	}

	/**
	 * 拼接成json的字符串是否成功
	 * @param stringbuffer StringBuffer
	 * @param s String
	 * @param obj Object
	 * @param flag boolean
	 * @param flag1 boolean
	 * @return boolean
	 */
	private static boolean ifTo4Json(StringBuffer stringbuffer, String s, Object obj,
			boolean flag, boolean flag1) {
		try {
			if (obj == null) {
				return flag;
			}
			if (flag) {
				stringbuffer.append(",");
			}
			if (flag1) {
				stringbuffer.append("\"").append(s).append("\":\"");
			} else {
				stringbuffer.append("\"");
			}

			if (obj instanceof Timestamp) {
				SimpleDateFormat simpledateformat = new SimpleDateFormat(
						Constants.DATE_TIME_FORMATE);
				java.util.Date date;

				date = simpledateformat.parse(obj.toString());

				stringbuffer.append(simpledateformat.format(date));
			}else if (obj instanceof Date) {
				SimpleDateFormat simpledateformat = new SimpleDateFormat(
						Constants.DATE_TIME_FORMATE);

				stringbuffer.append(simpledateformat.format(obj));
			} else {
				stringbuffer.append(replaceSpecChar(obj));
			}
			stringbuffer.append("\"");
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * JSON字符串特殊字符替换
	 * @param jsonValue JSON字符串
	 * @return String 替换后字符串
	 */
	public static String replaceSpecChar(Object jsonValue) {
		if (jsonValue == null){
			return null;
		}
		String s = jsonValue.toString();
		if (s == null){
			return s;
		}
		StringBuffer stringbuffer = new StringBuffer();
		for (int l = 0; l < s.length(); l++) {
			char c1 = s.charAt(l);
			switch (c1) {
				case 34: // '"'
					stringbuffer.append("\\\"");
					break;
	
				case 92: // '\\'
					stringbuffer.append("\\\\");
					break;
	
				case 47: // '/'
					stringbuffer.append("\\/");
					break;
	
				case 8: // '\b'
					stringbuffer.append("\\b");
					break;
	
				case 12: // '\f'
					stringbuffer.append("\\f");
					break;
	
				case 10: // '\n'
					stringbuffer.append("<br/>");
					break;
	
				case 13: // '\r'
					stringbuffer.append("\\r");
					break;
	
				case 9: // '\t'
					stringbuffer.append("\\t");
					break;
	
				default:
					stringbuffer.append(c1);
					break;
			}
		}

		return stringbuffer.toString();
	}

	/**
	 * 将Page对象转换为JSON对象
	 * @param page JqgridPage对象
	 * @param booleanObject 
	 * @return String JSON 字符串
	 */
	public static String toJson4JqGrid(Page page, boolean booleanObject) {
		return toJson4JqGrid(page, booleanObject, false);
	}

	/**
	 * 将Page对象转换为JSON对象
	 * @param page JqgridPage对象
	 * @param booleanObject 
	 * @param format 
	 * @return String JSON 字符串
	 */
	public static String toJson4JqGrid(Page page, boolean booleanObject,
			boolean format) {
		PageUtil pageutil = new PageUtil();
		pageutil.setPageNo("page");
		pageutil.setPageSize(null);
		pageutil.setTotalCount("records");
		pageutil.setTotalPages("total");
		pageutil.setStartRow(null);
		pageutil.setEndRow(null);
		pageutil.setOrderBy(null);
		pageutil.setOrderType(null);
		pageutil.setResult("rows");
		pageutil.k = format;
		return pageutil.toJson(page, booleanObject, null);
	}

	/**
	 * 将List对象转换为JSON对象
	 * @param list List
	 * @param booleanObject 
	 * @return String JSON 字符串
	 */
	public static String toJson4JqGrid(List<?> list, boolean booleanObject) {
		return toJson4JqGrid(list, booleanObject, false);
	}

	/**
	 * 将List对象转换为JSON对象
	 * @param list List
	 * @param booleanObject 
	 * @param format 
	 * @return String JSON 字符串
	 */
	public static String toJson4JqGrid(List<?> list, boolean booleanObject,
			boolean format) {
		Page page = new Page();
		page.setPageNo(1);
		page.setPageSize(list.size());
		page.setTotalCount(list.size());
		page.setResult(list);
		return toJson4JqGrid(page, booleanObject, format);
	}

	/**
	 * 对结果进行分页处理
	 * @param page Page
	 * @param result List
	 * @return Page
	 */
	public static Page pageScroll(Page page, List<?> result) {
		Object obj = new ArrayList<Object>();
		if(page != null){
			if (result != null && result.size() > 0) {
				int l = result.size();
				page.setTotalCount(l);
				int i1 = page.getFirst() - 1;
				int j1 = i1 + page.getPageSize();
				obj = result.subList(i1 <= 0 ? 0 : i1, j1 <= l ? j1 : l);
			}
			page.setResult((List<Object>)obj);
		}
		return page;
	}

	/**
	 * 设置分页页码
	 * @param pageNo String
	 */
	public void setPageNo(String pageNo) {
		b = pageNo;
	}

	/**
	 * 设置分页显示记录条数
	 * @param pageSize String
	 */
	public void setPageSize(String pageSize) {
		c = pageSize;
	}

	/**
	 * 设置分页总页数
	 * @param totalPages String
	 */
	public void setTotalPages(String totalPages) {
		d = totalPages;
	}

	/**
	 * 设置分页结果总条数
	 * @param totalCount String
	 */
	public void setTotalCount(String totalCount) {
		e = totalCount;
	}

	/**
	 * 设置分页结果
	 * @param result String
	 */
	public void setResult(String result) {
		f = result;
	}

	/**
	 * 设置排序字段
	 * @param orderBy String
	 */
	public void setOrderBy(String orderBy) {
		i = orderBy;
	}

	/**
	 * 设置分页排序方式：升序？降序
	 * @param orderType String
	 */
	public void setOrderType(String orderType) {
		j = orderType;
	}

	/**
	 * 设置分页开始记录行数
	 * @param startRow String
	 */
	public void setStartRow(String startRow) {
		g = startRow;
	}

	/**
	 * 设置分页开始记录行数
	 * @param endRow String
	 */
	public void setEndRow(String endRow) {
		h = endRow;
	}

	/**
	 * 初始化
	 * @param s String
	 * @return Class
	 * @throws Throwable if has error
	 */
	static Class<?> a(String s) throws Throwable {
		try {
			return Class.forName(s);
		} catch (ClassNotFoundException classnotfoundexception) {
			throw (new NoClassDefFoundError())
					.initCause(classnotfoundexception);
		}
	}

	private String b;
	private String c;
	private String d;
	private String e;
	private String f;
	private String g;
	private String h;
	private String i;
	private String j;
	private boolean k;
	protected static Logger logger;

	static {
		logger = LoggerFactory
				.getLogger(PageUtil.class);
	}
}