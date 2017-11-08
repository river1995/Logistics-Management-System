package com.bms.utils.common;


import java.io.IOException;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {
	
	public static String toUpperCaseFirstOne(String s){
		  if(Character.isUpperCase(s.charAt(0)))
		    return s;
		  else
		    return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	public static String changeChineseToPinyin(String Chinese){
		if (Chinese.equals("厦门")) {
			return "Xiamen";
		}
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        char[] input = Chinese.trim().toCharArray();  
        StringBuffer output = new StringBuffer("");  
        for (int i = 0; i < input.length; i++) {  
            if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]+")) {  
                String[] temp;
				try {
					temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
					output.append(temp[0]);  
	                output.append("");  
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                  
            } else  
                output.append(Character.toString(input[i]));  
        }  
        
        return toUpperCaseFirstOne(output.toString());
	}

	public static void main(String[] args) throws IOException, BadHanyuPinyinOutputFormatCombination {		
        System.out.println(changeChineseToPinyin("厦门"));
	}

}
