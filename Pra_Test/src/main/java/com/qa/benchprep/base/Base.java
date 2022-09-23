/**
 * Contains in
 */

package com.qa.benchprep.base;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.annotations.AfterSuite;

import com.qa.benchprep.constant.Field;
import com.qa.benchprep.constant.Static;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
	public static Properties prop;
	public static Properties webconfig;
	public static Properties jsonconfig;
	public static Properties payloadconfig;
	public static WebDriver webdriver;

	/*
	 * Creating a folder to store downloaded files of the projects
	 */
	static File downloadedFiles = new File(System.getProperty(Static.USER_DIR), Static.DOWNLOADED_FILES);
	public static String DownloadFilepath = downloadedFiles.getAbsolutePath();

	/*
	 * Initialising Properties
	 */

	public static void initializeProperty() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(Static.CONFIGERATION_PROPERTIES_FILE_PATH);
			prop.load(fis);

			webconfig = new Properties();
			FileInputStream config_path = new FileInputStream(prop.getProperty(Field.WEB_CONFIG));
			webconfig.load(config_path);

			jsonconfig = new Properties();
			FileInputStream jsonconfig_path = new FileInputStream(prop.getProperty(Field.JSON_CONFIG));
			jsonconfig.load(jsonconfig_path);

			payloadconfig = new Properties();
			FileInputStream payloadconfig_path = new FileInputStream(prop.getProperty(Field.PAYLOAD_CONFIG));
			payloadconfig.load(payloadconfig_path);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * Initialising Webdriver
	 */
	@SuppressWarnings("deprecation")
	public WebDriver initializedriver() throws IOException {

		initializeProperty();

		// Initialising Chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePref = new HashMap<String, Object>();
		chromePref.put(Field.CHROME_DOWNLOAD_DEFAULT_DIRECTORY, DownloadFilepath);
		options.setExperimentalOption(Static.PREFERED_DOWNLOAD_DIRECTORY, chromePref);
		webdriver = new ChromeDriver(options);

		webdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webdriver.manage().window().maximize();
		return webdriver;

	}

//	public  void webgetScreenshot(String result) throws IOException {
//		File src = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
//		System.out.print(result);
//		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\screenshot\\"+result+ ".png"));
//	}

	public static ArrayList<String> webaccess(String input) throws IOException {
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(prop.getProperty(Field.TESTDATA));
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).contains(input)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row first = rows.next();
				Iterator<Cell> ce = first.cellIterator();
				int k = 0;
				int column = 0;

				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("UserName")) {
						column = k;
					}
					k++;
				}

				System.out.println(column);
				while (rows.hasNext()) {
					Row r = rows.next();
					// System.out.println(r.getCell(column)+"**");
					// if (r.getCell(column).getStringCellValue().equalsIgnoreCase(input)) {
					// System.out.println("yesss");
					Iterator<Cell> cv = r.cellIterator();

					while (cv.hasNext()) {
						Cell c = cv.next();
						if (c.getCellType() == CellType.STRING) {
							a.add(c.getStringCellValue());
						} else if (c.getCellType() == CellType.NUMERIC) {

							a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
						}

					}

				}
			}
		}
		return a;
	}

	public static ArrayList<String> webAccessTestCase() throws IOException {
		// public String TestId=null;
		Cell d = null;
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream(prop.getProperty(Field.TESTDATA));
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).contains("Sheet1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row first = rows.next();
				Iterator<Cell> ce = first.cellIterator();
				int k = 0;
				@SuppressWarnings("unused")
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("Test Case ID")) {
						column = k;
					}
					k++;
				}
				// System.out.println(column);
				while (rows.hasNext()) {
					Row r = rows.next();
					Iterator<Cell> cv = r.cellIterator();
					while (cv.hasNext()) {
						Cell c = cv.next();

						if (c.getStringCellValue().equalsIgnoreCase("Y")) {
							a.add(d.getStringCellValue());
							// a.add(value.getStringCellValue());
							// a.add(c.getStringCellValue());
							while (cv.hasNext()) {
								c = cv.next();
								/*
								 * if(c.getCellTypeEnum()== CellType.STRING) { System.out.println(c+"if block");
								 * a.add(c.getStringCellValue()); } else
								 * if(c.getCellTypeEnum()==CellType.NUMERIC) {
								 * System.out.println(c+"else block"); a.add(
								 * NumberToTextConverter.toText(c.getNumericCellValue())); }
								 */
							}

						}

						d = c;
					}
				}

			}
		}
		return a;
	}

	@AfterSuite
	public void teardown() {
		webdriver.close();
	}

}
