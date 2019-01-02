package Scripts;

import java.util.Map;

import org.testng.annotations.Test;

import Generic_Library.Basefunctions;
import PageFactory.pf_groups_create;

public class script1 extends Basefunctions {
	
	@Test(dataProvider = "IDAMdata",dataProviderClass=Dataprovider.dprov.class,enabled = true,priority=1,groups={"Smoke","Regression"})
	public void scripting(Map mh) throws Exception{
		
		String usern = mh.get("userId").toString();
		String passw = mh.get("pass").toString();
		String fname = mh.get("Firstname").toString();
		String lname = mh.get("Lastname").toString();
		String gen = mh.get("Gender").toString();
		String db = mh.get("DOB").toString();
		String mob = mh.get("Mobile").toString();
		String mail = mh.get("Email").toString();
		String usname =mh.get("username").toString();
		String organ = mh.get("organisation").toString();
		String eid = mh.get("empID").toString();
		String add1 = mh.get("Add1").toString();
		String cn = mh.get("Country").toString();
		String zip = mh.get("Zipcode").toString();
		String input = mh.get("Groupname").toString();
		
		pf_groups_create gc = new pf_groups_create(ei);
		gc.creategroups(input, ei);

}
}