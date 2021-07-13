package pageObjects.bankguru;

import org.openqa.selenium.WebDriver;

public class PageGenerationManager {
	public static BankHomePageObject getBankHomePage(WebDriver driver) {
		return new BankHomePageObject(driver);
	}
	
	public static BankManagerPageObject getBankManagerHomePage(WebDriver driver) {
		return new BankManagerPageObject(driver);
	}
}
