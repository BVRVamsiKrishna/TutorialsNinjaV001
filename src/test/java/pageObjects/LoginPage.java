package pageObjects;
//Page Object Class for login Page in the application.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage
{
	//constructor
	public LoginPage(WebDriver driver) 
	 {
		super(driver);
	 }

	//Locators
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement txtEmailAddress;
		
	@FindBy(xpath="//input[@id='input-password']") 
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@value='Login']") 
	WebElement btnlogin;
	
	//Action Methods
	public void setLoginEmail(String email)
	{
		txtEmailAddress.sendKeys(email);
	}
	
	public void setLoginPassword(String pwd)
	{
		txtpassword.sendKeys(pwd);
	}
	
	public void clickLogin()
	{
		btnlogin.click();
	}
}
