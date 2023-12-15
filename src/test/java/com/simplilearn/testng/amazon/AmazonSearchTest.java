package com.simplilearn.testng.amazon;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonSearchTest {

	// step1: formulate a test domain url & driver path
	String siteUrl = "https://www.amazon.in/";
	String driverPath = "drivers/windows/geckodriver.exe";
	WebDriver driver;
	ExtentSparkReporter htmlReporter;
	ExtentReports extentReports;
	ExtentTest test1, test2 ;
	
	@BeforeClass
	public void beforeClass() {

		// create the htmlReporter object
		htmlReporter = new ExtentSparkReporter("extentReport.html");

		// create ExtentReport and attach this reports
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);

		
		// initialize and start the browser
		WebDriverManager.chromedriver().setup();
				
		// step2: set system properties for selenium dirver
		System.setProperty("webdriver.geckodriver.driver", driverPath);

		// step3: instantiate selenium webdriver
		driver = new FirefoxDriver();

		// step4: launch browser
		driver.get(siteUrl);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
		extentReports.flush();
	}

	@Test(description = "Search Iphone 15 pro max")
	public void testSearch1() throws InterruptedException {
		// create a test and add logs
		test1 = extentReports.createTest("Amazon Search Iphone 15 pro max", "Test Amazon Search Iphone 15 pro max");
		test1.log(Status.INFO, "Starting test case");
		
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		test1.pass("Find search box on www.amazon.in");
		
		searchBox.sendKeys("Iphone 15 pro max");
		test1.pass("Enter input data 'Iphone 15 pro max' ");
		
		searchBox.submit();
		test1.pass("Click Submit");
		
		// add delay
		Thread.sleep(2000);

		String expectedTitle = "Amazon.in : Iphone 15 pro max";
		String actualTitle = driver.getTitle();

		assertEquals(actualTitle, expectedTitle);
		test1.pass("After search the title are matched");
		
		test1.log(Status.INFO, "End test case");
	}

	@Test(description = "Search Apple Mac book")
	public void testSearch2() throws InterruptedException {
		// create a test and add logs
		test2 = extentReports.createTest("Amazon Search Apple Mac book", "Test Amazon Search Apple Mac book");
		test2.log(Status.INFO, "Starting test case");
		
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		test2.pass("Find search box on www.amazon.in");
		
		searchBox.clear();
		test2.pass("Clear search box for previous searches");
		
		searchBox.sendKeys("Apple Mac book");
		test2.pass("Enter input data 'Apple Mac book' ");
		
		searchBox.submit();
		test2.pass("Click Submit");
		
		// add delay
		Thread.sleep(2000);

		String expectedTitle = "Amazon.in : Apple Mac book";
		String actualTitle = driver.getTitle();

		assertEquals(actualTitle, expectedTitle);
		test2.pass("After search the title are matched");
		
		test2.log(Status.INFO, "End test case");
	}

}
