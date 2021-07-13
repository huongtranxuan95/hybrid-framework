package pageObjects.livegurru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static MyDashBroadPageObject getMyDashBoardPage(WebDriver driver) {
		return new MyDashBroadPageObject(driver);
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static AddressBookPageObject getAddressBookPage(WebDriver driver) {
		return new AddressBookPageObject(driver);
	}
	
	public static SearchTermPageObject getSearchTermPage(WebDriver driver) {
		return new SearchTermPageObject(driver);
	}
	public static CustomerServicePageObject getCustomerServicePage(WebDriver driver) {
		return new CustomerServicePageObject(driver);
	}
	public static AboutUsPageObject getAboutUsPage(WebDriver driver) {
		return new AboutUsPageObject(driver);
	}
	public static AdvanceSearcgPageObject getAdvanceSearcgPage(WebDriver driver) {
		return new AdvanceSearcgPageObject(driver);
	}
}
