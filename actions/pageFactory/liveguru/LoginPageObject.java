package pageFactory.liveguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject extends AbstractPage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//@CacheLookup
	@FindBy(how = How.XPATH, using = "//a[@title='Create an Account']")
	private WebElement createAnAccountButton;
	
	public void clickToCreatAnAccountButton() {
		waitElementClickable(driver, createAnAccountButton);
		clickToElement(driver, createAnAccountButton);
	}
	
}
