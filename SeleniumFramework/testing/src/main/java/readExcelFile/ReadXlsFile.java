package readExcelFile;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXlsFile {
	
	XSSFWorkbook workbook =null;
	XSSFSheet sheet = null;
	
	public int getRowCount() {
		int rowCount = 0;
		try {
			workbook= new XSSFWorkbook("testData.xlsx");
			sheet = workbook.getSheet("Sheet1");
			rowCount = sheet.getPhysicalNumberOfRows();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.getStackTrace();
		}
		return rowCount;
	}
	
	public int getColCount() {
		int colCount = 0;
		try {
			workbook= new XSSFWorkbook("testData.xlsx");
			sheet = workbook.getSheet("Sheet1");
			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.getStackTrace();
		}
		return colCount;
	}
	
	public String getCellData(String rowData) {
		String colValue = null;
		try {
			workbook= new XSSFWorkbook("testData.xlsx");
			sheet = workbook.getSheet("Sheet1");
			
			for(int i=1; i< getRowCount(); i++) {
				if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(rowData)) {
					colValue =  sheet.getRow(i).getCell(2).getStringCellValue();
					System.out.println(rowData+" : " + colValue);
					break;
				}				
			}			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.getStackTrace();
		}
		return colValue;	
	}
	

}
