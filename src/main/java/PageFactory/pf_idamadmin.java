package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Generic_Library.Basefunctions;

public class pf_idamadmin extends pf_genericmethods {

	Basefunctions b = new Basefunctions();
	
	@FindBy(how = How.ID, using = "username") WebElement uname;
	@FindBy(how = How.ID, using = "password") WebElement pword;
	@FindBy(how = How.ID, using = "loginBtn") WebElement loginbutton;
	@FindBy(how = How.ID, using = "errorID") WebElement loginerror;
	
	public pf_idamadmin(WebDriver driver){
		
		PageFactory.initElements(driver,this);
	}
	
	public void mainfunction(String username, String password, WebDriver driver) throws Exception{
		
		Thread.sleep(3000);
		cl_entertext(uname,username);
		cl_entertext(pword, password);
		cl_click(loginbutton);
		Thread.sleep(5000);
		try{
			if(loginerror.isDisplayed()){
			
			System.out.println("Error is : "+loginerror.getText());
		}
		}catch(Exception e){
			System.out.println("Login Successful");
		}
		
	}
 }
