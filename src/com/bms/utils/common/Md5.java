package com.bms.utils.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Md5 {
	private final static String sKey = ConstantsUtil.aeskey;
	private final static String ivParameter = ConstantsUtil.aesiv;
	
	public static String getMd5(String sSrc){
		String result = "";  
        if (sSrc!=null) {		
	        try {  	              
	        	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
	            byte[] raw = sKey.getBytes();  
	            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
	            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度  
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	            byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));  
	            result = new BASE64Encoder().encode(encrypted); // 此处使用BASE64做转码。 
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	        return result; 
        } else {
			return sSrc;
		} 
	}
	
	public static String decodeMd5(String sSrc){  
		System.out.println("sSrc:"+sSrc);
    	if (sSrc!=null) {		
	        try {  
	            byte[] raw = sKey.getBytes("ASCII");  
	            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
	            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());  
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);  // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式  
	            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密  
	            byte[] original = cipher.doFinal(encrypted1);  
	            String originalString = new String(original, "UTF-8");
	            return originalString;  
	        } catch (Exception ex) {  
	            ex.printStackTrace();  
	            return null;  
	        } 
        } else{
        	return null;
        }
    }
}
