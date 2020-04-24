package com.php.qa.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.php.qa.base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class TestNGListener extends BaseClass implements ITestListener {

	ExtentReports Extent = ExtentManager.createInstance();
	ThreadLocal<ExtentTest> ExtentTest= new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		
		ExtentTest test = Extent.createTest(result.getClass()+" :: " + result.getMethod().getMethodName());
		ExtentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		
		String logtext = "Test Method " + result.getMethod().getMethodName() + " Successful ";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
		ExtentTest.get().log(Status.PASS, m);	
	}

	public void onTestFailure(ITestResult result) {
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		ExtentTest.get().fail("<details><summary><b><font color=red>Exception Occured, click to see details: " +"</font></b></summary>"+ exceptionMessage.replace(",", "<br>")+"</details> \n");

		String path = takeScreenshot(result.getMethod().getMethodName());
		try {
			ExtentTest.get().fail("Screenshot of failure", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		} catch (IOException e) {
			ExtentTest.get().fail("Test Failed, Cannot attach screenshot");
		}

		String logtext = "Test Method " + result.getMethod().getMethodName() + " Failed ";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
		ExtentTest.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		
		String logtext = "Test Method " + result.getMethod().getMethodName() + " Successful ";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
		ExtentTest.get().log(Status.SKIP, m);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		
		if(Extent != null) {
			Extent.flush();
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
