package com.testautomation.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.testautomation.Utility.ConfigFileReader;
import com.testautomation.Utility.ExcelHandler;
import com.testautomation.Utility.PropertiesFileReader;
import com.testautomation.Utility.TestDataHandler;

import ObjRepository.RepositoryParser;

public class SamplePage {

	
	private WebDriver driver;
	ConfigFileReader configFileReader = new ConfigFileReader();
	RepositoryParser prop = new RepositoryParser("./ObjectRepo.properties");
	PropertiesFileReader obj = new PropertiesFileReader();
	TestDataHandler testdata = new TestDataHandler();
	ExcelHandler handler = new ExcelHandler();

	public void launch_samplPage() {

		System.setProperty("webdriver.chrome.driver", configFileReader.getDriverPath());
		driver = new ChromeDriver();
		configFileReader.getImplicitlyWait();
		driver.manage().window().maximize();
		driver.get(configFileReader.getApplicationUrl());
	}

	public void enter_FormDetails() throws Exception {
		
		
		WebDriverWait wait = new WebDriverWait(driver, 100);
		

		Properties properties = obj.getProperty();

		Map<String, String> TD = handler.getTestDataInMap(properties.getProperty("testdatafilepath"),
				properties.getProperty("sheetname"), "TestCase_001");

		driver.findElement(prop.getLocator("FirstName")).sendKeys(TD.get("FirstName"));
		driver.findElement(prop.getLocator("LastName")).sendKeys(TD.get("LastName"));
		driver.findElement(prop.getLocator("Address")).sendKeys(TD.get("Address"));
		driver.findElement(prop.getLocator("Email")).sendKeys(TD.get("Email"));
		driver.findElement(prop.getLocator("Telephone")).sendKeys(TD.get("Phone"));
		
		driver.findElement(prop.getLocator("Gender")).click();
		
		driver.findElement(prop.getLocator("Hobbies")).click();
		
		driver.findElement(prop.getLocator("Languages")).click();
		
		
		
		
		List<WebElement> dropdown_items = new ArrayList<>();
		dropdown_items = driver.findElements(prop.getLocator("Array"));
		dropdown_items.get(0).click();
		
	
					
		Select skill = new Select(driver.findElement(prop.getLocator("Skills")));
		skill.selectByVisibleText("Analytics");
		
	
		
		Select country = new Select(driver.findElement(prop.getLocator("Countries")));
		country.selectByVisibleText("Albania");
		

		Select country2 = new Select(driver.findElement(prop.getLocator("country")));
		country2.selectByVisibleText("Japan");
		
		
		Select year = new Select(driver.findElement(prop.getLocator("Year")));
		year.selectByVisibleText("1991");
		Select month = new Select(driver.findElement(prop.getLocator("Month")));
		month.selectByVisibleText("May");
		Select day = new Select(driver.findElement(prop.getLocator("Day")));
		day.selectByVisibleText("3");
		
		
		driver.findElement(prop.getLocator("FirstPassword")).sendKeys(TD.get("Password"));
		
		driver.findElement(prop.getLocator("SecondPassword")).sendKeys(TD.get("ConfirmPassword"));
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,450)");
		
		
		
	}
	
	
	public void click_Submit() throws Exception {
		
		String tcstatus = null;
		
		WebElement element = driver.findElement(prop.getLocator("SubmitButton"));
		Actions action = new Actions(driver);

		action.moveToElement(element).click().perform();
	
		
		// String expectedTitle = "Automation Demo Site";
         String actualTitle = driver.getTitle();
         //Assert.assertEquals(actualTitle, expectedTitle);  
         
        // System.out.println(actualTitle);
		
		Properties propt = obj.getProperty();

		String testFilePath = propt.getProperty("testdatafilepath");
		String sheetNameUpdate = propt.getProperty("sheetname");

		handler.UpdateTestResultsToExcel(testFilePath, sheetNameUpdate,tcstatus, "TestCase_001", actualTitle);

		System.out.println("Updated in excel :" + actualTitle);
		
	
	}

	public void testData(int arg1) throws Exception {

		Properties properties = obj.getProperty();

		Map<String, String> TD = handler.getTestDataInMap(properties.getProperty("testdatafilepath"),
				properties.getProperty("sheetname"), "TestCase_001");
		System.out.println(TD.get("Skill_7"));

	}

}


