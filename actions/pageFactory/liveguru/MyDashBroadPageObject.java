package pageFactory.liveguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;



public class MyDashBroadPageObject extends AbstractPage{
	private WebDriver driver;

	public MyDashBroadPageObject(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, MyDashBroadPageObject.class);
	}
	
	@FindBy(how = How.XPATH, using = "//span[text()='Thank you for registering with Main Website Store.']")
	private WebElement welcomeMSGSucessful;
	 
	@FindBy(how = How.XPATH, using = "//div[@class='welcome-msg']//strong")
	private WebElement helloMSGUser;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Change Password']//parent::p")
	private WebElement contactInfomation;
	
	@FindBy(how = How.XPATH, using = "//a[contains(.,'Address Book')]")
	private WebElement addressBookLink;
	
	public boolean isMyDashbroadDisplayed() {
		//waitElementVisible(driver);
		return false;
	}
	
	public boolean isWelcomeMessageSuccessful() {
		waitElementVisible(driver, welcomeMSGSucessful);
		return isElementDisplayed(driver, welcomeMSGSucessful);
	}
	
	public void clickToAddressBookLink() {
		waitElementVisible(driver, addressBookLink);
		clickToElement(driver, addressBookLink);	
	}
}
