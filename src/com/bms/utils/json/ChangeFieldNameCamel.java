package com.bms.utils.json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.processors.PropertyNameProcessor;

public class ChangeFieldNameCamel implements PropertyNameProcessor{
	
	public static String underline2Camel(String line,boolean smallCamel){
		  if(line==null||"".equals(line)){
		   return "";
		  }
		  StringBuffer sb=new StringBuffer();
		  Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
		  Matcher matcher=pattern.matcher(line);
		  while(matcher.find()){
		   String word=matcher.group();
		   sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
		   int index=word.lastIndexOf('_');
		   if(index>0){
		    sb.append(word.substring(1, index).toLowerCase());
		   }else{
		    sb.append(word.substring(1).toLowerCase());
		   }
		  }
		  return sb.toString();
		 }

	@Override
	public String processPropertyName(Class target, String fieldName) {
		// TODO Auto-generated method stub
		String regex = "([A-Za-z\\d]+)(_)?";
		StringBuilder stringBuilder = new StringBuilder();
		boolean flag = false;
		Matcher m = Pattern.compile(regex).matcher(fieldName);
		while (m.find()) {
			StringBuilder tempString = new StringBuilder();
			if (!flag) {
				tempString.append(m.group(0));
				flag = true;	
			} else {
				tempString.append("_");
				tempString.append((m.group(0)).toLowerCase());
			}
			stringBuilder.append(tempString);
		}
		return stringBuilder.toString();
	}
}
