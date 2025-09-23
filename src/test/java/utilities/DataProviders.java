package utilities;
//DataProvider Class 
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders 
{

	//DataProvider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		//Taking the test data excel file from testData Folder 
		String path=".\\testData\\TutorialNinja_LoginData.xlsx";
		
		//Approach 2- using System.getProperty method
		//String path=System.getProperty("user.dir")+"\\testData\\TutorialNinja_LoginData.xlsx";
		
		//creating an object for Excel Utility
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
	
		//created for two dimension array which can store
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1; i<=totalrows;i++) //1 //read the data from xl storing in two dimensional array
		{
			for(int j=0; j<totalcols;j++) //0 i is rows and j is col
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0
			}
		}
		
		return logindata; //Returning Two dimension array	
	}
	
	//DataProvider 2

	//DataProvider 3
}
