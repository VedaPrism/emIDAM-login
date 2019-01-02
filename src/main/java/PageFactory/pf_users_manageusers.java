package PageFactory;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Generic_Library.Basefunctions;

public class pf_users_manageusers extends pf_genericmethods {

	
	Basefunctions b = new Basefunctions();
	
	@FindBy(how = How.ID, using = "manageUsers") WebElement manageusers;
	@FindBy(how = How.XPATH, using = "//div[@class = 'exbtnBlk']/a") WebElement newuser;
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@id = 'gview_allUsersList']/div[2]/div/table/thead/tr/th")}) public List<WebElement> tableheadcount;
	
	String tablehead = "//div[@id = 'gview_allUsersList']/div[2]/div/table/thead/tr/th[#DELIM#]/div";
	
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@id = 'gview_allUsersList']/div[3]/div/table/tbody/tr")}) public List<WebElement> tablerecordcount;
	
	@FindBy(how = How.XPATH, using = "//div[@id = 'pg_allUsersPager']/table/tbody/tr/td[2]/table/tbody/tr/td[2]") WebElement prevpage;
	@FindBy(how = How.XPATH, using = "//div[@id = 'pg_allUsersPager']/table/tbody/tr/td[2]/table/tbody/tr/td[6]") WebElement nextpage;
	@FindBy(how = How.XPATH, using = "//article[@class = 'inner-wrap']/div[2]/div/div[1]/a[2]") WebElement exporttoxcel;
	
	
	public pf_users_manageusers(WebDriver driver){
		
		PageFactory.initElements(driver,this);
	}
	
	public void manageuser(WebDriver driver) throws Exception{
		
		cl_click(manageusers);
		Thread.sleep(2000);
		int thcount = tableheadcount.size();
		System.out.println(thcount);
		System.out.println(" Table Headings are : ");
		
		for(int i=1;i<=thcount;i++){
			
			
			System.out.println(driver.findElement(By.xpath(tablehead.replace("#DELIM#",String.valueOf(i)))).getText());
		}
	
		System.out.println("No of Records displayed by default : "+(tablerecordcount.size()-1));
		
		String attribute = prevpage.getAttribute("class");
		System.out.println(attribute);
		if(attribute.contains("ui-state-disabled"))
		{
			System.out.println("Your are in First page");
			do{
			cl_click(nextpage);
			Thread.sleep(2000);
			}while (!nextpage.getAttribute("class").contains("ui-state-disabled"));
				
				System.out.println("You are in last page");
				try{
				cl_click(exporttoxcel);
				Thread.sleep(10000);
				Alert m = driver.switchTo().alert();
				m.accept();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}

