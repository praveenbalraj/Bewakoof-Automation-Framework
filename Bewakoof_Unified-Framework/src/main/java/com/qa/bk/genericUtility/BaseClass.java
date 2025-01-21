package com.qa.bk.genericUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Praveen B
 *
 */
public class BaseClass {
	
    protected WebDriver driver;
    protected static WebDriver sdriver;
	public static String URL;
	/**
	 * This block will be executed to get the driver address for both Mobile and Desktop
	 * Here we will call the 'DriverManager.Class' to Initialize driver 
	 * @param platform
	 * @param browser
	 * @throws Throwable
	 */
	@BeforeClass
	@Parameters({"browser" })
	public void setUp(String browser) throws Throwable {
		FileUtility fileUtil = new FileUtility();
		
		// Initialize browser driver for desktop
		if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
	        EdgeOptions options = new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(options);
		}else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options); 
           //Getting ConnectionNotFound Exception, try other browsers
		}else if (browser.equalsIgnoreCase("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new FirefoxDriver(options);
		}else{
			System.out.println("Invalid browser value on .xml file");
		}
		
		sdriver=driver;
		//maximize the browser screen - specific action for Desktop
			driver.manage().window().maximize();

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
		driver.quit();
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
	
	public static String takeScreenshot(String testName) {
        String timestamp = new JavaUtility().getSystemDateTime();
        String destinationPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
        try {
            File srcFile = ((TakesScreenshot) sdriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationPath;
    }
	

}
