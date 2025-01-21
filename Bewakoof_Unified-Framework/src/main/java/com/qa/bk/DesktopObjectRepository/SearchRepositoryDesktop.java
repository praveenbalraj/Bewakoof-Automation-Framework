package com.qa.bk.DesktopObjectRepository;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchRepositoryDesktop {

	WebDriver driver;
	public  SearchRepositoryDesktop(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[@href='/men-clothing']")
	private WebElement MensTab;
	public WebElement getMensTab() {
		return MensTab;
	}
	
	@FindBy(xpath="//div[@class='sc-f82926ad-15 fduepM']")
	private WebElement viewAllResultCTA;
	public WebElement getViewAllResultCTA() {
		return viewAllResultCTA;
	}
	
	/**
	 * @return
	 */
	@FindBy(xpath="//div[@class='sc-f82926ad-13 epKqHh']")
	private List<WebElement> searchSuggestionResult;
	public List<WebElement> getSearchSuggestionResult() {
		return searchSuggestionResult;
	}
	
}
