package util.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import static java.util.Collections.unmodifiableMap;

public class ExcelUtility {
	public static Workbook getWorkBook ( String fileName ) 
	{
		try {
			File file = new File ( fileName );
			FileInputStream fis = new FileInputStream( file );
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook( fis );
			}
			if (fileName.toLowerCase().endsWith("xls")) {
				workbook = new HSSFWorkbook( fis );
			}
			return workbook;
		} catch (Exception e) {
			return null;
		}
	}
	public static HSSFSheet getSheet( String fileName, String sheetName )
	{
		try {
	        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream( fileName ));
	      //  HSSFWorkbook workbook = getWorkBook( fileName );

	        return workbook.getSheet( sheetName );
		} catch (Exception e) {
			//K2 : Implement Proper Error Message , Handle THis Exception
			return null;
		}
	}
	public static String getValue (HSSFSheet sheet, String key )
	{
		int rowCount = sheet.getLastRowNum();
		for (int i = 0; i < rowCount ; i++) 
		{
			Cell cell = sheet.getRow(i).getCell(0);
			if (cell.toString().trim().equals(key.trim())) 
				return sheet.getRow(i).getCell(1).toString().trim();
		}
		return null;
	}
	
	public static String getValue (HSSFSheet sheet, String key ,int position )
	{
		int rowCount = sheet.getLastRowNum();
		for (int i = 0; i < rowCount ; i++) 
		{
			Cell cell = sheet.getRow(i).getCell(0);
			if (cell.toString().trim().equals(key.trim())) 
				return sheet.getRow(i).getCell(position).toString().trim();
		}
		return null;
	}
	
	public static Map<String, Map<String, String>> setMapFromExcelData(String fileName, int sheetNumber) throws IOException{
		String path = "./TestData/" + fileName + ".xls";
		FileInputStream fis = new FileInputStream(path);
		Workbook workbook = new HSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		System.out.println("Sheet name is: " + sheet);
		int lastRow = sheet.getLastRowNum();
		System.out.println("Last row in excel: " + lastRow);
		Map<String, Map<String, String>> excelFileMap = new HashMap<>();
		
		//Looping over entire row
		for(int i = 1; i <= lastRow; i++) {
			Map<String, String> dataMap = new HashMap<>();
			Row row = sheet.getRow(i);
			for(int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				Cell keyCell = sheet.getRow(0).getCell(j);
				Cell valueCell = row.getCell(j);
				String value = "";
				if(valueCell != null) {
					switch(valueCell.getCellType()) {
						case STRING:
							value = valueCell.getStringCellValue();
							break;
						
						case NUMERIC:
							value = NumberToTextConverter.toText(valueCell.getNumericCellValue());
							break;
					}
				}else value = "";
				String key = keyCell.getStringCellValue().trim();
				dataMap.put(key, value);
			}
			if(sheet.getRow(i).getCell(0) != null) {
				excelFileMap.put(sheet.getRow(i).getCell(0).getStringCellValue().trim(), dataMap);
			}
		}
		return unmodifiableMap(excelFileMap);
		
	}

}
