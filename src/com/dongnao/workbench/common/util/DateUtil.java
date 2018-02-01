package com.dongnao.workbench.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.dongnao.workbench.common.Constant;

/**
 * 
 * 时间处理工具类
 * 
 * @author joan.xiong
 * 
 */
public class DateUtil {

	public static final String FORMAT_YMD = "yyyyMMdd";
	
	public static final String FORMAT_Y_M_D = "yyyy-MM-dd";

	public static final String FORMAT_Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取当前时间
	 * 
	 * @return 当前时间
	 */
	public static final Date now() {
		return new Date();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前时间
	 */
	public static final Timestamp nowSqlTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前时间
	 */
	public static final Calendar nowCalendar() {
		return Calendar.getInstance();
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 当前时间
	 */
	public static final java.sql.Date nowSqlData() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * <p>
	 * 获取某年某月的最大天数
	 * </p>
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return int 当月最大天数
	 */
	public static final int dayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 1);
		cal.add(Calendar.DATE, -1);
		return cal.get(Calendar.DATE);
	}

	/**
	 * 是否是今天，根据System.currentTimeMillis() / 1000 / 60 / 60 / 24计算
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {
		long day = date.getTime() / 1000 / 60 / 60 / 24;
		long currentDay = System.currentTimeMillis() / 1000 / 60 / 60 / 24;
		return day == currentDay;
	}

	/**
	 * 判断是否是本周，取出日期，依据日期取出该日所在周所有日期，在依据这些日期是否和本日相等
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static boolean isThisWeek(Date date) {
		List<Date> dates = dateToWeek(date);
		Boolean flag = false;
		for (Date d : dates) {
			if (isToday(d)) {
				flag = true;
				break;
			} else {
				continue;
			}
		}
		return flag;
	}

	/**
	 * 判断是否是本月的日期
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static boolean isThisMonth(Date date) {
		long year = date.getYear();
		long month = date.getMonth();
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().getYear() == year
				&& calendar.getTime().getMonth() == month;
	}

	/**
	 * 判断是否是本年的日期
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static boolean isThisYear(Date date) {
		long year = date.getYear();
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime().getYear() == year;
	}

	/**
	 * 取出该日的一周所有日期
	 * 
	 * @param mdate
	 *            日期
	 * @return
	 */
	public static List<Date> dateToWeek(Date mdate) {
		long day = mdate.getDay();
		Date fdate;
		List<Date> list = new ArrayList<Date>();
		long fTime = mdate.getTime() - day * 24 * 3600000;
		for (int i = 0; i < 7; i++) {
			fdate = new Date();
			fdate.setTime(fTime + (((long) i) * 24 * 3600000));
			list.add(i, fdate);
		}
		return list;
	}

	/**
	 * 计算两个日期之间的相隔天数
	 * 
	 * @param begin
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return
	 */
	public static Double diffTwoDate(Date begin, Date end) {
		if (begin == null || end == null) {
			return 0.0;
		}
		return (end.getTime() - begin.getTime()) / 1000 / 60.0;
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static String parseDate(Date date, String format) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		String dateString;
		dateString = formater.format(date);
		return dateString;
	}

