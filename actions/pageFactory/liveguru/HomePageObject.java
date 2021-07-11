package pageFactory.liveguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.ByLocator;
import pageUIs.liveguru.HomePageUI;

public class HomePageObject extends AbstractPage{
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//@CacheLookup
	@FindBy(how = How.XPATH, using = "//div[@class='footer']//a[text()='My Account']")
	private WebElement myAccountLink;
	
	public void clickToMyAccountLink() {
		waitElementClickable(driver, myAccountLink);
		clickToElement(driver,myAccountLink);
	}
}
