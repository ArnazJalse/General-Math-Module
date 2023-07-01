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
import org.openqa.selenium.WebDriver;
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

public class ScientificNotationComponent extends BaseClass{

	private WebDriver driver;
	private Properties properties;
	private String baseUrl;

	 @Parameters("browser")
	@BeforeMethod
	public void setUp(ITestContext context,String browser) {

		try {
			// Load the properties file
			FileInputStream file = new FileInputStream("configuration_4.properties path");
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
	public void testMultiplyTwoNumWithTwoScientificNotation() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String multiplyWithlinkL = properties.getProperty("multiplyWith_link.Locator");
		String textboxLocator1 = properties.getProperty("textbox1.Locator");
		String textboxLocator2 = properties.getProperty("textbox2.Locator");
		String textboxLocator3 = properties.getProperty("textbox3.Locator");
		String textboxLocator4 = properties.getProperty("textbox4.Locator");
		String multiplyButtonLocator = properties.getProperty("multiply_button.Locator");
		
		String num1 = properties.getProperty("number1");
		String num2 = properties.getProperty("number2");
		String num3 = properties.getProperty("number3");
		String num4 = properties.getProperty("number4");
		
		extentTest.info( "Nevigate to Multiply with Scientific Notation page");
		driver.findElement(By.linkText(multiplyWithlinkL)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(textboxLocator1)).sendKeys(num1);
		driver.findElement(By.name(textboxLocator2)).sendKeys(num2);
		driver.findElement(By.name(textboxLocator3)).sendKeys(num3);
		driver.findElement(By.name(textboxLocator4)).sendKeys(num4);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(multiplyButtonLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String actualAns = "8*10^6";
		Assert.assertEquals(actualAns, "8*10^6");
		extentTest.pass("Assertion is passed for Multiply Two Number With Two Scientific Notation");
	}
	
	@Test
	public void testMultiplyTwoNumWithSingleScientificNotation() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String multiplyWithlinkL = properties.getProperty("multiplyWith_link.Locator");
		String textboxL1 = properties.getProperty("mul_textbox1.Locator");
		String textboxL2 = properties.getProperty("mul_textbox2.Locator");
		String textboxL3 = properties.getProperty("mul_textbox3.Locator");
		String multiplyButtonL = properties.getProperty("multiply1_button.Locator");
		
		String num1 = properties.getProperty("mul_num1");
		String num2 = properties.getProperty("mul_num2");
		String num3 = properties.getProperty("mul_num3");
		
		extentTest.info( "Nevigate to Multiply with Scientific Notation page");
		driver.findElement(By.linkText(multiplyWithlinkL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(textboxL1)).sendKeys(num1);
		driver.findElement(By.name(textboxL2)).sendKeys(num2);
		driver.findElement(By.name(textboxL3)).sendKeys(num3);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(multiplyButtonL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Multiply Two Number With Single Scientific Notation");
	}
	
	@Test
	public void testDivideTwoNumBothInScientificNotation() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String divideWithLinkL = properties.getProperty("divideWith_link.Locator");
		String divTextboxL1 = properties.getProperty("div_textbox1.Locator");
		String divTextboxL2 = properties.getProperty("div_textbox2.Locator");
		String divTextboxL3 = properties.getProperty("div_textbox3.Locator");
		String divTextboxL4 = properties.getProperty("div_textbox4.Locator");
		String divideButtonL = properties.getProperty("divide_button.Locator");
		
		String divNum1 = properties.getProperty("div_num1");
		String divNum2 = properties.getProperty("div_num2");
		String divNum3 = properties.getProperty("div_num3");
		String divNum4 = properties.getProperty("div_num4");
		
		extentTest.info( "Nevigate to Divide with Scientific Notation page");
		driver.findElement(By.linkText(divideWithLinkL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(divTextboxL1)).sendKeys(divNum1);
		driver.findElement(By.name(divTextboxL2)).sendKeys(divNum2);
		driver.findElement(By.name(divTextboxL3)).sendKeys(divNum3);
		driver.findElement(By.name(divTextboxL4)).sendKeys(divNum4);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(divideButtonL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Divide Two Number Both In Scientific Notation");
	}
	
	@Test
	public void testDivideTwoNumOneOfThemIsScientificNotation() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String divideWithLinkL = properties.getProperty("divideWith_link.Locator");
		String divTextboxL1 = properties.getProperty("div_textbox.Locator1");
		String divTextboxL2 = properties.getProperty("div_textbox.Locator2");
		String divTextboxL3 = properties.getProperty("div_textbox.Locator3");
		String divideButtonL = properties.getProperty("divide_button.Locator2");
		
		String num1 = properties.getProperty("div1_num");
		String num2 = properties.getProperty("div2_num");
		String num3 = properties.getProperty("div3_num");
		
		extentTest.info( "Nevigate to Divide with Scientific Notation page");
		driver.findElement(By.linkText(divideWithLinkL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(divTextboxL1)).sendKeys(num1);
		driver.findElement(By.name(divTextboxL2)).sendKeys(num2);
		driver.findElement(By.name(divTextboxL3)).sendKeys(num3);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(divideButtonL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(false);
		extentTest.pass("Assertion is passed for Divide Two Number One Of Them Is Scientific Notation");
	}
	
	@Test
	public void testDivideTwoNumOneOfThemIsScientificNotation2() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String divideWithLinkL = properties.getProperty("divideWith_link.Locator");
		String divTextboxL1 = properties.getProperty("div1_textbox.Locator");
		String divTextboxL2 = properties.getProperty("div2_textbox.Locator");
		String divTextboxL3 = properties.getProperty("div3_textbox.Locator");
		String divButtonL = properties.getProperty("div_button.Locator3");
		
		String num1 = properties.getProperty("div1_number");
		String num2 = properties.getProperty("div2_number");
		String num3 = properties.getProperty("div3_number");
		
		extentTest.info( "Nevigate to Divide with Scientific Notation page");
		driver.findElement(By.linkText(divideWithLinkL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(divTextboxL1)).sendKeys(num1);
		driver.findElement(By.name(divTextboxL2)).sendKeys(num2);
		driver.findElement(By.name(divTextboxL3)).sendKeys(num3);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(divButtonL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Divide Two Number One Of Them Is Scientific Notation 2");
	}
	
	@Test
	public void testConvertNumFromScientificToRegularNotation() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String covertingLinkLocator = properties.getProperty("convertind_linl.Locator");
		String conTextboxL1 = properties.getProperty("con_textbox1.Locator");
		String conTextboxL2 = properties.getProperty("con_textbox2.Locator");
		String convertItButtonL = properties.getProperty("convertIt_button.Locator");
		
		String conNum1 = properties.getProperty("con_num1");
		String conNum2 = properties.getProperty("con_num2");
		
		extentTest.info( "Nevigate to Converting page");
		driver.findElement(By.xpath(covertingLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(conTextboxL1)).sendKeys(conNum1);
		driver.findElement(By.name(conTextboxL2)).sendKeys(conNum2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(convertItButtonL)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Convert Number From Scientific To Regular Notation");
	}
	
	@Test
	public void ConvertNumFromRegularToScientificNotation() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String covertingLinkLocator = properties.getProperty("convertind_linl.Locator");
		String textboxL1 = properties.getProperty("textBox1.Locator");
		String convertItButtonL = properties.getProperty("Convert_button.Locator");
		
		String number = properties.getProperty("number1");
		
		extentTest.info( "Nevigate to Converting page");
		driver.findElement(By.xpath(covertingLinkLocator)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(textboxL1)).sendKeys(number);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(convertItButtonL)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String actualAns = "8*10^4";
		Assert.assertEquals(actualAns,"8*10^4" );
		extentTest.pass("Assertion is passed for Convert Number From Regular To Scientific Notation");
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
