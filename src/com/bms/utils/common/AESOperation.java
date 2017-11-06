package com.bms.utils.common;  
 
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;  
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/** 
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化； 
 */  
public class AESOperation {  
    /* 
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。 
     */  
//  a0b891c2d563e4f7  
    private static  String sKey;  
    private static  String ivParameter ;  
    private static  AESOperation instance = null;  
    
    
    static{
    	sKey = ConstantsUtil.aeskey;
    	ivParameter = ConstantsUtil.aesiv;
    }
  
    private AESOperation() {  
  
    }  
  
    public static AESOperation getInstance() {  
        if (instance == null)  
            instance = new AESOperation();  
        return instance;  
    }  
  
    // 加密  
    public  String encrypt(String sSrc){  
        String result = "";  
        if (sSrc!=null) {		
	        try {  
	            Cipher cipher;  
	            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
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
    
    // 解密  
    public String decrypt(String sSrc){  
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
  
//public static void main(String[] args){  
//    // 需要加密的字串  
//    String cSrc = "[Null]";
//    System.out.println(cSrc + "  长度为" + cSrc.length());  
//    // 加密  
//    long lStart = System.currentTimeMillis();  
//    String enString = AESOperation.getInstance().encrypt(cSrc);  
//    System.out.println("加密后的字串是：" + enString + "长度为" + enString.length());  
//      
//    long lUseTime = System.currentTimeMillis() - lStart;  
//    System.out.println("加密耗时：" + lUseTime + "毫秒");  
//    // 解密  
//    lStart = System.currentTimeMillis();  
//    String DeString = AESOperation.getInstance().decrypt(enString);  
//    System.out.println("解密后的字串是：" + DeString);  
//    lUseTime = System.currentTimeMillis() - lStart;  
//    System.out.println("解密耗时：" + lUseTime + "毫秒");  
//    }  
//    

}  