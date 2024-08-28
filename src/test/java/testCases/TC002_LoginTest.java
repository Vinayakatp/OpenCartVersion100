package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{	
	@Test(groups= {"Sanity","Master"})
	public void verify_login()
	{
		logger.info("***** Starting TC002_LoginTest *****");
		//Home page
		try {
				HomePage hg = new HomePage(driver);
				hg.clickMyaccount();
				logger.info("Homepage");
				hg.clickLogin();
				//login page
				LoginPage lg = new LoginPage(driver);
				lg.setEmail(p.getProperty("email"));
				lg.setPassword(p.getProperty("password"));
				lg.clickLogin();
				//myaccountPage
				MyAccountPage mg = new MyAccountPage(driver);
				boolean targetpage =mg.isMyAccountPageExists();
				Assert.assertTrue(targetpage);
				//mg.clicklogout();
			}catch(Exception e)
		{
				Assert.fail();
		}
		logger.info("***** Finished TC002_LoginTest *****");
}
}

