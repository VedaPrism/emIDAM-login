package Generic_Library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLdata {
	
	FileInputStream inpstr;
	XSSFWorkbook wb;
	
	public XLdata(String filepath) throws Exception{
		
		inpstr = new FileInputStream(filepath);
		wb = new XSSFWorkbook(inpstr);
	}
	
	public int getrowcount(String sheetname){
		
		XSSFSheet sh = wb.getSheet(sheetname);
		return sh.getLastRowNum();
	}
	
	public int getcolumncount(String sheetname){
		
		XSSFSheet sh = wb.getSheet(sheetname);
		return sh.getRow(0).getLastCellNum();
	}
	public String XLread(int ri, int ci, String sheetname){
		
		XSSFSheet sh = wb.getSheet(sheetname);
		XSSFCell c = sh.getRow(ri).getCell(ci);
		String cell = null;
		
		if(c.getCellType() == c.CELL_TYPE_STRING){
			
			cell = c.getStringCellValue();
			
		} else if(c.getCellType() == c.CELL_TYPE_NUMERIC){
			
			cell = String.valueOf(c.getNumericCellValue());
		}
		else if((c.getCellType() == c.CELL_TYPE_BLANK) && (c.getCellType() == c.CELL_TYPE_ERROR)){
			
			cell ="";
		}
		return cell;
	}
	
	public void writeXL(int ri,int ci,String sheetname,String input){
		
		XSSFSheet sh = wb.getSheet(sheetname);
		sh.getRow(ri).getCell(ci).setCellValue(input);
	}
	
	public void saveclose(String filepath) throws Exception{
		
		FileOutputStream fs = new FileOutputStream(filepath);
		wb.write(fs);
		fs.close();
		inpstr.close();
	}
}
