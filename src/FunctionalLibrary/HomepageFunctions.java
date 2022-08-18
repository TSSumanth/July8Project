package FunctionalLibrary;

import java.util.Properties;

import org.openqa.selenium.By;

import GenericComponents.GenericComponents;

public class HomepageFunctions extends GenericComponents
{
	Properties locators = GenericComponents.getObjectLibrary("HomePageObjects");
	GenericComponents gc = new GenericComponents();
	public void clickOnCheckboxesLink()
	{
		gc.click(By.xpath((String)locators.get("checkboxs_xpath")));
	}

}
