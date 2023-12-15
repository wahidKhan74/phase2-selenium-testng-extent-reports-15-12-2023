package com.simplilearn.testng;

import static org.testng.Assert.assertEquals;

import java.sql.Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class demonstrate extent report creation.
 * 
 * @author khanw
 *
 */
public class ExtentReport {

	// step1: formulate a test domain url & driver path
	static String siteUrl = "https://www.amazon.in/";
	static String driverPath = "drivers/windows/chromedriver.exe";
	static WebDriver driver;

	public static void main(String[] args) {

		// create the htmlReporter object
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReport.html");

		// create ExtentReport and attach this reports
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);

		// create a test and add logs
		ExtentTest test1 = extentReports.createTest("Amazon Title Match Test", "Test Amazon Home Page Title Match");

		// initialize and start the browser
		WebDriverManager.chromedriver().setup();
		// set system properties for selenium dirver
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();

		test1.log(Status.INFO, "Starting test case");

		// maximize the browser window
		driver.manage().window().maximize();
		test1.pass("Maximize the browser window");

		// Navigate Open amazon.in
		driver.get(siteUrl);
		test1.pass("Navigate Open www.amazon.in");

		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String actualTitle = driver.getTitle();
		assertEquals(actualTitle, expectedTitle);
		test1.pass("The title are matched");

		driver.quit();
		test1.pass("The Browser is quited");

		test1.log(Status.INFO, "Completed test case");

		extentReports.flush();
	}
}
