package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class Utilities {

	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME = 10;

	public static String genarateEmailWithTimeStamp() {

		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "dilipdj21" + timeStamp + "@gmail.com";

	}

//	public static void main(String[] args) {
//		File fileExcel = new File(System.getProperty("user.dir")
//				+ "/src/main/java/com/tutorialninja/qa/testdata/TutorialsNinjaTestData.xlsx");
//		System.out.println(fileExcel.exists());
//		
//		
//	}

	public static Object[][] getTestDataFromExcel(String sheetName) {
		XSSFWorkbook workbook = null;

		File fileExcel = new File(System.getProperty("user.dir")
				+ "/src/main/java/com/tutorialninja/qa/testdata/TutorialsNinjaTestData.xlsx");

		try {
			FileInputStream fisExcel = new FileInputStream(fileExcel);
			workbook = new XSSFWorkbook(fisExcel);

		} catch (Throwable e) {
			e.printStackTrace();
		}

		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][columns];

		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < columns; j++) {

				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();

				switch (celltype) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;
	}

	public static String captureScreenshot(WebDriver driver, String testName) {

		File srcScreenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "/screenshots/" + testName + ".jpg";
//				System.getProperty("\\screenshots\\" + testName + ".png");

		try {
			FileHandler.copy(srcScreenShot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destinationScreenshotPath;

	}
}
