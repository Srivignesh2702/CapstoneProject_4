package com.restassured;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;


public class ExcelData {
	public static Object[][] readExcelData(String filePath, String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum() + 1;
        int columnCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                data[i][j] = cell.getStringCellValue();
            }
        }

        workbook.close();
        file.close();
        return data;
    }
}
