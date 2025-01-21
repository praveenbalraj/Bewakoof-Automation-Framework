package com.qa.bk.DesktopObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ObjectRepositoryDesktop {

	private static WebDriver driver;

	public ObjectRepositoryDesktop(WebDriver driver2) {
		PageFactory.initElements(driver2, this);
		ObjectRepositoryDesktop.driver = driver2;
	}

	public PLPRepositoryDesktop getPLPRepositoryDesktop() {
		PLPRepositoryDesktop plpRepo = new PLPRepositoryDesktop(driver);
		return plpRepo;
	}
	
	public HomeRepositoryDesktop getHomeRepositoryDesktop() {
		HomeRepositoryDesktop homeRepo = new HomeRepositoryDesktop(driver);
		return homeRepo;
	}
	
	public PDPRepositoryDesktop getPDPRepositoryDesktop() {
		PDPRepositoryDesktop homeRepo = new PDPRepositoryDesktop(driver);
		return homeRepo;
	}
	
	public SearchRepositoryDesktop getSearchRepositoryDesktop() {
		SearchRepositoryDesktop searchRepo = new SearchRepositoryDesktop(driver);
		return searchRepo;
	}
	
	public MobileRepositoryDesktop getMobileRepositoryDesktop() {
		MobileRepositoryDesktop MobileRepo = new MobileRepositoryDesktop(driver);
		return MobileRepo;
	}
	
/*	public PDPRepository getPDPRepository() {
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
*/
}
