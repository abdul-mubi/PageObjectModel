package com.php.qa.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.php.qa.base.BaseClass;

public class TestUtils extends BaseClass {
	
	public static long page_load_timeout = 20;
	public static long implicit_timeout = 20;
	
	public static String path = "/Users/abdul/eclipse-workspace/DemoProject/src/main/java/com/php/qa/testdata/TestData.xlsx" ;
	
	public static XSSFWorkbook book;
	public static XSSFSheet sheet;
	
	
	public static void switchToFrame() {
		
		driver.switchTo().frame("mainpanel");
	}

	public static Object[][] addTestData(String sheetname) {
		FileInputStream fis = null;
		File file;
		
			try {
				file = new File(path);
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
			try {
				book = new XSSFWorkbook(fis);
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
			sheet = book.getSheet(sheetname);
			
			
			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(i).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					
				}
			}
			return data;
		}
	
	public static void calendar(String birthDate) {
		
        String[] arryDate = birthDate.split("-");
		
		String date = arryDate[0].toString();
		
		String month = arryDate[1].toString();
		
		String year = arryDate[2].toString();
		
		int yr = Integer.parseInt(year);
		//int dt = Integer.parseInt(date);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		for(int i =2020; i>yr;i--){
			
			driver.findElement(By.xpath("/html/body/div[6]/table/thead/tr[2]/td[1]/div")).click();
		}
		
		//driver.findElement(By.cssSelector("body > div.calendar > table > thead > tr.headrow > td:nth-child(4) > div")).click();
		
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.cssSelector("body > div.calendar > table > thead > tr.headrow > td:nth-child(4) > div"))).clickAndHold().build().perform();
		
		//driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]"));
		//html/body/div[7]/div[1]/div[7]
		String xpath = "/html/body/div[6]/div[1]/div[";
		
		for(int i=1; i<=12; i++) {
			
			String mon = driver.findElement(By.xpath(xpath+i+"]")).getText();
			
			if(mon.equals(month)) {
				
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath+i+"]"))));
				
				act.moveToElement(driver.findElement(By.xpath(xpath+i+"]"))).click().build().perform();
			
				break;
			}
		}
		//html/body/div[6]/table/tbody/tr[6]/td[8]
		//html/body/div[6]/table/tbody
		//html/body/div[6]/table/tbody/tr[2]/td[3]
		//html/body/div[6]/table/tbody/tr[1]/td[5]
		
		String startXPath = "/html/body/div[6]/table/tbody/tr[";
		String endXPath = "]/td[";
		
		boolean flag = false;
		
		for(int i=1; i<=6; i++) {
			for(int j=2; j<=8; j++) {
				
				WebElement ele = driver.findElement(By.xpath(startXPath+i+endXPath+j+"]"));
				
				String out = ele.getText();
				
				
				if(out.equals(date)) {
					driver.findElement(By.xpath(startXPath+i+endXPath+j+"]")).click();
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
	}
	
  public static void calendarJS(String dateVal, WebElement element) {
	  
	  JavascriptExecutor jse = ((JavascriptExecutor)driver);
	  jse.executeScript("arguments[0].setAttribute('value','"+dateVal+"');", element);
  }

  public static void screenshot() {
	  
	  try {
		  
		  TakesScreenshot tss =((TakesScreenshot)driver);
			
		  File sourcefile = tss.getScreenshotAs(OutputType.FILE);
		  
		  File destinationfile = new File("./screenshots/"+System.currentTimeMillis()+".png");
		  
		  FileUtils.copyFile(sourcefile, destinationfile);
		  
	} catch (Exception e) {
		
		System.out.println("Some Error Occured - "+e.getMessage());
	} 
  }
  
  public static void pageScroll(WebElement element) {
	  
	  JavascriptExecutor jse = ((JavascriptExecutor) driver);
	  jse.executeScript("arguments[0].scrollIntoView()", element);
  }
  
  
  public static String addTestData1(String sheetname) {
		FileInputStream fis = null;
		File file;
		
			try {
				file = new File(path);
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		
			try {
				book = new XSSFWorkbook(fis);
			} catch (EncryptedDocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
			sheet = book.getSheet(sheetname);
			
			
			String data = sheet.getRow(3).getCell(3).getStringCellValue();
			
			System.out.println(data);
			return data;
		}
  
  
}