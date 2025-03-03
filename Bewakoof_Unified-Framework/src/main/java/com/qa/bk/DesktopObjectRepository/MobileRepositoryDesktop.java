/**
 * 
 */
package com.qa.bk.DesktopObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Praveen B
 *
 */
public class MobileRepositoryDesktop {

	WebDriver driver;
	public  MobileRepositoryDesktop(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[text()='Choose your Brand']")
	private WebElement brandDropdown;
	public WebElement getBrandDropdown() {
		return brandDropdown;
	}
	
	@FindBy(xpath = "//div[text()='Choose your Brand']/following-sibling::div/div")
	private List<WebElement> mobileBrandOptions;
	public List<WebElement> getMobileBrandOptions() {
		return mobileBrandOptions;
	}
	
	@FindBy(xpath="//div[text()='Choose your Model']")
	private WebElement modelDropdown;
	public WebElement getModelDropdown() {
		return modelDropdown;
	}
	
	@FindBy(xpath = "//div[text()='Choose your Model']/following-sibling::div/div")
	private List<WebElement> mobileModelOptions;
	public List<WebElement> getMobileModelOptions() {
		return mobileModelOptions;
	}
	
	@FindBy(xpath = "//button[text()='Show mobile Cover']")
	private WebElement showMobileButton;
	public WebElement getshowMobileCoverButton() {
		return showMobileButton;
	}
	
	@FindBy(xpath = "//*[@id='__next']/main/main/div[1]/div/div[2]/div[2]/div/div/div")
	private List<WebElement> mobileBrand;
	public List<WebElement> getMobileBrand() {
		return mobileBrand;
	}
	
	@FindBy(xpath = "//*[@id='__next']/main/main/div[1]/div/div[2]/div[2]/div")
	private List<WebElement> mobileModel;
	public List<WebElement> getMobileModel() {
		return mobileModel;
	}
	@FindBy(xpath = "//span[text()='No Items Found']")
	private WebElement noItemFoundText;
	public WebElement getNoItemFoundText() {
		return noItemFoundText;
	}
	
}
