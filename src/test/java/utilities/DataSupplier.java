package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataSupplier {

	ExcelUtility excelutility;

	@DataProvider(name="login")
	public String[][] loginData() throws IOException {
		excelutility = new ExcelUtility(System.getProperty("user.dir")+"/src/test/resources/datasupplier.xlsx");

		int rows = excelutility.getRowCount("loginData");
		int cells = excelutility.getCellCount("loginData", 0);

		String data[][] = new String[rows][cells];

		for(int r=1;r<=rows;r++)
		{
			for(int c=0;c<cells;c++)
			{
				data[r-1][c]=excelutility.getCellData("loginData", r, c);
			}
		}
		return data;
	}
	
	@DataProvider(name="signup")
	public String[][] signupData() throws IOException {
		excelutility = new ExcelUtility(System.getProperty("user.dir")+"/src/test/resources/datasupplier.xlsx");

		int rows = excelutility.getRowCount("signupinfo");
		int cells = excelutility.getCellCount("signupinfo", 0);

		String data[][] = new String[rows][cells];

		for(int r=1;r<=rows;r++)
		{
			for(int c=0;c<cells;c++)
			{
				data[r-1][c]=excelutility.getCellData("signupinfo", r, c);
			}
		}
		return data;
	}

}
