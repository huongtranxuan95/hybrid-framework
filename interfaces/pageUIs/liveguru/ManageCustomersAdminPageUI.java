package pageUIs.liveguru;

public class ManageCustomersAdminPageUI {
	public static final String POPUP_ID = "message-popup-window";
	public static final String CLOSE_POPUP_BUTTON_XPATH = "//a[@title='close']";
	public static final String SEARCH_BUTTON_XPATH ="//button[@title='Search']";
	public static final String DYNAMIC_HEADER_NAME_LOCATOR_XPATH = "//span[text()='%s']//ancestor::th/preceding-sibling::th";
	public static final String DYNAMIC_TEXTBOX_LOCATOR_BY_INDEX_COLUMN_XPATH = "//th[%s]//input";
	public static final String DYNAMIC_VALIDATE_INFORMATION = "//tr[%s]//td[%s][contains(text(),'%s')]";
	
}
