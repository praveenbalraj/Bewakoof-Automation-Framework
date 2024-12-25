package com.qa.bk.MobileObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.bk.genericUtility.WebdriverUtility;

public class PLPRepository {

	WebDriver driver;
	public  PLPRepository(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//a[@class='w-full cursor-pointer']")
	private List<WebElement> ProductCards;
	public List<WebElement> getProductCards() {
		return ProductCards;
	}
	
	@FindBy(xpath="//div[@class='flex justify-between']/following-sibling::span[@type='2xs' and @variant='regular']")
	private WebElement PDPTitle;
	public WebElement getPDPTitle() {
		return PDPTitle;
	}
	
	@FindBy(xpath="//div[text()='Filter']")
	private WebElement filterButton;
	public WebElement getFilterButton() {
		return filterButton;
	}
	
	@FindBy(xpath="//div[contains(text(),'Sort')]")
	private WebElement sortButton;
	public WebElement getSortButton() {
		return sortButton;
	}
	
	@FindBy(xpath="//span[text()='Brand']")
	private WebElement filterBrandButton;
	public WebElement getFilterBrandButton() {
		return filterBrandButton;
	}
	
	@FindBy(xpath="//ul[@class='sc-e3d24b13-15 cmfZae']/label")
	private List<WebElement> filterBrandOptions;
	public List<WebElement> getfilterBrandOptions() {
		return filterBrandOptions;
	}
	
	@FindBy(xpath="//div[text()='APPLY']")
	private WebElement filterApplyButton;
	public WebElement getfilterApplyButton() {
		return filterApplyButton;
	}
	
	@FindBy(xpath="//div[@class='sc-cc115ed6-3 dcVVIc']/div/div/span[@color='#292D35']")
	private List<WebElement> brandNameProductCard;
	public List<WebElement> getbrandNameProductCard() {
		return brandNameProductCard;
	}
	@FindBy(xpath="//div[@class='sc-cc115ed6-3 dcVVIc']/div/span")
	private List<WebElement> productNameProductCard;
	public List<WebElement> getproductNameProductCard() {
		return productNameProductCard;
	}
	
	@FindBy(xpath="//div[@class='sc-cc115ed6-3 dcVVIc']/div/child::div[2]/span[@color='black']")
	private List<WebElement> sellingPriceProductCard;
	public List<WebElement> getsellingPriceProductCard() {
		return sellingPriceProductCard;
	}
	@FindBy(xpath="//div[@class='sc-cc115ed6-3 dcVVIc']/div/child::div[2]/span[@color='#676767']")
	private List<WebElement> mrpProductCard;
	public List<WebElement> getMRPProductCard() {
		return mrpProductCard;
	}
	@FindBy(xpath="//div[@class='sc-cc115ed6-3 dcVVIc']/div/child::div[2]/span[@color='#00B53A']")
	private List<WebElement> offerProductCard;
	public List<WebElement> offerProductCard() {
		return offerProductCard;
	}
	
	/**
	 * This method is used for Mobile version to go to PLP page from Home page
	 * Used to avoid repetition of Code on test scripts
	 * @param driver
	 */
	public void goToPLP_Desktop(WebDriver driver) {
		WebdriverUtility wUtil = new WebdriverUtility();
		ObjectRepository repo = new ObjectRepository(driver);

		// 1. Go To PLP page
		// Click on Categories
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepository().getCategoryBottomNav());

		// 2.Click on T-shirt collection
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepository().getTshirtCollection());
	}
	
}
