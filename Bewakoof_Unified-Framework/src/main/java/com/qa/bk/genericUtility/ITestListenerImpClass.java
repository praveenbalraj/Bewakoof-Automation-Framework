package com.qa.bk.genericUtility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ITestListenerImpClass implements ITestListener {
	ExtentReports reports;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getMethod().getMethodName() + " started");
		System.out.println("Test Started - Listener impl");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName() + " passed");
		System.out.println("Test success - Listener impl");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		/*
		 * //Get the WebDriver instance from the test class Object testClass =
		 * result.getInstance(); driver = ((BaseClass) testClass).getDriver();
		 * 
		 * // Take a screenshot if (driver != null) { TakesScreenshot ts =
		 * (TakesScreenshot) driver; File srcFile = ts.getScreenshotAs(OutputType.FILE);
		 * String timestamp = new JavaUtility().getSystemDateInYYYYMMDD(); String
		 * testName = result.getName();
		 * 
		 * // Define destination for the screenshot File destFile = new
		 * File("screenshots/" + testName + "_" + timestamp + ".png");
		 * 
		 * try { FileUtils.copyFile(srcFile, destFile);
		 * System.out.println("Screenshot saved at: " + destFile.getAbsolutePath()); }
		 * catch (IOException e) { e.printStackTrace();
		 * System.out.println("Failed to save screenshot: " + e.getMessage()); } }
		 */
		test.log(Status.FAIL, result.getMethod().getMethodName() + " failed");
		test.log(Status.FAIL, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test.log(Status.SKIP, result.getMethod().getMethodName() + " skipped");
		test.log(Status.SKIP, result.getThrowable());

		/*
		 * try { //String path=BaseClass.ts();
		 * test.addScreenCaptureFromBase64String(BaseClass.ts()); } catch (Exception e)
		 * { e.printStackTrace(); }
		 */
	}

	@Override
	public void onStart(ITestContext context) {
		FileUtility fileUtil = new FileUtility();
		String platform = context.getSuite().getParameter("platform");
		ExtentSparkReporter reporter = new ExtentSparkReporter(IConstants.Extent_ReportPath + "TestReport" + "_"
				+ platform + new JavaUtility().getSystemDateTime() + ".html");
		reporter.config().setDocumentTitle("Bewakoof");
		reporter.config().setReportName("Web Test");
		reporter.config().setTheme(Theme.STANDARD);

		reports = new ExtentReports();
		reports.attachReporter(reporter);

		if (platform.equalsIgnoreCase("desktop")) {
			try {
				reports.setSystemInfo("Platform Name", platform);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} else {

			try {
				reports.setSystemInfo("Platform Name",
						fileUtil.getPropertyKeyValue("PLATFORM_NAME", IConstants.qaCapablityProprtyFile));
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				reports.setSystemInfo("Platform Version",
						fileUtil.getPropertyKeyValue("PLATFORM_VERSION", IConstants.qaCapablityProprtyFile));
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				reports.setSystemInfo("Device Name",
						fileUtil.getPropertyKeyValue("DEVICE_NAME", IConstants.qaCapablityProprtyFile));
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				reports.setSystemInfo("Browser Name",
						fileUtil.getPropertyKeyValue("BROWSER_NAME", IConstants.qaCapablityProprtyFile));
			} catch (Throwable e) {
				e.printStackTrace();
			}
			try {
				reports.setSystemInfo("Environment URL",
						fileUtil.getPropertyKeyValue("prodURL", IConstants.qaURLPropertyFilePath));
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		System.out.println("Extent report generated - Listener impl");
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
		System.out.println("Test report flushed - Listener impl");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}
}
