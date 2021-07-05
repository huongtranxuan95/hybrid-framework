package pageObjects.livegurru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.HomePageUI;

public class HomePageObject extends AbstractPage{
	WebDriver driver;
	public void clickToMyAccountLink() {
		// TODO Auto-generated method stub
		clickToElement(driver,locatorElement(ByLocator.XPATH, HomePageUI.MY_ACCOUNT_LINK_XPATH));
	}
	// chứa những actions của từng page
	// 7 Sự kiện 
}
