package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtilities {

	public static void main(String[] args) 
	{
//		Properties prop = new Properties();
//		
//		FileInputStream fis;
//		try {
//			fis = new FileInputStream("C:\\Users\\tssum\\eclipse-workspace\\July8AMProj\\Selenium\\ObjectLibrary\\Homepage.properties");
//			prop.load(fis);
//		}catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(prop.getProperty("Test1"));
//		System.out.println(prop.getProperty("Test2"));
//		System.out.println(prop.getProperty("Test3"));
//		
//		prop.setProperty("Test4", "Steetingvalue4");
//		
//		System.out.println(prop.getProperty("Test4"));
//		
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream("C:\\Users\\tssum\\eclipse-workspace\\July8AMProj\\Selenium\\ObjectLibrary\\Homepage.properties");
//			prop.store(fos, null);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}
	
	
	
	
	public Properties getObjectLibrary(String FileName)
	{
		Properties prop = new Properties();

		//creates input stream between properties file and Properties class.
		FileInputStream fis;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\Selenium\\ObjectLibrary\\"+FileName+".properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return prop;
	}
	

}
