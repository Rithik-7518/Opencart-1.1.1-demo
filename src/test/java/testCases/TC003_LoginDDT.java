package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")  // if dataProvider in another class --> dataProviderClass=DataProviders.class ---this need
	public void verify_LoginDDT(String email,String pwd ,String exp) {
		
		logger.info("*********  Tesing Start TC003_LoginDDT  *******");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		/*
		 * valid:
		 * 
		 * login success - test pass - logout 
		 * login fail - test fail
		 * 
		 * invalid:
		 * 
		 * login success - test fail - logout
		 * login fail - test pass
		 *  
		 * 
		 */
		
		if(exp.equalsIgnoreCase("Valid")) 
		{
			if (targetPage== true) 
			{
				
				Assert.assertTrue(true);
				macc.clickLogout();
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid")) 
		{
			if (targetPage== true) 
			{
				macc.clickLogout();
				Assert.assertTrue(false);
				
			}
			else {
				Assert.assertTrue(true);
			}
		}
	}catch(Exception e) {
		Assert.fail();
	}
		logger.info("********* Finished Testing TC003_LoginDDT  *******");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
