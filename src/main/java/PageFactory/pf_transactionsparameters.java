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

public class pf_transactionsparameters extends pf_genericmethods{
	
	Basefunctions b = new Basefunctions();
	
	@FindBy(how = How.XPATH, using = "//ul[@id = 'accordion']/li[5]/a") WebElement transactionparam;
	@FindBy(how = How.XPATH, using = "//ul[@id = 'accordion']/li[5]/div/ul/li[2]") WebElement createtranparam;
	@FindBy(how = How.ID, using = "paramID") WebElement enterparam;
	@FindBy(how = How.XPATH, using = "//form[@id = 'txnParamForm']/div/div/div/div[1]/ul/li[2]/select") WebElement datatype;
	@FindBy(how = How.XPATH, using = "//div[@class = 'pageBtnBlk']/input") WebElement createbutton;
	@FindBy(how = How.XPATH, using = "//div[@class = 'pageBtnBlk']/a") WebElement cancelbutton;
	@FindBy(how = How.XPATH, using = "//form[@id = 'txnParamForm']/div/div/div/div[1]/ul/li[1]/span") WebElement nameerror;
	@FindBy(how = How.XPATH, using = "//div[@id = 'alertWindow']/div/div/div[1]/button") WebElement successmsg;
	
//	Manage transaction parameters elements
	
	@FindBy(how = How.XPATH, using = "//ul[@id = 'accordion']/li[5]/div/ul/li[1]") WebElement managetranparam;
	@FindAll({@FindBy(how = How.XPATH, using = "//table[@id = 'txnparameter']/tbody/tr")}) public List<WebElement> tablelist;
	String tab = "//table[@id = 'txnparameter']/tbody/tr[#DELIM#]/td[2]";
	String de = "//table[@id = 'txnparameter']/tbody/tr[#DELIM#]/td[5]/ul/li[3]/a";
	@FindBy(how = How.XPATH, using = "//div[@id = 'popup']/div/div/div[2]/div/a[1]") WebElement confirmpopup;
	@FindBy(how = How.XPATH, using = "//div[@id = 'popup']/div/div/div[1]/button") WebElement popupclose;
	
	
	public pf_transactionsparameters(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public void createtp(WebDriver driver, String input1) throws Exception{
		
	cl_click(transactionparam);
	Thread.sleep(2000);
	cl_click(createtranparam);
	Thread.sleep(2000);
	cl_entertext(enterparam, input1);
	Select sc = new Select(datatype);
	sc.selectByVisibleText("String");
	cl_click(createbutton);
	Thread.sleep(2000);
	if(nameerror.isDisplayed()){
		
		System.out.println(input1 +" is already exists. Please try with some other name");
	}else{
	cl_click(successmsg);
	Thread.sleep(2000);
	System.out.println("Transaction Parameter created successfully!!");
	
	}
/*}
	public void managetp(WebDriver driver, String input1) throws Exception{*/
		
		cl_click(managetranparam);
		Thread.sleep(2000);
		int tl = tablelist.size();
		for(int i=2;i<=tl;i++){
			
			WebElement e = driver.findElement(By.xpath(tab.replace("#DELIM#",String.valueOf(i))));
			System.out.println(e.getText());
			if(e.getText().equals(input1)){
				System.out.println("Transaction name is there already. Do you want to delete the existing parameter? :");
				Scanner sc1 = new Scanner(System.in);
				String in = sc1.next();
				if(in.equalsIgnoreCase("Yes")){
					WebElement e1 = driver.findElement(By.xpath(de.replace("#DELIM#",String.valueOf(i))));
					cl_click(e1);
					Thread.sleep(2000);
					cl_click(confirmpopup);
					Thread.sleep(2000);
					cl_click(popupclose);
					
				}
			}
		}
		
	}
}