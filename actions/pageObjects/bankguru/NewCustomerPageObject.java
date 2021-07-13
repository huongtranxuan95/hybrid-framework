package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage{
	private WebDriver driver;

	public NewCustomerPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
}