	/**
	 * 字符串格式化成日期
	 * 
	 * @param dataStr
	 *            日期字符串2015-11-11 11:11:11
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static Date parseStringToDate(String dataStr, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 字符串格式化成日期
	 * 
	 * @param dataStr
	 *            日期字符串2015-11-11 11:11:11
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static Date parseStringToyyyyMMddHHmmss(String dataStr) {
		SimpleDateFormat format = new SimpleDateFormat(Constant.DATETIME_FORMAT);
		Date date = null;
		try {
			date = format.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串格式化成日期 yyyy-MM-dd
	 * 
	 * @param dataStr
	 *            日期字符串2015-11-11 11:11:11
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static Date parseStringToyyyyMMdd(String dataStr) {
		SimpleDateFormat format = new SimpleDateFormat(Constant.DATE_FORMAT);
		Date date = null;
		try {
			date = format.parse(dataStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 计算相隔天数的日期
	 * 
	 * @param date
	 *            日期
	 * @param after
	 *            相隔天数
	 * @return
	 */
	public static Date afterDate(Date date, Integer after) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + after);
		return calendar.getTime();
	}

	/**
	 * 根据发布日期获取起止时间
	 * 
	 * @param releaseDate
	 *            发布日期（字典表信息）
	 * @return map 起止日期map,startTime:开始时间，endTime:结束时间
	 */
	public static Map<String, String> startTimeAndEndTime(String releaseDate,
			Date customDate) {
		SimpleDateFormat format = new SimpleDateFormat(Constant.DATE_FORMAT);
		Map<String, String> map = new HashMap<String, String>();
		if (!StringUtil.isBlank(releaseDate)) {
			Date startTim = DateUtil.now();
			StringBuffer now = new StringBuffer(format.format(startTim));
			if (Constant.RELEASE_DATE_WITHIN_WEEK.equals(releaseDate)) {
				startTim = DateUtil.afterDate(DateUtil.now(), -7);
			}
			if (Constant.RELEASE_DATE_WITHIN_MONTH.equals(releaseDate)) {
				startTim = DateUtil.afterDate(DateUtil.now(), -30);
			}
			String endTime = now.append(Constant.END_TIME).toString();
			String startTime = format.format(startTim) + Constant.START_TIME;
			if (Constant.RELEASE_DATE_CUSTOMDATES.equals(releaseDate)) {
				if (customDate != null) {
					startTim = customDate;
					startTime = format.format(startTim) + Constant.START_TIME;
				} else {
					endTime = "";
					startTime = "";
				}
			}

			if (customDate != null) {
				endTime = format.format(customDate) + Constant.END_TIME;
			}
			map.put("startTime", startTime);
			map.put("endTime", endTime);
		}
		return map;
	}

	public static Date stringToDate1(String str) throws ParseException {
		if (str.length() > 10) {
			if (str.indexOf(".") > 0) {
				return stringToDate4(str);
			}
			return stringToDate3(str);
		}

		return stringToDate2(str);
	}

	/**
	 * 
	 * @Title: getSimpleDateFormat
	 * @Description: TODO(统一日期格式对象)
	 * @param
	 * @param formatter
	 * @param
	 * @return 设定文件
	 * @return SimpleDateFormat 返回类型
	 * @throws
	 */
	public static SimpleDateFormat getSimpleDateFormat(String formatter) {
		SimpleDateFormat ft = new SimpleDateFormat(formatter);
		// System.setProperty("user.timezone","Asia/Shanghai");
		ft.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		return ft;
	}

	public static Date stringToDate(String str, String formatString)
			throws ParseException {
		if (str == null)
			return null;
		DateFormat df = getSimpleDateFormat(formatString);

		Date d1 = df.parse(str);
		return d1;
	}

	public static Date stringToDate2(String str) throws ParseException {
		return stringToDate(str, "yyyy-MM-dd");
	}

	public static Date stringToDate3(String str) throws ParseException {
		return stringToDate(str, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date stringToDate4(String str) throws ParseException {
		return stringToDate(str, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获取相隔年份日期
	 * 
	 * @param establistCode
	 *            相隔年份
	 * @return 计算后的日期
	 */
	public static String getEstablistDate(String establistCode) {
		if (!StringUtil.isBlank(establistCode)) {
			StringBuffer dateBuff = new StringBuffer();
			Calendar now = Calendar.getInstance();
			if (Constant.ZERO.equals(establistCode)) {
				int year = (now.get(Calendar.YEAR) - 1);
				dateBuff.append(year);
			} else {
				int year = (now.get(Calendar.YEAR) - Integer
						.parseInt(establistCode));
				dateBuff.append(year);
			}
			int month = now.get(Calendar.MONTH) + 1;
			int day = now.get(Calendar.DAY_OF_MONTH);
			dateBuff.append(Constant.DATESEPCHAR + month + Constant.DATESEPCHAR
					+ day);
			return dateBuff.toString();
		}
		return null;
	}

		/**
	 * 字符串格式化成今日日期 yyyy-MM-dd，返回字符串
	 * 
	 * @param dataStr
	 *            日期字符串2015-11-11 11:11:11
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static String parseDateString(Date dateStr,String formatStr){
		SimpleDateFormat sdf =   new SimpleDateFormat(formatStr);
		String date = "";
		date = sdf.format(dateStr);
		return date;
	}

		/**
	 *  yyyy-MM-01，返回字符串
	 * 
	 * @param dataStr
	 *            日期字符串2015-11-11 11:11:11
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static String getFirDate(Date dateStr){
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-01");
		String date = "";
		date = sdf.format(dateStr);
		return date;
	}
	
}
