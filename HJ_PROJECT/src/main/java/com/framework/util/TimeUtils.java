package com.framework.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtils {
	
	public final static String DAY="day";//天
	public final static String HOUR="hour";//小时
	public final static String MILLIS="millis";//分钟

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String
	 * @return String  
	 */
//	public String getMonthBegin(String strdate) {
//		java.util.Date date = parseFormatDate(strdate);
//		return formatDateByFormat(date, "yyyy-MM") + "-01";
//	}

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strdate  
	 *            String
	 * @return String
	 */
//	public String getMonthEnd(String strdate) {
//		java.util.Date date = parseFormatDate(getMonthBegin(strdate));
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.MONTH, 1);
//		calendar.add(Calendar.DAY_OF_YEAR, -1);
//		return formatDate(calendar.getTime());
//	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 把时间戳转换成时间字符串
	 * @param tlong
	 * @param format
	 * @return
	 */
	public static String formatTimeLongFormat(Long tlong,String format){
		String result = "";
		if (tlong != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(new Date(tlong));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 格式化日期
	 * 
	 * @param dateStr
	 *            字符型日期
	 * @param format
	 *            格式
	 * @return 返回日期
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		if(dateStr.length() < format.length()) {
			format = format.substring(0, dateStr.length());
		}
		try {
			java.text.DateFormat df = new SimpleDateFormat(format);
			date = (Date) df.parse(dateStr);
		} catch (Exception e) {
			System.out.println("日期解析错误:"+dateStr);
		}
		return date;
	}

	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd HH:mm:ss");
	}

	public static Date parseDate(java.sql.Date date) {
		return date;
	}

	public static java.sql.Date parseSqlDate(Date date) {
		if (date != null)
			return new java.sql.Date(date.getTime());
		else
			return null;
	}

	public static java.sql.Date parseSqlDate(String dateStr, String format) {
		Date date = parseDate(dateStr, format);
		return parseSqlDate(date);
	}

	public static java.sql.Date parseSqlDate(String dateStr) {
		return parseSqlDate(dateStr, "yyyy/MM/dd");
	}

	public static java.sql.Timestamp parseTimestamp(String dateStr,
			String format) {
		Date date = parseDate(dateStr, format);
		if (date != null) {
			long t = date.getTime();
			return new java.sql.Timestamp(t);
		} else
			return null;
	}

	public static java.sql.Timestamp parseTimestamp(String dateStr) {
		return parseTimestamp(dateStr, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 格式化输出日期
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static String format(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 返回年份
	 * 
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 返回月份
	 * 
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回日份
	 * 
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回小时
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 返回字符型日期
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 返回字符型时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型时间
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}
	
	/**
	 * 返回字符型时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型时间
	 */
	public static String getTimeLong(Date date) {
		return format(date, "yyyyMMddHHmmss");
	}

	/**
	 * 返回字符型日期时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期时间
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 返回字符型日期时间
	 * 
	 * @param date
	 *            日期
	 * @return 返回字符型日期时间
	 */
	public static String getDateTimeLong(Date date) {
		return format(date, "yyyyMMdd");
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	}
	
	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的时间
	 */
	public static int diffDateTime(Date date, Date date1) {
		return (int) ((getMillis(date) - getMillis(date1)) / (3600 * 1000));
	}
	

	/**
	 * 日期对象与时间对象相加
	 * 
	 * @param date
	 *            日期对象
	 * @param time
	 *            时间对象
	 * @return 返回相加后的日期
	 */
	public static Date addTwoDate(Date date, Date time) {
		return new Date(date.getYear(), date.getMonth(), date.getDate(), 
				time.getHours(), time.getMinutes(), time.getSeconds());
	}
	
	/**
	   * 取得当前日期所在周的第一天,星期天开始是第一天
	   * 也可以设置星期一为第一天，把参数改为setFirstDayOfWeek（Calendar.MONDAY）
	   * 
	   * @param date
	   * @return
	   */
	   public static Date getFirstDayOfWeek(Date date) { 
	   Calendar c = new GregorianCalendar();
	   c.setFirstDayOfWeek(Calendar.SUNDAY);
	   c.setTime(date);
	   c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
	   return c.getTime ();
	   }

	   /**
	   * 取得当前日期所在周的最后一天
	   * 
	   * @param date
	   * @return
	   */
	   public static Date getLastDayOfWeek(Date date) {
	   Calendar c = new GregorianCalendar();
	   c.setFirstDayOfWeek(Calendar.SUNDAY);
	   c.setTime(date);
	   c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
	   return c.getTime();
	   }
	   
	   /**
	    * 获取制定月份最后一天
	    * @param date
	    * @return
	    */
	   public static int getLastMonthDay(Date date){
		   Calendar cal = Calendar.getInstance();
	        //设置年份
	        cal.setTime(date);
	        //获取某月最大天数
	        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		   return lastDay;
	   }
	   
	   /**
	    * 比较日期参数
	    * @param ntimeLong	最新时间
	    * @param ctimeLong	比较时间
	    * @param type(天day,小时hour,分钟millis)
	    * @return
	    */
	   public static int timeCompare(Date ntime,Date ctime,String type){
		   int num=0;
		   if(type.equals("month")) {//比较月份
			   num=(int) ((ntime.getTime()/1000/60/60/24-ctime.getTime()/1000/60/60/24)); 
		   }else if (type.equals("day")) {//比较天
			   num=(int) ((ntime.getTime()/1000/60/60/24-ctime.getTime()/1000/60/60/24)); 
		   }else if (type.equals("hour")) {//比较小时
			   num=(int) ((ntime.getTime()/1000-ctime.getTime()/1000)/60/60);
		   }else if (type.equals("millis")) {//比较分钟
				num=(int) ((ntime.getTime()/1000-ctime.getTime()/1000)/60);
		   }
		   
		   return num;
	   }
	   /**
	    * 获取当前时间的前一天
	    * @return
	    */
	   public static int getBeforeDay(){
		   Calendar cal = Calendar.getInstance();
		   cal.add(Calendar.DATE, -1);
		   return cal.get(Calendar.DAY_OF_MONTH);
	   }
	   /**
	    * 获取指定时间的前一天
	    * @param date
	    * @return
	    */
	   public static int getBeforeDayByDate(Date date){
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(date);
		   int day = cal.get(Calendar.DATE);
		   cal.set(Calendar.DATE, day-1);
		   return cal.get(Calendar.DAY_OF_MONTH);
	   }
	   
	   public static void main(String[] args) {
		String tt="2016-02";
		Date t1=TimeUtils.parseDate(tt,"yyyy-MM");
		Date t2=new Date();
		Calendar cal = Calendar.getInstance();
//		cal.setTime(t2);
		
		System.out.println(getLastMonthDay(t1));
		System.out.println(TimeUtils.getBeforeDayByDate(TimeUtils.parseDate("2016-12-29", "yyyy-MM-dd")));
//		System.out.println(TimeUtils.getMonth(t2)+"<=====>"+TimeUtils.getMonth(t1)+"<=======>"+(TimeUtils.getMonth(t2)-TimeUtils.getMonth(t1)));
		   
	}
	   
}
