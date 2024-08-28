package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTest extends BaseClass
{	
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups= {"DataDriven"})
	public void verify_LoginDataDriven(String username,String pwd,String exp)
	{
		logger.info("***** Starting of the TC003_LoginDataDrivenTest *****");
	try
	{
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		//login page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(username);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage mg = new MyAccountPage(driver);
		boolean targetpage = mg.isMyAccountPageExists();
		// Data is valid -valid username+password login success testpass -logout
		//		 -invalid username+password login unsuccess testfail
		//Data is Invalid -valid username+passowrd login sucess testpass -logout
		//		  -invalid username+password loginfail testpass
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
				mg.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				mg.clickLogout();
				Assert.assertTrue(false);
			}	
			else
			{
				Assert.assertTrue(true);
			}
		
		}
	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("***** finished of the TC003_LoginDataDrivenTest *****");
	}
}
