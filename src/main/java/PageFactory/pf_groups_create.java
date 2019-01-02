package PageFactory;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Generic_Library.Basefunctions;

public class pf_groups_create extends pf_genericmethods {
	
	Basefunctions b = new Basefunctions();
	
	@FindBy(how = How.XPATH, using = "//ul[@id='accordion']/li[2]/a") WebElement groups;
	@FindBy(how = How.XPATH, using = "//ul[@id='accordion']/li[2]/div/ul/li[2]/a") WebElement creategroups;
	@FindBy(how = How.ID, using = "groupName") WebElement groupname;
	@FindBy(how = How.ID, using = "groupNameError") WebElement groupnameerror;
	@FindBy(how = How.XPATH, using = "//*[@class='pageBtnBlk']/input") WebElement savebutton;
	@FindBy(how = How.XPATH, using = "//table[@id ='userslist']/tbody/tr[3]/td[1]/input") WebElement usercheckbox;
	@FindBy(how = How.XPATH, using = "//*[@class='pageBtnBlk']/a") WebElement usersavebutton;
	@FindBy(how = How.XPATH, using = "//table[@id = 'loadSelectedGrpUserlist']/tbody/tr[2]/td[2]") WebElement selecteduser;
	@FindBy(how = How.ID, using = "reviewAndconfirmGroup") WebElement review_confirm;
	@FindBy(how = How.XPATH, using = "//article[@class= 'inner-wrap']/div[4]/div/div[2]/div/form/ul/li/span") WebElement groupnam;
	@FindBy(how = How.ID, using = "successMSG") WebElement successmsg;
	@FindAll({@FindBy(how = How.XPATH, using = "//table[@id ='userslist']/tbody/tr")}) public List<WebElement> usercheckboxes;
	String Source = "//table[@id ='userslist']/tbody/tr[#DELIM#]/td[1]/input";
	@FindAll({@FindBy(how = How.XPATH, using = "//table[@id = 'loadSelectedGrpUserlist']/tbody/tr")}) public List<WebElement> selecteduserscount;
	String sourc = "//table[@id = 'loadSelectedGrpUserlist']/tbody/tr[#DELIM#]/td[2]";
	@FindBy(how = How.ID, using = "manageGroup") WebElement managegroup;
	@FindAll({@FindBy(how = How.XPATH, using = "//table[@id = 'groupList']/tbody/tr")}) public List<WebElement> grouplist;
	String mgu = "//table[@id = 'groupList']/tbody/tr[#DELIM#]/td[1]";
	String del = "//table[@id = 'groupList']/tbody/tr[#DELIM#]/td[4]/ul/li[3]/a/i";
	@FindBy(how = How.XPATH, using = "//div[@id = 'popup']/div/div/div[2]/div/a[1]") WebElement popupyes;
	@FindBy(how = How.XPATH, using = "//div[@id = 'popup']/div/div/div[1]/button") WebElement closepopup;
	
	
	
	public pf_groups_create(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public void creategroups(String input, WebDriver driver) throws Exception{
		
		cl_click(groups);
		Thread.sleep(2000);
		cl_click(creategroups);
		Thread.sleep(2000);
		cl_click(groupname);
		cl_entertext(groupname, input);
		cl_click(savebutton);
		Thread.sleep(3000);
		if(groupnameerror.isDisplayed()){
			
			System.out.println("Error is : "+groupnameerror.getText());
			cl_click(managegroup);
			Thread.sleep(3000);
			int glc = grouplist.size();
			System.out.println(glc);
			for(int k=2;k<=glc;k++){
				
				WebElement gl = driver.findElement(By.xpath(mgu.replace("#DELIM#", String.valueOf(k))));
				String te = gl.getText();
				if(gl.getText().equalsIgnoreCase(input)){
					
					System.out.println("Group name given by user is already exists. Please enter different groupname");
					Scanner scn = new Scanner(System.in);
					System.out.println("Please enter your answer: ");
					System.out.println("Do you want to delete the existing group?");
					String userans = scn.next();
					if(userans.equalsIgnoreCase("yes")){
						
						WebElement dele = driver.findElement(By.xpath(del.replace("#DELIM#", String.valueOf(k))));
						cl_click(dele);
						Thread.sleep(3000);
						cl_click(popupyes);
						Thread.sleep(2000);
						cl_click(closepopup);
						System.out.println("Success!!! "+te+" is deleted");
						b.screenshot(driver);
					
					}
					break;
				}
			}
		}
		else{
		int us = usercheckboxes.size();
		//System.out.println(us);
		for(int i=2;i<=us;i++){
			
			Random r = new Random();
			int a = r.nextInt(us);
			//System.out.println(a);
			if(a == 0){
				a = a+2;
			}else if(a==11){
				a = a-1;
			}else if(a==1){
				a = a+1;
			}
			WebElement ss = driver.findElement(By.xpath(Source.replace("#DELIM#",String.valueOf(a))));
			cl_click(ss);
		}
		
		cl_click(usersavebutton);
		Thread.sleep(2000);
		int suc = selecteduserscount.size();
		System.out.println(suc);
		System.out.println("Selected Users are : ");
		for(int j=2;j<=suc;j++){
			
		WebElement su = driver.findElement(By.xpath(sourc.replace("#DELIM#",String.valueOf(j))));
		System.out.println(su.getText());
		}
		System.out.println("Group Name displayed is "+groupnam.getText());
		cl_click(review_confirm);
		Thread.sleep(3000);
		if(successmsg.isDisplayed()){
			System.out.println("Success Message displayed");
		}
		
		}
		
	}

}
