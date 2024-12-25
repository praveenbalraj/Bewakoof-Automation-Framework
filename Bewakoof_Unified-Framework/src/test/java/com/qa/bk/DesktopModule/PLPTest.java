package com.qa.bk.DesktopModule;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.qa.bk.DesktopObjectRepository.ObjectRepositoryDesktop;
import com.qa.bk.genericUtility.BaseClass;
import com.qa.bk.genericUtility.JavaUtility;
import com.qa.bk.genericUtility.WebdriverUtility;

public class PLPTest extends BaseClass {

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class, enabled = true)
	public void Verify_ProductName_Data_Integrity() throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		ObjectRepositoryDesktop repo = new ObjectRepositoryDesktop(driver);

		//Go to PLP page from Home page
		repo.getPLPRepositoryDesktop().goToPLP_Desktop(driver);
		
		List<WebElement> productCards = repo.getPLPRepositoryDesktop().getProductCards();
		int totalProducts = productCards.size();
		System.out.println("Total products found: " + totalProducts);

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

		/*
		 * WebElement FilterBrand =
		 * repo.getPLPRepositoryDesktop().getFilterBrand().get(0);
		 * wUtil.moveToElement(driver, FilterBrand);
		 * wUtil.waitAndClickOnElement(driver, FilterBrand);
		 * 
		 * WebElement FilterDesign =
		 * repo.getPLPRepositoryDesktop().getFilterDesign().get(0);
		 * wUtil.moveToElement(driver, FilterDesign);
		 * wUtil.waitAndClickOnElement(driver, FilterDesign);
		 * 
		 * WebElement FilterFit = repo.getPLPRepositoryDesktop().getFilterFit().get(0);
		 * wUtil.moveToElement(driver, FilterFit);
		 * wUtil.waitAndClickOnElement(driver, FilterFit);
		 */

		// 3. Click on the Product
		Thread.sleep(2000);
		productCards = repo.getPLPRepositoryDesktop().getProductCards();
		WebElement productCard = productCards.get(0);
		wUtil.moveToElement(driver, productCard);
		Thread.sleep(2000);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(productCard));
			//wait.until(ExpectedConditions.invisibilityOf(productCard));
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
	public void Validate_Sort_Functionality() throws InterruptedException {
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
		//wait.until(ExpectedConditions.visibilityOfAllElements(productCards));

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

	@Test(enabled = true)
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

}
