package com.qa.bk.genericUtility;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *its contains External File specific libraries
 * @author Praveen B
 *
 */
public class FileUtility {

	/**
	 *   its used return the value from the property file based on key
	 * @param key
	 * @return value
	 * @throws Throwable
	 */
		public String getPropertyKeyValue(String key, String path) throws Throwable {
			
			FileInputStream fis = new FileInputStream(path);
			Properties pObj = new Properties();
			pObj.load(fis);
			String value = pObj.getProperty(key);
			return value;
		}
	
	}
