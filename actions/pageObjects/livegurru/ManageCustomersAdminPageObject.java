package pageObjects.livegurru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.ByLocator;
import pageUIs.liveguru.ManageCustomersAdminPageUI;

public class ManageCustomersAdminPageObject extends AbstractPage{
	private WebDriver driver;

	public ManageCustomersAdminPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void inputToCustomerTableTextboxByColumnName(String nameColumn, String value) {
		//System.out.println("nameColumn: "+nameColumn);
		waitElementVisible(driver, ManageCustomersAdminPageUI.DYNAMIC_HEADER_NAME_LOCATOR_XPATH, nameColumn);
		String indexColumnByName = String.valueOf(countElementNumber(driver, ManageCustomersAdminPageUI.DYNAMIC_HEADER_NAME_LOCATOR_XPATH, nameColumn) +1);
		//System.out.println("index: "+indexColumnByName);
		waitElementVisible(driver, ManageCustomersAdminPageUI.DYNAMIC_TEXTBOX_LOCATOR_BY_INDEX_COLUMN_XPATH, indexColumnByName);
		sendKeysToElement(driver, ManageCustomersAdminPageUI.DYNAMIC_TEXTBOX_LOCATOR_BY_INDEX_COLUMN_XPATH, value, indexColumnByName);
	}

	public void closePopup() {
		waitElementClickable(driver, ByLocator.XPATH, ManageCustomersAdminPageUI.CLOSE_POPUP_BUTTON_XPATH);
		clickToElement(driver, ByLocator.XPATH, ManageCustomersAdminPageUI.CLOSE_POPUP_BUTTON_XPATH);
		
	}

	public void clickSearchButton() {
		waitElementClickable(driver, ByLocator.XPATH, ManageCustomersAdminPageUI.SEARCH_BUTTON_XPATH);
		clickToElement(driver, ByLocator.XPATH, ManageCustomersAdminPageUI.SEARCH_BUTTON_XPATH);
		
	}

	public boolean isValueDisplayedAtColumnNameByRowIndex(String columnName	, String row, String value) {
		waitElementVisible(driver, ManageCustomersAdminPageUI.DYNAMIC_HEADER_NAME_LOCATOR_XPATH, columnName);
		String indexColumnByName = String.valueOf(countElementNumber(driver, ManageCustomersAdminPageUI.DYNAMIC_HEADER_NAME_LOCATOR_XPATH, columnName) +1);
		waitElementVisible(driver, ManageCustomersAdminPageUI.DYNAMIC_VALIDATE_INFORMATION,row, indexColumnByName,value);
		return isElementDisplayed(driver, ManageCustomersAdminPageUI.DYNAMIC_VALIDATE_INFORMATION, row, indexColumnByName, value);

	}
	
	
}
