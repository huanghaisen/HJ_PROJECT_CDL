package com.framework.util;

import java.util.*;


public class PropertyUtils {

    private static String sqlResourceName = "conf.sql"; 
    
    private static String messageResName = "conf.msg";
    
    private static String jspConstantsResName = "conf.constants";
    
	public static String getSqlConfig(String proKey) {

        ResourceBundle rb = ResourceBundle.getBundle(sqlResourceName, Locale.CHINA); 
        
        //返回
        return rb.getString(proKey);
		
	}
	
	public static String getMessageConfig(String messageKey)
	{
        ResourceBundle rb = ResourceBundle.getBundle(messageResName, Locale.CHINA); 
        
        //返回
        return rb.getString(messageKey);
	}
	
	public static Map<String, String> getConstantsConfig(String constantsKey) {

        ResourceBundle rb = ResourceBundle.getBundle(jspConstantsResName, Locale.CHINA); 
        String prefix;
        if (!constantsKey.endsWith("_")) {
        	prefix = constantsKey + "_";
        } else {
        	prefix = constantsKey;
        }
        
        Map<String, String> result = new HashMap<String, String>();
        Enumeration<String> keys = rb.getKeys();

        while (keys.hasMoreElements()) {
            String name = (String) keys.nextElement();
            if (name.startsWith(prefix)) {
            	result.put(name.substring(prefix.length()), rb.getString(name));
            }
        }
        return result;
	}

}
