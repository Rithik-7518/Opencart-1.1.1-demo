package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']")    // for login test cases
	WebElement linklogin;
	
	
	public void clickMyAccount() {
		linkMyAccount.click();
	}
	
	public void clickregister() {
		linkRegister.click();
	}
	
	public void clickLogin() {
		linklogin.click();
	}
	
	
	
}
