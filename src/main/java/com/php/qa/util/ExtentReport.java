package com.php.qa.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/reports/extent.html");
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "ABD");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.selenium.dev/");
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
		extent.flush();
	}

	@Test
	public void testSuccess() {

		extentTest = extent.createTest("Successfull Test");

	}

	@Test
	public void testFailed() {

		extentTest = extent.createTest("Failed Test");
		Assert.fail("Executing Failed Test Case");

	}

	@Test
	public void testSkipped() {

		extentTest = extent.createTest("Skipped Test");
		throw new SkipException("Executing Skipped Test Case");

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {

		String methodName = result.getMethod().getMethodName();

		if (result.getStatus() == ITestResult.FAILURE) {

			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			extentTest.fail("<details><summary><b><font color=red>Exception Occured, click to see details: " +"</font></b></summary>"+ exceptionMessage.replace(",", "<br>")+"</details> \n");

			String path = takeScreenshot(methodName);
			try {
				extentTest.fail("Screenshot of failure", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (IOException e) {
				extentTest.fail("Test Failed, Cannot attach screenshot");
			}

			String logtext = "Test Method " + methodName + " Failed ";
			Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
			extentTest.log(Status.FAIL, m);
		} else if (result.getStatus() == ITestResult.SUCCESS) {

			String logtext = "Test Method " + methodName + " Successful ";
			Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
			extentTest.log(Status.PASS, m);
		} else if (result.getStatus() == ITestResult.SKIP) {

			String logtext = "Test Method " + methodName + " Skipped ";
			Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
			extentTest.log(Status.SKIP, m);
		}

	}

	public String takeScreenshot(String methodName) {

		String filename = getScreenshootName(methodName);
		String directory = System.getProperty("user.dir") + "/Screenshots/";
		new File(directory).mkdirs();
		String path = directory + filename;

		try {
			TakesScreenshot scr = ((TakesScreenshot) driver);
			File srcFile = scr.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(path));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

	public static String getScreenshootName(String methodName) {
		

		Date d = new Date();
		String FileName = methodName + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return FileName;
	}

}
