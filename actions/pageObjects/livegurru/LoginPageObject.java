package pageObjects.livegurru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.LoginPageUI;

public class LoginPageObject extends AbstractPage{
	WebDriver driver; 
	public LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	public void clickToCreatAnAccountButton() {
		// TODO Auto-generated method stub
		waitElementClickable(driver, ByLocator.XPATH, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON_XPATH);
		clickToElement(driver, ByLocator.XPATH, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON_XPATH);
	}
	
}
