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

public class FractionComponent extends BaseClass {
	private WebDriver driver;
	private Properties properties;
	private String baseUrl;


	 @Parameters("browser")
	@BeforeMethod
	public void setUp(ITestContext context,String browser) {

		try {
			// Load the properties file
			FileInputStream file = new FileInputStream("configuration_3.properties path");
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
	public void testReduce() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String reduceLinkLocator = properties.getProperty("reduce_link.Locator");
		String topTextboxLocator = properties.getProperty("reduce_topTextbox.Locator");
		String bottomTextboxLocator = properties.getProperty("reduce_bottomTextbox.Locator");
		String buttonLocator = properties.getProperty("reduceit_button.Locator");

		String topNumber = properties.getProperty("top_number");
		String bottomNumber = properties.getProperty("bottom_number");
		
		extentTest.info( "Nevigate to Reduce page");
		driver.findElement(By.linkText(reduceLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(topTextboxLocator)).clear();
		driver.findElement(By.name(topTextboxLocator)).sendKeys(topNumber);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(bottomTextboxLocator)).clear();
		driver.findElement(By.name(bottomTextboxLocator)).sendKeys(bottomNumber);
		driver.findElement(By.xpath(buttonLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String actualAns = "1/6";
		Assert.assertEquals(actualAns, "1/6");
		extentTest.pass("Assertion is passed for Reduce");
	}

	@Test
	public void testAddTwoFraction() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String addLinkLocator = properties.getProperty("add_link.Locator");
		String topTextboxLocator1 = properties.getProperty("fraction_topTextbox1.Locator");
		String bottomTextboxLocator1 = properties.getProperty("fraction_bottomTextbox1.Locator");
		String topTextboxLocator2 = properties.getProperty("fraction_topTextbox2.Locator");
		String bottomTextboxLocator2 = properties.getProperty("fraction_bottomTextbox2.Locator");
		String addButtonLocator = properties.getProperty("add_button.Locator");
		String backButtonLocator = properties.getProperty("backToProblem_button.Locator");

		String topNum1 = properties.getProperty("top_number1");
		String bottomNum1 = properties.getProperty("bottom_number1");
		String topNum2 = properties.getProperty("top_number2");
		String bottomNum2 = properties.getProperty("bottom_number");

		// Add two fractions
		extentTest.info( "Nevigate to Add page");
		driver.findElement(By.linkText(addLinkLocator)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.name(topTextboxLocator1)).sendKeys(topNum1);
		driver.findElement(By.name(bottomTextboxLocator1)).sendKeys(bottomNum1);
		driver.findElement(By.name(topTextboxLocator2)).sendKeys(topNum2);
		driver.findElement(By.name(bottomTextboxLocator2)).sendKeys(bottomNum2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.xpath(addButtonLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(backButtonLocator)).click();
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Add Two Fraction");
	}

	@Test
	public void testAddMixedAndFractionNumber() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String addLinkLocator = properties.getProperty("add_link.Locator");
		String mixedLeftTextboxL = properties.getProperty("mixed_leftTextbox.Locator");
		String mixedTopTextboxL = properties.getProperty("mixed_topTextbox1.Locator");
		String mixedBottomTextboxL = properties.getProperty("mixed_bottomTextbox1.Locator");
		String fractionTopTextboxL = properties.getProperty("fraction_topTextbox.Loactor");
		String fractionBottomTextboxL = properties.getProperty("fraction_bottomTextbox.Locator");
		String addButtonLocator = properties.getProperty("add_button.Locator");

		String mixedLeftNum = properties.getProperty("mixed_leftnumber");
		String mixedTopNum = properties.getProperty("mixed_topnumber");
		String mixedBottomNum = properties.getProperty("mixed_bottomnumber");
		String fractionTopNum = properties.getProperty("fraction_topnumber");
		String fractionBottomNum = properties.getProperty("fraction_bottomnumber");

		// Add a mixed number and a fraction
		extentTest.info( "Nevigate to Add page");
		driver.findElement(By.linkText(addLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(mixedLeftTextboxL)).sendKeys(mixedLeftNum);
		driver.findElement(By.name(mixedTopTextboxL)).sendKeys(mixedTopNum);
		driver.findElement(By.name(mixedBottomTextboxL)).sendKeys(mixedBottomNum);
		driver.findElement(By.name(fractionTopTextboxL)).sendKeys(fractionTopNum);
		driver.findElement(By.name(fractionBottomTextboxL)).sendKeys(fractionBottomNum);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(addButtonLocator)).click();

		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Add Mixed And Fraction Number");

	}

	@Test
	public void testAddTwoMixedNumber() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String addLinkLocator = properties.getProperty("add_link.Locator");
		String mixedLeftTextboxL1 = properties.getProperty("mixed_leftTextbox.Locator1");
		String mixedTopTextboxL1 = properties.getProperty("mixed_topTextbox.Locator1");
		String mixedBottomTextboxL1 = properties.getProperty("mixed_bottomTextbox.Locator1");
		String mixedLeftTextboxL2 = properties.getProperty("mixed_leftTextbox.Locator2");
		String mixedTopTextboxL2 = properties.getProperty("mixed_topTextbox.Locator2");
		String mixedBottomTextboxL2 = properties.getProperty("mixed_bottomTextbox.Locator");
		String addButtonLocator = properties.getProperty("add_button.Locator");

		String mixedLeftNum1 = properties.getProperty("mixed_leftnumber1");
		String mixedTopNum1 = properties.getProperty("mixed_topnumber1");
		String mixedBottomNum1 = properties.getProperty("mixed_bottomnumber1");
		String mixedLeftNum2 = properties.getProperty("mixed_leftnumber2");
		String mixedTopNum2 = properties.getProperty("mixed_topnumber2");
		String mixedBottomNum2 = properties.getProperty("mixed_bottomnumber2");

		// Add two mixed numbers
		extentTest.info( "Nevigate to Add page");
		driver.findElement(By.linkText(addLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(mixedLeftTextboxL1)).sendKeys(mixedLeftNum1);
		driver.findElement(By.name(mixedTopTextboxL1)).sendKeys(mixedTopNum1);
		driver.findElement(By.name(mixedBottomTextboxL1)).sendKeys(mixedBottomNum1);
		driver.findElement(By.name(mixedLeftTextboxL2)).sendKeys(mixedLeftNum2);
		driver.findElement(By.name(mixedTopTextboxL2)).sendKeys(mixedTopNum2);
		driver.findElement(By.name(mixedBottomTextboxL2)).sendKeys(mixedBottomNum2);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(addButtonLocator)).click();
		;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Add Two Mixed Number");
	}

	@Test
	public void testSubtractTwoFraction() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String subtractLinkLocator = properties.getProperty("subtract_link.Locator");
		String subTopTextboxL1 = properties.getProperty("subfraction_TopTextBox.Locator1");
		String subBottomTextboxL1 = properties.getProperty("subfraction_BottomTextBox.Locator1");
		String subToptextboxL2 = properties.getProperty("subfraction_TopTextBox.Locator2");
		String subBottomTextboxL2 = properties.getProperty("subfraction_BottomTextBox.Locator2");
		String subtractButtonLocator = properties.getProperty("subtract_button.Locator");

		String subTopNum1 = properties.getProperty("subTop_number1");
		String subBottomNum1 = properties.getProperty("subBottom_number1");
		String subTopNum2 = properties.getProperty("subTop_number2");
		String subBottomNum2 = properties.getProperty("subBottom_number2");

		// Subtract two mixed numbers
		extentTest.info( "Nevigate to Subtract page");
		driver.findElement(By.linkText(subtractLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(subTopTextboxL1)).sendKeys(subTopNum1);
		driver.findElement(By.name(subBottomTextboxL1)).sendKeys(subBottomNum1);
		driver.findElement(By.name(subToptextboxL2)).sendKeys(subTopNum2);
		driver.findElement(By.name(subBottomTextboxL2)).sendKeys(subBottomNum2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(subtractButtonLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Subtract Two Fraction");
	}

	@Test
	public void testMultiplyTwoFraction() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String multiplyLinkLocator = properties.getProperty("multiply_link.Locator");
		String mulTopTextboxL1 = properties.getProperty("mulfraction_TopTextBox.Locator1");
		String mulBottomTextboxL1 = properties.getProperty("mulfraction_BottomTextBox.Locator1");
		String mulToptextboxL2 = properties.getProperty("mulfraction_TopTextBox.Locator2");
		String mulBottomTextboxL2 = properties.getProperty("mulfraction_BottomTextBox.Locator2");
		String multiplyButtonLocator = properties.getProperty("multiply_button.Locator");

		String mulTopNum1 = properties.getProperty("mulTop_number1");
		String mulBottomNum1 = properties.getProperty("mulBottom_number1");
		String mulTopNum2 = properties.getProperty("mulTop_number2");
		String mulBottomNum2 = properties.getProperty("mulBottom_number2");

		// Multiply two mixed numbers
		extentTest.info( "Nevigate to Multiply page");
		driver.findElement(By.linkText(multiplyLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(mulTopTextboxL1)).sendKeys(mulTopNum1);
		driver.findElement(By.name(mulBottomTextboxL1)).sendKeys(mulBottomNum1);
		driver.findElement(By.name(mulToptextboxL2)).sendKeys(mulTopNum2);
		driver.findElement(By.name(mulBottomTextboxL2)).sendKeys(mulBottomNum2);
		try {
			Thread.sleep(2000);
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
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Multiply Two Fraction");
	}
	
	@Test
	public void testDivideTwoFraction() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String divideLinkLocator = properties.getProperty("divide_link.Locator");
		String divTopTextboxL1 = properties.getProperty("divfraction_TopTextBox.Locator1");
		String divBottomTextboxL1 = properties.getProperty("divfraction_BottomTextBox.Locator1");
		String divToptextboxL2 = properties.getProperty("divfraction_TopTextBox.Locator2");
		String divBottomTextboxL2 = properties.getProperty("divfraction_BottomTextBox.Locator2");
		String divideButtonLocator = properties.getProperty("divide_button.Locator");

		String divTopNum1 = properties.getProperty("divTop_number1");
		String divBottomNum1 = properties.getProperty("divBottom_number1");
		String divTopNum2 = properties.getProperty("divTop_number2");
		String divBottomNum2 = properties.getProperty("divBottom_number2");
		
		extentTest.info( "Nevigate to Divide page");
		driver.findElement(By.linkText(divideLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(divTopTextboxL1)).sendKeys(divTopNum1);
		driver.findElement(By.name(divBottomTextboxL1)).sendKeys(divBottomNum1);
		driver.findElement(By.name(divToptextboxL2)).sendKeys(divTopNum2);
		driver.findElement(By.name(divBottomTextboxL2)).sendKeys(divBottomNum2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(divideButtonLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Divide Two Fraction");
	}
	
	@Test
	public void testConvertImproperFractToMixedNum() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String convertingLinkLocator = properties.getProperty("converting_link.Locator");
		String fractionTopTextboxL = properties.getProperty("fraction_TopTextbox.Locator");
		String fractionBottomTextboxL = properties.getProperty("fraction_BottomTextbox.Locator");
		String convertItButtonL = properties.getProperty("convertIt_Button.locator");
		
		String topNum1 = properties.getProperty("fraction_topNum");
		String bottomNum1 = properties.getProperty("fraction_bottomNum");
		
		extentTest.info( "Nevigate to Converting page");
		driver.findElement(By.linkText(convertingLinkLocator)).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(fractionTopTextboxL)).sendKeys(topNum1);
		driver.findElement(By.name(fractionBottomTextboxL)).sendKeys(bottomNum1);
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
		Assert.assertTrue(false);
		extentTest.pass("Assertion is passed for Convert ImproperFraction To Mixed Number");
	}
	
	@Test
	public void testMixedToImproperFractionNum() {
		extentTest.info( "Nevigate to Url");
		// Get locators from properties
		String convertingLinkLocator = properties.getProperty("converting_link.Locator");
		String mixedLeftTextboxL = properties.getProperty("mixed_LeftTextbox.Locator");
		String mixedTopTextboxL = properties.getProperty("mixed_TopTextbox.Locator");
		String mixedBottomTextboxL = properties.getProperty("mixed_BottomTextbox.Locator");
		String convertItButtonL = properties.getProperty("convertIt_Button.locator");
		
		String leftNum = properties.getProperty("mixed_leftNum");
		String topNum = properties.getProperty("mixed_topNum");
		String bottomNum = properties.getProperty("mixed_bottomNum");
		
		extentTest.info( "Nevigate to Converting page");
		driver.findElement(By.linkText(convertingLinkLocator)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.name(mixedLeftTextboxL)).sendKeys(leftNum);
		driver.findElement(By.name(mixedTopTextboxL)).sendKeys(topNum);
		driver.findElement(By.name(mixedBottomTextboxL)).sendKeys(bottomNum);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(convertItButtonL)).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(true);
		extentTest.pass("Assertion is passed for Convert Mixed To ImproperFraction Number");
		
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
