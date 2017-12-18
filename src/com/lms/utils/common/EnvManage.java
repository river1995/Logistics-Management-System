package com.lms.utils.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class EnvManage {
	private static EnvManage instance;
	public static final String EVN_FILE = "config.properties";
	private Properties prop = new Properties();
	

	private EnvManage() {
		
		try {
			InputStream in =  EnvManage.class.getClassLoader().getResourceAsStream(EVN_FILE);
			prop = new Properties();
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Properties getInstance() {
		if (instance == null) {
			instance = new EnvManage();
		}
		return instance.prop;
	}
				
}
