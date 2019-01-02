package Generic_Library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class Utility {
	
	public static Iterator<Object> datafromXL(String sheetname, String scriptname) throws Exception{
		
		XLdata xd = new XLdata(System.getProperty("user.dir")+"//src//main//resources//logincredentials.xlsx");
		
		int rc = xd.getrowcount(sheetname);
		int cc = xd.getcolumncount(sheetname);
		
		List<Object> al = new ArrayList<Object>();
	
		for(int i=1;i<=rc;i++){
			
			String flag = xd.XLread(i,1,sheetname);
			String script = xd.XLread(i,2,sheetname);
		
			if(flag.equalsIgnoreCase("Y")&& (script.equalsIgnoreCase(scriptname))){
				
				Map<String, String> m = new HashMap<String,String>();
				Object[] o = new Object[1];
				
				for(int j =0;j<cc;j++){
					
					String Key = xd.XLread(0,j, sheetname);
					String Value = xd.XLread(i,j, sheetname);
					m.put(Key, Value);
				}
				o[0]=m;
				al.add(o);
			}
		
		}return al.iterator();
	}

	
	public static String getpropertyvalue(String key) throws Exception{
		
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//IDAMprop.properties");
		Properties p = new Properties();
		p.load(f);
		return p.getProperty(key);
	}
}
