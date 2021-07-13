package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class BankManagerPageObject extends AbstractPage{
	private WebDriver driver;

	public BankManagerPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
}
