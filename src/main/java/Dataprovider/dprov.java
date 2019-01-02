package Dataprovider;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import Generic_Library.Utility;

public class dprov {

	@DataProvider(name = "IDAMdata")
	public static Iterator<Object> dp_valid() throws Exception{
		
		return Utility.datafromXL("Sheet1", "Data1");
	}
	
	@DataProvider(name = "IDAMdata_inv")
	public static Iterator<Object> dp_invalid() throws Exception{
		
		return Utility.datafromXL("Sheet1", "Data2");
	}
}
