package com.lms.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateFormatUtil {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String changeLongTimeToString(long unixTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		String result = "";
		if (unixTime > 0) {
			result = format.format(unixTime);
		}
		return result;
	}

	public static String changeDateToSimple(long time) {
		String result = "";
		if (time > 0) {
			try {
				String formatTime = dateFormat.format(time);
				Date date = dateFormat.parse(formatTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int hour = calendar.get(Calendar.HOUR_OF_DAY);
				String minute = String.valueOf(calendar.get(Calendar.MINUTE));
				StringBuffer sb = new StringBuffer();
				for(int i = 0; i < 2 - minute.length(); i++) {
					sb.append("0");
				}
				sb.append(minute);
				minute = sb.toString();
				result = day+"/"+month+" "+hour+":"+minute;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public  static String getDate(long time){
		String result = "";
		if (time > 0) {
			try {
				String formatTime = dateFormat.format(time);
				Date date = dateFormat.parse(formatTime);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				result = day+"/"+month+"/"+year;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public static long  changeTimeStampToUnixTime(String timestamp){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Long time = 0l;
		try {
			time = format.parse(timestamp).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}
	
	public static long createDayTime(long unixTime){
		long time = 0l;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		try {
			String formatTime = dateFormat.format(unixTime);
			Date date = dateFormat.parse(formatTime);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int hour = (int)RandomUtil.getRandomTime(10, 17);
			int second = (int)RandomUtil.getRandomTime(1, 60);
			String timestamp = year+"-"+month+"-"+day+" "+hour+":"+second;
			time = format.parse(timestamp).getTime();
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return time;
	}
	
	public static String changePhpTimeToString(String phpTime){
		String rs = ""; 
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		rs = format.format(new Date(Integer.parseInt(phpTime)*1000L));
		return rs;
	}
	
	public static void main(String[] args) {
		//getDate(1509351453385l);
		//System.out.println(System.currentTimeMillis());
//		long time = changeTimeStampToUnixTime("2017-11-11 23:56:34");
//		String timeString = changeLongTimeToString(time);
//		System.out.println(time);
//		System.out.println(timeString);
//		long t1 = System.currentTimeMillis();
//		long t2 = (long) (t1 + 1000*60*60*23.5);
//		System.out.println("t1:"+changeLongTimeToString(t1));
//		System.out.println("t2:"+changeLongTimeToString(t2));
//		
		System.out.println("day time:"+DateFormatUtil.createDayTime(1511461440000l));
		System.out.println("timestamp:"+DateFormatUtil.changeLongTimeToString(1511506260000l));
		//System.out.println(System.currentTimeMillis());
		//System.out.println(DateFormatUtil.getDate(1510516080000l));
		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-24 02:24"));
	}

}