package pageObjects;
//Home page class- this is the first page of the application so used in all page object classes.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	//Extending the Constructor from BasePage
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']") 
	WebElement lnkRegister;
	
	//login link added in step5-->login page locators
	@FindBy(xpath="//a[normalize-space()='Login']") 
	WebElement lnklogin;
	
	//Action Methods
	
	public void clickMyAccount()
	{
		lnkMyaccount.click();	
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	//login link added in step5
	public void clickLogin()
	{
		lnklogin.click();
	}
}
