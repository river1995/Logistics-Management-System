package com.bms.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class TestPinYin {

	public static void main(String[] args) throws IOException, BadHanyuPinyinOutputFormatCombination {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
       // System.out.println(PinyinHelper.toHanyuPinyinStringArray("中国"., format));
        //读文件
//        File fileName = new File("C:/name.txt");
//        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
//        BufferedReader br = new BufferedReader(reader);
//        String line = "";
//        line = br.readLine();
//        while (line != null) {
//            char[] c = line.toCharArray();
//            String linePinyin = "";
//            for (int i=0; i<c.length; i++) {
//                String[] cPinyin = PinyinHelper.toHanyuPinyinStringArray(c[i], format);
//                linePinyin += cPinyin[0];
//            }
//            System.out.println(linePinyin);
//
//            line = br.readLine();
//        }

	}

}
