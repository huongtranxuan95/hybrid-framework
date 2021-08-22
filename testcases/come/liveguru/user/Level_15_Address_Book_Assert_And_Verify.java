package come.liveguru.user;

import org.testng.annotations.Test;
import org.w3c.dom.NodeList;

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
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;

//import java.util.Random;
//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_15_Address_Book_Assert_And_Verify extends AbstractTest {
	JavascriptExecutor js;
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
	String userDir = System.getProperty("user.dir");
	String url;
	
	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeClass(String browserName, String appUrl) {
		driverManager = DriverFactory.getBrowserDriver(browserName);
		
		driver = driverManager.getDriver("https://www.walmart.com/account/signup?tid=0&returnUrl=%2F");
		url = appUrl;
		firstName = "name";
		lastname = "nguyen";
		email = "namnguyen" + randomInt() + "@gmail.com";
		password = "123456";
		confirmPass = password;
		homePage = PageGeneratorManager.getHomePage(driver);
		

	}

	//@Test
	public void TC_01_Use_Hard_Assert() {
		driver.get("http://live.demoguru99.com/");
		// homePage = new HomePageObject(driver);
		homePage = PageGeneratorManager.getHomePage(driver);
		email = "namnguyen" + randomInt() + "@gmail.com";
		loginPage = homePage.clickToMyAccountLink();
		registerPage = loginPage.clickToCreatAnAccountButton();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		mydashbroadPage = registerPage.clickToRegisterButton();

		Assert.assertTrue(mydashbroadPage.isWelcomeMessageSuccessful());
		homePage = mydashbroadPage.clickYoLogoutLink();

		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputEmailTextbox(email);
		loginPage.inputPasswordTextbox(password);

		mydashbroadPage = loginPage.clickToLoginButton();

		Assert.assertFalse(mydashbroadPage.ismyDashBroadPageHeaderDisplayed());
		
		
		myAddressBook = mydashbroadPage.clickToAddressBookLink();

		myAddressBook.inputToTelephoneTextbox("0818386687");
		myAddressBook.inputToStreetAddressTextbox("186 Le Lai");
		myAddressBook.inputToCityTextbox("Ha Noi");
		myAddressBook.inputToZipTextbox("10000");
		myAddressBook.selectValueCountry("Vietnam");
		myAddressBook.clictSaveAddressButton();
		Assert.assertFalse(myAddressBook.isAddAddressMessageSuccessful());
		
	}

	//@Test
	public void TC_02_Use_Verify() {
		homePage = PageGeneratorManager.getHomePage(driver);
		email = "namnguyen" + randomInt() + "@gmail.com";
		loginPage = homePage.clickToMyAccountLink();
		registerPage = loginPage.clickToCreatAnAccountButton();

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		mydashbroadPage = registerPage.clickToRegisterButton();

		verifyTrue(mydashbroadPage.isWelcomeMessageSuccessful());
		homePage = mydashbroadPage.clickYoLogoutLink();

		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputEmailTextbox(email);
		loginPage.inputPasswordTextbox(password);

		mydashbroadPage = loginPage.clickToLoginButton();

		verifyFalse(mydashbroadPage.ismyDashBroadPageHeaderDisplayed());

		myAddressBook = mydashbroadPage.clickToAddressBookLink();

		myAddressBook.inputToTelephoneTextbox("0818386687");
		myAddressBook.inputToStreetAddressTextbox("186 Le Lai");
		myAddressBook.inputToCityTextbox("Ha Noi");
		myAddressBook.inputToZipTextbox("10000");
		myAddressBook.selectValueCountry("Vietnam");
		myAddressBook.clictSaveAddressButton();
		verifyFalse(myAddressBook.isAddAddressMessageSuccessful());
	}
	
	@Test
	public void TC_01() {
		js = (JavascriptExecutor)driver;
		driver.get("https://demo-m2.bird.eu/admin/admin/index/index/key/d11585721945bddaf60cbfdf7c6bf56d6fc71fd21b013c5c6ea0efc79a4acad2/");
		driver.findElement(By.xpath("//span[text()='Sign in']/parent::button")).click();
		sleepSeconds(3);
		driver.findElement(By.xpath("//span[text()='Customers']")).click();
		
		sleepSeconds(2);
		driver.findElement(By.xpath("//span[text()='All Customers']")).click();
		sleepSeconds(3);
		
		driver.findElement(By.xpath("(//a[text()='Edit'])[4]")).click();
		sleepSeconds(3);
		
		driver.findElement(By.xpath("//span[text()='Account Information']")).click();
		
		sleepSeconds(3);
		
		System.out.println("Name: "+ js.executeScript("document.getElementsByClassName(\"admin__control-text\").namedItem(\"J57YC6H\").value"));
		
		
		
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
