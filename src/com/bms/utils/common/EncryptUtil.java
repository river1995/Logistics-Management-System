package com.bms.utils.common;


public class EncryptUtil {
	/*
	 * 加密工具
	 */
	public  String encryptString(String src){
		if(ConstantsUtil.encrypt.equals("yes")){
			return AESOperation.getInstance().encrypt(src);
		}else{
			return src;
		}
	}
	/*
	 * 解密工具
	 */
	public String decryptString(String src){
		if(ConstantsUtil.encrypt.equals("yes")){
			return AESOperation.getInstance().decrypt(src);
		}else{
			return src;
		}
	}
}
