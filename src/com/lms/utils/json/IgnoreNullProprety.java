package com.lms.utils.json;

import net.sf.json.util.PropertyFilter;

public class IgnoreNullProprety implements PropertyFilter{

	@Override
	public boolean apply(Object source, String name, Object value) {
		return value == null;
	}

}
