package com.qa.bk.MobileObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeRepository {

	WebDriver driver;
	public  HomeRepository(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[text()='Categories']")
	private WebElement categoryBottomNav;
	public WebElement getCategoryBottomNav() {
		return categoryBottomNav;
	}
	@FindBy(xpath="//div[@class='sc-d43ca826-11 lkuqaM' and text()='T-Shirts']")
	private WebElement tshirtCollection;
	public WebElement getTshirtCollection() {
		return tshirtCollection;
	}
	@FindBy(xpath="(//div[@class='flex items-center gap-4 relative']/*[local-name()='svg'])[1]")
	private WebElement searchIcon;
	public WebElement getSearchIcon() {
		return searchIcon;
	}
}
