package pageObjects.livegurru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.HomePageUI;

public class HomePageObject extends AbstractPage{
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	public HomePageObject(WebDriver driver, WebDriver implicitWait) {
		super();
		this.driver = driver;
	}

	public LoginPageObject clickToMyAccountLink() {
		waitElementClickable(driver, ByLocator.XPATH, HomePageUI.MY_ACCOUNT_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, HomePageUI.MY_ACCOUNT_LINK_XPATH);
		return PageGeneratorManager.getLoginPage(driver);
	}
	// chứa những actions của từng page
	// 7 Sự kiện 
}
