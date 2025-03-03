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
	
	@FindBy(xpath="//div[@id='__next']/main/main/header/div/div[2]/div[2]/ul/li/div/div/div[2]")
	private WebElement searchSuggestionContainer;
	public WebElement getSearchSuggestionContainer() {
		return searchSuggestionContainer;
	}
	
	/**
	 * @return
	 */
	@FindBy(xpath="//div[@class='sc-f82926ad-13 epKqHh']")
	private List<WebElement> searchSuggestionResult;
	public List<WebElement> getSearchSuggestionResult() {
		return searchSuggestionResult;
	}
	
	@FindBy(xpath="//div[@id='__next']/main/main/header/div/div[2]/div[2]/ul/li/div/div/div[2]/div")
	private List<WebElement> allSearchSuggestionResult;
	public List<WebElement> getALLSearchSuggestionResult() {
		return allSearchSuggestionResult;
	}
	
	@FindBy(xpath="//img[@alt='no-search-results']")
	private WebElement noResultsFoundImage;
	public WebElement getNoResultsFoundImage() {
		return noResultsFoundImage;
	}
	
	@FindBy(xpath="//img[@alt='no-search-results']/parent::div/div")
	private WebElement noResultsFoundParagraph;
	public WebElement getNoResultsFoundParagraph() {
		return noResultsFoundParagraph;
	}
	
}
