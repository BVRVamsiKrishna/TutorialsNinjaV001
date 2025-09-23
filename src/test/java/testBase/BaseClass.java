package testBase;
//Base Class- it contains the reusable methods
//whatever required for all test cases those methods we will keep inside the base class
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j

public class BaseClass 
{
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p; //
	
	@BeforeClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	 {
		//For loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass()); //Log4j
		
		//Below block of code for Remote Server environment--Selenium Grid
		 if(p.getProperty("execution_env").equalsIgnoreCase("remote"))	
		  {
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//For OS-Operating System
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No Matching OS");
				return;
			}
			
			//For Browser
			switch(br.toLowerCase())
			 {
				case "chrome": capabilities.setBrowserName("chrome"); break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				case "firefox": capabilities.setBrowserName("firefox"); break;
				default: System.out.println("No matching Browser"); return;
			 }
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		  }
		
		//Below block of code for Local Server environment--Selenium Grid
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		  //Step 3: Run Tests On Desired Browser/Cross Browser/Parallel
		  //below block of code with switch method is related to parallel testing etc mentioned above.
		  switch(br.toLowerCase())
		   {
			 case "chrome" : driver=new ChromeDriver(); break;
			 case "edge" : driver=new EdgeDriver(); break;
			 case "firefox": driver=new FirefoxDriver(); break;
			 default: System.out.println("Invalid browser name.."); return;
		   }
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); //reading URL from properties file.
		driver.manage().window().maximize();	
	 }
	
	@AfterClass(groups= {"Sanity","Regression","Master","DataDriven"})
	public void teardown()
	 {
		driver.quit();
	 }
	
	/*//Below Methods are created for generating the data randomly
	 *The method randomNumeric(int)and randomAlphabetic(int)from the type RandomStringUtils is deprecated.
	 *Above random msg is shown just ignore it. Code is working.
	 */
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);	
		return generatedstring;	
	}
	
	public String randomeNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomeAlphaNumberic()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return(generatedstring+"@"+generatednumber);
	}
	
	// This method is for Capturing the screenshot with Time Stamp.
	public String captureScreen(String tname) throws IOException
	{
		String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		File sourceFile =takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+ "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;	
	}
}
