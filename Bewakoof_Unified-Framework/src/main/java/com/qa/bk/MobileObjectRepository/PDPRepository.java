package com.qa.bk.MobileObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PDPRepository {

	WebDriver driver;
	public  PDPRepository(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//div[@class='sc-fb1f0418-0 kqOFyP']/div/div/a/span[@color='#000000']")
	private WebElement brandNamePDP;
	public WebElement getbrandNamePDP() {
		return brandNamePDP;
	}
	@FindBy(xpath="//div[@class='sc-fb1f0418-0 kqOFyP']/div/span")
	private WebElement productNamePDP;
	public WebElement getproductNamePDP() {
		return productNamePDP;
	}
	
	@FindBy(xpath="//div[@class='sc-fb1f0418-0 kqOFyP']/div[2]//h2")
	private WebElement sellingPricePDP;
	public WebElement getsellingPricePDP() {
		return sellingPricePDP;
	}
	@FindBy(xpath="//div[@class='sc-fb1f0418-0 kqOFyP']/div[2]//span[@type='base' and @color='#979797']")
	private WebElement mrpPDP;
	public WebElement getMRPPDP() {
		return mrpPDP;
	}
	@FindBy(xpath="//div[@class='sc-fb1f0418-0 kqOFyP']/div[2]//span[@type='base' and @color='#008C2D']")
	private WebElement offerPDP;
	public WebElement offerPDP() {
		return offerPDP;
	}
	
	@FindBy(xpath="//div[@class='sc-9d458f08-14 fIRGUP']/label[@class='sc-dd7c066a-2 sc-dd7c066a-5 eNIdhK igtXFB' or @class='sc-dd7c066a-2 sc-dd7c066a-5 eNIdhK bxepIQ']")
	private List<WebElement> selectSizeCheckboxInStock;
	public List<WebElement> getselectSizeCheckboxInStock() {
		return selectSizeCheckboxInStock;
	}
	@FindBy(xpath="//button[@data-testid='button' and contains(text(),'Add to bag')]")
	private WebElement AddToCartButton;
	public WebElement getAddToCartButton() {
		return AddToCartButton;
	}
	
	@FindBy(xpath="//button[@data-testid='button' and contains(text(),'Go to bag')]")
	private WebElement GoToCartButton;
	public WebElement getGoToCartButton() {
		return GoToCartButton;
	}
	
}
