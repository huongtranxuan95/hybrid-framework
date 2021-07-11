package pageFactory.liveguru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import commons.ByLocator;

public class AbstractPage {
	//waitElemetClickAble
	//waitElementVisible
	//sendKeysToElement
	//getElementText
	//clickToElement
	private WebDriverWait explicitWait;
	private long timeout = 30;
	
	public void waitElementClickable(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitElementVisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, timeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	
	public void sendKeysToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public boolean isElementDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}
}
