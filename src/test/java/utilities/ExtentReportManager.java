package utilities;
//Extent report Listener class-utility file
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		//Approach 1- this way we can set timestamp for file name
		/*SimpleDateFormat df=new SimpleDateFormat("yy.mm.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		
		//Approach 2- this way also we can set timestamp for file name		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //TimeStamp
		
		repName= "Test-ExtentReport-" + timeStamp +".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\" + repName); //specify the location of the report
		
		
		sparkReporter.config().setDocumentTitle("TutorialsNinja Automation Report"); //Title of Report
		sparkReporter.config().setReportName("TutorialsNinja Functional Testing"); //Name of the Report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "TutorialNinja");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name")); //username getting dynamically
		extent.setSystemInfo("Environment", "QA");
		
		//Getting OS related data from XML file
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		//Getting browser related data from XML file		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		//To Get the Groups info from the test methods when we execute test cases through grouping xml
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		 {
			extent.setSystemInfo("Groups", includedGroups.toString());
		 }
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.PASS, result.getName()+ "Got Successfully Executed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); 
		
		test.log(Status.FAIL, result.getName()+ " Got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
			String imgPath= new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);		
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP, result.getName()+ " Got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		/*If we want to open the extent report automatically 
		 *after the execution is completed and report is generated--This below block code is used */
		
		String pathOfExtentReport = System.getProperty("user.dir")+ "\\reports\\" + repName;
		File extentReport= new File(pathOfExtentReport);
		
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	
	   /*//If we want to send the report automatically to the team through email.--This below block code is used.
	    	
		try
		 {
			URL url=new URL("file:///"+ System.getProperty("user.dir")+ "\\reports\\"+ repName);
			
			//Create the email message
			ImageHtmlEmail email= new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"));
			email.setSSLOnConnect(true)
			email.setFrom("pavanoltraining@gmail.com"); //sender email
			email.setMsg("Please find attached Report...");
			email.addTo("pavankumar.busyqa@gmail.com"); //Receiver Email
			email.attach(url, "extent report","please check report...");
			email.send(); //send the email			
		 }
		catch(Exception e)
		 {
			e.printStackTrace();
		 }
	   */
		
	}
}
