package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("*********** Starting TC001_AccountRegistrationTest **************");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link.....");
		hp.clickregister();
		logger.info("Clicked on Register link.....");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing custmoer details for register.....");
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLasttName(randomString().toUpperCase());
		
		regpage.setEmail(randomString().toLowerCase()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumber();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expexted message.....");
		String confmsg=regpage.getConfrimationMsg();
		if(confmsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed....");
			logger.debug("debug logs....");
			Assert.assertTrue(false);
		}
		
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("*********** Finished TC001_AccountRegistrationTest **************");
	}
	
	
	
	
	
}
