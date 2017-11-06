package com.bms.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {

	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String changeLongTimeToString(long unixTime) {
		String result = "";
		if (unixTime > 0) {
			result = dateFormat.format(unixTime);
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
	
	public static void main(String[] args) {
		//getDate(1509351453385l);
		//System.out.println(System.currentTimeMillis());
	}

}
