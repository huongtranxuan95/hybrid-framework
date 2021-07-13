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

public class Level_10_Address_Book_Switch_Page extends AbstractTest {
	private WebDriver driver;
	private DriverManager driverManager;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MyDashBroadPageObject mydashbroadPage;
	AddressBookPageObject myAddressBook;
	AboutUsPageObject aboutUsPage;
	SearchTermPageObject searchTermPage;
	AdvanceSearcgPageObject advanceSearchPage;
	CustomerServicePageObject customerServicePage;
	
	String firstName = "", lastname = "", email = "", password = "", confirmPass = "";
	String userDir= System.getProperty("user.dir");
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driverManager = DriverFactory.getBrowserDriver(browserName);
		//driver = getDriverBroswer(browserName, appUrl);
		driver = driverManager.getDriver(appUrl);
		
		firstName = "name";
		lastname = "nguyen";
		email = "namnguyen" + randomInt() + "@gmail.com";
		password = "123456";
		confirmPass = password;

		//homePage = new HomePageObject(driver);
		homePage = PageGeneratorManager.getHomePage(driver);
		
	}
	
	@Test
	public void TC_01_Register() {
		loginPage=homePage.clickToMyAccountLink();
		registerPage=loginPage.clickToCreatAnAccountButton();
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		mydashbroadPage=registerPage.clickToRegisterButton();

		Assert.assertTrue(mydashbroadPage.isWelcomeMessageSuccessful());
		homePage = mydashbroadPage.clickYoLogoutLink();
	}
	
	@Test
	public void TC_02_Login() {
		loginPage=homePage.clickToMyAccountLink();
		
		loginPage.inputEmailTextbox(email);
		loginPage.inputPasswordTextbox(password);
		
		mydashbroadPage=loginPage.clickToLoginButton();
		
		Assert.assertTrue(mydashbroadPage.ismyDashBroadPageHeaderDisplayed());
	}

	@Test
	public void TC_03_Manage_Address_Book() {
		myAddressBook =mydashbroadPage.clickToAddressBookLink();
		
		myAddressBook.inputToTelephoneTextbox("0818386687");
		myAddressBook.inputToStreetAddressTextbox("186 Le Lai");
		myAddressBook.inputToCityTextbox("Ha Noi");
		myAddressBook.inputToZipTextbox("10000");
		myAddressBook.selectValueCountry("Vietnam");
		myAddressBook.clictSaveAddressButton();
		Assert.assertTrue(myAddressBook.isAddAddressMessageSuccessful());
	}
	@Test
	public void TC_04_Navigate_Page_In_Footer() {
		aboutUsPage = myAddressBook.openAboutUsPage( driver);
		
		searchTermPage = aboutUsPage.openSearchTermPage(driver);
		
		customerServicePage = searchTermPage.openCustomerServicePage(driver);
		
		advanceSearchPage = customerServicePage.openAdvanceSearchPage(driver);
		
		aboutUsPage = advanceSearchPage.openAboutUsPage(driver);
		
		customerServicePage = aboutUsPage.openCustomerServicePage(driver);
		
	}

	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
