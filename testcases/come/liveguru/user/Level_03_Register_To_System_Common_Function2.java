package come.liveguru.user;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Register_To_System_Common_Function2 extends AbstractPage{
	WebDriver driver;
	
	By firstnameId = By.id("firstname");
	By lasttnameId = By.id("lastname");
	By emailId = By.id("email_address");
	By passwordId = By.id("password");
	By confirmPassId = By.id("confirmation");
	By registerButtonId = By.xpath("//button[@title='Register']");
	
	String firstName = "", lastname="", email ="", password ="", confirmPass ="";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",".\\browserDrivers\\geckodriver.exe" );
		driver = new FirefoxDriver();
		
		openPageUrl(driver, "http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
		
		firstName = "name";
		lastname = "nguyen";
		email = "namnguyen"+randomInt() +"@gmail.com";
		password = "123456";
		confirmPass = password;
		
	}
	@BeforeMethod
	public void beforeMethod() {
		clickToElement(driver, locatorElement("XPATH", "//div[@class='footer']//a[text()='My Account']"));
		sleepSeconds(1);
		clickToElement(driver, locatorElement("XPATH", "//span[contains(text(),'Create an Account')]"));
		sleepSeconds(1);
		
	}
	
	@Test
	public void Register_01_Empty_Data() {

		//sleepSeconds(1);
		clickToElement(driver, locatorElement("XPATH", "//button[@title='Register']"));
		sleepSeconds(1);
		
		Assert.assertEquals(getElementText(driver, locatorElement("XPATH", "//div[@id='advice-required-entry-firstname']")), "This is a required field.");
		Assert.assertEquals(getElementText(driver, locatorElement("XPATH", "//div[@id='advice-required-entry-lastname']")), "This is a required field.");
		Assert.assertEquals(getElementText(driver, locatorElement("XPATH", "//div[@id='advice-required-entry-email_address']")), "This is a required field.");
		Assert.assertEquals(getElementText(driver, locatorElement("XPATH", "//div[@id='advice-required-entry-password']")), "This is a required field.");
		Assert.assertEquals(getElementText(driver, locatorElement("XPATH", "//div[@id='advice-required-entry-confirmation']")), "This is a required field.");
		
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		sendKeysToElement(driver, locatorElement("ID", "firstname"), firstName);
		sendKeysToElement(driver, locatorElement("ID", "lastname"), lastname);
		sendKeysToElement(driver, locatorElement("ID", "email_address"), "namnguyen@123.123");
		sendKeysToElement(driver, locatorElement("ID", "password"), password);
		sendKeysToElement(driver, locatorElement("ID", "confirmation"), password);
		clickToElement(driver, locatorElement("XPATH", "//button[@title='Register']"));
		
		Assert.assertEquals(getElementText(driver, locatorElement("XPATH", "//div[@id='advice-validate-email-email_address']")), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void Register_03_Password_Less_Than_Six_Character() {

		sendKeysToElement(driver, locatorElement("ID", "firstname"), firstName);
		sendKeysToElement(driver, locatorElement("ID", "lastname"), lastname);
		sendKeysToElement(driver, locatorElement("ID", "email_address"), email);
		sendKeysToElement(driver, locatorElement("ID", "password"), "123");
		sendKeysToElement(driver, locatorElement("ID", "confirmation"), "123");
		clickToElement(driver, locatorElement("XPATH", "//button[@title='Register']"));
		
		Assert.assertEquals(getElementText(driver, locatorElement("XPATH", "//div[@id='advice-validate-password-password']")), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Register_04_ConfirmPassword_Not_Matching_Password() {

		sendKeysToElement(driver, locatorElement("ID", "firstname"), firstName);
		sendKeysToElement(driver, locatorElement("ID", "lastname"), lastname);
		sendKeysToElement(driver, locatorElement("ID", "email_address"), email);
		sendKeysToElement(driver, locatorElement("ID", "password"), password);
		sendKeysToElement(driver, locatorElement("ID", "confirmation"), "123123");
		clickToElement(driver, locatorElement("XPATH", "//button[@title='Register']"));
		
		Assert.assertEquals(getElementText(driver, locatorElement("XPATH", "//div[@id='advice-validate-cpassword-confirmation']")), "Please make sure your passwords match.");
		
	}

	@Test
	public void Register_05_Valid() {

		sendKeysToElement(driver, locatorElement("ID", "firstname"), firstName);
		sendKeysToElement(driver, locatorElement("ID", "lastname"), lastname);
		sendKeysToElement(driver, locatorElement("ID", "email_address"), email);
		sendKeysToElement(driver, locatorElement("ID", "password"), password);
		sendKeysToElement(driver, locatorElement("ID", "confirmation"), password);
		clickToElement(driver, locatorElement("XPATH", "//button[@title='Register']"));
		sleepSeconds(1);
		
		Assert.assertTrue(isElementDisplayed(driver, locatorElement("XPATH", "//h1[contains(text(),'My Dashboard')]")));
		Assert.assertTrue(isElementDisplayed(driver, locatorElement("XPATH", "//span[contains(text(),'Thank you for registering with Main Website Store.')]")));
	}

	public int randomInt() {
		Random ran = new Random();
		return ran.nextInt(10000)+1;
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
