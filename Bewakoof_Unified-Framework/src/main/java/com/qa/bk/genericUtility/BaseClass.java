package com.qa.bk.genericUtility;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

/**
 * @author Praveen B
 *
 */
public class BaseClass {
	private AppiumDriverLocalService server;

    protected WebDriver driver;
    public static WebDriver sdriver;
	public static String URL;

	/**
	 * We will be getting this parameters from .xml file
	 * This method is only used for Android devices, should not be used for Desktop platform to avoid unnecessary exception since there is no need of starting the Android server 
	 * @param platform
	 * @param browser
	 */
	@BeforeSuite
	@Parameters({ "platform","browser" })
	public void startserver(@Optional("mobile") String platform, @Optional("chrome") String browser) {
		if (platform.equalsIgnoreCase("mobile")) {
			server = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().usingPort(4723)
					.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe")));
			server.start();
		}else {
            System.out.println("Skipping server start for desktop environment.");
        }
	}

	/**
	 * This block will be executed to get the driver address for both Mobile and Desktop
	 * Here we will call the 'DriverManager.Class' to Initialize driver 
	 * @param platform
	 * @param browser
	 * @throws Throwable
	 */
	@BeforeClass
	@Parameters({ "platform","browser" })
	public void setUp(String platform, String browser) throws Throwable {
		FileUtility fileUtil = new FileUtility();
		DriverManager.initializeDriver(platform, browser);
		driver = DriverManager.getDriver();
		
		//Perform Specific actions for Desktop to maximize and mobile to unlock
		if (platform.equalsIgnoreCase("desktop")) {
			//maximize the browser screen - specific action for Desktop
			driver.manage().window().maximize();
			
		} else if (platform.equalsIgnoreCase("mobile")) {			
			// Unlock Device - specific action for Mobile
			((AppiumDriver) driver).executeScript("mobile: unlock");
		} else {
			throw new IllegalArgumentException("Unsupported platform type: " + platform);
		}

		// Launch the Browser
		URL = fileUtil.getPropertyKeyValue("prodURL", IConstants.qaURLPropertyFilePath);
		driver.get(URL);
		handleBewakoofAlertPopUpBC();
	}
	
	/**
	 * Every time when the test starts, It should start from the home page so we are passing the Home page URL on After Method
	 */
	@AfterMethod
	public void am() {
		driver.get(URL);
	}
	/**
	 * After all test scripts are executed it is important to close the driver 
	 */
	@AfterClass
	public void ac() {
		DriverManager.quitDriver();
	}

	/**
	 * It is important to stop the Android server which was started Before suite
	 * @param platform
	 * @param browser
	 */
	@AfterSuite
	@Parameters({ "platform","browser" })
	public void stopserver(@Optional("mobile") String platform, @Optional("chrome") String browser) {
		if (platform.equalsIgnoreCase("mobile")) {
			server.stop();
		}
	}
	
	public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }
	
	/**
	 * this method will handle the app level permission pop up, when application is launched otherwise this pop up is going to block Clicking on other elements and throw ElementClickInterceptedException
	 * @throws InterruptedException
	 */
	public void handleBewakoofAlertPopUpBC() throws InterruptedException{
		Thread.sleep(3000);
		WebdriverUtility wUtil = new WebdriverUtility();
		wUtil.handleBewakoofAlertPopUp(driver);	
	}

	

}
