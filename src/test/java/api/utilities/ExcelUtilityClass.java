package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilityClass {
	
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	String path;
	
	public ExcelUtilityClass(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetname) throws Exception{
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getPhysicalNumberOfRows();
		workbook.close();
		fis.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetname) throws Exception {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		int cellcount = sheet.getRow(0).getPhysicalNumberOfCells();
		workbook.close();
		fis.close();
		return cellcount;
	}
	
	public String getCellData(String sheetname, int rowNo, int cellNo) throws Exception {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowNo);
		cell = row.getCell(cellNo);
		String cellData = cell.toString();
		return cellData;
	}
}



