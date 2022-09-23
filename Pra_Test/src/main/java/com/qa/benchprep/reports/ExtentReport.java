package com.qa.benchprep.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.benchprep.base.Base;

import java.io.File;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class ExtentReport {
	Base b = new Base();
	
	ExtentReports extent = new ExtentReports();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {	
		 
		
		
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator+"test-output"+File.separator+"webreport.html");//Targeted file 
		extent.attachReporter(spark);
		extent.createTest("MyFirstTest").assignAuthor("Indu Sahu").assignDevice("Windows 10 Chrome 104.**").assignCategory("Regression")
		  .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
			
		
		extent.setSystemInfo("Name", "Error-Reporting");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Host Name", "Indu Sahu");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Indu Sahu");

		spark.config().setDocumentTitle("AutomationTesting Report");
		spark.config().setReportName("My AutomationTesting Report");
		spark.config().setTheme(Theme.DARK);
	}

	/**
	 * GetResult Method is to show result whether is passed/failed/skipped 
	 * */
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable(),
					MediaEntityBuilder
							.createScreenCaptureFromPath(
									System.getProperty("user.dir") + File.separator+"screenshot" + File.separator+ result.getName() + ".png")
							.build());

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
}
