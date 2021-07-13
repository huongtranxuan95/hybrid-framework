package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.bankguru.BankHomePageUI;

public class BankHomePageObject extends AbstractPage{
	private WebDriver driver;

	public BankHomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputPasswordTextbox(String pass) {
		waitElementVisible(driver, ByLocator.NAME, BankHomePageUI.PASSWORD_TEXTBOX_NAME);
		sendKeysToElement(driver, ByLocator.NAME, BankHomePageUI.PASSWORD_TEXTBOX_NAME, pass);
		
	}

	public void inputUserIDTextbox(String username) {
		waitElementVisible(driver, ByLocator.NAME, BankHomePageUI.USER_ID_TEXTBOX_NAME);
		sendKeysToElement(driver, ByLocator.NAME, BankHomePageUI.USER_ID_TEXTBOX_NAME, username);
		
	}

	public BankManagerPageObject clickToButtonLogin() {
		waitElementClickable(driver, ByLocator.NAME, BankHomePageUI.LOGIN_BUTTON_NAME);
		clickToElement(driver, ByLocator.NAME, BankHomePageUI.LOGIN_BUTTON_NAME);
		return PageGenerationManager.getBankManagerHomePage(driver);
		
	}
	
}
