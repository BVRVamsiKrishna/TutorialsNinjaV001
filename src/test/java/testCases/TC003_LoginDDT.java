package testCases;
//TC003_LoginDDT--Login data driven test case class 
/*Test Steps for this test case
* 1.call the base class- url, quit from here 
* 2.call home page pom class 
* 3.call the login page pom class
* 4.call my account page pom class for logout the application and my account text verification.
* 5. add the logic for data driven tests from data providers class and data from excel.

Different Cases Covered for data in excel file in this test case are below:
1.Data is Valid, if login is successful then test case is passed.--logout
2.Data is Valid, if login is unsuccessful then test case is failed.

3.Data is Invalid, if login is successful then test case is failed.--logout
4.Data is Invalid, if login is unsuccessful then test case is passed.
*/

//import static org.testng.Assert.assertFalse;
//import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData" ,dataProviderClass=DataProviders.class, groups="DataDriven") //getting data provider from different class 
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
	{
	  logger.info("*****Starting TC003_LoginDDT *****");
	  try
	   {
		  //HomePage
		  HomePage HP=new HomePage(driver);
		  HP.clickMyAccount();
		  HP.clickLogin();
			
		  //Login Page
		  LoginPage LP=new LoginPage(driver);
		  LP.setLoginEmail(email);
		  LP.setLoginPassword(pwd);
		  LP.clickLogin();
			
		  //MyAccount Page
		  MyAccountPage macc=new MyAccountPage(driver);
		  boolean targetPage=macc.isMyAccountExists();
			
		  //verification with data in excel logic below
			
		  if(exp.equalsIgnoreCase("Valid"))
		   {
			 if(targetPage==true)
			  {	
				 macc.clicklogout();
				 Assert.assertTrue(true);
			  }
			 else
			  {
				Assert.assertTrue(false);
			  }
			}
	
		 if(exp.equalsIgnoreCase("Invalid"))
		  {
			if(targetPage=false)
			 {
				macc.clicklogout();
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
	Thread.sleep(3000);
	logger.info("*****Finished TC003_LoginDDT *****");
	}	
}
