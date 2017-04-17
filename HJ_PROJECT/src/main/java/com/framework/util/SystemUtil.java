/**
 * 处理读取配置文件
 */
package com.framework.util;

import java.util.ResourceBundle;

public class SystemUtil {
	
	
	/**
	 * 读取配置文件
	 * @param key 属性
	 * @return  value
	 */
	public static String getPro(String key){
		ResourceBundle resources = ResourceBundle.getBundle("config");
		return resources.containsKey(key)==false?"":resources.getString(key);
	}
	
	/**
	 * 读取配置文件
	 * @param filename 文件名
	 * @param key 属性
	 * @return  value
	 */
	public static String getPro(String filename,String key){
		ResourceBundle resources = ResourceBundle.getBundle(""+filename);
		return resources.containsKey(key)==false?"":resources.getString(key);
	}
}
