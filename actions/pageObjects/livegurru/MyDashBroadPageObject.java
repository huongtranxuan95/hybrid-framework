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

	public void clickToAddressBookLink() {
		waitElementVisible(driver, ByLocator.XPATH, MyDashBroadPageUI.ADDRESS_BOOK_LINK_XPATH);
		clickToElement(driver, ByLocator.XPATH, MyDashBroadPageUI.ADDRESS_BOOK_LINK_XPATH);	
	}

	
}
