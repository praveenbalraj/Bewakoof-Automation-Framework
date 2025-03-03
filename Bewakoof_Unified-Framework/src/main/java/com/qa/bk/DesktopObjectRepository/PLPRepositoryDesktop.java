package com.qa.bk.DesktopObjectRepository;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.bk.genericUtility.IConstants;
import com.qa.bk.genericUtility.JavaUtility;
import com.qa.bk.genericUtility.WebdriverUtility;

public class PLPRepositoryDesktop {

	WebDriver driver;

	public PLPRepositoryDesktop(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//section[@class='sc-89d6e897-4 FQouP']")
	private List<WebElement> ProductCards;
	public List<WebElement> getProductCards() {
		return ProductCards;
	}

	@FindBy(xpath = "//h1[@data-testid='heading']")
	private WebElement PLPTitle;
	public WebElement getPLPTitle() {
		return PLPTitle;
	}
	
	@FindBy(xpath = "//h1[@data-testid='heading']/parent::div/span")
	private WebElement PLPCount;

	public WebElement getPLPCount() {
		return PLPCount;
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

	@FindBy(xpath = "//div[@class='sc-89d6e897-3 hjDXYa']/div/div/span[@color='#292D35']")
	private List<WebElement> brandNameProductCard;

	public List<WebElement> getbrandNameProductCard() {
		return brandNameProductCard;
	}

	@FindBy(xpath = "//span[@class='sc-f48c17b3-0 fjEpcQ']")
	private List<WebElement> productNameProductCard;

	public List<WebElement> getproductNameProductCard() {
		return productNameProductCard;
	}

	@FindBy(xpath = "//div[@class='sc-89d6e897-3 hjDXYa']/div/child::div[2]/span[@color='black']")
	private List<WebElement> sellingPriceProductCard;

	public List<WebElement> getsellingPriceProductCard() {
		return sellingPriceProductCard;
	}

	@FindBy(xpath = "//div[@class='sc-89d6e897-3 hjDXYa']/div/child::div[2]/span[@color='#676767']")
	private List<WebElement> mrpProductCard;

	public List<WebElement> getMRPProductCard() {
		return mrpProductCard;
	}

	@FindBy(xpath = "//div[@class='sc-89d6e897-3 hjDXYa']/div/child::div[2]/span[@color='#00B53A']")
	private List<WebElement> offerProductCard;

	public List<WebElement> offerProductCard() {
		return offerProductCard;
	}

	@FindBy(xpath = "//h4[text()='Filters']/../../../form/div/div/following-sibling::div/div/ul/label")
	private List<WebElement> firstPLPFilterOptions;

	public List<WebElement> getFirstPLPFilterOptions() {
		return firstPLPFilterOptions;
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
	}

	public void goToDynamicPLP_Desktop(WebDriver driver) throws InterruptedException {
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
		WebdriverUtility wUtil = new WebdriverUtility();
		JavaUtility jUtil = new JavaUtility();

		List<WebElement> TopNav = repo.getHomeRepositoryDesktop().getTopNavigation();
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfAllElements(TopNav));
		// Removing Mobile Cover
		int num = jUtil.getRanDomNumberInInteger(TopNav.size() - 1);
		wUtil.moveToElement(driver, TopNav.get(num - 1));

		// Click on any collection
		List<WebElement> tshirtCollection = repo.getHomeRepositoryDesktop().getCategoryOptions();
		int numOpt = jUtil.getRanDomNumberInInteger(tshirtCollection.size());
		wUtil.moveToElement(driver, tshirtCollection.get(numOpt - 1));

	}

	public void Click_On_ProductCard_In_PLP() throws InterruptedException {
		WebdriverUtility wUtil = new WebdriverUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);

		int ranProductCardNum = 1; // jUtil.getRanDomNumberInInteger(productCards.size());

		List<WebElement> productCards = repo.getPLPRepositoryDesktop().getProductCards();
		WebElement productCard = productCards.get(ranProductCardNum);
		wUtil.moveToElement(driver, productCard);
		Thread.sleep(2000);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(productCard));
			wait.until(ExpectedConditions.invisibilityOf(productCard));
			productCard.click();
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			driver.navigate().refresh();
			List<WebElement> products = repo.getPLPRepositoryDesktop().getProductCards();
			WebElement product = products.get(ranProductCardNum);
			wait.until(ExpectedConditions.invisibilityOf(productCard));
			wUtil.waitAndClickOnElement(driver, product);
		}
	}

	public void scrollThePLPpageUnderLimit(int productLimit) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		JavaUtility jUtil = new JavaUtility();

		// Load all products
		List<WebElement> productCards = getProductCards();
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Locate the footer element
		WebElement collection_description = driver.findElement(By.id("collection-description"));

		// Helper method to handle type casting dynamically
		long footerPosition = jUtil.getLongValue(js.executeScript(
				"return arguments[0].getBoundingClientRect().top + window.scrollY;", collection_description));

		long previousScrollHeight = 0; // Track the previous scroll height
		long currentScroll = 0; // Track the current scroll position

		// Scroll until the footer is reached
		while (currentScroll + jUtil.getLongValue(js.executeScript("return window.innerHeight;")) < footerPosition) {
			// Scroll down
			js.executeScript("window.scrollBy(0, 300);");
			Thread.sleep(1000); // Wait for products to load

			// Get the new scroll height and position
			long newScrollHeight = jUtil.getLongValue(js.executeScript("return document.body.scrollHeight;"));
			currentScroll = jUtil.getLongValue(js.executeScript("return window.scrollY;"));

			// If new products are loaded (scroll height changes), update footer position
			if (newScrollHeight != previousScrollHeight) {
				footerPosition = jUtil.getLongValue(js.executeScript(
						"return arguments[0].getBoundingClientRect().top + window.scrollY;", collection_description));
				previousScrollHeight = newScrollHeight;
			}

			// Scroll Limit - stop the loops when the product card count reaches 100
			int pCount = getProductCards().size();
			if (pCount >= productLimit) {
				System.out.println("Product limit reached. Stopping the loop.");
				break;
			}

		}
	}
}

// PDP
