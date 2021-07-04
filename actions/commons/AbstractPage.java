package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	private Alert alert;
	private WebElement element;
	private Select select;

	private WebDriverWait explicitWait;
	private JavascriptExecutor js;
	private List<WebElement> itemList;
	private Actions action;

	private long longTimeout = 30;
	private long shortTimeout = 10;

	// WebBrowser
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getTextAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendKeyToAlert(WebDriver driver, String key) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.sendKeys(key);
	}

	public void switchWindowById(WebDriver driver, String parentID) {
		Set<String> currentWindowHandle = driver.getWindowHandles();

		for (String childWindow : currentWindowHandle) {
			if (!childWindow.equals(parentID)) {

				driver.switchTo().window(childWindow);
				break;
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String title) {
		Set<String> runWindows = driver.getWindowHandles();
		for (String childWindow : runWindows) {
			driver.switchTo().window(childWindow);
			String titleCurrentWindow = driver.getTitle();
			if (titleCurrentWindow.equals(title)) {
				break;
			}
		}

	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentId) {
		Set<String> runWindows = driver.getWindowHandles();
		for (String child : runWindows) {
			if (!child.equals(parentId)) {
				driver.switchTo().window(child);
				driver.close();
			}
		}
		driver.switchTo().window(parentId);
	}

	// WebElement

	public WebElement find(WebDriver driver, By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> finds(WebDriver driver, By locator) {
		return driver.findElements(locator);
	}

	public void clickToElement(WebDriver driver, By locator) {
		find(driver, locator).click();
	}

	public void sendKeysToElement(WebDriver driver, By locator, String text) {
		element = find(driver, locator);
		element.clear();
		element.sendKeys(text);
	}

	public void selectItemInDropdown(WebDriver driver, By locator, String itemValue) {
		select = new Select(find(driver, locator));
		select.selectByVisibleText(itemValue);
	}

	public String getSelectedItemInDropdown(WebDriver driver, By locator) {
		select = new Select(find(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMutiple(WebDriver driver, By locator) {
		select = new Select(find(driver, locator));
		return select.isMultiple();

	}

	public void selectItemInCustomDropdownList(WebDriver driver, By parentLocator, By childLocator,
			String itemValue) {
		clickToElement(driver, parentLocator);
		sleepSeconds(1);
		explicitWait = new WebDriverWait(driver, longTimeout);

		explicitWait.until(ExpectedConditions.presenceOfElementLocated(childLocator));
		itemList = finds(driver, childLocator);
		for (WebElement webElement : itemList) {
			if (webElement.getText().equals(itemValue)) {
				js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", webElement);
				webElement.click();
				sleepSeconds(1);
				break;
			}
		}

	}

	public String getElementAttribute(WebDriver driver, By locator, String attributeName) {
		return find(driver, locator).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, By locator) {
		return find(driver, locator).getText();
	}

	public int countElementNumber(WebDriver driver, By locator) {
		return finds(driver, locator).size();
	}

	public boolean isElementDisplayed(WebDriver driver, By locator) {
		return find(driver, locator).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, By locator) {
		return find(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, By locator) {
		return find(driver, locator).isSelected();
	}

	public void checkToCheckbox(WebDriver driver, By locator) {
		element = find(driver, locator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void switchToFrameOrIframe(WebDriver driver, By locator) {
		driver.switchTo().frame(find(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, By locator) {
		action = new Actions(driver);
		action.moveToElement(find(driver, locator)).perform();
	}

	public void clickAndHoldToRandomElement(WebDriver driver, By locator, List<WebElement> itemList) {
		action = new Actions(driver);
		// action.clickAndHold();
	}

	public void doubleClickToElement(WebDriver driver, By locator) {
		action = new Actions(driver);
		action.doubleClick(find(driver, locator));
	}

	public void rightClickToElement(WebDriver driver, By locator) {
		action = new Actions(driver);
		action.contextClick(find(driver, locator));
	}

	public void sendKeyBroadToElement(WebDriver driver, By locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, locator), key);
	}

	public void dragAndDropHTML4(WebDriver driver, By dragLocator, By dropLocation) {
		action = new Actions(driver);
		action.dragAndDrop(find(driver, dragLocator), find(driver, dropLocation));

	}

	// JS

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		js = (JavascriptExecutor) driver;
		return js.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		js = (JavascriptExecutor) driver;
		String textActual = (String) js
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, By locator) {
		element = find(driver, locator);
		js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepSeconds(1);
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, By locator) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", find(driver, locator));
	}

	public void scrollToElement(WebDriver driver, By locator) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", find(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, By locator, String value) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + value + "')", find(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, By locator, String attributeRemove) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, By locator) {
		js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].validationMessage;", find(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, By locator) {
		js = (JavascriptExecutor) driver;
		boolean status = (boolean) js.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				find(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void removeDisableAttributeOfElementByJS(WebDriver driver, By locator) {
		js = (JavascriptExecutor) driver;
		element = find(driver, locator);
		js.executeScript("arguments[0].removeAttribute('disabled')", element);
	}

	public void waitElementVisible(WebDriver driver, By locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitElementInVisible(WebDriver driver, By locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitElementPresence(WebDriver driver, By locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitElementClickable(WebDriver driver, By locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void sleepSeconds(long timeout) {
		try {
			Thread.sleep(1000 * timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// type locator: id, class, name, css, xpath

	public By locatorElement(String namePath, String locatorPath) {
		if (namePath.contains("ID")) {
			return By.id(locatorPath);
		} else if (namePath.contains("CLASS")) {
			return By.className(locatorPath);
		} else if (namePath.contains("NAMEP")) {
			return By.className(locatorPath);
		} else if (namePath.contains("CSS")) {
			return By.cssSelector(locatorPath);
		} else {
			return By.xpath(locatorPath);
		}
	}
}
