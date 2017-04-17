package com.framework.util;

import java.text.DecimalFormat;


public class MathsUtils {
	
	/**
	 * 数字转格式输出
	 * @param num
	 * @param formatstr
	 * @return
	 */
	public static String doubleFormat(Double num,String formatstr){
		DecimalFormat df=new DecimalFormat(formatstr);
		return df.format(num).toString();
	}
	
	public static void main(String[] args) {
		
		Integer procsum=0;//已处理总数
		Integer faultsum=0;//已处理总数
		
		System.out.println(MathsUtils.doubleFormat(Double.valueOf(procsum)/Double.valueOf(faultsum),"#%"));
	}
}
