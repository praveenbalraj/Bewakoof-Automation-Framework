package com.qa.bk.genericUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

/**
 * @author Praveen B
 *
 */
public class DriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	/**
	 * This method is used to Launch the browser on mobile and desktop
	 * We are passing the driver address to Base class using 'Getter' technique, So every time we launch the browser we will pass updated address
	 * 'DesiredCapabilities' is made generic, we can change the capabilities for new Mobile device from Property File
	 * @param platform
	 * @param browser
	 * @throws Throwable
	 */
	public static void initializeDriver(String platform, String browser) throws Throwable {
		//Launch this if block to launch browser in desktop
		if (platform.equalsIgnoreCase("desktop")) {
			// Initialize browser driver for desktop
				if(browser.equalsIgnoreCase("edge")) {
					WebDriverManager.edgedriver().setup();
			        EdgeOptions options = new EdgeOptions();
					options.addArguments("--remote-allow-origins=*");
		            driver.set(new EdgeDriver(options));
				}else if (browser.equalsIgnoreCase("chrome")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--remote-allow-origins=*");
		            driver.set(new ChromeDriver(options)); 
		           //Getting ConnectionNotFound Exception, try other browsers
				}else if (browser.equalsIgnoreCase("firefox")) {
			        WebDriverManager.firefoxdriver().setup();
			        FirefoxOptions options = new FirefoxOptions();
					options.addArguments("--remote-allow-origins=*");
		            driver.set(new FirefoxDriver(options));
				}else{
					System.out.println("Invalid browser value on .xml file");
				}
		}
		
		//Launch this else-if block to launch browser in mobile
			else if (platform.equalsIgnoreCase("mobile")) {
		
			FileUtility fileUtil = new FileUtility();
			
			// Setup ChromeOptions
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("androidPackage", "com.android.chrome");
	        options.addArguments("--remote-allow-origins=*");

			// Set the capabilities
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("automationName","UiAutomator2");
					//fileUtil.getPropertyKeyValue("automationName", IConstants.qaCapablityProprtyFile));
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
					fileUtil.getPropertyKeyValue("PLATFORM_NAME", IConstants.qaCapablityProprtyFile));
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
					fileUtil.getPropertyKeyValue("PLATFORM_VERSION", IConstants.qaCapablityProprtyFile));
			desiredCapabilities.setCapability(MobileCapabilityType.UDID,
					fileUtil.getPropertyKeyValue("UDID", IConstants.qaCapablityProprtyFile));
			desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
					fileUtil.getPropertyKeyValue("DEVICE_NAME", IConstants.qaCapablityProprtyFile));
			desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
					fileUtil.getPropertyKeyValue("BROWSER_NAME", IConstants.qaCapablityProprtyFile));
			desiredCapabilities.setCapability("chromedriverExecutable",
					fileUtil.getPropertyKeyValue("chromedriverExecutable", IConstants.qaCapablityProprtyFile));
			desiredCapabilities.setCapability("goog:chromeOptions", options);

			//Initializing mobile driver...
			URL url = new URL("http://localhost:4723");
            driver.set(new AndroidDriver(url, desiredCapabilities));
	        		
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
