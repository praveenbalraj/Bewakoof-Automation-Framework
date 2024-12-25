package com.qa.bk.DesktopObjectRepository;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomeRepositoryDesktop {

	WebDriver driver;
	public  HomeRepositoryDesktop(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[@href='/men-clothing']")
	private WebElement MensTab;
	public WebElement getMensTab() {
		return MensTab;
	}
	
	@FindBy(xpath="//span[text()='Topwear']/parent::a/following-sibling::a")
	private WebElement MensTShirt;
	public WebElement getMensTShirt() {
		return MensTShirt;
	}
	
	public void movetoPLP() throws InterruptedException {
		Thread.sleep(3000);
		//WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.elementToBeClickable(getMensTab()));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getMensTab());
		jsExecutor.executeScript("arguments[0].click();", getMensTab());

	}
	
}
