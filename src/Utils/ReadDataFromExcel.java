package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

public class ReadDataFromExcel {

	static Sheet sht;
	public static void main(String[] args) 
	{
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\testData\\TestSuite1.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			sht = workbook.getSheet("TestData");
			//sht = workbook.getSheetAt(0);

			int row = findRow("validateCheckbox2");
			if(row == -1) {
				System.out.println("row not found");
			}
			int column = findColumn("Checkbox2FinalStatus");
			if(column == -1) {
				System.out.println("column not found");
			}
			String value = findcellData(row, column);
			
			System.out.print(value);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int findRow(String TestCaseName) {
		int r = sht.getLastRowNum();
		for(int i=0;i<=r;i++)
		{
			Row row = sht.getRow(i);
			String value = row.getCell(0).getStringCellValue();
			if(value.equals(TestCaseName))
			{
				return i;
			}
		}
		return -1;
	}
	
	public static int findColumn(String DataKeyName)
	{
				Row row = sht.getRow(0);
				System.out.println("Last cell number in required row: "+ row.getLastCellNum());
				for(int i=0;i<=row.getLastCellNum();i++)
				{
					Cell cell = row.getCell(i);
					String value  = cell.getStringCellValue();
					System.out.println("Value in cell: "+ value);
					if(value.equals(DataKeyName))
					{
						System.out.println("Required column number is: "+ i);
						return i;
					}
				}
				return -1;
	}

	public static String findcellData(int rownum,int colnum)
	{
		return sht.getRow(rownum).getCell(colnum).getStringCellValue();
	}

}
