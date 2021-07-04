package come.liveguru.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Register_To_System_Repeat_Yourself {
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
		
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
		
		firstName = "name";
		lastname = "nguyen";
		email = "namnguyen"+randomInt() +"@gmail.com";
		password = "123456";
		confirmPass = password;
		
	}
	@BeforeMethod
	public void beforeMethod() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		sleepSeconds(1);
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		sleepSeconds(1);
		
	}
	
	@Test
	public void Register_01_Empty_Data() {
		driver.findElement(registerButtonId).click();
		//sleepSeconds(1);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-firstname']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-lastname']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email_address']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-password']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-confirmation']")).getText(), "This is a required field.");
		
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		driver.findElement(firstnameId).sendKeys(firstName);
		driver.findElement(lasttnameId).sendKeys(lastname);
		driver.findElement(emailId).sendKeys("namnguyen@123.123");
		driver.findElement(passwordId).sendKeys(password);
		driver.findElement(registerButtonId).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email_address']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
//
	@Test
	public void Register_03_Password_Less_Than_Six_Character() {
		driver.findElement(firstnameId).sendKeys(firstName);
		driver.findElement(lasttnameId).sendKeys(lastname);
		driver.findElement(emailId).sendKeys(email);
		driver.findElement(passwordId).sendKeys("123");
		
		driver.findElement(registerButtonId).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-password']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void Register_04_ConfirmPassword_Not_Matching_Password() {
		driver.findElement(firstnameId).sendKeys(firstName);
		driver.findElement(lasttnameId).sendKeys(lastname);
		driver.findElement(emailId).sendKeys(email);
		driver.findElement(passwordId).sendKeys(password);
		driver.findElement(confirmPassId).sendKeys("654321");
		
		driver.findElement(registerButtonId).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-cpassword-confirmation']")).getText(), "Please make sure your passwords match.");
		
	}

	@Test
	public void Register_05_Valid() {
		driver.findElement(firstnameId).sendKeys(firstName);
		driver.findElement(lasttnameId).sendKeys(lastname);
		driver.findElement(emailId).sendKeys(email);
		driver.findElement(passwordId).sendKeys(password);
		driver.findElement(confirmPassId).sendKeys(password);
		
		driver.findElement(registerButtonId).click();
		
		sleepSeconds(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'My Dashboard')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]")).isDisplayed());
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
	}

	
}
