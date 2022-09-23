/**
 * 
 */
package com.qa.benchprep.base;

import org.openqa.selenium.WebDriver;

/**
 * @author Techment Technology
 *
 */
public class DriverManager {

	/**
	 * @param args
	 */
	private DriverManager() {
		
	}
	
	public static ThreadLocal<WebDriver> dr = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return dr.get();
	}

	public static void setDriver(WebDriver driverref) {
		dr.set(driverref);
	}

	public static void unload() {
		dr.remove();
	}
}
