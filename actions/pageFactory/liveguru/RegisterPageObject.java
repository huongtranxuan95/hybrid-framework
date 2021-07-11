package pageFactory.liveguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.ByLocator;
import pageUIs.liveguru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.ID, using = "firstname")
	private WebElement firstnameTextbox;

	@FindBy(how = How.ID, using = "lastname")
	private WebElement lastnameTextbox;

	@FindBy(how = How.ID, using = "email_address")
	private WebElement emailTextbox;

	@FindBy(how = How.ID, using = "password")
	private WebElement passwordTextbox;

	@FindBy(how = How.ID, using = "confirmation")
	private WebElement confimationTextbox;

	@FindBy(how = How.XPATH, using = "//button[@title='Register']")
	private WebElement registerButton;

	@FindBy(how = How.ID, using = "advice-required-entry-firstname")
	private WebElement requireErrorMGSFirstnameTextbox;

	@FindBy(how = How.ID, using = "advice-required-entry-lastname")
	private WebElement requireErrorMGSLastnameTextbox;

	@FindBy(how = How.ID, using = "advice-required-entry-email_address")
	private WebElement requireErrorMGSEmailTextbox;

	@FindBy(how = How.ID, using = "advice-required-entry-password")
	private WebElement requireErrorMGSPassTextbox;

	@FindBy(how = How.ID, using = "advice-required-entry-confirmation")
	private WebElement requireErrorMGSConfirmPassTextbox;

	@FindBy(how = How.ID, using = "advice-validate-email-email_address")
	private WebElement invalidErrorMGSEmailTextbox;

	@FindBy(how = How.ID, using = "advice-validate-password-password")
	private WebElement invalidErrorMGSPassTextbox;

	@FindBy(how = How.ID, using = "advice-validate-cpassword-confirmation")
	private WebElement invalidErrorMGSConfirmPassTextbox;

	public void clickToRegisterButton() {
		waitElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getRequireErrorMessageAtFirstnameTextbox() {
		waitElementVisible(driver, requireErrorMGSFirstnameTextbox);
		return getElementText(driver, requireErrorMGSFirstnameTextbox);
	}

	public String getRequireErrorMessageAtLastnameTextbox() {
		waitElementVisible(driver, requireErrorMGSLastnameTextbox);
		return getElementText(driver, requireErrorMGSLastnameTextbox);
	}

	public String getRequireErrorMessageAtEmailTextbox() {
		waitElementVisible(driver, requireErrorMGSEmailTextbox);
		return getElementText(driver, requireErrorMGSEmailTextbox);
	}

	public String getRequireErrorMessageAtPasswordTextbox() {
		waitElementVisible(driver, requireErrorMGSPassTextbox);
		return getElementText(driver, requireErrorMGSPassTextbox);
	}

	public String getRequireErrorMessageAtConfirmTextbox() {
		waitElementVisible(driver, requireErrorMGSConfirmPassTextbox);
		return getElementText(driver, requireErrorMGSConfirmPassTextbox);
	}

	public void inputToEmailTextbox(String email) {

		waitElementVisible(driver, emailTextbox);
		sendKeysToElement(driver, emailTextbox, email);
	}
	
	public String getInvalidErrorMessageAtEmailTextbox() {
		waitElementVisible(driver, invalidErrorMGSEmailTextbox);
		return getElementText(driver, invalidErrorMGSEmailTextbox);
	}
	
	public void inputToFirstnameTextbox(String firstName) {

		waitElementVisible(driver, firstnameTextbox);
		sendKeysToElement(driver,firstnameTextbox , firstName);

	}
	
	public void inputToLastnameTextbox(String lastname) {
		waitElementVisible(driver, lastnameTextbox);
		sendKeysToElement(driver, lastnameTextbox, lastname);

	}
	
	public void inputToPasswordTextbox(String password) {
		waitElementVisible(driver, passwordTextbox);
		sendKeysToElement(driver, passwordTextbox, password);
	}
	
	public void inputToConfirmPassTextbox(String cPassword) {
		waitElementVisible(driver,confimationTextbox);
		sendKeysToElement(driver, confimationTextbox, cPassword);
	}

	public String getInvalidErrorMessageAtPasswordTextbox() {
		waitElementVisible(driver, invalidErrorMGSPassTextbox);
		return getElementText(driver,invalidErrorMGSPassTextbox);
	}

	public String getInvalidErrorMessageAtConfirmTextbox() {
		waitElementVisible(driver, confimationTextbox);
		return getElementText(driver, confimationTextbox);
	}
}
