package com.lms.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.print.DocFlavor.STRING;

import com.lms.utils.common.DateFormatUtil;
import com.sun.jmx.snmp.Timestamp;

public class TestDistance {
	private final static  double EARTH_RADIUS = 6378137; 
	private static final Double PI = Math.PI;
	private static final Double PK = 180 / PI; 
	
	private static double rad(double d)  
	{  
	    return d * Math.PI / 180.0;  
	}  
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2)  
	{  
	    double radLat1 = rad(lat1);  
	    double radLat2 = rad(lat2);  
	    double a = radLat1 - radLat2;  
	    double b = rad(lng1) - rad(lng2);  
	    double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +   
	    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));  
	    s = s * EARTH_RADIUS;  
	    s = Math.round(s * 10000) / 10000;  
	    return s;  
	} 
	
	
	public static double getDistanceFromTwoPoints(double lat_a, double lng_a, double lat_b, double lng_b) {  
        double t1 = Math.cos(lat_a / PK) * Math.cos(lng_a / PK) * Math.cos(lat_b / PK) * Math.cos(lng_b / PK);  
        double t2 = Math.cos(lat_a / PK) * Math.sin(lng_a / PK) * Math.cos(lat_b / PK) * Math.sin(lng_b / PK);  
        double t3 = Math.sin(lat_a / PK) * Math.sin(lat_b / PK);  
  
        double tt = Math.acos(t1 + t2 + t3);  
  
        return 6366000 * tt;  
    } 
	
	// 弧度  
    private static double radian(double d) {  
        return d * Math.PI / 180.0;  
    }  
    
    public static void distanceOfTwoPoints(double lat1, double lng1, double lat2, double lng2) {  
        double radLat1 = radian(lat1);  
        double radLat2 = radian(lat2);  
        double a = radLat1 - radLat2;  
        double b = radian(lng1) - radian(lng2);  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));  
        s = s * EARTH_RADIUS;  
        s = Math.round(s * 10000) / 10000;  
        double ss = s * 1.0936132983377;  
        System.out.println("method3:两点间的距离是：" + s + "米" + "," + (int) ss + "码");  
    }  
	
	public static void main(String[] args) {
		System.out.println(DateFormatUtil.changeTimeStampToUnixTime("2017-11-14 12:30:00"));
		
		
	}
}
