package com.qa.bk.MobileModule;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.bk.MobileObjectRepository.ObjectRepository;
import com.qa.bk.genericUtility.BaseClass;
import com.qa.bk.genericUtility.JavaUtility;
import com.qa.bk.genericUtility.WebdriverUtility;

public class PLPTest extends BaseClass {

	// @Listeners(com.qa.bk.genericUtility.ITestListenerImpClass.class)
	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class)
	public void Validate_the_Product_name_on_PLP_and_PDP() throws Throwable {
		ObjectRepository repo = new ObjectRepository(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebdriverUtility wUtil = new WebdriverUtility();

		// Click on Categories
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepository().getCategoryBottomNav());

		// Click on T-shirt collection
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepository().getTshirtCollection());

		List<WebElement> productCards = repo.getPLPRepository().getProductCards();
		wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
		int totalProducts = productCards.size();
		System.out.println("Total products found: " + totalProducts);

		for (int i = 0; i < totalProducts; i++) {
			try {
				// Re-fetch product cards to avoid stale element references
				productCards = repo.getPLPRepository().getProductCards();

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
					WebElement productTitle = repo.getPLPRepository().getPDPTitle();
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

	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class)
	public void Verify_Product_Data_Integrity_On_PLP_PDP_and_Cart() throws InterruptedException {
		WebdriverUtility wUtil = new WebdriverUtility();
		JavaUtility jUtil = new JavaUtility();
		ObjectRepository repo = new ObjectRepository(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 1. Go To PLP page
		// Click on Categories
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepository().getCategoryBottomNav());

		// 2.Click on T-shirt collection
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepository().getTshirtCollection());

		// 3. Apply Filter for Brand
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepository().getFilterButton());

		// 4. Select brand left Nav on filter
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepository().getFilterBrandButton());

		// 5. Get all options of brand and click random brand
		List<WebElement> brandOptions = repo.getPLPRepository().getfilterBrandOptions();
		WebElement brand = brandOptions.get(jUtil.getRanDomNumberInInteger(brandOptions.size()));
		wUtil.moveToElement(driver, brand);
		wUtil.waitAndClickOnElement(driver, brand);

		// 6. Click on Apply Button
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepository().getfilterApplyButton());
		Thread.sleep(2000);

		// 7. Get random product on PLP
		List<WebElement> productCards = repo.getPLPRepository().getProductCards();
		// wait.until(ExpectedConditions.visibilityOfAllElements(productCards));
		int ranProductCardNum = 0; // jUtil.getRanDomNumberInInteger(productCards.size());
		wUtil.scrollToElementXAndY(driver, productCards.get(ranProductCardNum));

		// 8. Get the product data from the product card
		String brandName = repo.getPLPRepository().getbrandNameProductCard().get(ranProductCardNum).getText();
		String productName = repo.getPLPRepository().getproductNameProductCard().get(ranProductCardNum).getText();
		float sellingPrice = jUtil
				.extractAmount(repo.getPLPRepository().getsellingPriceProductCard().get(ranProductCardNum).getText());
		float MRP = jUtil.extractAmount(repo.getPLPRepository().getMRPProductCard().get(ranProductCardNum).getText());
		int off = jUtil.extractNumbers(repo.getPLPRepository().offerProductCard().get(ranProductCardNum).getText());

		// 9. lick on product and Goto PDP
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepository().getProductCards().get(ranProductCardNum));

		// 10. Get the product data from the PDP

		String brandNamePDP = wUtil.waitAndGetTextOfOnElement(driver, repo.getPDPRepository().getbrandNamePDP());
		String productNamePDP = wUtil.waitAndGetTextOfOnElement(driver, repo.getPDPRepository().getproductNamePDP());
		float sellingPricePDP = jUtil.extractAmount(repo.getPDPRepository().getsellingPricePDP().getText());
		float MRPPDP = jUtil.extractAmount(repo.getPDPRepository().getMRPPDP().getText());
		int offPDP = jUtil.extractNumbers(repo.getPDPRepository().offerPDP().getText());

		// 11. Assertions to validate PDP matches PLP
		Assert.assertEquals(brandName, brandNamePDP);
		Assert.assertEquals(productName, productNamePDP);
		Assert.assertEquals(sellingPrice, sellingPricePDP);
		Assert.assertEquals(MRP, MRPPDP);
		Assert.assertEquals(off, offPDP);

		// 12. Add the product to the cart
		List<WebElement> inStockSizes = repo.getPDPRepository().getselectSizeCheckboxInStock();
		int sizeNum = jUtil.getRanDomNumberInInteger(inStockSizes.size()) - 1;
		WebElement SizeEle = inStockSizes.get(sizeNum);
		
		try {
			wUtil.waitAndClickOnElement(driver, SizeEle);
		} catch (ElementClickInterceptedException e) {
			js.executeScript("arguments[0].scrollIntoView({block: 'center'});", SizeEle);
			SizeEle.click();
		}
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepository().getAddToCartButton());

		// 13. Go to Cart
		wUtil.waitAndClickOnElement(driver, repo.getPDPRepository().getGoToCartButton());

		// 14. Get the product data on cart page
		String brandNameCart = wUtil.waitAndGetTextOfOnElement(driver, repo.getCartRepository().getbrandNameCart());
		String productNameCart = wUtil.waitAndGetTextOfOnElement(driver, repo.getCartRepository().getproductNameCart());
		float sellingPriceCart = jUtil.extractAmount(repo.getCartRepository().getproductSellingPriceCart().getText());
		float MRPCart = jUtil.extractAmount(repo.getCartRepository().getproductMRPCart().getText());

		// 15. Assertions to validate PDP matches PLP
		Assert.assertEquals(brandNamePDP, brandNameCart);
		Assert.assertEquals(productNamePDP, productNameCart);
		Assert.assertEquals(sellingPricePDP, sellingPriceCart);
		Assert.assertEquals(MRPPDP, MRPCart);

	}
	
	@Test(retryAnalyzer = com.qa.bk.genericUtility.RetryAnalyzer.class)
	public void Verify_Sort_Functionality() {
		WebdriverUtility wUtil = new WebdriverUtility();
		ObjectRepository repo = new ObjectRepository(driver);

		// 1. Go To PLP page
		// Click on Categories
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepository().getCategoryBottomNav());

		// 2.Click on T-shirt collection
		wUtil.waitAndClickOnElement(driver, repo.getHomeRepository().getTshirtCollection());

		// 3. Apply Filter for Brand
		wUtil.waitAndClickOnElement(driver, repo.getPLPRepository().getSortButton());
	}
	
	@Test
	public void Verify_Search_Functionality() throws InterruptedException {
		ObjectRepository repo = new ObjectRepository(driver);
		WebdriverUtility wUtil = new WebdriverUtility();
		
		//1. Get the search icon from home page
		
		WebElement searchIcon = repo.getHomeRepository().getSearchIcon();
		wUtil.waitAndClickOnElement(driver, searchIcon);
		
		Thread.sleep(10000);
		
	}

}
