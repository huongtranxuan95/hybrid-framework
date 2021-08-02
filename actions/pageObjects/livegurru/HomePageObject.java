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
	 
	public boolean isMyAccountLinkFooterDisplayed() {
		waitElementVisible(driver,ByLocator.XPATH,HomePageUI.MY_ACCOUNT_LINK_XPATH); 
		return isElementDisplayed(driver,ByLocator.XPATH,HomePageUI.MY_ACCOUNT_LINK_XPATH); 
	}
	public boolean isMyAccountLinkHeaderUnDisplayed() {
		return isElementUnDisplayed(driver, HomePageUI.MY_ACCOUNT_HEADER_XPATH);
	}
	public boolean isRequireMessUnDisplayed() {
		return isElementUnDisplayed(driver, HomePageUI.REQUIRE_MESS_XPATH);
	}
	public void clickToSubscribeButton() {
		waitElementClickable(driver, ByLocator.XPATH, HomePageUI.SUBSCRIBE_BUTTON_XPATH);
		clickToElement(driver, ByLocator.XPATH, HomePageUI.SUBSCRIBE_BUTTON_XPATH);
	}
	public boolean isRequireMessDisplayed() {
		waitElementVisible(driver,ByLocator.XPATH,HomePageUI.REQUIRE_MESS_XPATH); 
		return isElementDisplayed(driver,ByLocator.XPATH,HomePageUI.MY_ACCOUNT_LINK_XPATH);
	}
}
