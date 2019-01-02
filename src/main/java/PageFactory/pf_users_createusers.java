package PageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Generic_Library.Basefunctions;

public class pf_users_createusers extends pf_genericmethods {
	
	Basefunctions b = new Basefunctions();
	
	@FindBy(how = How.XPATH, using = "//ul[@id='accordion']/li[1]/a") WebElement users;
	@FindBy(how = How.XPATH, using = "//ul[@id='accordion']/li[1]/div/ul/div/li[2]/div/a") WebElement createusers;
	@FindBy(how = How.ID, using = "enrollUsers") WebElement enrolluser;
	@FindBy(how = How.ID, using = "first_name") WebElement firstname;
	@FindBy(how = How.ID, using = "last_name") WebElement lastname;
	@FindBy(how = How.ID, using = "gender") WebElement gender;
	@FindBy(how = How.ID, using = "dateValues") WebElement dob;
	@FindBy(how = How.ID, using = "mobile") WebElement mobile;
	@FindBy(how = How.ID, using = "email") WebElement email;
	@FindBy(how = How.ID, using = "user_name") WebElement user_name;
	@FindBy(how = How.ID, using = "organization") WebElement organization;
	@FindBy(how = How.ID, using = "empId") WebElement empId;
	@FindBy(how = How.ID, using = "addressline1") WebElement addressline1;
	@FindBy(how = How.ID, using = "country") WebElement country;
	@FindBy(how = How.ID, using = "zipcode") WebElement zipcode;
	@FindBy(how = How.XPATH, using ="//form[@id='enrollUserForm']/div[4]/input") WebElement saveproceed;
	@FindAll({@FindBy(how =How.XPATH, using = "//form[@id='enrollUserForm']/ul")}) public List<WebElement> errorullist;
	@FindAll({@FindBy(how =How.XPATH, using = "//form[@id='enrollUserForm']/ul[1]/li")}) public List<WebElement> errorlilist;

	
	String lisource = "//form[@id='enrollUserForm']/ul[#DELIM#]/li[#DELIM#]/span";
	@FindAll({@FindBy(how =How.XPATH, using = "//span[@class='error']")}) public List<WebElement> mandatoryerror;
	@FindBy(how = How.XPATH, using = "//span[@class='error']") WebElement mandatoryerr;
	@FindBy(how = How.ID, using = "reviewAndConfirm") WebElement reviewandconfirm;
	@FindBy(how = How.ID, using = "successMSG") WebElement successmsg;
	@FindBy(how = How.ID, using = "failureMSG") WebElement failuremsg;
	
	
	
	
	
	public pf_users_createusers(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public void usercreation(String fname, String lname, String gend,String db,String mob,String mail,String usname,String organisation,String eid,String add1,String cn,String zip, WebDriver driver) throws Exception{
		
		cl_click(users);
		Thread.sleep(2000);
		cl_click(createusers);
		Thread.sleep(2000);
		cl_click(enrolluser);
		Thread.sleep(5000);
		cl_entertext(firstname,fname);
		cl_entertext(lastname, lname);
		Select s = new Select(gender);
		s.selectByVisibleText(gend);
		cl_entertext(dob, db);
		cl_click(mobile);
		cl_entertext(mobile, mob);
		cl_entertext(email, mail);
		cl_entertext(user_name, usname);
		cl_entertext(organization, organisation);
		cl_entertext(empId, eid);
		cl_entertext(addressline1, add1);
		Select s1 = new Select(country);
		s1.selectByVisibleText(cn);
		cl_entertext(zipcode, zip);
		cl_click(saveproceed);
		Thread.sleep(3000);
		int ul = errorullist.size();
		System.out.println("UL count : "+ul);
		b.screenshot(driver);
		
		for(int i =1;i<=ul;i++){
			int li = errorlilist.size();
			System.out.println("li count : "+ul);

			for(int j=1;j<=li;j++){
				
				try{
				//System.out.println(driver.findElement(By.xpath("//form[@id='enrollUserForm']/ul["+i+"]/li["+j+"]/span")).getText());			
				if(driver.findElement(By.xpath(lisource.replaceFirst("#DELIM#",String.valueOf(i)).replace("#DELIM#",String.valueOf(j)))).isEnabled()){
					
				WebElement lsource = driver.findElement(By.xpath(lisource.replaceFirst("#DELIM#",String.valueOf(i)).replace("#DELIM#",String.valueOf(j))));
				System.out.println(lsource.getText());
				
			}
				}catch (Exception e){
					System.out.println("Either the field is not mandatory or Filled with details");
				}
			}

			}
		}
		/*cl_click(reviewandconfirm);
		Thread.sleep(2000);
		try{
			if(successmsg.isDisplayed()){
				System.out.println(successmsg.getText());
			}
			}catch(Exception e){
				System.out.println(failuremsg.getText());
			}
	}*/
}
