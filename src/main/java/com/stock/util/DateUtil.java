package com.stock.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	private static DateFormat stardardFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static DateFormat logDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat dayFormater = new SimpleDateFormat("yyyy-MM-dd");

	private static SimpleDateFormat timerOfDayFormater = new SimpleDateFormat("HH:mm:ss");

	private static final DateFormat LINUX_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
	// 一天的毫秒数
	public static final long ONE_DAY_MS = 24 * 60 * 60 * 1000;

	/**
	 * 
	 * 描述：将毫秒数的时间转为字符串时间：yyyy-mm-dd hh:mm:ss
	 *
	 * @param time
	 * @return
	 * @author wang guang shuai
	 *
	 *         2016年11月3日 下午4:03:22
	 *
	 */
	public static String millisToStr(long time) {
		Date date = new Date(time);
		return stardardFormat.format(date);

	}

	/**
	 * 比较时间,时间格式必须都一样，长度也一样，例如
	 * 
	 * @author 王广帅
	 * @param nowTime
	 * @param time1
	 * @param time2
	 * @return 2016年1月18日 上午11:26:38
	 */
	public static boolean compareTime(String nowTime, String time1, String time2) {
		if (time1 == null || time2 == null) {
			return false;
		}
		if (nowTime.length() != time1.length() || nowTime.length() != time2.length()) {
			return false;
		}
		if (nowTime.compareTo(time1) >= 0 && nowTime.compareTo(time2) <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 描述:这个方法是将毫秒数转化为yyyy-MM-dd HH:mm:ss格式的时间
	 *
	 * @param ms
	 * @return
	 * @author Terry
	 * @time 2016年6月30日-下午4:41:38
	 */
	public static String getFormatDate(long ms) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(ms);
		return stardardFormat.format(cal.getTime());
	}

	/**
	 * 
	 * @Desc 描述：将毫秒数转为日志
	 * @param ms
	 * @return
	 * @author 王广帅
	 * @Date 2017年3月17日 下午8:19:15
	 *
	 */
	public static Date getFormatDateFromMS(long ms) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(ms);
		return cal.getTime();
	}

	/**
	 * 
	 * 描述:这个方法是将date转化为yyyy-MM-dd HH:mm:ss格式的时间
	 *
	 * @param date
	 * @return
	 * @author Terry
	 * @time 2016年6月30日-下午4:42:19
	 */
	public static String dateToStardardTime(Date date) {
		return stardardFormat.format(date);
	}

	/**
	 * 将日期转化为String 格式：yyyyMMddHHmmss
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToYYYYMMDDHHMMSS(Date date) {
		return logDateFormat.format(date);
	}

	/**
	 * 获取日期。格式：yyyy-mm-dd
	 * 
	 * @author wang guang shuai
	 * @param date
	 * @return 2015-6-29 下午3:56:40
	 */
	public static String dateToStardardYYYYMMDD(Date date) {
		return dayFormater.format(date);
	}
	
	public static Date strToDate(String str) throws ParseException{
		return dayFormater.parse(str);
	}

	/**
	 * 获取当前天数的下一天的日期，date里面的HH:mm:ss都是0，即：00:00:00
	 * 
	 * @return
	 */
	public static Date getTomorrowZeroHour() {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_YEAR);

		calendar.set(Calendar.DAY_OF_YEAR, day + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/***
	 * 获取当前下一天的毫秒数,即下一天的yyyy-MM-dd 00:00:00转化为毫秒数
	 * 
	 * @return
	 */
	public static long getTomorrowZeroTime() {

		return getTomorrowZeroHour().getTime();
	}
	/**
	 * 
	 * @Desc  描述：根据给定的毫秒数，计算这个毫秒数代表的时间的下一天的零点时间戳。
	 * @param millSecond
	 * @return
	 * @author 王广帅
	 * @Date 2017年5月24日  上午9:50:19
	 *
	 */
	public static long getTomorrowZeroTime(long millSecond){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millSecond);
		int day = calendar.get(Calendar.DAY_OF_YEAR);
		calendar.set(Calendar.DAY_OF_YEAR, day + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}

	/**
	 * 
	 * @Description 获取时间是一年中的那一天
	 * @author linanjun
	 * @date 2013-9-2 下午04:05:06
	 * @return int
	 */
	public static int todayOfYear(long selectTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(selectTime);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 获取当前年份
	 * 
	 * @author 王广帅
	 * @param nowtime
	 * @return 2016年2月11日 上午12:07:36
	 */
	public static int getCurrentYear(long nowtime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(nowtime);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 字符串格式转毫秒
	 * 
	 * @author wang guang shuai
	 * @param date
	 * @return 2015-5-20 上午10:50:34
	 * @throws ParseException
	 */
	public static long strDateToMills(String date) throws ParseException {
		long day = 0;
		day = stardardFormat.parse(date).getTime();
		return day;
	}

	public static Date strDateToDate(String date) throws ParseException {
		return stardardFormat.parse(date);
	}

	/**
	 * 获取一天的时分秒：格式:hh:mm:ss
	 * 
	 * @author wang guang shuai 2015-1-4上午10:08:46
	 * @param date
	 * @return
	 */
	public static String timeOfDayHHMMSS(Date date) {
		return timerOfDayFormater.format(date);
	}

	/**
	 * 获取时间格式为：HH:MM的形式
	 * 
	 * @author 王广帅
	 * @param date
	 * @return 2016年1月7日 下午6:55:25
	 */
	public static String timeOfDayHHMM(Date date) {
		SimpleDateFormat timerOfDayFormater12 = new SimpleDateFormat("HH:mm");
		return timerOfDayFormater12.format(date);
	}

	/**
	 * 获取时间格式为：HH:MM的形式
	 * 
	 * @author 王广帅
	 * @param date
	 * @return 2016年1月7日 下午6:55:25
	 */
	public static String timeOfDayHHMM(long time) {

		return timeOfDayHHMM(new Date(time));
	}

	/**
	 * 根据当前时间 d获取此时间day之前的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * 得到几天后的时间
	 * 
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * @Description 获取date的零点正,即yyyy-MM-dd 00:00:00
	 * @author pingyang.li
	 * @date 2013-6-7 上午11:49:36
	 * @param date
	 * @return long
	 */
	public static Date getZeroDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 获取当天时间的最后时间 即：yyyy-MM-dd 23:59:59
	 * 
	 * @param d
	 * @return
	 */
	public static Date getLastTimeByDay(Date d) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.HOUR_OF_DAY, 23);
		now.set(Calendar.MINUTE, 59);
		now.set(Calendar.SECOND, 59);

		return now.getTime();
	}

	/**
	 * 判断是否同一天
	 * 
	 * @author wang guang shuai 2015-1-4下午5:35:15
	 * @param time1
	 * @param time2
	 * @return true 同一天；false 不在同一天
	 */
	public static boolean isOneDay(long time1, long time2) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time1);
		int year1 = calendar.get(Calendar.YEAR);
		int month1 = calendar.get(Calendar.MONTH);
		int day1 = calendar.get(Calendar.DAY_OF_YEAR);

		calendar.setTimeInMillis(time2);

		int year2 = calendar.get(Calendar.YEAR);
		int month2 = calendar.get(Calendar.MONTH);
		int day2 = calendar.get(Calendar.DAY_OF_YEAR);

		if (year1 == year2 && month1 == month2 && day1 == day2) {
			return true;
		}

		return false;
	}

	/**
	 * 返回两个时间之间相差的天数(默认参数1比参数2小)
	 * 
	 * @param atime
	 * @param btime
	 * @return
	 */
	public static int differenceNumberOfDays(long startTime, long currentTime) {
		int year1 = getCurrentYear(startTime);
		int year2 = getCurrentYear(currentTime);
		if (year1 != year2) {
			return 1;
		}
		int firstDay = todayOfYear(startTime);
		int secondDay = todayOfYear(currentTime);

		return Math.abs(secondDay - firstDay);
	}

	/**
	 * 
	 * 描述：获取当前时间的字符串格式时间：yyyy-MM-dd HH:mm:ss
	 *
	 * @return
	 * @author wang guang shuai
	 *
	 *         2016年11月3日 下午7:01:28
	 *
	 */
	public static String getNowDateStr() {
		Date date = new Date();
		return dateToStr(date);
	}

	/**
	 * 
	 * 描述：date转string字符串，格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 * @author wang guang shuai
	 *
	 *         2016年11月3日 下午6:59:52
	 *
	 */
	public static String dateToStr(Date date) {
		return stardardFormat.format(date);

	}

	/**
	 * 
	 * 描述:这个方法是更新系统时间为输入时间
	 *
	 * @param date
	 * @throws IOException
	 * @author Terry
	 * @time 2016年6月30日-下午5:07:19
	 */
	public static void updateSystemDate(Date date) throws IOException {
		// Operating system name
		String osName = System.getProperty("os.name");
		String cmd = "";
		date = new Date(date.getTime() - 5 * 1000);
		if (osName.matches("^(?i)Windows.*$")) {
			// Window 系统

			// 格式 HH:mm:ss
			String result = stardardFormat.format(date);
			cmd = "cmd /c time " + result.substring(result.indexOf(" ") + 1);
			Runtime.getRuntime().exec(cmd);
			// 格式：yyyy-MM-dd
			cmd = "cmd /c date " + result.substring(0, result.indexOf(" "));
			Runtime.getRuntime().exec(cmd);
		} else {

			// Linux 系统
			// // 格式：yyyyMMdd
			String result = LINUX_DATE_FORMAT.format(date);
			// 格式 HH:mm:ss
			cmd = "date -s " + result.substring(0, result.indexOf(" "));
			Runtime.getRuntime().exec(cmd);
			System.out.println(cmd);

			cmd = "date -s " + result.substring(result.indexOf(" ") + 1);
			Runtime.getRuntime().exec(cmd);
			System.out.println(cmd);

			//
		}
	}

	/**
	 * 
	 * <p>
	 * Title: isDayOfWeek
	 * </p>
	 * <p>
	 * Description: 判断今天是周几
	 * </p>
	 * 
	 * @param day
	 *            按正常的周几输入，比如周一，就是1
	 * @author guangshuai.wang
	 */
	public static boolean isDayOfWeek(int day) {
		Calendar strDate = Calendar.getInstance();
		if ((strDate.get(Calendar.DAY_OF_WEEK) - 1) == day) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * <p>
	 * Title: getEvenNumberHour
	 * </p>
	 * <p>
	 * Description:获取离现在最近的偶数小时毫秒数，比如只获取一天中第0，2，4，6.。。。小时的这毫秒数
	 * </p>
	 * 
	 * @return
	 * @author guangshuai.wang
	 */
	public static long getEvenNumberHour() {
		long result = 0;
		long nowTime = System.currentTimeMillis();
		long zeroTime = DateUtil.getZeroDate(new Date()).getTime();
		long cha = nowTime - zeroTime;
		long hours = cha / (60 * 60 * 1000);
		result = zeroTime;
		if (hours % 2 == 0) {
			result = result + (hours + 2) * 60 * 60 * 1000;
		} else {
			result = result + (hours + 1) * 60 * 60 * 1000;
		}
		return result;
	}

	public static long hhmmssTomills(String hhmmss) {
		if(hhmmss == null){
			throw new IllegalArgumentException("时间格式不能为null"  + ",应该是hh:mm:ss的格式");
		}
		String[] strs = hhmmss.split(":");
		if (strs.length != 3) {
			throw new IllegalArgumentException("时间格式不正确：" + hhmmss + ",应该是hh:mm:ss的格式");
		} else {
			int h = StringUtil.valueOfInt(strs[0]);
			int m = StringUtil.valueOfInt(strs[1]);
			int s = StringUtil.valueOfInt(strs[2]);
			long mills = (h * 3600L + m * 60L + s) * 1000L;
			return mills;
		}

	}
	
	 public static void main(String[] args) {
		 long endTime = System.currentTimeMillis() + 50000 * 60 * 1000L;
		 System.out.println(DateUtil.millisToStr(endTime));
			//System.out.println(DateUtil.millisToStr(1497447232134L));
	}

}
