package pageObjects.jquery;

import org.openqa.selenium.WebDriver;

import pageObjects.jquery.JqueryUploadFilePageObject;

public class PageGenerationManager {

	public static JqueryUploadFilePageObject getJqueryUploadFilePage(WebDriver driver) {
		return new JqueryUploadFilePageObject(driver);
	}
}
