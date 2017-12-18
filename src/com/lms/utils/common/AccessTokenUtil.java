package com.lms.utils.common;

import org.apache.commons.codec.digest.DigestUtils;

public class AccessTokenUtil {
	
	public static String createAccessToken(String email,String password){
		String sStr = email+password+StringUtil.getRandomStr().substring(0,6);
		String token = DigestUtils.md5Hex(sStr+ConstantsUtil.tokenMd5Salt);
		return token;		
	}
	
	public static void main(String[] args) {
		String encode = AccessTokenUtil.createAccessToken("390103721@qq.com", "asdf1234");
		System.out.println("encode:"+encode+"\n"+"decode:");
	}
	
}
