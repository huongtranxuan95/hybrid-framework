package pageObjects.livegurru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitElementClickable(driver, ByLocator.XPATH, RegisterPageUI.REGISTER_BUTTON_XPATH);
		clickToElement(driver, ByLocator.XPATH, RegisterPageUI.REGISTER_BUTTON_XPATH);
	}

	public String getRequireErrorMessageAtFirstnameTextbox() {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_FIRSTNAME_TEXTBOX_ID);	
		return getElementText(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_FIRSTNAME_TEXTBOX_ID);
	}

	public String getRequireErrorMessageAtLastnameTextbox() {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_LASTNAME_TEXTBOX_ID);
		return getElementText(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_LASTNAME_TEXTBOX_ID);
	}

	public String getRequireErrorMessageAtEmailTextbox() {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_EMAIL_TEXTBOX_ID);
		return getElementText(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_EMAIL_TEXTBOX_ID);
	}

	public String getRequireErrorMessageAtPasswordTextbox() {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_PASSWORD_TEXTBOX_ID);	
		return  getElementText(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_PASSWORD_TEXTBOX_ID);
	}

	public String getRequireErrorMessageAtConfirmTextbox() {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_CONFIRMATION_TEXTBOX_ID);
		return getElementText(driver, ByLocator.ID, RegisterPageUI.REQUIRE_ERROR_MSG_CONFIRMATION_TEXTBOX_ID);
	}

	public void inputToEmailTextbox(String email) {
		
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.EMAIL_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID, RegisterPageUI.EMAIL_TEXTBOX_ID, email);
	}

	public String getInvalidErrorMessageAtEmailTextbox() {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.INVALID_ERROR_MSG_EMAIL_TEXTBOX_ID);
		return getElementText(driver, ByLocator.ID, RegisterPageUI.INVALID_ERROR_MSG_EMAIL_TEXTBOX_ID);
	}

	public void inputToFirstnameTextbox(String firstName) {

		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.FIRSTNAME_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID, RegisterPageUI.FIRSTNAME_TEXTBOX_ID, firstName);

	}

	public void inputToLastnameTextbox(String lastname) {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.LASTNAME_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID, RegisterPageUI.LASTNAME_TEXTBOX_ID, lastname);

	}

	public void inputToPasswordTextbox(String password) {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.PASSWORD_ID);
		sendKeysToElement(driver, ByLocator.ID, RegisterPageUI.PASSWORD_ID, password);

	}

	public void inputToConfirmPassTextbox(String cPassword) {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.CONFIRMATION_ID);
		sendKeysToElement(driver, ByLocator.ID, RegisterPageUI.CONFIRMATION_ID, cPassword);
	}


	public String getInvalidErrorMessageAtPasswordTextbox() {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.INVALID_ERROR_MSG_PASSWORD_TEXTBOX_ID);
		return getElementText(driver, ByLocator.ID, RegisterPageUI.INVALID_ERROR_MSG_PASSWORD_TEXTBOX_ID);
	}

	public String getInvalidErrorMessageAtConfirmTextbox() {
		waitElementVisible(driver, ByLocator.ID, RegisterPageUI.INVALID_ERROR_MSG_CONFIMATION_TEXTBOX_ID);
		return getElementText(driver, ByLocator.ID, RegisterPageUI.INVALID_ERROR_MSG_CONFIMATION_TEXTBOX_ID);
	}

}
