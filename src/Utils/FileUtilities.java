package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class FileUtilities {
	
	public static String resultsFolderName;
	public static String testFolderLocation;
	public static ExtentReports reports;
	public static ExtentTest test;
	public static Sheet testSheet;
	public static int rownum;
	public static Properties getObjectLibrary(String FileName)
	{
		Properties prop = new Properties();

		//creates input stream between properties file and Properties class.
		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\ObjectLibrary\\"+FileName+".properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return prop;
	}
	
	public String createResultsFolder()
	{
		File f1 = new File(System.getProperty("user.dir")+"\\Results");
		boolean bool = f1.mkdir();  
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		
		resultsFolderName = System.getProperty("user.dir")+"\\Results\\"+dtf.format(now);
		//Instantiate the File class    
	    f1 = new File(resultsFolderName);
	    //Creating a folder using mkdir() method  
	    bool = f1.mkdir();  
	    return resultsFolderName;
	}
	
	public String createTestsFolder(String TestName)
	{
		File f1 =  new File(resultsFolderName + "\\" + TestName);
	    //Creating a folder using mkdir() method  
	    boolean bool = f1.mkdir();  
	    return resultsFolderName + "\\" + TestName;
	}
	
	public static void getExcelWorkBook(String fileName)
	{
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\testData\\"+fileName+".xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			testSheet = workbook.getSheet("TestData");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getData(String key)
	{
		int column = findColumn(key);
		return testSheet.getRow(rownum).getCell(column).getStringCellValue();
	}
	
	public static int findSheetRow(String TestCaseName) {
		int r = testSheet.getLastRowNum();
		for(int i=0;i<=r;i++)
		{
			Row row = testSheet.getRow(i);
			String value = row.getCell(0).getStringCellValue();
			if(value.equals(TestCaseName))
			{
				rownum = i;
				return i;
			}
		}
		return -1;
	}
	
	public static int findColumn(String DataKeyName)
	{
		Row row = testSheet.getRow(0);
		for(int i=0;i<=row.getLastCellNum();i++)
		{
			Cell cell = row.getCell(i);
			String value  = cell.getStringCellValue();
			
			if(value.equals(DataKeyName))
			{
				return i;
			}
		}
		return -1;
	}
	
	public  void CreateExtentReport()
	{
		reports = new ExtentReports(resultsFolderName+"\\Homepagetest.html");
	}
	
	public void CreateExtentTest()
	{
		
	}
	
	
	public void LogSuccess(String info)
	{
		// Add to extent reports
		// add to text file
	}
	
	public void LogSuccess(String screnshotpath,  String info)
	{
		
	}
	
	public void LogFailure(String info)
	{
		
	}
	
	public void LogFailure(String screnshotpath,  String info)
	{
		
	}
	
	public void LogInfo(String info)
	{
		
	}
	
	public void LogInfo(String screnshotpath,  String info)
	{
		
	}
}
