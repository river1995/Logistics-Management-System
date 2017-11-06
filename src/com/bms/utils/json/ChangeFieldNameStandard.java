package com.bms.utils.json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.processors.PropertyNameProcessor;

public class ChangeFieldNameStandard implements PropertyNameProcessor{

	@Override
	public String processPropertyName(Class target, String fieldName) {
		// TODO Auto-generated method stub
		String regex = "([A-Z]){0,1}[a-z]+";
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
