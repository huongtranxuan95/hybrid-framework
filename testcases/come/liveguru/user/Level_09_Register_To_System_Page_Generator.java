package come.liveguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.AbstractTest;
import commons.ByLocator;
import driverFactory.DriverFactory;
import driverFactory.DriverManager;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_09_Register_To_System_Page_Generator extends AbstractTest {
	private WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MyDashBroadPageObject mydashbroadPage;
	//
	String firstName = "", lastname = "", email = "", password = "", confirmPass = "";
	private DriverManager driverManager;
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driverManager = DriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();

		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		firstName = "name";
		lastname = "nguyen";
		email = "namnguyen" + randomInt() + "@gmail.com";
		password = "123456";
		confirmPass = password;

		homePage = new HomePageObject(driver);

	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickToMyAccountLink();
		//clickToElement(driver,ByLocator.XPATH, "//div[@class='footer']//a[text()='My Account']");
		sleepSeconds(1);

		loginPage = new LoginPageObject(driver);
		loginPage.clickToCreatAnAccountButton();
		registerPage = new RegisterPageObject(driver);
		sleepSeconds(1);

	}

	@Test
	public void Register_01_Empty_Data() {

		// sleepSeconds(1);
		registerPage.clickToRegisterButton();
		//clickToElement(driver, ByLocator.XPATH, "//button[@title='Register']");sleepSeconds(1);

		Assert.assertEquals(registerPage.getRequireErrorMessageAtFirstnameTextbox(), "This is a required field.");
		Assert.assertEquals(registerPage.getRequireErrorMessageAtLastnameTextbox(), "This is a required field.");
		Assert.assertEquals(registerPage.getRequireErrorMessageAtEmailTextbox(), "This is a required field.");
		Assert.assertEquals(registerPage.getRequireErrorMessageAtPasswordTextbox(), "This is a required field.");
		Assert.assertEquals(registerPage.getRequireErrorMessageAtConfirmTextbox(), "This is a required field.");

	}

	@Test
	public void Register_02_Invalid_Email() {
		registerPage.inputToEmailTextbox("namnguyen@123.123");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getInvalidErrorMessageAtEmailTextbox(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Register_03_Password_Less_Than_Six_Character() {

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox("123");
		registerPage.clickToRegisterButton();

		Assert.assertEquals(
				registerPage.getInvalidErrorMessageAtPasswordTextbox(),
				"Please enter 6 or more characters without leading or trailing spaces.");
		// Assert.assertEquals(getElementText(driver, locatorElement("XPATH",
		// "//div[@id='advice-validate-password-password']")), "Please enter 6 or more
		// characters without leading or trailing spaces.");
	}

	@Test
	public void Register_04_ConfirmPassword_Not_Matching_Password() {

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox("123123");
		registerPage.clickToRegisterButton();

		
		Assert.assertEquals(registerPage.getInvalidErrorMessageAtConfirmTextbox(), "Please make sure your passwords match.");
		
		
	}

	@Test
	public void Register_05_Valid() {

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		registerPage.clickToRegisterButton();
		
		mydashbroadPage = new MyDashBroadPageObject(driver);
		Assert.assertTrue(mydashbroadPage.isWelcomeMessageSuccessful());

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
