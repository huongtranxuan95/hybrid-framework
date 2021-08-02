package commons;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.prism.Image;

import pageObjects.bankguru.BankManagerPageObject;
import pageObjects.bankguru.DepositPageObject;
import pageObjects.bankguru.NewAccountPageObject;
import pageObjects.bankguru.NewCustomerPageObject;
import pageObjects.bankguru.WithdrawalPageObject;
import pageObjects.livegurru.AboutUsPageObject;
import pageObjects.livegurru.AdvanceSearcgPageObject;
import pageObjects.livegurru.CustomerServicePageObject;
import pageObjects.livegurru.PageGeneratorManager;
import pageObjects.livegurru.SearchTermPageObject;
import pageUIs.bankguru.AbstractBankGuruPageUI;
import pageUIs.liveguru.AbstractPageUI;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import sun.management.counter.StringCounter;

public abstract class AbstractPage extends AbstractPageUI{
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

	public void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
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

	public WebElement find(WebDriver driver, ByLocator locatorType, String locatorPath) {
		return driver.findElement(locatorElement(locatorType, locatorPath));
	}
	
	public WebElement find(WebDriver driver, String locatorPath) {
		return driver.findElement(By.xpath(locatorPath));
	}
	public WebElement find(WebDriver driver, String locatorPath, String...values) {
		return driver.findElement(By.xpath(getDynamicLocator(locatorPath, values)));
	}

	public List<WebElement> finds(WebDriver driver, ByLocator locatorType, String locatorPath) {
		return driver.findElements(locatorElement(locatorType, locatorPath));
	}
	public List<WebElement> finds(WebDriver driver, String locatorPath) {
		return driver.findElements( By.xpath(locatorPath));
	}
	public List<WebElement> finds(WebDriver driver, String locatorPath, String...values) {
		return driver.findElements(By.xpath(getDynamicLocator(locatorPath, values)));
	}

	public void clickToElement(WebDriver driver, ByLocator locatorType, String locatorPath) {
		find(driver, locatorType, locatorPath).click();
	}
	public void clickToElement(WebDriver driver, String locatorPath, String...values) {
		find(driver, getDynamicLocator(locatorPath, values)).click();
	}

	public void sendKeysToElement(WebDriver driver, ByLocator locatorType, String locatorPath, String text) {
		element = find(driver, locatorType, locatorPath);
		element.clear();
		element.sendKeys(text);
	}
	public void sendKeysToElement(WebDriver driver, String locatorPath, String text, String... values ) {
		element = find(driver, getDynamicLocator(locatorPath, values));
		element.clear();
		element.sendKeys(text);
	}

	public void selectItemInDropdown(WebDriver driver, ByLocator locatorType, String locatorPath, String itemValue) {
		select = new Select(find(driver, locatorType, locatorPath));
		select.selectByVisibleText(itemValue);
	}
	public void selectItemInDropdown(WebDriver driver, String locatorPath, String itemValue, String... values) {
		select = new Select(find(driver, getDynamicLocator(locatorPath, values)));
		select.selectByVisibleText(itemValue);
	}

