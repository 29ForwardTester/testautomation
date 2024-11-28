package managers;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class DataManager {
	
	public  static HSSFWorkbook workbook;
	public  static HSSFSheet worksheet;
	public  HSSFCell cell;
	

//	public  static String path ="C:\\Users\\User29F\\Desktop\\testData\\mydata.xls";
//	public  static String sheetname ="Sheet1";
	
	public  static void setDataFile(String npath, String nsheet) throws Exception { //access the data
		
		try {
			FileInputStream excelFile = new FileInputStream(npath); //open the excel file
			//load the data into workbooks
			workbook = new HSSFWorkbook(excelFile);
			worksheet = workbook.getSheet(nsheet);
			//cell = worksheet.getRow(0).getCell(0);
//			String valueOfCell = cell.getStringCellValue();
//			System.out.println(valueOfCell);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public  static HSSFCell[] findCells(String tableName ) {
		
		String pos = "begin";
		HSSFCell[] cells = new HSSFCell[2];
		
		try {
		for (Row row : worksheet) {
			for (Cell cell : row) {
				if (tableName.equals(cell.getStringCellValue())){
					if(pos.equalsIgnoreCase("begin")) {
						cells[0] = (HSSFCell) cell;
						pos ="end";
					} else {
						cells[1] = (HSSFCell) cell;
					}
						
				}
				
			}
		}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
		}
		return cells;
	}

	
	public  static String[][] getTestData(String dataName) {
		
		String[][] testData = null;
		
		try {
			
			HSSFCell[] boundaryCell = findCells(dataName);
			HSSFCell startCell = boundaryCell[0];
			HSSFCell endCell = boundaryCell[1];
			
			int startRow = startCell.getRowIndex() + 1;
			int endRow = endCell.getRowIndex() - 1;
			int startColumn = startCell.getColumnIndex() + 1;
			int endColumn = endCell.getColumnIndex() - 1;
			
			testData = new String[endRow - startRow + 1][endColumn - startColumn +1];
			
			for(int i = startRow; i < endRow +1; i++ ) {
				for (int j = startColumn; j < endColumn + 1; j++ ) {
					testData[i - startRow][j - startColumn] = worksheet.getRow(i).getCell(j).getStringCellValue();
				}
			}

			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		return  testData;
	}
	

	@DataProvider(name="mydata")
	public  String[][]testDataProvider(String dataname){
			return  getTestData(dataname);
		
		
	}
	
	
	
	
	

}
