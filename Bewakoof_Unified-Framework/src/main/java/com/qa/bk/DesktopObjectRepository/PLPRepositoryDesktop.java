package com.qa.bk.DesktopObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.bk.genericUtility.WebdriverUtility;

public class PLPRepositoryDesktop {

	WebDriver driver;

	public PLPRepositoryDesktop(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//section[@class='sc-cc115ed6-4 lhwfAJ']")
	private List<WebElement> ProductCards;

	public List<WebElement> getProductCards() {
		return ProductCards;
	}

	@FindBy(xpath = "//div[@class='flex justify-between']/following-sibling::span[@type='2xs' and @variant='regular']")
	private WebElement PDPTitle;

	public WebElement getPDPTitle() {
		return PDPTitle;
	}

	@FindBy(xpath = "//span[text()='category']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> FilterCategories;

	public List<WebElement> getFilterCategories() {
		return FilterCategories;
	}

	@FindBy(xpath = "//span[text()='sizes']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> FilterSizes;

	public List<WebElement> getFilterSizes() {
		return FilterSizes;
	}

	@FindBy(xpath = "//span[text()='Brand']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> FilterBrand;

	public List<WebElement> getFilterBrand() {
		return FilterBrand;
	}

	@FindBy(xpath = "//span[text()='color']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> Filtercolor;

	public List<WebElement> getFilterColor() {
		return Filtercolor;
	}

	@FindBy(xpath = "//span[text()='design']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> FilterDesign;

	public List<WebElement> getFilterDesign() {
		return FilterDesign;
	}

	@FindBy(xpath = "//span[text()='fit']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> FilterFit;

	public List<WebElement> getFilterFit() {
		return FilterFit;
	}

	@FindBy(xpath = "//span[text()='sleeve']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> FilterSleeve;

	public List<WebElement> getFilterSleeve() {
		return FilterSleeve;
	}

	@FindBy(xpath = "//span[text()='neck']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> FilterNeck;

	public List<WebElement> getFilterNeck() {
		return FilterNeck;
	}

	@FindBy(xpath = "//span[text()='type']/ancestor::div[@border='bottom']//div[@class='desktop-filters_grid_filter__pNOqv']")
	private List<WebElement> FilterType;

	public List<WebElement> getFilterType() {
		return FilterType;
	}

	@FindBy(xpath = "//div[@class='sc-e3d24b13-1 kfCdXx']")
	private WebElement sortButton;

	public WebElement getSortButton() {
		return sortButton;
	}

	@FindBy(xpath = "//span[text()='Popularity' and @type='base']")
	private WebElement sortByPopularity;

	public WebElement getSortByPopularity() {
		return sortByPopularity;
	}

	@FindBy(xpath = "//span[text()='New Arrival' and @type='base']")
	private WebElement sortByNewArrival;

	public WebElement getSortByNewArrival() {
		return sortByNewArrival;
	}

	@FindBy(xpath = "//span[text()='Price : High to Low' and @type='base']")
	private WebElement sortByPriceHighToLow;

	public WebElement getSortByPriceHighToLow() {
		return sortByPriceHighToLow;
	}

	@FindBy(xpath = "//span[text()='Price : Low to High' and @type='base']")
	private WebElement sortByLowToHigh;

	public WebElement getSortByLowToHigh() {
		return sortByLowToHigh;
	}

	@FindBy(xpath = "//span[text()='Brand' and @type='base']/ancestor::div[@border='bottom']/child::div[2]/div/ul/button")
	private WebElement brandHideOrShow;

	public WebElement getbrandHideOrShow() {
		return brandHideOrShow;
	}

	@FindBy(xpath = "//span[text()='Brand' and @type='base']/ancestor::div[@border='bottom']/child::div[2]/div/ul/label/div")
	private List<WebElement> brandOptions;

	public List<WebElement> getbrandOptions() {
		return brandOptions;
	}

	@FindBy(xpath = "//div[@class='sc-cc115ed6-3 dcVVIc']/div/div/span[@color='#292D35']")
	private List<WebElement> brandNameProductCard;

	public List<WebElement> getbrandNameProductCard() {
		return brandNameProductCard;
	}

	@FindBy(xpath = "//div[@class='sc-cc115ed6-3 dcVVIc']/div/span")
	private List<WebElement> productNameProductCard;

	public List<WebElement> getproductNameProductCard() {
		return productNameProductCard;
	}

	@FindBy(xpath = "//div[@class='sc-cc115ed6-3 dcVVIc']/div/child::div[2]/span[@color='black']")
	private List<WebElement> sellingPriceProductCard;

	public List<WebElement> getsellingPriceProductCard() {
		return sellingPriceProductCard;
	}

	@FindBy(xpath = "//div[@class='sc-cc115ed6-3 dcVVIc']/div/child::div[2]/span[@color='#676767']")
	private List<WebElement> mrpProductCard;

	public List<WebElement> getMRPProductCard() {
		return mrpProductCard;
	}

	@FindBy(xpath = "//div[@class='sc-cc115ed6-3 dcVVIc']/div/child::div[2]/span[@color='#00B53A']")
	private List<WebElement> offerProductCard;

	public List<WebElement> offerProductCard() {
		return offerProductCard;
	}

	/**
	 * This method is used for Desktop version to go to PLP page from Home page Used
	 * to avoid repetition of Code on test scripts
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public void goToPLP_Desktop(WebDriver driver) throws InterruptedException {
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
		WebdriverUtility wUtil = new WebdriverUtility();

		// 1. Go To PLP page & load all products
		// 2. Mouse hover on Mens Tab
		WebElement mensTab = repo.getHomeRepositoryDesktop().getMensTab();
		wUtil.moveToElement(driver, mensTab);
		// Click on T-shirt collection
		WebElement tshirtCollection = repo.getHomeRepositoryDesktop().getMensTShirt();
		try {
			tshirtCollection.click();
		} catch (Exception e) {
			new WebdriverUtility().waitAndClickOnElement(driver, tshirtCollection);
		}
	}}

	// PDP
	
