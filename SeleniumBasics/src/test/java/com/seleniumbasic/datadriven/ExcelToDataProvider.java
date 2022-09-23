/**
 * 
 */
package com.seleniumbasic.datadriven;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Indu Sahu
 * This set of code is working in state
 */
public class ExcelToDataProvider {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream("D://Indu//Automation//Learning//Selenium 4//Test Data//Data Driven Automation.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(1);
		System.out.println(wb.getSheetAt(1).getSheetName());
		System.out.println(sheet.getLastRowNum());
		int rowCount = sheet.getPhysicalNumberOfRows();
		System.out.println(rowCount);
		XSSFRow row = sheet.getRow(1);
		int colCount = row.getLastCellNum();
		Object data[][] = new Object[rowCount-1][colCount];
		for(int i=0; i<rowCount; i++) {
			System.out.println("outer loop started");
			row = sheet.getRow(i+1);
			for (int j=0; j<colCount; j++) {
				System.out.println(row.getCell(j));
			}
			System.out.println("outer loop ended");
		}
		

	}

}
