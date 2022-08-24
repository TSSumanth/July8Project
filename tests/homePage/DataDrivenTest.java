package homePage;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {

	@Test (dataProvider = "mytestData")
	public void mytest(String name, int age, boolean value)
	{
		System.out.println(name + " - "+ age + " - "+ value);
	}
	
	
	
	@DataProvider (name = "mytestData")
	public Object[][] testData()
	{
		Object[][] obj = new Object[3][3];
		
		obj[0][0] = "Ravi";
		obj[0][1] = 1;
		obj[0][2] = true;
		
		obj[1][0] = "Ajay";
		obj[1][1] = 2;
		obj[1][2] = false;
		
		obj[2][0] = "Kumar";
		obj[2][1] = 3;
		obj[2][2] = false;
		
		
		return obj;
	}
	
}
