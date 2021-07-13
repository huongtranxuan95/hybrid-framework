package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class NewAccountPageObject extends AbstractPage{
	private WebDriver driver;

	public NewAccountPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
}
