package managers;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;


public class DataManager {
	
	public  HSSFWorkbook workbook;
	public  HSSFSheet worksheet;
	public  HSSFCell cell;
	

	public  String path ="\"C:\\Users\\User29F\\Desktop\\testData\\mydata.xls\"";
	public  String sheetname ="Sheet1";
	
	public  void setDataFile() throws Exception { //access the data
		
		try {
			FileInputStream excelFile = new FileInputStream(path); //open the excel file
			
			//initialize the file readers
			workbook = new HSSFWorkbook(excelFile);
			worksheet = workbook.getSheet(sheetname);
			
			cell = worksheet.getRow(1).getCell(1);
			
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public  String[][] getTestData(String dataName) {
		
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
		
		return testData;
	}
	
	public  HSSFCell[] findCells(String tableName ) {
			
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
	

	@DataProvider(name="mydata")
	public  String[][]dataProvider(String thedataname){
		return (String[][]) getTestData(thedataname);
	}
	
	
	
	

}
