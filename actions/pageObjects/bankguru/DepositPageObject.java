package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class DepositPageObject extends AbstractPage{
	private WebDriver driver;

	public DepositPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
}
