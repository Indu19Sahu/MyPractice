/**
 * 
 */
package com.qa.pra.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * @author Indu Sahu
 *
 */
public class DataDrivenTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream("D://Indu//Automation//Learning//Selenium 4//Test Data//Automation.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		int sheets = wb.getNumberOfSheets();
		for(int i=0; i<sheets; i++) {
			
			
			if(wb.getSheetName(i).equalsIgnoreCase("Data Driven")) {
			XSSFSheet sheet = wb.getSheetAt(i);	
			
			//Identify testcases column by scanning the entire row
			Iterator<Row> rows = sheet.rowIterator(); //sheet is collection of Rows
			Row firstRow =  rows.next();
			Iterator<Cell> ce =  firstRow.cellIterator(); //Rows are collection of cells
			int k=0;
			int column =0;
			while(ce.hasNext()) {
				Cell value = ce.next();
				if(value.getStringCellValue().equalsIgnoreCase("Testcases")) {
					column=k;
				}
				k++;
			}
			System.out.println(column);
			
			//Once column is identified then scan entire testcase to identify purchase test case row
			
			while(rows.hasNext()) {
				Row r = rows.next();
				if(r.getCell(column).getStringCellValue().equalsIgnoreCase("Purchase")) {
					Iterator<Cell> cv = r.cellIterator();
					while (cv.hasNext()) {
						System.out.println(cv.next().getStringCellValue());
					}
				}
			}
			
			}
		}

	}

}
