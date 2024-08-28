package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	@Test(groups= {"Regression","Master"})
	public void verify_Account_Registration() 
	{
		logger.info("****** verify_Account_Registration ******");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyaccount();
			logger.info("Clicked on Myaccount Link");
			hp.clickRegister();
			logger.info("Clicked on Register Link");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			logger.info("Providing Customer Details");
			regpage.setFistname(randomeString().toUpperCase());
			regpage.setLastname(randomeString().toUpperCase());
			regpage.setEmail(randomeString() + "@gmail.com"); //randomly generated the email
			regpage.setTelephone(randomeNumber());
			String password = randomeAlphaNumberic();
			regpage.setPassword(password);
			regpage.setCfnPwd(password);
			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			logger.info("Validating expected msg");
			String confmsg = regpage.GetConfirmationMsg();
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				logger.error("Test Failed....");
				logger.debug("debug logs");
				Assert.assertTrue(false);	
			}
				
				
			//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
			}catch(Exception e)
			{
				Assert.fail();
			}
		logger.info("******Finished verify_Account_Registration ******");
	}	
}
