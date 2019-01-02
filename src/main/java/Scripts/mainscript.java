package Scripts;

import java.util.Map;

import org.testng.annotations.Test;

import Generic_Library.Basefunctions;
import PageFactory.pf_groups_create;
import PageFactory.pf_idamadmin;
import PageFactory.pf_manageauthenticationsettings;
import PageFactory.pf_severity;
import PageFactory.pf_transactionsparameters;
import PageFactory.pf_users_createusers;
import PageFactory.pf_users_manageusers;

public class mainscript extends Basefunctions {
	
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
		String input1 = mh.get("Transparamname").toString();
		String input2 = mh.get("Severityname").toString();
		//ext = repo.startTest(browser_type);
		pf_idamadmin pi = new pf_idamadmin(ei);
		pi.mainfunction(usern, passw, ei);
		/*pf_users_createusers uc = new pf_users_createusers(ei);
		uc.usercreation(fname,lname,gen,db,mob,mail,usname,organ,eid,add1,cn,zip,ei);
		pf_users_manageusers mu = new pf_users_manageusers(ei);
		mu.manageuser(ei);
		pf_groups_create gc = new pf_groups_create(ei);
		gc.creategroups(input, ei);
		pf_manageauthenticationsettings ma = new pf_manageauthenticationsettings(ei);
		ma.manage(ei);
		pf_transactionsparameters tp = new pf_transactionsparameters(ei);
		tp.createtp(ei,input1);*/
		pf_severity sv = new pf_severity(ei);
		sv.severitytype(ei, input2);
	}

}
