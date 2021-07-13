package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class WithdrawalPageObject extends AbstractPage{
	private WebDriver driver;

	public WithdrawalPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
}
