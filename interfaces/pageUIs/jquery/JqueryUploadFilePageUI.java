package pageUIs.jquery;

public class JqueryUploadFilePageUI {
	public static final String IMAGE_NAME_LOADED_XPATH = "//p[@class='name' and text()='%s']";
	public static final String BUTTON_UPLOAD_START_XPATH = IMAGE_NAME_LOADED_XPATH + "//parent::td//following-sibling::td//button[contains(@class,'start')]";
	public static final String IMAGE_LINK_XPATH = "//p[@class='name']//a[text()='%s']";
	public static final String IMAGE_LINK_LOAD = "//img[@title='%s']";
}
