package PageFactory;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Generic_Library.Basefunctions;

public class pf_manageauthenticationsettings extends pf_genericmethods {
	
	Basefunctions b = new Basefunctions();

	@FindBy(how = How.XPATH, using = "//ul[@id = 'accordion']/li[4]/a") WebElement authenticationtypes;
	@FindBy(how = How.XPATH, using = "//ul[@id = 'accordion']/li[4]/div/ul/li[2]/a") WebElement manageauthentication;
	@FindAll({@FindBy(how = How.XPATH, using = "//table[@class = 'table']/tbody/tr")}) public List<WebElement> tabledetails;
	String rm = "//table[@class = 'table']/tbody/tr[#DELIM#]/td[1]";
	String mr = "//table[@class = 'table']/tbody/tr[#DELIM#]/td[3]/ul/li/a";
	String dsc = "//table[@class = 'table']/tbody/tr[#DELIM#]/td[3]";
	String kba = "//table[@class = 'table']/tbody/tr[#DELIM#]/td[3]/ul/li[1]/a";
	String mqt = "//table[@id = 'allQuestionList']/tbody/tr[#DELIM#]/td[3]";

//	Password Settings page elements
	@FindBy(how = How.XPATH, using = "//div[@class = 'pageBtnBlk']/a") WebElement backbutton;
	
//	KBA Settings - Manage Questions
	@FindBy(how = How.XPATH, using = "//table[@class = 'table']/tbody/tr[5]/td[3]/ul/li[2]/a") WebElement managequestions;
	@FindAll({@FindBy(how = How.XPATH, using = "//table[@id = 'allQuestionList']/tbody/tr")}) public List<WebElement> managequestionslist;
	
//	Add question function elements
	
	@FindBy(how = How.XPATH, using = "//div[@class = 'exbtnBlk']/a") WebElement addques;
	@FindBy(how = How.ID, using = "question") WebElement enterques;
	@FindBy(how = How.XPATH, using = "//select[@class = 'formField']") WebElement rank;
	@FindBy(how = How.XPATH, using = "//div[@class = 'pageBtnBlk']/button[2]") WebElement createbutton;
	String del = "//table[@id = 'allQuestionList']/tbody/tr[#DELIM#]/td[6]/ul/li[3]/a";
	@FindBy(how = How.XPATH, using = "//div[@id = 'popup']/div/div/div[2]/div/button[1]") WebElement confirmpopup;
	@FindBy(how = How.XPATH, using = "//div[@id = 'alertSuccessWindow']/div/div/div[1]/button") WebElement popupclose;
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@id = 'alertSuccessWindow']/div/div/div[2]/p[3]/span")}) public List<WebElement> usermapppedmsg;
//	@FindBy(how = How.ID, using = "alertWarningAuthPage") WebElement usermapppedmsg;
	
	
	
	public pf_manageauthenticationsettings(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public void manage(WebDriver driver) throws Exception{
		
		cl_click(authenticationtypes);
		Thread.sleep(3000);
		cl_click(manageauthentication);
		int t = tabledetails.size();
		
		System.out.println("Authentication Types are :");
		for(int i=1; i<=t; i++){
			
			WebElement el = driver.findElement(By.xpath(rm.replace("#DELIM#",String.valueOf(i))));
			System.out.println(el.getText());
			
			if(!el.getText().equalsIgnoreCase("DSC")){
			
			WebElement le = driver.findElement(By.xpath(mr.replace("#DELIM#",String.valueOf(i))));
			System.out.println("Actions coulmn :");
			System.out.println(le.getAttribute("href"));
			if(el.getText().equalsIgnoreCase("Password")){
				cl_click(le);
				Thread.sleep(2000);
				b.screenshot(driver);
				Thread.sleep(2000);
				cl_click(backbutton);
			}else if(el.getText().equalsIgnoreCase("SMS OTP")){
				cl_click(le);
				Thread.sleep(2000);
				b.screenshot(driver);
				Thread.sleep(2000);
				cl_click(backbutton);
			}else if(el.getText().equalsIgnoreCase("EMAIL OTP")){
				cl_click(le);
				Thread.sleep(2000);
				b.screenshot(driver);
				Thread.sleep(2000);
				cl_click(backbutton);
			}else if(el.getText().equalsIgnoreCase("KBA")){
				WebElement kba1 = driver.findElement(By.xpath(kba.replace("#DELIM#",String.valueOf(i))));
				cl_click(kba1);
				Thread.sleep(2000);
				b.screenshot(driver);
				cl_click(backbutton);
				Thread.sleep(2000);
				cl_click(managequestions);
				int mql = managequestionslist.size();
				System.out.println(mql);
				System.out.println("Questions in the table: ");
				for(int r=1;r<mql;r++){
					WebElement fe = driver.findElement(By.xpath(mqt.replace("#DELIM#",String.valueOf(r))));
					System.out.println(fe.getText());
					for(int m = r+1;m<mql;m++){
						WebElement fe1 = driver.findElement(By.xpath(mqt.replace("#DELIM#",String.valueOf(m))));
						if(fe1.getText().equals(fe.getText())){
							System.out.println("Question entered is duplicate. Do you want to delete the existing question? :");
							Scanner sn = new Scanner(System.in);
							String inp = sn.next();
							if(inp.equalsIgnoreCase("Yes")){
								WebElement fe2 = driver.findElement(By.xpath(del.replace("#DELIM#",String.valueOf(r))));
								cl_click(fe2);
								/*System.out.println("user mapped error count : "+usermapppedmsg.size());
								if(usermapppedmsg.size()>0){
									cl_click(popupclose);
								}else{*/
								Thread.sleep(2000);
								cl_click(confirmpopup);
								Thread.sleep(2000);
								cl_click(popupclose);
								System.out.println("Duplicate question deleted successfully");
							
							}		
						}
					}
				}
				Thread.sleep(2000);
				cl_click(addques);
				cl_entertext(enterques, "What new ques you have?");
				Select sc = new Select(rank);
				sc.selectByValue("Medium");
				cl_click(createbutton);
				cl_click(backbutton);
			}
			}else{
			WebElement ds = driver.findElement(By.xpath(dsc.replace("#DELIM#",String.valueOf(i))));
			System.out.println(ds.getText());
		}
	}
		b.screenshot(driver);
		
}
}