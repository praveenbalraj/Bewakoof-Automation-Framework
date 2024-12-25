package com.qa.bk.genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author Praveen B
 * This method is used to Retry running the test script again in case of Test SKIP or FAIL
 */
public class RetryAnalyzer implements IRetryAnalyzer
	{
	    int retry=IConstants.retryValue;
	    public boolean retry(ITestResult iTestResult) {
	        if(retry>=0)
	        {
	            retry--;
	            return  true;
	        }
	        return false;
	    }
	}
