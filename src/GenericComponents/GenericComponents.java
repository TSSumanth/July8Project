package GenericComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.FileUtilities;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  

public class GenericComponents extends FileUtilities
{
	
	public static WebDriver driver;

	private static int counter = 1;
	/*
	 *  LaunchApplication - Method opens the browser of our choice and lauches the application url that we test
	 *  Parameters: BrowserName - Provide the beowser that you want to use for the current test
	 *  URL - Provide the Applciation URL that you are testing
	 */
	public  void LaunchApplication(String BrowserName, String URL)
	{
		if(BrowserName.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\lib\\"+"chromedriver.exe"); 
			driver =  new ChromeDriver();
			driver.get(URL);
		}
		else if(BrowserName.equalsIgnoreCase("Firefox")) 
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\lib\\"+"geckodriver.exe"); 
			driver = new FirefoxDriver();
			driver.get(URL);
		}
		this.takeScreenshot();
	}
	
	
	/*
	 * getTitleOfWindow - to get current window title
	 */
	public  String getTitleOfWindow()
	{
		return driver.getTitle();
	}
	
	
	/*
	 * getURLOfWindow - to get current window URL
	 */
	public  String getURLOfWindow()
	{
		return driver.getCurrentUrl();
	}
	
	
	/*
	 * quitBrowser - to close our current browser
	 */
	public  void quitBrowser()
	{
		driver.quit();
	}
	
	
	/*
	 * takeScreenshot - to take screenshot of current window
	 */
	public String takeScreenshot()
	{
		String ScreenshotName = "screenshot"+counter+ ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File scrFile = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(testFolderLocation +"\\" +ScreenshotName ));
				counter++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		return testFolderLocation +"\\" +ScreenshotName;
	}
	
	/*
	 * handleAlert - to accept or dismiss an Alert
	 */
	public  void handleAlert(String action) 
	{
		Alert alert = driver.switchTo().alert();
		
		String text = alert.getText();
		System.out.println(text);
		if(action.equals("accept"))
		{
			alert.accept();
		}else {
			alert.dismiss();
		}
		this.takeScreenshot();
	}

	/*
	 * handleAlertPrompt - to sendtext to alert and accept or dismiss the Alert
	 */
	public  void handleAlert(String action, String value)
	{
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		
		String text = alert.getText();
		System.out.println(text);
		alert.sendKeys(value);
		if(action.equals("accept"))
		{
			alert.accept();
		}else {
			alert.dismiss();
		}
		this.takeScreenshot();
	}
	
	public  boolean click(By by)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			this.takeScreenshot();
		}catch(Exception e)
		{
			System.out.println("Unable to perfrom Click on Element: "+ by.toString());
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public  boolean sendKeys(By by, String value)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(value);
		}catch(Exception e)
		{
			System.out.println("Unable to send keys to Element: "+ by.toString());
			return false;
		}
		return true;
	}
	
	public  boolean verifyCheckboxIsSelected(By by)
	{
		boolean selection = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
			wait.until(ExpectedConditions.visibilityOf(element));
			selection = element.isSelected();
			this.takeScreenshot();
		}catch(Exception e)
		{
			System.out.println("Unable to verify element is selected: "+ by.toString());
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return selection;
	}
	
	public  void sleep(int seconds)
	{
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

}
