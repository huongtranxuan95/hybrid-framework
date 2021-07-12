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
	public RegisterPageObject clickToCreatAnAccountButton() {
		waitElementClickable(driver, ByLocator.XPATH, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON_XPATH);
		clickToElement(driver, ByLocator.XPATH, LoginPageUI.CREATE_AN_ACCOUNT_BUTTON_XPATH);
		return PageGeneratorManager.getRegisterPage(driver);
	}
	public void inputPasswordTextbox(String password) {
		waitElementVisible(driver, ByLocator.ID, LoginPageUI.PASSWORD_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID, LoginPageUI.PASSWORD_TEXTBOX_ID, password);
		
	}
	public void inputEmailTextbox(String email) {
		waitElementVisible(driver, ByLocator.ID, LoginPageUI.EMAIL_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID, LoginPageUI.EMAIL_TEXTBOX_ID, email);
		
	}
	public MyDashBroadPageObject clickToLoginButton() {
		waitElementClickable(driver, ByLocator.XPATH, LoginPageUI.LOGIN_BUTTON_XPATH);
		clickToElement(driver,  ByLocator.XPATH, LoginPageUI.LOGIN_BUTTON_XPATH);
		return PageGeneratorManager.getMyDashBoardPage(driver);
	}
	
}
