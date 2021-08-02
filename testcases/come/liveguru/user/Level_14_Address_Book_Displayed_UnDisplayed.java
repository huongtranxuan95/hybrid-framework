package come.liveguru.user;

import org.testng.annotations.Test;

//import commons.AbstractPage;
import commons.AbstractTest;
import driverFactory.DriverFactory;
import driverFactory.DriverManager;
import pageObjects.livegurru.AboutUsPageObject;
//import commons.Browser;
//import commons.ByLocator;
import pageObjects.livegurru.AddressBookPageObject;
import pageObjects.livegurru.AdvanceSearcgPageObject;
import pageObjects.livegurru.CustomerServicePageObject;
import pageObjects.livegurru.HomePageObject;
import pageObjects.livegurru.LoginPageObject;
import pageObjects.livegurru.MyDashBroadPageObject;
import pageObjects.livegurru.PageGeneratorManager;
import pageObjects.livegurru.RegisterPageObject;
import pageObjects.livegurru.SearchTermPageObject;

import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

//import java.util.Random;
//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_14_Address_Book_Displayed_UnDisplayed extends AbstractTest {
	private WebDriver driver;
	private DriverManager driverManager;
	HomePageObject homePage;	
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driverManager = DriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver(appUrl);
		
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}
	
	@Test
	public void TC_01_Verify_Element_Displayed_UnDisplayed() {
		Assert.assertTrue(homePage.isMyAccountLinkFooterDisplayed());
		Assert.assertTrue(homePage.isMyAccountLinkHeaderUnDisplayed());
		
		Assert.assertTrue(homePage.isRequireMessUnDisplayed());
		homePage.clickToSubscribeButton();
		Assert.assertTrue(homePage.isRequireMessDisplayed());
		
		
		
	}
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
