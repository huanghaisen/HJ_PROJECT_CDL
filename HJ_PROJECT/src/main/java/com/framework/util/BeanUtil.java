package com.framework.util;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {

	/**
	 * Map 转 Bean
	 * 
	 * @param map
	 * @param obj
	 */
	public static void map2Bean(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
//			System.out.println("transMap2Bean2 Error " + e);
		}

	}


	/**
	 * Bean 转 Map
	 * @param obj
	 */
	public static Map<String, String> bean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					if(getter.invoke(obj)!=null && getter.invoke(obj)!=""){
						String value = getter.invoke(obj)==null?"":getter.invoke(obj).toString();
						map.put(key, value);
					}
				}

			}
		} catch (Exception e) {
			System.out.println("对象转换异常请检查:" + obj.getClass().getName());
		}
		return map;
	}
	
	/**
	 * Bean 转 Map
	 * @param obj
	 */
	public static Map<String, Object> bean2MapObj(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if(getter.invoke(obj)!=null && getter.invoke(obj)!=""){
						map.put(key, value.toString());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("对象转换异常请检查:" + obj.getClass().getName());
		}
		return map;
	}

}
