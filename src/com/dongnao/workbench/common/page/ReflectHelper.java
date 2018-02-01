package com.dongnao.workbench.common.page;

import java.lang.reflect.Field;

/**
 * 描述：反射工具 
 * 
 * @author joan.xiong
 * @version 1.0  2014-05-12
 */
public class ReflectHelper {  
    /** 
     * 获取obj对象fieldName的Field 
     * @param obj 
     * @param fieldName 
     * @return Field
     */  
    public static Field getFieldByFieldName(Object obj, String fieldName) {
    	for (Class<?> superClass = obj.getClass(); superClass != Object.class;
    			superClass = superClass.getSuperclass()) {  
            try {  
                return superClass.getDeclaredField(fieldName);  
            } catch (NoSuchFieldException e) {  
            }  
        }  
		return null;
    } 
    
    /**
     * 检查是否含有分页或本来就是分页类
     * @param obj Object
     * @param fieldName String
     * @return Object
     */
    public static Object isPage(Object obj, String fieldName) {
    	
		if (obj instanceof java.util.Map) {
			java.util.Map map = (java.util.Map)obj;
			if(map.containsKey(fieldName)){
				return map.get(fieldName);
			}else{
				return null;
			}

		} else {
    		for (Class<?> superClass = obj.getClass(); superClass != Object.class
    				; superClass = superClass.getSuperclass()) {  
                try {  
                	Field field=(Field)superClass.getDeclaredField(fieldName);//根据变量名m获得字段 
        			field.setAccessible(true);//设置字段可访问，即暴力反射 
        			Object obj1=(Object)field.get(obj);//在那个对象上获取此字段的值 
                    return obj1;  
                } catch (NoSuchFieldException e) {  
                } catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}  
            }  
    		return null;
    	}
      
    } 
    

  
    /** 
     * 获取obj对象fieldName的属性值 
     * @param obj 
     * @param fieldName 
     * @return Object
     * @throws NoSuchFieldException if has error
     * @throws IllegalArgumentException if has error
     * @throws IllegalAccessException if has error
     */  
    public static Object getValueByFieldName(Object obj, String fieldName)throws NoSuchFieldException,  
            IllegalArgumentException, IllegalAccessException {  
        Field field = getFieldByFieldName(obj, fieldName);  
        Object value = null;  
        if(field!=null){  
            if (field.isAccessible()) {  
                value = field.get(obj);  
            } else {  
                field.setAccessible(true);  
                value = field.get(obj);  
                field.setAccessible(false);  
            }  
        }  
        return value;  
    }  
  
    /** 
     * 设置obj对象fieldName的属性值 
     * @param obj 
     * @param fieldName 
     * @param value 
     * @throws NoSuchFieldException if has error 
     * @throws IllegalArgumentException if has error 
     * @throws IllegalAccessException if has error 
     */  
    public static void setValueByFieldName(Object obj, String fieldName, Object value) throws NoSuchFieldException
    ,IllegalArgumentException, IllegalAccessException {  
        
    	if(obj instanceof java.util.Map){
			java.util.Map map = (java.util.Map)obj;
			map.put(fieldName,value);
    	}else{
        	Field field = obj.getClass().getDeclaredField(fieldName);  
            if (field.isAccessible()) {  
                field.set(obj, value);  
            } else {  
                field.setAccessible(true);  
                field.set(obj, value);  
                field.setAccessible(false);  
            }  
    	}

    }  
}  
