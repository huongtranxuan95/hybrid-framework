package come.liveguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.Browser;
import commons.ByLocator;
import pageObjects.livegurru.AddressBookPageObject;
import pageObjects.livegurru.HomePageObject;
import pageObjects.livegurru.LoginPageObject;
import pageObjects.livegurru.MyDashBroadPageObject;
import pageObjects.livegurru.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_04_Address_Book_Mutiple_Browser extends AbstractTest {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MyDashBroadPageObject mydashbroadPage;
	AddressBookPageObject myAddressBook;
	String firstName = "", lastname = "", email = "", password = "", confirmPass = "";
	String userDir= System.getProperty("user.dir");
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
//		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
//		
//		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
//		
//		 System.setProperty("webdriver.edge.driver",".\\browserDrivers\\msedgedriver.exe");
//		 driver= new EdgeDriver();
		String url = "http://live.demoguru99.com/";
		driver = getDriverBroswer(browserName, url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		firstName = "name";
		lastname = "nguyen";
		email = "namnguyen" + randomInt() + "@gmail.com";
		password = "123456";
		confirmPass = password;

		homePage = new HomePageObject(driver);
		homePage.clickToMyAccountLink();

		loginPage = new LoginPageObject(driver);
		loginPage.clickToCreatAnAccountButton();
		registerPage = new RegisterPageObject(driver);
		
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		registerPage.clickToRegisterButton();

		mydashbroadPage = new MyDashBroadPageObject(driver);
		
	}

	

	@Test
	public void Address_01_Manage_Address_Book() {
		mydashbroadPage.clickToAddressBookLink();
		
		myAddressBook = new AddressBookPageObject(driver);
		myAddressBook.inputToTelephoneTextbox("0818385687");
		myAddressBook.inputToStreetAddressTextbox("156 Le Loi");
		myAddressBook.inputToCityTextbox("Ha Noi");
		myAddressBook.inputToZipTextbox("10000");
		myAddressBook.selectValueCountry("Vietnam");
		myAddressBook.clictSaveAddressButton();
		Assert.assertTrue(myAddressBook.isAddAddressMessageSuccessful());
	}

	
	public int randomInt() {
		Random ran = new Random();
		return ran.nextInt(10000) + 1;
	}

	public void sleepSeconds(long timeout) {
		try {
			Thread.sleep(1000 * timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
