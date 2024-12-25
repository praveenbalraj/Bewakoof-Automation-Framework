package com.qa.bk.genericUtility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author Praveen B 
 * Here we are storing all the application and browser related operations 
 * Used to avoid repetition of code on test scripts
 */

public class WebdriverUtility {
	public void elementClick(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void slideSectionFromRightToLeft(WebDriver driver, WebElement slider) {
		// Get the width of the slider element
		int sliderWidth = slider.getSize().getWidth();

		// Create an instance of the Actions class
		Actions actions = new Actions(driver);

		// Click and hold the left mouse button and Move the mouse to the right then
		// Release the left mouse button to finish the sliding action
		actions.clickAndHold(slider).moveByOffset(-(sliderWidth / 2), 0).release().perform();

	}

	public void slideSectionFromLeftToRight(WebDriver driver, WebElement slider) {
		// Get the width of the slider element
		int sliderWidth = slider.getSize().getWidth();

		// Create an instance of the Actions class
		Actions actions = new Actions(driver);

		// Move the mouse to the slider element
		actions.moveToElement(slider).build().perform();

		// Click and hold the left mouse button on the slider element
		actions.clickAndHold().build().perform();

		// Move the mouse to the right to slide the image
		actions.moveByOffset(sliderWidth / 2, 0).build().perform();

		// Release the left mouse button to finish the sliding action
		actions.release().build().perform();

	}

	public void slideSectionFromBottomToTop(WebDriver driver, WebElement slider) {
		// Get the width of the slider element
		int sliderWidth = slider.getSize().getHeight();

		// Create an instance of the Actions class
		Actions actions = new Actions(driver);

		// Move the mouse to the slider element
		actions.moveToElement(slider).build().perform();

		// Click and hold the left mouse button on the slider element
		actions.clickAndHold().build().perform();

		// Move the mouse to the right to slide the image
		actions.moveByOffset(sliderWidth / 2, 0).build().perform();

		// Release the left mouse button to finish the sliding action
		actions.release().build().perform();

	}

	public void slideSectionFromTopToBottom(WebDriver driver, WebElement slider) {
		// Get the width of the slider element
		int sliderWidth = slider.getSize().getHeight();

		// Create an instance of the Actions class
		Actions actions = new Actions(driver);

		// Move the mouse to the slider element
		actions.moveToElement(slider).build().perform();

		// Click and hold the left mouse button on the slider element
		actions.clickAndHold().build().perform();

		// Move the mouse to the right to slide the image
		actions.moveByOffset(sliderWidth / 2, 0).build().perform();

		// Release the left mouse button to finish the sliding action
		actions.release().build().perform();

	}

	public void handleBewakoofAlertPopUp(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement alertDontClickButton = wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='wzrk-overlay']/following-sibling::div[1]/div[3]/button")));
		wait.until(ExpectedConditions.elementToBeClickable(alertDontClickButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", alertDontClickButton);
		alertDontClickButton.click();

	}

	public void scrollToPoint(WebDriver driver, String x, String y) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(" + x + "," + y + ");");

	}

	public void scrollToElementXAndY(WebDriver driver, WebElement WE) {

		Point point = WE.getLocation();
		int x = point.getX();
		int y = point.getY() - 200;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(" + x + "," + y + ");");

	}

	public void scrollToElementJS(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToEndPage(WebDriver driver) {
		try {
			long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
			System.out.println(lastHeight);
			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(2000);
				long newHeight = (long) ((JavascriptExecutor) driver)
						.executeScript("return document.body.scrollHeight");
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String waitAndGetTextOfOnElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public void waitAndClickOnElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void waitAndVerifyForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
		Assert.assertTrue(element.isDisplayed(), "Element is Dispalyed");
	}

	public void ScrollToElementAndClick(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
		this.scrollToElementXAndY(driver, element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForClickableOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method use used to Click on a element which is there on DOM but not on
	 * the screen, uses JavascriptExecutor
	 * 
	 * @param driver
	 * @param element
	 */
	public void jsClick(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		jsExecutor.executeScript("arguments[0].click();", element);

	}

	/**
	 * Mouse hovering on element, Generally used to get the element to be visible on
	 * screen to avoid ElementClickInterceptedException
	 * 
	 * @param driver
	 * @param element
	 * @throws InterruptedException 
	 */
	public void moveToElement(WebDriver driver, WebElement element) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		Actions act = new Actions(driver);
		wait.until(ExpectedConditions.visibilityOf(element));
		try{
			act.moveToElement(element).build().perform();
		}catch (Exception e) {
			Thread.sleep(2000);
			act.moveToElement(element).build().perform();
		}
	}
}
