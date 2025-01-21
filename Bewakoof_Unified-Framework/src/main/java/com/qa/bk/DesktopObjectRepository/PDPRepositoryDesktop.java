/**
 * 
 */
package com.qa.bk.DesktopObjectRepository;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Praveen B
 *
 */
public class PDPRepositoryDesktop {
	
	WebDriver driver;

	public PDPRepositoryDesktop(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//@FindBy(xpath="//div[@class='sc-9d458f08-14 fIRGUP']/label[@class='sc-dd7c066a-2 sc-dd7c066a-5 eNIdhK igtXFB' or @class='sc-dd7c066a-2 sc-dd7c066a-5 eNIdhK bxepIQ']")
	@FindBy(xpath="//div[@class='sc-9d458f08-14 fIRGUP']/label[@class='sc-fc819127-2 sc-fc819127-5 bcHcti ckKNWd' or @class='sc-fc819127-2 sc-fc819127-5 bcHcti fIPNFk']")
	private List<WebElement> selectSizeCheckboxInStock;
	public List<WebElement> getselectSizeCheckboxInStock() {
		return selectSizeCheckboxInStock;
	}

	@FindBy(xpath="//button[@data-testid='button' and contains(text(),'add to bag')]")
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
