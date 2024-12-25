package com.qa.bk.MobileObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartRepository {

	WebDriver driver;
	public  CartRepository(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[@class=\"sc-a30ed679-0 fggoPn\"]/div/div/following-sibling::div/div/span[@color='black']")
	private WebElement brandNameCart;
	public WebElement getbrandNameCart() {
		return brandNameCart;
	}
	@FindBy(xpath="//div[@class=\"sc-a30ed679-0 fggoPn\"]/div/div/following-sibling::div/div/span[@color='#737E93']")
	private WebElement productNameCart;
	public WebElement getproductNameCart() {
		return productNameCart;
	}
	
	@FindBy(xpath="//div[@class=\"sc-a30ed679-0 fggoPn\"]/div/section/div/following-sibling::div/div/span[@color='#292D35']")
	private WebElement productSellingPriceCart;
	public WebElement getproductSellingPriceCart() {
		return productSellingPriceCart;
	}
	@FindBy(xpath="//div[@class=\"sc-a30ed679-0 fggoPn\"]/div/section/div/following-sibling::div/div/span[@color='#737E93']")
	private WebElement productmrpCart;
	public WebElement getproductMRPCart() {
		return productmrpCart;
	}
	
	
}
