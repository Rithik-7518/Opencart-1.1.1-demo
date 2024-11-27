package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h2[text()='My Account']")
	WebElement msgHeading;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkMyAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement linkLogout;
	
	@FindBy(xpath="//a[normalize-space()='Continue']")
	WebElement btncontinue;
	
	
	
	public boolean isMyAccountPageExists() {
		try {
		return (msgHeading.isDisplayed());
		}
		catch(Exception e){
			return false;
		}
	}
	
	
	public void clickLogout() {
		linkMyAccount.click();
		linkLogout.click();
		btncontinue.click();
	}
	
}
