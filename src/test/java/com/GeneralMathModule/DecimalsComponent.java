package com.GeneralMathModule;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DecimalsComponent extends BaseClass {
	
	private WebDriver driver;
	private Properties properties;
	private String baseUrl;

	
	@Parameters("browser")
	@BeforeMethod
	public void setUp(ITestContext context,String browser) {
		
		try {
			// Load the properties file
			FileInputStream file = new FileInputStream("configuration_2.properties path");
			properties = new Properties();
			try {
				properties.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Set up WebDriver
		 if (browser.equalsIgnoreCase("chrome")) {
	            // Set up the ChromeDriver
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("firefox")) {
	            // Set up the GeckoDriver for Firefox
	            WebDriverManager.firefoxdriver().setup();
	            driver = new FirefoxDriver();
	        } else if (browser.equalsIgnoreCase("safari")) {
	            // Set up the InternetExplorerDriver
	            WebDriverManager.safaridriver().setup();
	            driver = new SafariDriver();
	        } else {
	            throw new IllegalArgumentException("Invalid browser specified");
       }
		
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
		
		baseUrl = properties.getProperty("base.url");
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String device = capabilities.getBrowserName()+" "+capabilities.getBrowserVersion();
		String author = context.getCurrentXmlTest().getParameter("author");
		
		extentTest = BaseClass.extentReports.createTest(context.getName());
		extentTest.assignAuthor(author);
		extentTest.assignDevice(device);

	}
	
	@Test
	public void testFractionToDecimal() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String fracToDecLinkLocator = properties.getProperty("fractionToDecimal_link.Locator");
		String topTextBoxLocator = properties.getProperty("fractionToDecimal_topTextbox.Locator");
		String bottomTextBoxLocator = properties.getProperty("fractionToDecimal_bottomTextbox.Locator");
		String converItButtonLocator = properties.getProperty("converIt_button.Locator");
		
		String topNumber = properties.getProperty("top_number");
		String bottomNumber = properties.getProperty("bottom_number");
		
		extentTest.info("Nevigated to Fraction to Decimal page");
		driver.findElement(By.linkText(fracToDecLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(topTextBoxLocator)).clear();
		driver.findElement(By.name(topTextBoxLocator)).sendKeys(topNumber);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(bottomTextBoxLocator)).clear();
		driver.findElement(By.name(bottomTextBoxLocator)).sendKeys(bottomNumber);
		driver.findElement(By.xpath(converItButtonLocator)).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement actualAns = driver.findElement(By.xpath("/html/body/font/font/p[2]"));
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scrolling down the page till the element is found		
        js.executeScript("arguments[0].scrollIntoView();", actualAns);
        
        actualAns.getText();
        String expectedAns = "And that's about it!  1/4 written as a decimal to 3 decimal places is 0.250.";
		
		Assert.assertEquals(actualAns, expectedAns);
		extentTest.pass("Assertion is passed for Fractor To Decimal number");
				
	}
	
	@Test
	public void testDecimalToFraction() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String decToFracLinkLocator = properties.getProperty("decimalToFraction_link.Locator");
		String textBoxLocator = properties.getProperty("decimalToFraction_textbox.Locator");
		String buttonLocator = properties.getProperty("convertIt_button.Locator");
		
		String number = properties.getProperty("decimal_number");
		
		extentTest.info("Nevigated to Decimal to Fraction page");
		driver.findElement(By.linkText(decToFracLinkLocator)).click();
		driver.findElement(By.name(textBoxLocator)).sendKeys(number);
		driver.findElement(By.xpath(buttonLocator)).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Decimal To Factor number");
		
	}
	
	@AfterMethod
	public void tearDown(Method m, ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(result.getThrowable());
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(m.getName() + " is passed");
		}
		driver.quit();
	}
}
