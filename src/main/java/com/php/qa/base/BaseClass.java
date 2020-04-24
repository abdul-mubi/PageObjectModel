package com.php.qa.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.php.qa.util.TestUtils;
import com.php.qa.util.WebEventListener;

public class BaseClass {
	
	public static Properties prop;
	public static WebDriver driver;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener webeventlistener;
	
	public BaseClass() {
		
		prop = new Properties();

		try {
				FileInputStream ip = new FileInputStream("/Users/abdul/eclipse-workspace/DemoProject/src/main/java/com/php/qa/"
						+ "env_var/config.properties");
				prop.load(ip);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	public static void initialization()
	{
		
		String browserNAme = prop.getProperty("BROWSER");
		
		if(browserNAme.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.crome.driver", "chromedriver"); 
			
			driver = new ChromeDriver();
		}else if (browserNAme.equalsIgnoreCase("FF")) {
			
            System.setProperty("webdriver.firefox.driver", "firefoxdriver"); 
			
			driver = new FirefoxDriver();
		}
		
		/*e_driver = new EventFiringWebDriver(driver);
		webeventlistener = new WebEventListener();
		e_driver.register(webeventlistener);
		driver = e_driver;*/
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtils.page_load_timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtils.implicit_timeout, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("URL"));
	}
	
	
	
	

}
