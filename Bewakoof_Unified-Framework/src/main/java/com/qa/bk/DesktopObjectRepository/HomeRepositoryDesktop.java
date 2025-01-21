package com.qa.bk.DesktopObjectRepository;

import java.util.List;

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
	
	@FindBy(xpath="//a[text()='Mobile Covers']")
	private WebElement MobileCovers;
	public WebElement getMobileCoversTab() {
		return MobileCovers;
	}
	
	@FindBy(xpath="//span[text()='Topwear']/parent::a/following-sibling::a")
	private WebElement MensTShirt;
	public WebElement getMensTShirt() {
		return MensTShirt;
	}
	
	@FindBy(xpath="//form[@class='header_search_container__2QbV9']/descendant::input")
	private WebElement searchField;
	public WebElement getSearchField() {
		return searchField;
	}
	
	@FindBy(xpath="//a[@href='/cart']")
	private WebElement cartIcon;
	public WebElement getCartIcon() {
		return cartIcon;
	}
	
	@FindBy(xpath="//div[@id='BackToTop']")
	private WebElement backToTopButton;
	public WebElement getBackToTopButton() {
		return backToTopButton;
	}
	
	//Dynamic PLP
	@FindBy(xpath = "//img[@title='bewakoof logo' and @fetchpriority='high']/../following-sibling::nav//li")
	private List<WebElement> topNavs;
	public List<WebElement> getTopNavigation() {
		return topNavs;
	}
	
	@FindBy(xpath = "//img[@title='bewakoof logo' and @fetchpriority='high']/../following-sibling::nav//li/div/div/div[@index='0' or @index='1']/div/a")
	private List<WebElement> categoryOptions;
	public List<WebElement> getCategoryOptions() {
		return categoryOptions;
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
