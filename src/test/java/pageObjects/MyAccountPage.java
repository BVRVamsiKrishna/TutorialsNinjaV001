package pageObjects;
//Page Object Class for My Account Page in the application.
//This page is shown after login is successfully completed in the application.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
	//Constructor
	public MyAccountPage(WebDriver driver)
	 {
		super(driver);
	 }
	
	//Locators
	@FindBy(xpath="//h2[normalize-space()='My Account']") 
	WebElement msgHeading;
	
	//Added in Step 6 which is logout button locators
	/*//Pavan sir gave locators
	 @FindBy(xpath="//div[@class='list-group']//a[text()='Logout']")
	 WebElement lnkLogout;
	*/
	//Generated from the app -selectors hub-logout
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	//Action Methods
	public boolean isMyAccountExists()
	{
		try
		{
			return (msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}	
	
	public void clicklogout()
	{
		lnkLogout.click();
	}
}
