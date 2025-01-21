package com.qa.bk.DesktopModule;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.qa.bk.DesktopObjectRepository.ObjectRepositoryDesktop;
import com.qa.bk.genericUtility.BaseClass;
import com.qa.bk.genericUtility.IConstants;
import com.qa.bk.genericUtility.JavaUtility;
import com.qa.bk.genericUtility.WebdriverUtility;

public class PLPTest extends BaseClass {

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_ProductName_Data_Integrity() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);

		// Go to PLP page from Home page
		repo.getPLPRepositoryDesktop().goToPLP_Desktop(driver);
		Thread.sleep(3000);
		List<WebElement> productCards = repo.getPLPRepositoryDesktop().getProductCards();
		// int totalProducts = productCards.size();
		int totalProducts = 10;
		// System.out.println("Total products found: " + totalProducts);

		for (int i = 0; i < totalProducts; i++) {
			try {
				// Re-fetch product cards to avoid stale element references
				productCards = repo.getPLPRepositoryDesktop().getProductCards();

				// Ensure the product card exists before clicking
				if (i < productCards.size()) {
					WebElement productCard = productCards.get(i);

					// Scroll into view if necessary
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productCard);

					// Wait for the product card to be clickable
					wait.until(ExpectedConditions.elementToBeClickable(productCard));
					System.out.println("Clicking on product card " + (i + 1));
					productCard.click();// Thread.sleep(3000);

					// Perform actions on the PDP (e.g., capture product details)
					WebElement productTitle = repo.getPLPRepositoryDesktop().getPDPTitle();
					wait.until(ExpectedConditions.visibilityOf(productTitle));
					System.out.println("Product Title: " + productTitle.getText());

					// Navigate back to the PLP
					driver.navigate().back();

					// Wait for the PLP to reload
					wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
					Thread.sleep(3000);
				} else {
					System.out.println("Product card at index " + i + " is no longer available.");
				}
			} catch (StaleElementReferenceException e) {
				System.out
						.println("Encountered StaleElementReferenceException for product " + (i + 1) + ", retrying...");
				i--; // Retry the current iteration
			} catch (Exception e) {
				System.out.println("Exception occurred for product " + (i + 1) + ": " + e.getMessage());
			}
		}

	}

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Validate_Filter_Functionality() throws Throwable {
		WebdriverUtility wUtil = new WebdriverUtility();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
		// 1. Go To PLP page & load all products
		repo.getPLPRepositoryDesktop().goToPLP_Desktop(driver);

		List<WebElement> productCards = repo.getPLPRepositoryDesktop().getProductCards();
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		// 2. Apply Filter
		WebElement FilterCategories = repo.getPLPRepositoryDesktop().getFilterCategories().get(0);
		wUtil.waitForVisibilityOfElement(driver, FilterCategories);
		wUtil.scrollToElementXAndY(driver, FilterCategories);
		wUtil.waitAndClickOnElement(driver, FilterCategories);

		// 3. Click on the Product
		Thread.sleep(2000);
		productCards = repo.getPLPRepositoryDesktop().getProductCards();
		WebElement productCard = productCards.get(0);
		wUtil.moveToElement(driver, productCard);
		Thread.sleep(2000);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(productCard));
			// wait.until(ExpectedConditions.invisibilityOf(productCard));
			productCard.click();
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			driver.navigate().refresh();
			List<WebElement> products = repo.getPLPRepositoryDesktop().getProductCards();
			WebElement product = products.get(0);
			wait.until(ExpectedConditions.invisibilityOf(productCard));
			wUtil.waitAndClickOnElement(driver, product);
		}
		// 4. Select Size and Add to cart
		Thread.sleep(3000);
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getselectSizeCheckboxInStock().get(0));
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getAddToCartButton());

	}

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_Sort_Functionality() throws InterruptedException {
		WebdriverUtility wUtil = new WebdriverUtility();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
		// 1. Go To PLP page & load all products
		repo.getPLPRepositoryDesktop().goToPLP_Desktop(driver);

		// 2. Apply Sort Price : High to Low
		WebElement sortButton = repo.getPLPRepositoryDesktop().getSortButton();
		wUtil.waitAndClickOnElement(driver, sortButton);
		Thread.sleep(2000);

		// Click on Price High to Low
		WebElement sortPriceHighToLow = repo.getPLPRepositoryDesktop().getSortByPriceHighToLow();
		wUtil.waitAndClickOnElement(driver, sortPriceHighToLow);

		// Load all products
		List<WebElement> productCards = repo.getPLPRepositoryDesktop().getProductCards();
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		// 3. Apply Sort Price : Low to High
		wUtil.waitAndClickOnElement(driver, sortButton);
		Thread.sleep(2000);

		// Click on Price High to Low
		WebElement sortByLowToHigh = repo.getPLPRepositoryDesktop().getSortByLowToHigh();
		wUtil.waitAndClickOnElement(driver, sortByLowToHigh);

		// Load all products
		// wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		// 4. Apply Sort By New Arrivals
		wUtil.waitAndClickOnElement(driver, sortButton);
		Thread.sleep(2000);

		// Click on Price High to Low
		WebElement sortByNewArrival = repo.getPLPRepositoryDesktop().getSortByNewArrival();
		wUtil.waitAndClickOnElement(driver, sortByNewArrival);

		// Load all products
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		// 5. Apply Sort By Popularity
		wUtil.waitAndClickOnElement(driver, sortButton);
		Thread.sleep(2000);

		// Click on Price High to Low
		WebElement sortByPopularity = repo.getPLPRepositoryDesktop().getSortByPopularity();
		wUtil.waitAndClickOnElement(driver, sortByPopularity);

		// Load all products
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		// 6. Click on the Product
		Thread.sleep(2000);
		productCards = repo.getPLPRepositoryDesktop().getProductCards();
		WebElement productCard = productCards.get(0);
		wUtil.waitAndClickOnElement(driver, productCard);

		// 7. Select Size and Add to cart
		Thread.sleep(3000);
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getselectSizeCheckboxInStock().get(0));
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getAddToCartButton());

	}

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_Product_Data_Integrity_On_PLP_PDP_and_Cart() throws InterruptedException {
		WebdriverUtility wUtil = new WebdriverUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
		// 1. Go To PLP page & load all products
		repo.getPLPRepositoryDesktop().goToPLP_Desktop(driver);

		// Get all options of brand and click random brand
		List<WebElement> brandOptions = repo.getPLPRepositoryDesktop().getbrandOptions();
		wait.until(ExpectedConditions.visibilityOfAllElements(brandOptions));
		WebElement brandFilter = brandOptions.get(jUtil.getRanDomNumberInInteger(brandOptions.size()));
		Thread.sleep(2000);
		wUtil.moveToElement(driver, brandFilter);
		wUtil.waitAndClickOnElement(driver, brandFilter);

		// 3. spot a Random product on PLP page

		int ranProductCardNum = 1; // jUtil.getRanDomNumberInInteger(productCards.size());

		// 4. Get the product data from the product card
		String brandName = repo.getPLPRepositoryDesktop().getbrandNameProductCard().get(ranProductCardNum).getText();
		String productName = repo.getPLPRepositoryDesktop().getproductNameProductCard().get(ranProductCardNum)
				.getText();
		float sellingPrice = jUtil.extractAmount(
				repo.getPLPRepositoryDesktop().getsellingPriceProductCard().get(ranProductCardNum).getText());
		float MRP = jUtil
				.extractAmount(repo.getPLPRepositoryDesktop().getMRPProductCard().get(ranProductCardNum).getText());
		int off = jUtil
				.extractNumbers(repo.getPLPRepositoryDesktop().offerProductCard().get(ranProductCardNum).getText());
		System.out.println(brandName + " " + productName + " " + sellingPrice + " " + MRP + " " + off);

		// 5. Click on the product card
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

		// 6. Verify the product data on PDP

		// 7. select size and Add the product to cart

		// 8. Validate the product data on Cart page

	}

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_search_functionality() throws InterruptedException {
		// Create object for repository and utilities
		WebdriverUtility wUtil = new WebdriverUtility();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);

		// 1. Go to Home page

		// 2. Input the product or collection name on search text field
		wUtil.waitAndInputText(driver, repo.getHomeRepositoryDesktop().getSearchField(), "Joggers");
		Thread.sleep(2000);
		// 3. Click on any search suggestion
		List<WebElement> searchSuggestions = repo.getSearchRepositoryDesktop().getSearchSuggestionResult();
		int ranProductSearchNum = searchSuggestions.size();
		WebElement searchCollection = searchSuggestions.get(ranProductSearchNum - 1);
		wUtil.waitAndClickOnElement(driver, searchCollection);

		// 4. Go to PDP
		Thread.sleep(3000);
		List<WebElement> productCards = repo.getPLPRepositoryDesktop().getProductCards();
		int ranProductCardNum = 1;
		WebElement productCard = productCards.get(ranProductCardNum);
		wUtil.waitAndClickOnElement(driver, productCard);
		Thread.sleep(2000);

		// 5. Add the product to cart
		Thread.sleep(3000);
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getselectSizeCheckboxInStock().get(0));
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getAddToCartButton());

		// 6. Go to Cart and Close the cart
		Thread.sleep(3000);
		try {
			wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getGoToCartButton());
		} catch (Exception e) {
			wUtil.waitAndClickOnElement(driver, repo.getHomeRepositoryDesktop().getCartIcon());
		}
		Thread.sleep(3000);
		driver.navigate().back();

		// 7. Input the product or collection name on search text field
		wUtil.waitAndInputText(driver, repo.getHomeRepositoryDesktop().getSearchField(), "shirt");

		// 8. Click on Enter key or view all result
		try {
			wUtil.waitAndClickOnElement(driver, repo.getSearchRepositoryDesktop().getViewAllResultCTA());
		} catch (Exception e) {
			repo.getSearchRepositoryDesktop().getViewAllResultCTA().sendKeys(Keys.ENTER);
		}

		// 9. Go to PDP
		Thread.sleep(3000);
		productCards = repo.getPLPRepositoryDesktop().getProductCards();
		productCard = productCards.get(ranProductCardNum);
		wUtil.waitAndClickOnElement(driver, productCard);
		Thread.sleep(2000);

		// 10. Click on Back button
		driver.navigate().back();

		// 11. Apply Sort
		WebElement sortButton = repo.getPLPRepositoryDesktop().getSortButton();
		wUtil.waitAndClickOnElement(driver, sortButton);
		Thread.sleep(2000);

		// 12. Click on New Arrival
		WebElement sortByNewArrival = repo.getPLPRepositoryDesktop().getSortByNewArrival();
		wUtil.waitAndClickOnElement(driver, sortByNewArrival);

		// 13. Load all products
		Thread.sleep(2000);
		productCards = repo.getPLPRepositoryDesktop().getProductCards();
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		// 14. Click on the Product
		Thread.sleep(2000);
		productCards = repo.getPLPRepositoryDesktop().getProductCards();
		productCard = productCards.get(0);
		wUtil.waitAndClickOnElement(driver, productCard);

		// 15. Select Size and Add to cart
		Thread.sleep(3000);
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getselectSizeCheckboxInStock().get(0));
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getAddToCartButton());

		// Assert.fail();
	}

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_MobileCover_functionality() throws InterruptedException {
		// Create object for repository and utilities
		WebdriverUtility wUtil = new WebdriverUtility();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);

		// 1. Go to Mobile cover
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepositoryDesktop().getMobileCoversTab());

		// 2. Select mobile brand
		wUtil.waitAndClickOnElement(driver, repo.getMobileRepositoryDesktop().getBrandDropdown());

		// 3. Handle Brand drop-downs
		List<WebElement> brandOpt = repo.getMobileRepositoryDesktop().getMobileBrandOptions();
		int brandClick = new JavaUtility().getRanDomNumberInInteger(brandOpt.size());
		wUtil.waitAndClickOnElement(driver, brandOpt.get(brandClick));

		// 4. Select mobile model
		wUtil.waitAndClickOnElement(driver, repo.getMobileRepositoryDesktop().getModelDropdown());

		// 5. Handle model drop-downs
		List<WebElement> ModelOptions = repo.getMobileRepositoryDesktop().getMobileModelOptions();
		int modelClick = new JavaUtility().getRanDomNumberInInteger(ModelOptions.size());
		wUtil.waitAndClickOnElement(driver, ModelOptions.get(modelClick - 1));
		Thread.sleep(2000);

		// 6. Click on Show all Mobile button
		wUtil.waitAndClickOnElement(driver, repo.getMobileRepositoryDesktop().getshowMobileCoverButton());
		Thread.sleep(3000);

		// 7. Apply Filter
		try {
			wUtil.waitAndClickOnElement(driver, repo.getPLPRepositoryDesktop().getFirstPLPFilterOptions().get(0));
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 8. Scroll the PLP page 
		//Limit the scroll to 21 products
		int plpCount = repo.getPLPRepositoryDesktop().getProductCards().size();
		
		WebElement body = driver.findElement(By.tagName("body"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", body);

		// 9. Scroll down in smaller increments
		for (int i = 0; i < 10; i++) {
			js.executeScript("window.scrollBy(0, 250);"); // Scroll 250px down
			Thread.sleep(1000); // Wait for products to load
		}

		// 10. Click on Back to Top button
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepositoryDesktop().getBackToTopButton());

		// 11. Load all products
		List<WebElement> productCards = repo.getPLPRepositoryDesktop().getProductCards();
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		// 12. Apply Sort Price : Low to High
		WebElement sortButton = repo.getPLPRepositoryDesktop().getSortButton();
		wUtil.waitAndClickOnElement(driver, sortButton);
		Thread.sleep(2000);

		// 13.Click on Price High to Low
		WebElement sortPriceHighToLow = repo.getPLPRepositoryDesktop().getSortByPriceHighToLow();
		wUtil.waitAndClickOnElement(driver, sortPriceHighToLow);

		// 14. click on any product
		Thread.sleep(2000);
		productCards = repo.getPLPRepositoryDesktop().getProductCards();
		WebElement productCard = productCards.get(0);
		wUtil.waitAndClickOnElement(driver, productCard);

		// 15. Add to cart
		Thread.sleep(3000);
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getAddToCartButton());

		// 16. Go to Cart and Close the cart
		Thread.sleep(3000);
		try {
			wUtil.waitAndClickOnElement(driver, repo.getPDPRepositoryDesktop().getGoToCartButton());
		} catch (Exception e) {
			wUtil.waitAndClickOnElement(driver, repo.getHomeRepositoryDesktop().getCartIcon());
		}

	}

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_InfiniteScroll_functionality() throws InterruptedException {

		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
		WebdriverUtility wUtil = new WebdriverUtility();
		JavaUtility jUtil = new JavaUtility();

		List<WebElement> TopNav = repo.getHomeRepositoryDesktop().getTopNavigation();
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfAllElements(TopNav));

		// Click on any top navigation (Men and Women tab)
		int num = jUtil.getRanDomNumberInInteger(TopNav.size() - 1);
		wUtil.moveToElement(driver, TopNav.get(num));
		Thread.sleep(2000);

		// Click on any collection
		List<WebElement> Collection = repo.getHomeRepositoryDesktop().getCategoryOptions();
		wait.until(ExpectedConditions.visibilityOfAllElements(Collection));
		int numOpt = jUtil.getRanDomNumberInInteger(Collection.size());
		wUtil.moveToElement(driver, Collection.get(numOpt));
		wUtil.waitAndClickOnElement(driver, Collection.get(numOpt));

		// Load all products
		List<WebElement> productCards = repo.getPLPRepositoryDesktop().getProductCards();
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Locate the footer element
		WebElement collection_description = driver.findElement(By.id("collection-description"));

		// Helper method to handle type casting dynamically
		long footerPosition = jUtil.getLongValue(js.executeScript(
				"return arguments[0].getBoundingClientRect().top + window.scrollY;", collection_description));

		long previousScrollHeight = 0; // Track the previous scroll height
		long currentScroll = 0; // Track the current scroll position
		int productLimit = 60;

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
			int pCount = repo.getPLPRepositoryDesktop().getProductCards().size();
			if (pCount >= productLimit) {
				System.out.println("Product limit reached. Stopping the loop.");
				break;
			}

		}

	}

}
