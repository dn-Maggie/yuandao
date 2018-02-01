package com.dongnao.workbench.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;

import com.dongnao.workbench.common.exceptions.DAOException;
import com.dongnao.workbench.common.exceptions.UnexpectedException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReflectUtil {
	protected static Map methods = Collections.synchronizedMap(new HashMap());

	protected static Map fieldsCache = Collections
			.synchronizedMap(new HashMap());

	public static Object newInstance(Class type) {
		try {
			return Class.forName(type.getName()).newInstance();
		} catch (Exception localException) {
			throw new UnexpectedException(localException);
		}
	}

	public static Object getValueByFieldName(Object entity, String fieldName)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, IntrospectionException,
			SecurityException, NoSuchFieldException {
		if (entity == null) {
			return null;
		}
		PropertyDescriptor propertyDescriptor = new PropertyDescriptor(
				fieldName, entity.getClass());
		Method readMethod = propertyDescriptor.getReadMethod();
		return readMethod.invoke(entity);

	}

	public static Object setModelPropertyValueToNull(Object model) {
		Method[] arrayOfMethod = model.getClass().getMethods();
		for (int i = 0; i < arrayOfMethod.length; i++) {
			Method localMethod = arrayOfMethod[i];
			try {
				if (localMethod.getName().startsWith("set")) {
					Object[] arrayOfObject = new Object[1];
					localMethod.invoke(model, arrayOfObject);
				}
			} catch (Exception localException1) {
				throw new UnexpectedException(
						"setModelPropertyValueToNull error:", localException1);
			}
		}
		return model;
	}

	public static String getPropertyType(String propName, Class mclass) {
		Field localField = null;
		String typeStr = null;
		try {
			localField = getPropertyField(mclass, propName);
		} catch (NoSuchFieldException localNoSuchFieldException) {
			throw new DAOException("No such field: " + propName,
					localNoSuchFieldException);
		}
		typeStr = localField.getType().getName();
		if (typeStr.equalsIgnoreCase("[B")) {
			typeStr = "byte[]";
		}
		return typeStr;
	}

	public static Object getPropertyValue(String propName, Object model) {
		try {
			Method localMethod = getGetMethod(model.getClass(), propName);
			return localMethod.invoke(model, null);
		} catch (Exception localException) {
			throw new UnexpectedException("getPropertyValue error:",
					localException);
		}
	}

	public static Class getPropertyClass(String propName, Object model) {
		try {
			Method localMethod = getGetMethod(model.getClass(), propName);
			return localMethod.getReturnType();
		} catch (Exception localException) {
			throw new UnexpectedException("getPropertyValue error:",
					localException);
		}
	}

	public static Method getMethod(Class clazz, String methodName,
			Class[] parmTypes) throws SecurityException, NoSuchMethodException {
		Map localMap = (Map) methods.get(clazz.getName());
		Method localMethod = null;
		if (localMap == null) {
			localMethod = clazz.getMethod(methodName, parmTypes);
			localMap = Collections.synchronizedMap(new HashMap());
			localMap.put(methodName, localMethod);
			methods.put(clazz.getName(), localMap);
		} else {
			localMethod = (Method) localMap.get(methodName);
			if (localMethod == null) {
				localMethod = clazz.getMethod(methodName, parmTypes);
				localMap.put(methodName, localMethod);
			}
		}
		return localMethod;
	}

	public static Field getPropertyField(Class clazz, String fieldName)
			throws SecurityException, NoSuchFieldException {
		Map localMap = (Map) fieldsCache.get(clazz.getName());
		Field localField = null;
		if (localMap == null) {
			localField = clazz.getDeclaredField(fieldName);
			localMap = Collections.synchronizedMap(new HashMap());
			localMap.put(fieldName, localField);
			fieldsCache.put(clazz.getName(), localMap);
		} else {
			localField = (Field) localMap.get(fieldName);
			if (localField == null) {
				localField = clazz.getDeclaredField(fieldName);
				localMap.put(fieldName, localField);
			}
		}
		return localField;
	}

	public static Method getGetMethod(Class clazz, String fieldName)
			throws Exception {
		String str = "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		return clazz.getMethod(str, null);
	}

	public static Method getSetMethod(Class clazz, String fieldName)
			throws Exception {
		String str = "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		Class[] arrayOfClass = new Class[1];
		arrayOfClass[0] = clazz.getDeclaredField(fieldName).getType();
		return clazz.getMethod(str, arrayOfClass);
	}

	public static Object getPropertityValue(Object object, String name)
			throws Exception {
		if ((object == null) || (name == null)) {
			return null;
		}
		String[] arrayOfString = name.split("\\.");
		if (arrayOfString.length == 0) {
			return null;
		}
		Object localObject = object;
		for (int i = 0; i < arrayOfString.length; i++) {
			Method localMethod = getGetMethod(localObject.getClass(),
					arrayOfString[i]);
			localObject = localMethod.invoke(localObject, null);
		}
		return localObject;
	}

	public static void invoke(Object value, Object model, String property) {
		try {
			Method localMethod = getSetMethod(model.getClass(), property);
			Object[] arrayOfObject = { value };
			localMethod.invoke(model, arrayOfObject);
		} catch (Exception localException1) {
			throw new UnexpectedException("invoke error:", localException1);
		}
	}

	public static boolean isImplementType(Class type1, Class type2) {
		if ((type1 == null) || (type2 == null))
			return false;
		try {
			Object localObject = type1.newInstance();
			return type2.isInstance(localObject);
		} catch (Exception localException1) {
		}
		return false;
	}
}
