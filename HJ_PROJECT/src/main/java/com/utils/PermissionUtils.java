package com.utils;

import org.apache.commons.lang3.StringUtils;

public class PermissionUtils {
	
	
	public final static String[] SENCELIST={"soa","moa","coa"};
	
	public static String strToSence(String str){
		for (int i = 0; i < SENCELIST.length; i++) {
			String sence=SENCELIST[i];
			if (str.indexOf(sence)!=-1) {
				String strList[]=StringUtils.split(str, "/");
				str="/"+strList[0]+"/"+strList[1];
			}
		}
		return str;
	}

}
