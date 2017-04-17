package com.framework.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 字符实用类，含有转换编码的方法
 * @author lixy
 *
 */
public class StringUtil {
	private static final Log log = LogFactory.getLog(StringUtil.class);
	 /**
     * 将文件名中的汉字转为UTF8编码的串,以便下载时能正确显示另存的文件名.
     * 
     * @param s 原文件名
     * @return 重新编码后的文件名
     */
    public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<s.length();i++) {
		    char c = s.charAt(i);
		    if (c >= 0 && c <= 255) {
			sb.append(c);
		    } else {
			byte[] b;
			try {
			    b = Character.toString(c).getBytes("utf-8");
			} catch (Exception ex) {
			    System.out.println(ex);
			    b = new byte[0];
			}
			for (int j = 0; j < b.length; j++) {
			    int k = b[j];
			    if (k < 0) k += 256;
			    sb.append("%" + Integer.toHexString(k).
			    toUpperCase());
			}
		    }
	}
	return sb.toString();
    }
    
    /**
     * 生成形如 %123%的查询字符串
     * @param s
     * @return
     */
    public static String getLRLike(String s){
    	StringBuffer sb = new StringBuffer("");
    	if(s!=null && !s.equals(""))
    		sb.append("%"+s);
    	sb.append("%");
    	log.debug("生成的字符串为: "+sb.toString());
    	return sb.toString();
    }
    
    /**
     * 生成形如 43%的查询字符串
     * @param s
     * @return
     */
    public static String getRLike(String s){
    	StringBuffer sb = new StringBuffer("");
    	if(s!=null && !s.equals(""))
    		sb.append(s);
    	sb.append("%");
    	log.debug("生成的字符串为: "+sb.toString());
    	return sb.toString();
    }
    
    /**
     * 生成sql形式的查询字符串
     * @param fieldName  字段名
     * @param statrDate  开始时间
     * @param endDate	 结束时间
     * @return
     */
    public static String getDateQueryStr(String fieldName,String startDate,String endDate){
    	StringBuffer sb = new StringBuffer("");
		if(startDate!=null && !startDate.equals("")){
			sb.append(" and "+fieldName+">='"+startDate+"' ");
		}
		if(endDate!=null && !endDate.equals("")){
			sb.append(" and "+fieldName+"<='"+endDate+"' ");
		}
		log.debug("生成的时间区间查询字符串为: "+sb.toString());
    	return sb.toString();
    }
    
    /**
     * 字符串为空
     * @param str
     * @return
     */
    public static boolean isEmpty( String str )
	{
		return str==null || str.equalsIgnoreCase("null") || str.length()==0 ;
	}
    
    /**
     * 字符串不为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty( String str )
	{
		return !(str==null || str.equalsIgnoreCase("null") || str.length()==0) ;
	}
    
    /**
     * 返回形如yyyy-MM-dd的字符串,只取10位
     * @param dateStr
     * @return
     */
    public static String getDateString(String dateStr){
    	if(isNotEmpty(dateStr) && dateStr.length()>10){
    		dateStr = dateStr.substring(0,10);
    	}
    	return dateStr;
    }
    
    /**
     * 返回如yyyy-MM-dd时间转换
     * @param date
     * @return
     */
    public static String getSimpleDateFormat(Date date){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//格式化对象
    	return sdf.format(date);
    }
    
    /**
     * 将字符数组转换成List集合
     * @param ids
     * @return
     */
    public static List toList(String[] ids){
    	List list = new ArrayList();
    	if(ids != null && ids.length > 0){
	      int size = ids.length;		     
	      for(int i = 0;i < size;i++){ 
	    	  list.add(ids[i]);
	      }
		}
    	return list;
    }
    
    /**
     * <b>如:</b> replaceString (yourString, "\n", "&lt;BR&gt;") 
     * @param mainString 要转换的字符串 yourString
     * @param oldString  要转换的字 "\n"
     * @param newString  转换后的字 "&lt;BR&gt;"
     * @return
     */
    public static String replaceString(String mainString, String oldString, String newString) {
        if (mainString == null) {
            return null;
        }
        if (oldString == null || oldString.length() == 0) {
            return mainString;
        }
        if (newString == null) {
            newString = "";
        }

        int i = mainString.lastIndexOf(oldString);
        if (i < 0) return mainString;

        StringBuffer mainSb = new StringBuffer(mainString);

        while (i >= 0) {
            mainSb.replace(i, i + oldString.length(), newString);
            i = mainString.lastIndexOf(oldString, i - 1);
        }
        return mainSb.toString();
    }
	
    /**
	 * 数字字符串的整型转换
	 * @param str 数字字符串
	 * @param defaultVal 默认值
	 * @return 转换后的结果
	 */
	public final static int parseInt(String str, int defaultVal)
	{
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return defaultVal;
		}
	}
	
	
	/**
	 * 返回一个对象的字符串，多数是处理字符串的
	 */
	public static String trim(Object obj)
	{
		return obj == null ? "" : String.valueOf(obj).trim();
	}
	
	
	public static boolean search(String str,String chars){
		boolean flag=false;
		if (str.indexOf(chars)!=-1) {
			flag=true;
		}
		return flag;
	}
}
