package com.dongnao.workbench.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.dongnao.workbench.common.Constant;

/**
 * 描述：实体对象转换类
 * @author  zhou.zheng 
 * @version 1.0 2013-10-16
 */
public class FormatEntity {

	/**
	 * 将对象值转换成Map<String, String>对象
	 * @param object 
	 * @return Map<String, String>
	 */
	public static Map<String, String> getObjectValue(Object object) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 拿到该类
			Class<?> clz = object.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			for (PropertyDescriptor property : propertyDescriptors) {  
				// 如果类型是String
				String v = "";
				if (property.getPropertyType().equals(java.lang.String.class)) { // 如果type是类类型，则前面包含"class "，后面跟类名
					// 拿到该属性的gettet方法
					Method m =  property.getReadMethod();
					String val = (String) m.invoke(object);// 调用getter方法获取属性值
					if (val != null) {
						v = val;
					}
				}

				// 如果类型是Integer
				if (property.getPropertyType().equals(java.lang.Integer.class)) {
					Method m = property.getReadMethod();
					Integer val = (Integer) m.invoke(object);
					if (val != null) {
						v = val + "";
					}

				}

				// 如果类型是Double
				if (property.getPropertyType().equals(java.lang.Double.class)) {
					Method m = property.getReadMethod();
					Double val = (Double) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}

				// 如果类型是Boolean 是封装类
				if (property.getPropertyType().equals(java.lang.Boolean.class)) {
					Method m = property.getReadMethod();
					Boolean val = (Boolean) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}

				// 如果类型是Date
				if (property.getPropertyType().equals(java.util.Date.class)) {
					Method m = property.getReadMethod();
					Date val = (Date) m.invoke(object);
					if (val != null) {
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								Constant.DATETIME_FORMAT);
						v = dateFormat.format(val);
					}

				}
				// 如果类型是Short
				if (property.getPropertyType().equals(java.lang.Short.class)) {
					Method m = property.getReadMethod();
					Short val = (Short) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}
				
				// 如果类型是boolean，基本类型。add by zt
				if (property.getPropertyType().equals(Boolean.TYPE)) {
					Method m = property.getReadMethod();
					Boolean val = (Boolean) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}
				
				// 如果类型是int，基本类型。add by zt
				if (property.getPropertyType().equals(Integer.TYPE)) {
					Method m = property.getReadMethod();
					Integer val = (Integer) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}
				
				// 如果类型是BigDecimal，基本类型。add by zt
				if (property.getPropertyType().equals(BigDecimal.class)) {
					Method m = property.getReadMethod();
					BigDecimal val = (BigDecimal) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}
				map.put(property.getName(), v);
			}

		} catch (Exception e) {

		}
		return map;

	}
	
	/**
	 * 将对象值转换成Map<String, String>对象
	 * @param object 
	 * @return Map<String, String>
	 */
	public static Map<String, Object> getObjectValueObj(Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 拿到该类
			Class<?> clz = object.getClass();
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			for (PropertyDescriptor property : propertyDescriptors) {  
				// 如果类型是String
				
				Object v =property.getReadMethod().invoke(object);// 调用getter方法获取属性值
				if (property.getPropertyType().equals(java.lang.String.class)) { // 如果type是类类型，则前面包含"class "，后面跟类名
					// 拿到该属性的gettet方法
					Method m =  property.getReadMethod();
					String val = (String) m.invoke(object);// 调用getter方法获取属性值
					if (val != null) {
						v = val;
					}
				}

				// 如果类型是Integer
				if (property.getPropertyType().equals(java.lang.Integer.class)) {
					Method m = property.getReadMethod();
					Integer val = (Integer) m.invoke(object);
					if (val != null) {
						v = val + "";
					}

				}

				// 如果类型是Double
				if (property.getPropertyType().equals(java.lang.Double.class)) {
					Method m = property.getReadMethod();
					Double val = (Double) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}

				// 如果类型是Boolean 是封装类
				if (property.getPropertyType().equals(java.lang.Boolean.class)) {
					Method m = property.getReadMethod();
					Boolean val = (Boolean) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}

				// 如果类型是Date
				if (property.getPropertyType().equals(java.util.Date.class)) {
					Method m = property.getReadMethod();
					Date val = (Date) m.invoke(object);
					if (val != null) {
						SimpleDateFormat dateFormat = new SimpleDateFormat(
								Constant.DATETIME_FORMAT);
						v = dateFormat.format(val);
					}

				}
				// 如果类型是Short
				if (property.getPropertyType().equals(java.lang.Short.class)) {
					Method m = property.getReadMethod();
					Short val = (Short) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}
				
				// 如果类型是boolean，基本类型。add by zt
				if (property.getPropertyType().equals(Boolean.TYPE)) {
					Method m = property.getReadMethod();
					Boolean val = (Boolean) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}
				
				// 如果类型是int，基本类型。add by zt
				if (property.getPropertyType().equals(Integer.TYPE)) {
					Method m = property.getReadMethod();
					Integer val = (Integer) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}
				
				// 如果类型是BigDecimal，基本类型。add by zt
				if (property.getPropertyType().equals(BigDecimal.class)) {
					Method m = property.getReadMethod();
					BigDecimal val = (BigDecimal) m.invoke(object);
					if (val != null) {
						v = val + "";
					}
				}
				map.put(property.getName(), v);
			}

		} catch (Exception e) {

		}
		return map;

	}

	/**
	 * 把一个字符串的第一个字母大写、效率是最高的、
	 * @param fildeName 字段值
	 * @return	String
	 * @throws Exception 异常抛出
	 */
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

}