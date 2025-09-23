package testCases;
/*
//TC002_LoginTest
//Test Case-02:- Login Test Class
//Doing actions in Login page in application like login with valid credentials.
//TestSteps:
*=====================
* 1.Click on MY Account dropdown.---call from HomePage POM
* 2.Click on login button.---call from HomePage POM

* 3.Enter the email in login page---call from Login Page POM
* 4.Enter the password in login page---call from Login Page POM

* 5.Enter Email and password from the properties file--call from properties file.

* 6.Click on login button in login page---call from Login Page POM

* 7.Verify that either My Account text is shown in My Account page 
* 	after login in the application---call from My Account Page POM

* 8.Add the log related steps also in this test case for generating logs
* 9.Execute the test case from the master testng xml file.
*/
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
  @Test(groups={"Sanity","Master"})
  public void logintest()
  {
   logger.info("***** Starting TC002_LoginTest *****");
   try
    {
	   //Actions in Home Page in app 
	   HomePage HP=new HomePage(driver);
	   logger.info("Clicked on Myaccount Link ");
	   HP.clickMyAccount();
	   
	   logger.info("Clicked on Login Link");
	   HP.clickLogin();
	   
	   //Action in Login page in app
	   logger.info("providing the login email and password in Login Page");
	   LoginPage LP=new LoginPage(driver);
	   LP.setLoginEmail(p.getProperty("email2"));
	   LP.setLoginPassword(p.getProperty("password2"));
	   LP.clickLogin();
		
	   //Verification of login status-MY account text.
	   Thread.sleep(3000);
	   logger.info("Below validating expected message");
	   MyAccountPage macc=new MyAccountPage(driver);
	   boolean targetpage=macc.isMyAccountExists();
	   //Approach 1
	   //Assert.assertEquals(targetpage, true, "Login Failed");
	   //Approach 2
	   Assert.assertTrue(targetpage);
     }
	catch(Exception e)
	 {
	   Assert.fail();
	 }
	
   	logger.info("*****Finished TC002_LoginTest *****");
  }
}
