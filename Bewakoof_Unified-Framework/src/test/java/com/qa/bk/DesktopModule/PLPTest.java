package com.qa.bk.DesktopModule;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.bk.DesktopObjectRepository.ObjectRepositoryDesktop;
import com.qa.bk.MobileObjectRepository.HomeRepository;
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
		try {
			wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
		} catch (Exception e) {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		}
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
		// wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
		Thread.sleep(2000);

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
			// wait.until(ExpectedConditions.invisibilityOf(productCard));
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
		Thread.sleep(1000);
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

		int plpCount = repo.getPLPRepositoryDesktop().getProductCards().size();
		List<WebElement> productCards;

		if (plpCount > 5) {

			// 8. Scroll the PLP page
			// Limit the scroll to 21 products
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
			productCards = repo.getPLPRepositoryDesktop().getProductCards();
			wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

		}

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

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_SortPriceHighToLow_functionality() throws InterruptedException {
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
		WebdriverUtility wUtil = new WebdriverUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);

		// Go to PLP page
		repo.getPLPRepositoryDesktop().goToPLP_Desktop(driver);
		Thread.sleep(3000);
		// Click on Sort
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepositoryDesktop().getSortButton());

		// Apply Sort for High to low
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepositoryDesktop().getSortByPriceHighToLow());
		Thread.sleep(3000);
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

		List<WebElement> mrp = repo.getPLPRepositoryDesktop().getsellingPriceProductCard();
		List<Integer> productPrices = new ArrayList<>();

		for (WebElement webElement : mrp) {
			int priceText = new JavaUtility().extractNumbers(webElement.getText());
			productPrices.add(priceText);
		}

		// Create a copy of the price list and sort it in descending order
		List<Integer> sortedPrices = new ArrayList<>(productPrices);
		Collections.sort(sortedPrices, Collections.reverseOrder());

		// Validate that the original list matches the sorted list
		if (productPrices.equals(sortedPrices)) {
			System.out.println("Validation Passed: Products are sorted from high to low.");
		} else {
			Assert.fail("Validation Failed: Products are not sorted correctly.");
		}

	}

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_SortPriceLowToHigh_functionality() throws InterruptedException {
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
		WebdriverUtility wUtil = new WebdriverUtility();
		JavaUtility jUtil = new JavaUtility();
		WebDriverWait wait = new WebDriverWait(driver, IConstants.Implicitly_TIMEOUT);

		// Go to PLP page
		repo.getPLPRepositoryDesktop().goToPLP_Desktop(driver);
		Thread.sleep(3000);
		// Click on Sort
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepositoryDesktop().getSortButton());

		// Apply Sort for High to low
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepositoryDesktop().getSortByLowToHigh());
		Thread.sleep(3000);
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

		List<WebElement> mrp = repo.getPLPRepositoryDesktop().getsellingPriceProductCard();
		List<Integer> productPrices = new ArrayList<>();

		for (WebElement webElement : mrp) {
			int priceText = new JavaUtility().extractNumbers(webElement.getText());
			productPrices.add(priceText);
		}

		// Create a copy of the price list and sort it in descending order
		List<Integer> sortedPrices = new ArrayList<>(productPrices);
		Collections.sort(sortedPrices);

		// Validate that the original list matches the sorted list
		if (productPrices.equals(sortedPrices)) {
			System.out.println("Validation Passed: Products are sorted from Low to High.");
		} else {
			Assert.fail("Validation Failed: Products are not sorted correctly.");
		}

	}
	
	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)

	public void Verify_MobileCovers() throws Throwable {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);
	    WebdriverUtility wUtil = new WebdriverUtility();
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    driver.get("https://copernicus-beta.bewakoof.com/mobile-covers-india");
	    
	    int totalBrands = repo.getMobileRepositoryDesktop().getMobileBrand().size();
	    
	    for (int i = 0; i < totalBrands; i++) {
	        // ✅ Re-fetch brands to avoid stale elements
	        List<WebElement> brands = repo.getMobileRepositoryDesktop().getMobileBrand();
	        WebElement brand = brands.get(i);
	        
	        scrollToElementAndClick(js, brand);
	        Thread.sleep(2000); // Allow page to load

	        // ✅ Check if models are present
	        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='__next']/main/main/div[1]/div/div[2]/div[2]/div")));
	        List<WebElement> models = repo.getMobileRepositoryDesktop().getMobileModel();
	        
	        if (models.isEmpty()) {
	            System.out.println("No models found for brand: " + brand.getText());
	            driver.navigate().back();
	            Thread.sleep(2000);
	            continue; // Skip to the next brand
	        }

	        for (int j = 0; j < models.size(); j++) {
	            models = repo.getMobileRepositoryDesktop().getMobileModel(); // ✅ Re-fetch to avoid stale elements
	            WebElement model = models.get(j);
	            
	            scrollToElementAndClick(js, model);
	            Thread.sleep(2000);

	            if (isNoItemFoundPresent(repo)) {
	                driver.navigate().back();
	                Thread.sleep(2000);
	            } else {
	                System.out.println(driver.getTitle());
	                driver.navigate().back();
	                Thread.sleep(2000);
	            }
	        }
	        driver.navigate().back();
	        Thread.sleep(2000);
	    }
	}

	// ✅ Scrolls to element and clicks it
	private void scrollToElementAndClick(JavascriptExecutor js, WebElement element) throws InterruptedException {
	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
	    Thread.sleep(1000);
	    js.executeScript("arguments[0].click();", element);
	}

	// ✅ Safely check if "No Item Found" element is present
	private boolean isNoItemFoundPresent(ObjectRepositoryDesktop repo) {
	    try {
	        WebElement noItemMessage = driver.findElement(By.xpath("//span[text()='No Items Found']"));
	        return noItemMessage.isDisplayed(); // ✅ Returns true if the message exists
	    } catch (Exception e) {
	        return false; // ✅ Avoids crashing if element is missing
	    }
	}

}
	
