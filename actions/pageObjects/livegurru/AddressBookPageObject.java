package pageObjects.livegurru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.AddressPageUI;

public class AddressBookPageObject extends AbstractPage {
	private WebDriver driver;
	
	public AddressBookPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputToTelephoneTextbox(String telephone) {
		waitElementVisible(driver, ByLocator.ID, AddressPageUI.TELEPHONE_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID, AddressPageUI.TELEPHONE_TEXTBOX_ID, telephone);
	}

	public void inputToStreetAddressTextbox(String stressAdd) {
		waitElementVisible(driver, ByLocator.XPATH, AddressPageUI.STRESS_ADDRESS_TEXTBOX_XPATH);
		sendKeysToElement(driver, ByLocator.XPATH, AddressPageUI.STRESS_ADDRESS_TEXTBOX_XPATH, stressAdd);
	}

	public void inputToCityTextbox(String city) {
		waitElementVisible(driver, ByLocator.ID, AddressPageUI.CITY_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID, AddressPageUI.CITY_TEXTBOX_ID, city);
		
	}

	public void selectValueCountry(String country) {
		waitElementVisible(driver, ByLocator.ID, AddressPageUI.COUNTRY_DROPDOWN_ID);
		selectItemInDropdown(driver, ByLocator.ID, AddressPageUI.COUNTRY_DROPDOWN_ID, country);
	}

	public void inputToZipTextbox(String zipCode) {
		waitElementVisible(driver, ByLocator.ID, AddressPageUI.ZIPCODE_TEXTBOX_ID);
		sendKeysToElement(driver, ByLocator.ID, AddressPageUI.ZIPCODE_TEXTBOX_ID, zipCode);
		
	}

	public void clictSaveAddressButton() {
		waitElementVisible(driver, ByLocator.XPATH	, AddressPageUI.SAVE_ADDRESS_BUTTON_XPATH);
		clickToElement(driver, ByLocator.XPATH,  AddressPageUI.SAVE_ADDRESS_BUTTON_XPATH);
	}

	public boolean isAddAddressMessageSuccessful() {
		waitElementVisible(driver, ByLocator.XPATH, AddressPageUI.ADD_ADDRESS_MSG_SUCCESSFUL_XPATH);
		return isElementDisplayed(driver, ByLocator.XPATH, AddressPageUI.ADD_ADDRESS_MSG_SUCCESSFUL_XPATH);
	}
	

}
