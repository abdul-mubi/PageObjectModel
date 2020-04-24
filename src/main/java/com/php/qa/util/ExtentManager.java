package com.php.qa.util;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	
	
	public static String getReportName() {

		Date d = new Date();
		String ReportName = "Automation_Report" + "_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		return ReportName;
	}
	
	
	public static ExtentReports createInstance() {
		
		String reportName = getReportName();
		String directory = System.getProperty("user.dir")+"/reports/";
		new File(directory).mkdirs();
		String path = directory+reportName;
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.STANDARD);

		ExtentReports extent = new ExtentReports();
		extent.setSystemInfo("Organization", "ABD");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);
		
		return extent;
	}

}