	public String getSelectedItemInDropdown(WebDriver driver, ByLocator locatorType, String locatorPath) {
		select = new Select(find(driver, locatorType, locatorPath));
		return select.getFirstSelectedOption().getText();
	}
	public String getSelectedItemInDropdown(WebDriver driver, String locatorPath, String... values) {
		select = new Select(find(driver, getDynamicLocator(locatorPath, values)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMutiple(WebDriver driver, ByLocator locatorType, String locatorPath) {
		select = new Select(find(driver, locatorType, locatorPath));
		return select.isMultiple();

	}

	public void selectItemInCustomDropdownList(WebDriver driver, String parentLocator,ByLocator parentLocatorType, String childLocator,ByLocator childLocatorType, String itemValue) {
		clickToElement(driver,parentLocatorType ,parentLocator);
		sleepSeconds(1);
		explicitWait = new WebDriverWait(driver, longTimeout);

		explicitWait.until(ExpectedConditions.presenceOfElementLocated(locatorElement(childLocatorType, childLocator)));
		itemList = finds(driver,childLocatorType ,childLocator);
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

	public String getElementAttribute(WebDriver driver, ByLocator locatorType, String locatorPath, String attributeName) {
		return find(driver, locatorType, locatorPath).getAttribute(attributeName);
	}
	public String getElementAttribute(WebDriver driver, String locatorPath, String attributeName, String... values) {
		return find(driver, getDynamicLocator(locatorPath, values)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, ByLocator locatorType, String locatorPath) {
		return find(driver, locatorType, locatorPath).getText();
	}
	public String getElementText(WebDriver driver, String locatorPath, String... values) {
		return find(driver, getDynamicLocator(locatorPath, values)).getText();
	}

	public int countElementNumber(WebDriver driver, ByLocator locatorType, String locatorPath) {
		return finds(driver,locatorType ,locatorPath).size();
	}
	public int countElementNumber(WebDriver driver, String locatorPath, String... values) {
		return finds(driver,getDynamicLocator(locatorPath, values)).size();
	}

	public boolean isElementDisplayed(WebDriver driver, ByLocator locatorType, String locatorPath) {
		try {
			return find(driver, locatorType, locatorPath).isDisplayed();
		}catch (NoSuchElementException e) {
			return false;
		}catch (StaleElementReferenceException e2) {
			return false;
		}
		
	}
	public boolean isElementUnDisplayed(WebDriver driver, String locatorPath) {
		overrideImplicitWait(driver, GlobalConstants.SHORT_TIMEOUT);
		itemList = finds(driver, locatorPath);
		overrideImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);
		if(itemList.size() == 0) {	
			return true;
		}else if (itemList.size() != 0 && !itemList.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isElementUnDisplayed(WebDriver driver, String locatorPath, String...values) {
		overrideImplicitWait(driver, GlobalConstants.SHORT_TIMEOUT);
		itemList = finds(driver, locatorPath, values);
		overrideImplicitWait(driver, GlobalConstants.LONG_TIMEOUT);
		if(itemList.size() == 0) {	
			return true;
		}else if (itemList.size() != 0 && !itemList.get(0).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isElementDisplayed(WebDriver driver, String locatorPath, String... values) {
		return find(driver, getDynamicLocator(locatorPath, values)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, ByLocator locatorType, String locatorPath) {
		return find(driver, locatorType, locatorPath).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, ByLocator locatorType, String locatorPath) {
		return find(driver, locatorType, locatorPath).isSelected();
	}

	public void checkToCheckbox(WebDriver driver, ByLocator locatorType, String locatorPath) {
		element = find(driver, locatorType, locatorPath);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void switchToFrameOrIframe(WebDriver driver, ByLocator locatorType, String locatorPath) {
		driver.switchTo().frame(find(driver, locatorType, locatorPath));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, ByLocator locatorType, String locatorPath) {
		action = new Actions(driver);
		action.moveToElement(find(driver, locatorType, locatorPath)).perform();
	}

	public void clickAndHoldToRandomElement(WebDriver driver, By locator, List<WebElement> itemList) {
		action = new Actions(driver);
		// action.clickAndHold();
	}

	public void doubleClickToElement(WebDriver driver, ByLocator locatorType, String locatorPath) {
		action = new Actions(driver);
		action.doubleClick(find(driver, locatorType, locatorPath));
	}

	public void rightClickToElement(WebDriver driver, ByLocator locatorType, String locatorPath) {
		action = new Actions(driver);
		action.contextClick(find(driver, locatorType, locatorPath));
	}

	public void sendKeyBroadToElement(WebDriver driver, ByLocator locatorType, String locatorPath, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, locatorType, locatorPath), key);
	}

	public void dragAndDropHTML4(WebDriver driver,ByLocator dragLocatorType ,String dragLocator, String dropLocation, ByLocator dropLocatorType) {
		action = new Actions(driver);
		action.dragAndDrop(find(driver,dragLocatorType ,dragLocator), find(driver,dropLocatorType ,dropLocation));

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

	public void highlightElement(WebDriver driver, ByLocator locatorType, String locatorPath) {
		element = find(driver, locatorType, locatorPath);
		js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepSeconds(1);
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	public void highlightElement(WebDriver driver, String locatorPath, String...values) {
		element = find(driver, getDynamicLocator(locatorPath, values));
		js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepSeconds(1);
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, ByLocator locatorType, String locatorPath) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", find(driver, locatorType, locatorPath));
	}
	public void clickToElementByJS(WebDriver driver, String locatorPath, String...values) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", find(driver, getDynamicLocator(locatorPath, values)));
	}

	public void scrollToElement(WebDriver driver, ByLocator locatorType, String locatorPath) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", find(driver, locatorType, locatorPath));
	}

	public void sendkeyToElementByJS(WebDriver driver,  ByLocator locatorType, String locatorPath, String value) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value', '" + value + "')", find(driver, locatorType, locatorPath));
	}

	public void removeAttributeInDOM(WebDriver driver,  ByLocator locatorType, String locatorPath, String attributeRemove) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, locatorType, locatorPath));
	}

	public String getElementValidationMessage(WebDriver driver, ByLocator locatorType, String locatorPath) {
		js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].validationMessage;", find(driver, locatorType, locatorPath));
	}

	public boolean isImageLoaded(WebDriver driver, ByLocator locatorType, String locatorPath) {
		js = (JavascriptExecutor) driver;
		boolean status = (boolean) js.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				find(driver, locatorType, locatorPath));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void removeDisableAttributeOfElementByJS(WebDriver driver, ByLocator locatorType, String locatorPath) {
		js = (JavascriptExecutor) driver;
		element = find(driver, locatorType, locatorPath);
		js.executeScript("arguments[0].removeAttribute('disabled')", element);
	}

	public void waitElementVisible(WebDriver driver, ByLocator locatorType, String locatorPath) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locatorElement(locatorType, locatorPath)));
	}
	public void waitElementVisible(WebDriver driver, String locatorPath, String...values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getDynamicLocator(locatorPath, values))));
	}

	public void waitElementInVisible(WebDriver driver, ByLocator locatorType, String locatorPath) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(locatorElement(locatorType, locatorPath)));
	}
	public void waitElementInVisible(WebDriver driver, String locatorPath, String...values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(getDynamicLocator(locatorPath, values))));
	}

	public void waitElementPresence(WebDriver driver, ByLocator locatorType, String locatorPath) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(locatorElement(locatorType, locatorPath)));
	}

	public void waitElementClickable(WebDriver driver, ByLocator locatorType, String locatorPath) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(locatorElement(locatorType, locatorPath)));
	}
	public void waitElementClickable(WebDriver driver, String locatorPath, String...values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(getDynamicLocator(locatorPath, values))));
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

	public By locatorElement(ByLocator locatorType, String locatorPath) {
		By locator = null;
		switch (locatorType) {
		case ID:
			locator = By.id(locatorPath);
			break;
		case CLASS:
			locator = By.className(locatorPath);
			break;
		case NAME:
			locator = By.name(locatorPath);
			break;
		case CSSLOCATOR:
			locator = By.cssSelector(locatorPath);
			break;
		case XPATH:
			locator = By.xpath(locatorPath);
			break;
		default:
			System.out.println("Need input type locator");
			break;
		}
		return locator;
	}
	
	public String getDynamicLocator(String xpathValue, String... values) {
		xpathValue = String.format(xpathValue, (Object[])values);
		return xpathValue;
		
	}
	
	//uploadMutipleFile
