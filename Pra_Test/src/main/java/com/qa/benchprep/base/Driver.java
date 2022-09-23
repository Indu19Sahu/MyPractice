/**
 * 
 */
package com.qa.benchprep.base;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.benchprep.constant.Field;

/**
 * @author Indu Sahu
 *
 */
public class Driver {
	
	private Driver() {
		
	}
	


	/**
	 * @param args
	 */
	
	
	
	
	public static void initDriver() {
		
		if(Objects.isNull(DriverManager.getDriver())) {
			System.setProperty("webdriver.chrome.driver", Field.getChromedriver());
			WebDriver driver = new ChromeDriver();
			DriverManager.setDriver(driver);
			DriverManager.getDriver().get("https://google.co.in");
		}
		
	}
	
	public static void quitDriver() {
		if(Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
		
	}

}
