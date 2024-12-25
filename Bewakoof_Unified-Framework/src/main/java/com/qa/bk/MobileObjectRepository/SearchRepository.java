package com.qa.bk.MobileObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchRepository {

	WebDriver driver;
	public  SearchRepository(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
}