//	/public String get
	public String getFolderUpload() {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		if(!isWindow()) {
			filePath.replaceAll("\\\\", "/");
		}
		return filePath;
	}
	public void uploadMutipleFiles(WebDriver driver, String...values) {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		if(!isWindow()) {
			filePath.replaceAll("\\\\", "/");
		}
		String fullFilePath = "";
		for (String fileName : values) {
			fullFilePath+=filePath+fileName+"\n";
		}
		find(driver, AbstractPageUI.UPLOAD_FILE_TYPE).sendKeys(fullFilePath.trim());
	}
	
	public boolean isFileExist(String filePath) {
		File checkSaveFileSuccesfull = new File(filePath);
		if (checkSaveFileSuccesfull.exists()) {
			return true;
		}else {
			return false;
		}
	}
	public String getFilePathDownloadFolder() {
		String filePath = GlobalConstants.DOWNLOAD_FILE_FOLDER;
		if(!GlobalConstants.OS_NAME.toLowerCase().contains("win")) {
			filePath = filePath.replaceAll("\\\\", "/");
		}
		
		return filePath;
	}
	public String getFilePathUploadFolder() {
		String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		if(!GlobalConstants.OS_NAME.toLowerCase().contains("win")) {
			filePath = filePath.replaceAll("\\\\", "/");
		}
		
		return filePath;
	}
	public boolean compareImage(WebDriver driver, String locator, String targetImgPath, String name) {
		WebElement imgActual = find(driver, locator, name);
		String imgPath = getFilePathDownloadFolder();
		
		Screenshot screenImg = new AShot().takeScreenshot(driver, imgActual);
		BufferedImage expectedImg = null;
		try {
			expectedImg = ImageIO.read(new File(targetImgPath));
		} catch (IOException e) {
			System.out.println("Read file");
			e.printStackTrace();
		}
		if(expectedImg!=null) {
			BufferedImage actualImg = screenImg.getImage();
			ImageDiffer imgdiff = new  ImageDiffer();
			ImageDiff diff  = imgdiff.makeDiff(expectedImg, actualImg);
			return diff.hasDiff();
		}
		
		return false;
	}
	//check os_name
	
	public boolean isWindow() {
		return GlobalConstants.OS_NAME.toLowerCase().contains("win");
	}
	public void overrideImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	// open Pages general Of liveguru
	
	public AboutUsPageObject openAboutUsPage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractPageUI.ABOUT_US_LINK_XPATH);
		clickToElement(driver, ByLocator.XPATH, AbstractPageUI.ABOUT_US_LINK_XPATH);
		return PageGeneratorManager.getAboutUsPage(driver);
	}
	
	public SearchTermPageObject openSearchTermPage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractPageUI.SEARCH_TERM_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, AbstractPageUI.SEARCH_TERM_LINK_XPATH);
		return PageGeneratorManager.getSearchTermPage(driver);
	}

	public CustomerServicePageObject openCustomerServicePage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractPageUI.CUSTOMER_SERVICE_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, AbstractPageUI.CUSTOMER_SERVICE_LINK_XPATH);
		return PageGeneratorManager.getCustomerServicePage(driver);
	}
	public AdvanceSearcgPageObject openAdvanceSearchPage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractPageUI.ADVANCE_SEARCH_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, AbstractPageUI.ADVANCE_SEARCH_LINK_XPATH);
		return PageGeneratorManager.getAdvanceSearcgPage(driver);
	}
	
	//open page use dynamic locator
	
	//Less than 20 page
	public AbstractPage openFooterPageByName(WebDriver driver, String pageName) {
		waitElementClickable(driver, AbstractPageUI.DYNAMIC_FOOTER_LOCATOR, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_FOOTER_LOCATOR, pageName);
		if(pageName.equals("About Us")) {
			return PageGeneratorManager.getAboutUsPage(driver);
		}else if (pageName.equals("Customer Service")) {
			return PageGeneratorManager.getCustomerServicePage(driver);
		}else if (pageName.equals("Search Terms")) {
			return PageGeneratorManager.getSearchTermPage(driver);
		}else if (pageName.equals("Advanced Search")) {
			return PageGeneratorManager.getAdvanceSearcgPage(driver);
		}else {
			return PageGeneratorManager.getHomePage(driver);
		}
	}
	// less or more pages
	public void openFooterByName(WebDriver driver, String pageName) {
		waitElementClickable(driver, AbstractPageUI.DYNAMIC_FOOTER_LOCATOR, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_FOOTER_LOCATOR, pageName);	
	}
	
	
	//open page of bankguru
	public BankManagerPageObject openManagerPage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractBankGuruPageUI.MANAGER_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, AbstractBankGuruPageUI.MANAGER_LINK_XPATH);
		return new BankManagerPageObject(driver);
	}
	
	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractBankGuruPageUI.NEW_CUSTOMER_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, AbstractBankGuruPageUI.NEW_CUSTOMER_LINK_XPATH);
		return new NewCustomerPageObject(driver);
	}
	public NewAccountPageObject openNewAccountPage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractBankGuruPageUI.NEW_ACCOUNT_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, AbstractBankGuruPageUI.NEW_ACCOUNT_LINK_XPATH);
		return new NewAccountPageObject(driver);
	}
	public WithdrawalPageObject openWithdrawalPage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractBankGuruPageUI.WITH_DRAWAL_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, AbstractBankGuruPageUI.WITH_DRAWAL_LINK_XPATH);
		return new WithdrawalPageObject(driver);
	}
	public DepositPageObject openDepositPage(WebDriver driver) {
		waitElementClickable(driver, ByLocator.XPATH, AbstractBankGuruPageUI.DEPOSIT_LINK_XPATH);
		clickToElement(driver,ByLocator.XPATH, AbstractBankGuruPageUI.DEPOSIT_LINK_XPATH);
		return new DepositPageObject(driver);
	}
}
