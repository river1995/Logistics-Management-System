package com.lms.utils.common;

import java.util.UUID;
import com.sun.org.glassfish.external.statistics.Statistic;

import java.util.Random;


public class StringUtil {
	
	public boolean isNullString(String str) {
		boolean flag = true;
		if (str != null && str.length() > 0) {
			flag = false;
		}
		return flag;
	}
	

	public static String getRandomStr(){
		String uuid = UUID.randomUUID().toString();		
		return uuid;
	}
	
	public static String changeIntToString(int num){
		String returnStr = "";
		if (0 == num) {
			returnStr = null;
		}else{
			returnStr = num+"";
		}
		return returnStr;
	}

	public String RandomString(int length) {  
	    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";  
	    Random random = new Random();  
	    StringBuffer buf = new StringBuffer();  
	    for (int i = 0; i < length; i++) {  
	        int num = random.nextInt(62);  
	        buf.append(str.charAt(num));  
	    }  
	    return buf.toString();  
	} 
	
	public String changeWorkStatusIntToString(int status){
		String result = "";
		switch (status) {
		case 0:
			result = "Free";
			break;
		case 1:
			result = "In Process";
			break;

		default:
			result = "Others";
			break;
		}
		
		return result;
			
	}
	
	public static String changeAccountStatusIntToString(int accountStatus){
		String result = "";
		switch (accountStatus) {
		case ConstantsUtil.ACCOUNT_STATUS_OPEN:
			result = "Open";
			break;
		case ConstantsUtil.ACCOUNT_STATUS_APPROVED:
			result = "Approved";
			break;
		case ConstantsUtil.ACCOUNT_STATUS_REJECTED:
			result = "Rejected";
			break;
		default:
			result = "Others";
			break;
		}
		
		return result;
			
	}
	
	public static String generateLogisticNo(){
		String result = "";
		result = "ZE"+(System.currentTimeMillis()+"").substring(4, 13);
		return result;
	}
	
	public static int getRandomNumber(){
		int randomNum = (int)(Math.random()*3)+41;
		return randomNum;
	}
	
	public static void main(String[] args) {
		System.out.println(generateLogisticNo());
	}
	
	
	
	
}
