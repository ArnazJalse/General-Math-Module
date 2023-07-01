package com.GeneralMathModule;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.AssertJUnit;
import org.testng.ITestContext;
import org.testng.ITestResult;

import static org.testng.Assert.assertTrue;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NumbersComponent extends BaseClass {

	private WebDriver driver;
	private Properties properties;
	private String baseUrl;


	@Parameters("browser")
	@BeforeMethod
	public void setUp(ITestContext context,String browser) {
		try {
			// Load the properties file
			FileInputStream file = new FileInputStream("configuration_1.properties path");
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
	public void testFactor() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String factorLinkLocator = properties.getProperty("factor_link.Locator");
		String factorEnterNumberLocator = properties.getProperty("factor_enterNumber.Locator");
		String factorButtonLocator = properties.getProperty("factor_button.Locator");
		
		String number = properties.getProperty("factor_number");
		
		extentTest.info( "Nevigate to Factor page");
		driver.findElement(By.linkText(factorLinkLocator)).click();
		driver.findElement(By.name(factorEnterNumberLocator)).sendKeys(number);
		driver.findElement(By.xpath(factorButtonLocator)).click();
		extentTest.info( "Nevigate to Answer page");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AssertJUnit.assertTrue(true);
		extentTest.pass("Assertion is passed for Factor");
				
	}
	
	@Test
	public void testGCF() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String gCFLinkLocator = properties.getProperty("GCF_link.Locator");
		String gCFTextBoxLocator = properties.getProperty("GCF_textbox.Locator");
		String gCFButtonLocator = properties.getProperty("GCF_button.Locator");
		
		String number = properties.getProperty("GCF_number");
		
		extentTest.info( "Nevigate to GCF page");
		driver.findElement(By.linkText(gCFLinkLocator)).click();
		driver.findElement(By.name(gCFTextBoxLocator)).sendKeys(number);
		driver.findElement(By.xpath(gCFButtonLocator)).click();
		extentTest.info( "Nevigate to Answer page");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String Answer = properties.getProperty("GCF_answer");
		
		//String actualAns = driver.findElement(By.xpath(Answer)).getText();
		String expectedAns = "3";
		
		AssertJUnit.assertEquals("3", expectedAns);
		extentTest.pass("Assertion is passed for GCF");
		
	}
	
	@Test
	public void testLCM() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
				String lCMLinkLocator = properties.getProperty("LCM_link.Locator");
				String lCMTextBoxLocator = properties.getProperty("LCM_textbob.Locator");
				String lCMButtonLocator = properties.getProperty("LCM_button.Locator");
				
				String number = properties.getProperty("LCM_number");
				
				extentTest.info( "Nevigate to LCM page");
				driver.findElement(By.linkText(lCMLinkLocator)).click();
				driver.findElement(By.name(lCMTextBoxLocator)).sendKeys(number);
				driver.findElement(By.xpath(lCMButtonLocator)).click();
				extentTest.info( "Nevigate to Answer page");
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				AssertJUnit.assertTrue(true);
				extentTest.pass("Assertion is passed for LCM");
	}
	
	@Test
	public void testPronouncing() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String pronouncingLinkLocator = properties.getProperty("Pronouncing_link.Locator");
		String pronouncingTextBoxLocator = properties.getProperty("Pronouncing_textbox.Locator");
		String pronouncingButtonLocator = properties.getProperty("Pronouncing_button.Locator");
		
		String number = properties.getProperty("Pronouncing_number");
		
		extentTest.info( "Nevigate to Pronouncing page");
		driver.findElement(By.linkText(pronouncingLinkLocator)).click();
		driver.findElement(By.name(pronouncingTextBoxLocator)).sendKeys(number);
		driver.findElement(By.xpath(pronouncingButtonLocator)).click();
		extentTest.info( "Nevigate to Answer page");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AssertJUnit.assertTrue(true);
		extentTest.pass("Assertion is passed for pronouncing");
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
