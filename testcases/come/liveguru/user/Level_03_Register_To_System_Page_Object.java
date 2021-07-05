package come.liveguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;
import commons.ByLocator;
import pageObjects.livegurru.HomePageObject;
import pageObjects.livegurru.LoginPageObject;
import pageObjects.livegurru.MyDashBroadPageObject;
import pageObjects.livegurru.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Register_To_System_Page_Object extends AbstractPage {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	MyDashBroadPageObject mydashbroadPage;

	String firstName = "", lastname = "", email = "", password = "", confirmPass = "";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		firstName = "name";
		lastname = "nguyen";
		email = "namnguyen" + randomInt() + "@gmail.com";
		password = "123456";
		confirmPass = password;

		homePage = new HomePageObject();

	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickToMyAccountLink();
		clickToElement(driver, locatorElement(ByLocator.XPATH, "//div[@class='footer']//a[text()='My Account']"));
		sleepSeconds(1);

		loginPage = new LoginPageObject();
		loginPage.clickToCreatAnAccountButton();
		clickToElement(driver, locatorElement(ByLocator.XPATH, "//span[contains(text(),'Create an Account')]"));

		registerPage = new RegisterPageObject();
		sleepSeconds(1);

	}

	@Test
	public void Register_01_Empty_Data() {

		// sleepSeconds(1);
		registerPage.clickToRegisterButton();
		clickToElement(driver, locatorElement(ByLocator.XPATH, "//button[@title='Register']"));
		sleepSeconds(1);

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
				registerPage.getInvalidErrorMessageAtPasswordTextbox(
						locatorElement(ByLocator.XPATH, "//div[@id='advice-validate-password-password']")),
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

		
		Assert.assertEquals(registerPage.getInvalidErrorMessageAtConfirmTextbox(locatorElement(ByLocator.XPATH, "//div[@id='advice-validate-cpassword-confirmation']")), "Please make sure your passwords match.");
		
		
	}

	@Test
	public void Register_05_Valid() {

		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastname);
		registerPage.inputToEmailTextbox(email);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPassTextbox(password);
		registerPage.clickToRegisterButton();
		
		mydashbroadPage = new MyDashBroadPageObject();
		Assert.assertTrue(mydashbroadPage.isMyDashbroadDisplayed("//h1[contains(text(),'My Dashboard')]"));
		Assert.assertTrue(mydashbroadPage.isWelcomeMessageSuccessful("//span[contains(text(),'Thank you for registering with Main Website Store.')]"));

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
