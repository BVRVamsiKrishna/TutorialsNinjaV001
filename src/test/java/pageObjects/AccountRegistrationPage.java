package pageObjects;
//Page Object Class for Account Registration Page in the application.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{
 //Constructor
 public AccountRegistrationPage(WebDriver driver)
 {
	super(driver);
 }
	
 //Locators
 @FindBy(xpath="//input[@id='input-firstname']") 
 WebElement txtFirstname;

 @FindBy(xpath="//input[@id='input-lastname']") 
 WebElement txtLastname;
 
 @FindBy(xpath="//input[@id='input-email']") 
 WebElement txtEmail;

 @FindBy(xpath="//input[@id='input-telephone']")
 WebElement txtTelephone;

 @FindBy(xpath="//input[@id='input-password']")
 WebElement txtPassword;

 @FindBy(xpath="//input[@id='input-confirm']") 
 WebElement txtConfirmPassword;

 @FindBy(xpath="//input[@name='agree']") 
 WebElement chkdPolicy;

 @FindBy(xpath="//input[@value='Continue']")
 WebElement btncontinue;

 @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") 
 WebElement msgConfirmation;
 
//After account created clicking on continue button in successfully page added extra lets see
//@FindBy(xpath="//a[normalize-space()='Continue']") 
//WebElement accntbtncontinue;

	
 //Action Methods

 public void setFirstName(String fname)
 {
	 txtFirstname.sendKeys(fname);
 }
	
 public void setLastName(String lname)
 {
	 txtLastname.sendKeys(lname);
 }

 public void setEmail(String email)
 {
	 txtEmail.sendKeys(email);
 }

 public void setTelephone(String tel)
 {
	 txtTelephone.sendKeys(tel);
 }

 public void setPassword(String pwd)
 {
	 txtPassword.sendKeys(pwd);
 }

 public void setConfirmPassword(String pwd)
 {
	 txtConfirmPassword.sendKeys(pwd);
 }
 
 public void setPrivacyPolicy( )
 {
	 chkdPolicy.click();
 }

 public void clickContinue()
 {
	 btncontinue.click();
 }

 public String getConfirmationMsg()
 {
	try 
	 {
	   return (msgConfirmation.getText());
	 }
	catch (Exception e)
	 {
		return (e.getMessage());
	 }
 }
 
 
}
