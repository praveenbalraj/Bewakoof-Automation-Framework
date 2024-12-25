package com.qa.bk.MobileObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Praveen B
 *
 */
public class ObjectRepository {

	private static WebDriver driver;

	/**
	 * Its a singleton Class - used to call single and call then multiple classes
	 * It will be used to call the Locators which are stored on different classes
	 * we create a single object for this class and call other class, avoid creating multiple objects on test scripts
	 * @param driver2
	 */
	public ObjectRepository(WebDriver driver2) {
		PageFactory.initElements(driver2, this);
		ObjectRepository.driver = driver2;
	}

	public PLPRepository getPLPRepository() {
		PLPRepository plpRepo = new PLPRepository(driver);
		return plpRepo;
	}

	public PDPRepository getPDPRepository() {
		PDPRepository pdpRepo = new PDPRepository(driver);
		return pdpRepo;
	}

	public HomeRepository getHomeRepository() {
		HomeRepository homeRepo = new HomeRepository(driver);
		return homeRepo;
	}

	public CartRepository getCartRepository() {
		CartRepository cartRepo = new CartRepository(driver);
		return cartRepo;
	}

	public SearchRepository getSearchRepository() {
		SearchRepository searchRepo = new SearchRepository(driver);
		return searchRepo;
	}

}
