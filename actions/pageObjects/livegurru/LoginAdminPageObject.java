package pageObjects.livegurru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.LoginAdminPageUI;

public class LoginAdminPageObject extends AbstractPage{
	WebDriver driver;
	
	public LoginAdminPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputUsernameTextbox(String username) {
		waitElementVisible(driver, ByLocator.ID, LoginAdminPageUI.USERNAME_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID,LoginAdminPageUI.USERNAME_TEXTBOX_ID, username);
	}

	public void inputPasswordTextbox(String password) {
		waitElementVisible(driver, ByLocator.ID, LoginAdminPageUI.PASSWORD_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID,LoginAdminPageUI.PASSWORD_TEXTBOX_ID, password);
		
	}

	public void clickToLoginButton() {
		waitElementClickable(driver, ByLocator.XPATH, LoginAdminPageUI.LOGIN_BUTTON_XPATH);
		clickToElement(driver, ByLocator.XPATH, LoginAdminPageUI.LOGIN_BUTTON_XPATH);
		
	}
	
	
}
