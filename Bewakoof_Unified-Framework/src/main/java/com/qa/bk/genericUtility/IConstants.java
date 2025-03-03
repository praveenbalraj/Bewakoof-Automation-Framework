package com.qa.bk.genericUtility;

import java.time.Duration;

/**
 * @author Praveen B
 * Here we are declaring values, Used to avoid hard coding the value on the test scripts
 */
public interface IConstants {
		
		String qaCapablityProprtyFile = "./propertyFile/qa.capabilities.properties";
		String qaURLPropertyFilePath = "./propertyFile/qa.url.properties";
		
		int retryValue = 1;
		String Extent_ReportPath = "../Bewakoof_Unified-Framework/reports/extentReports/";
		Duration Explicitly_TIMEOUT = Duration.ofSeconds(20);
		Duration Implicitly_TIMEOUT = Duration.ofSeconds(20);
		String searchInput = "Joggers";
}

