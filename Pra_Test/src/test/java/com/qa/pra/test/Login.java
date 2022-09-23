/**
 * 
 */
package com.qa.pra.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.benchprep.constant.Field;

/**
 * @author Indu Sahu
 *
 */
public class Login {
	
	WebDriver driver;
	
	@BeforeTest
	public void setUp() {
		
		System.setProperty("webdriver.chrome.driver", Field.getChromedriver());
		driver = new ChromeDriver();
		driver.get("");
		
	}
	
	@Test
	public void test1() {
		
		driver.findElement(By.name("q")).sendKeys("Automatiokn", Keys.ENTER);
		driver.quit();
		
	}
	
	@Test
	public void test2() {
		
		driver.findElement(By.name("q")).sendKeys("Selenium", Keys.ENTER);
	
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
	

}
