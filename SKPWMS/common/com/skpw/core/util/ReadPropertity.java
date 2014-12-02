package com.skpw.core.util;

import java.io.IOException;
import java.util.Properties;

public class ReadPropertity {
	static Properties props = new Properties();

	public static String getProperty(String path,String key) {
		try {
			props.load(ReadPropertity.class.getClassLoader()
					.getResourceAsStream(path));
		} catch (Exception e) {
			return null;
		}
		return props.getProperty(key);
	}
}
