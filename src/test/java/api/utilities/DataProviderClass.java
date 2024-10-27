package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider(name = "getAllData")
	public String[][] getAllData() throws Exception {
		String path = System.getProperty("user.dir") + "//testdata//UserAPIAutomation.xlsx";
		ExcelUtilityClass utility = new ExcelUtilityClass(path);

		int rowcount = utility.getRowCount("Sheet1");
		int cellcount = utility.getCellCount("Sheet1");
		String data[][] = new String[rowcount - 1][cellcount];
		// System.out.println(rowcount);
		// System.out.println(cellcount);

		for (int i = 1; i < rowcount; i++) {
			for (int j = 0; j < cellcount; j++) {
				String cellData = utility.getCellData("Sheet1", i, j);
				data[i - 1][j] = cellData;
				// System.out.print(cellData);
			}
			// System.out.println();
		}
		return data;
	}

	@DataProvider(name = "getUserName")
	public String[] getUserName() throws Exception {
		String path = System.getProperty("user.dir") + "//testdata//UserAPIAutomation.xlsx";
		ExcelUtilityClass utility = new ExcelUtilityClass(path);
		int rowcount = utility.getRowCount("Sheet1");
		String[] username = new String[rowcount-1];
		
		for (int i = 1; i < rowcount; i++) {
			String cellData = utility.getCellData("Sheet1", i, 1); 
			username[i-1] = cellData;
			//System.out.println(cellData);
		}
		return username;
	}

	
}
