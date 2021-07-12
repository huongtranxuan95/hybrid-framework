package pageObjects.livegurru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.MyDashBroadPageUI;

public class MyDashBroadPageObject extends AbstractPage{
	WebDriver driver;
	
	public MyDashBroadPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean isMyDashbroadDisplayed() {
		//waitElementVisible(driver, ByLocator.ID, MyDashBroadPageUI.);
		return false;
	}
	

	public boolean isWelcomeMessageSuccessful() {
		waitElementVisible(driver, ByLocator.XPATH, MyDashBroadPageUI.WELCOME_MSG_SUCCESSFUL_XPATH);
		return isElementDisplayed(driver, ByLocator.XPATH, MyDashBroadPageUI.WELCOME_MSG_SUCCESSFUL_XPATH);
	}

	public AddressBookPageObject clickToAddressBookLink() {
		waitElementVisible(driver, ByLocator.XPATH, MyDashBroadPageUI.ADDRESS_BOOK_LINK_XPATH);
		clickToElement(driver, ByLocator.XPATH, MyDashBroadPageUI.ADDRESS_BOOK_LINK_XPATH);	
		return PageGeneratorManager.getAddressBookPage(driver);
	}

	public HomePageObject clickYoLogoutLink() {
		waitElementClickable(driver, ByLocator.XPATH, MyDashBroadPageUI.ACCOUNT_LINK_HEADER_XPATH);
		clickToElement(driver, ByLocator.XPATH, MyDashBroadPageUI.ACCOUNT_LINK_HEADER_XPATH);
		
		waitElementClickable(driver, ByLocator.XPATH, MyDashBroadPageUI.LOGOUT_LINK_HEADER_XPATH);
		clickToElementByJS(driver, ByLocator.XPATH, MyDashBroadPageUI.LOGOUT_LINK_HEADER_XPATH);
		return PageGeneratorManager.getHomePage(driver);
		
	}

	public boolean ismyDashBroadPageHeaderDisplayed() {
		// TODO Auto-generated method stub
		waitElementVisible(driver, ByLocator.XPATH, MyDashBroadPageUI.MY_DASHBROAD_HEADER_XPATH);
		return isElementDisplayed(driver, ByLocator.XPATH, MyDashBroadPageUI.MY_DASHBROAD_HEADER_XPATH);
	}

	
}
