package com.lms.utils.common;

import java.text.DecimalFormat;

public class RandomUtil {
	
	public static double getRandomTime(int fromTime ,int endTime){
		DecimalFormat df = new DecimalFormat( "0.00");
		String result = df.format((double)(fromTime+Math.random()*(endTime-fromTime+1))) ;
		double i = Double.parseDouble(result);
		return i;
	}
	
	public static void main(String[] args) {
		
	}
}
