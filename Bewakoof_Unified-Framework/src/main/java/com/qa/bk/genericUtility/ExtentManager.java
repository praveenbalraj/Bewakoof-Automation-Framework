package com.qa.bk.genericUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * @author Praveen B
 * This class is used to create a e-mailable report
 * Creates a new report on 'reports' folder
 */
public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            // Use ExtentSparkReporter instead of ExtentHtmlReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("DesktopTestReport.html");
            sparkReporter.config().setDocumentTitle("Desktop Test Report");
            sparkReporter.config().setReportName("Desktop  Testing");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String category) {
        ExtentTest test = extent.createTest(testName).assignCategory(category);
        testThread.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return testThread.get();
    }

    public static void removeTest() {
        testThread.remove();
    }
}
