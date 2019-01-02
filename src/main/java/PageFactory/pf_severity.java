package PageFactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Generic_Library.Basefunctions;

public class pf_severity extends pf_genericmethods {
	
	Basefunctions b = new Basefunctions();
	
	@FindBy(how = How.XPATH, using = "//ul[@id = 'accordion']/li[6]/a") WebElement severitymaster;
	@FindBy(how = How.XPATH, using = "//ul[@id = 'accordion']//li[6]/div/ul/li[2]") WebElement createseverity;
	@FindBy(how = How.ID, using = "severityID") WebElement type;
	@FindBy(how = How.XPATH, using = "//div[@class = 'pageBtnBlk']/input") WebElement create;
	@FindBy(how = How.XPATH, using = "//div[@class = 'pageBtnBlk']/a") WebElement cancel;
	@FindBy(how = How.ID, using = "emptyDetails") WebElement mandatoryerror;
	@FindBy(how = How.XPATH, using = "//div[@id = 'alertWindow']/div/div/div[1]/button") WebElement popclose;
	@FindBy(how = How.XPATH, using = "//div[@id = 'alertWindow']/div/div/div[2]/p[1]") WebElement successpopup;
	@FindBy(how = How.XPATH, using = "//form[@id = 'severityForm']/div/ul/li[1]/span") WebElement existerror;
	@FindBy(how = How.XPATH, using = "//div[@id = 'collapseFive']/ul/li[1]") WebElement manageseverity;
	@FindAll({@FindBy(how = How.XPATH, using = "//table[@id = 'severity']//tbody/tr")}) public List<WebElement> tablelist;
	String m = "//table[@id = 'severity']//tbody/tr[#DELIM#]/td[2]";
	String n = "//table[@id = 'severity']//tbody/tr[#DELIM#]/td[4]/ul/li[3]/a/i";
	@FindBy(how = How.XPATH, using = "//div[@id = 'actionBtns']/a[1]") WebElement deleteconfirm;
	
	
	
	
	
	public pf_severity(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
	public void severitytype(WebDriver driver, String input2) throws Exception{
		
		cl_click(severitymaster);
		Thread.sleep(2000);
		cl_click(createseverity);
		Thread.sleep(2000);
		cl_click(create);
		System.out.println(mandatoryerror.getText());
		Assert.assertTrue(true, mandatoryerror.getText());
		cl_entertext(type, input2);
		Thread.sleep(2000);
		cl_click(create);
		Thread.sleep(2000);
		if(existerror.isDisplayed()){
			System.out.println("Name entered already exists. Try with some other name");
			Thread.sleep(2000);
			cl_click(manageseverity);
			Thread.sleep(2000);
			int sz = tablelist.size();
			for(int i=2;i<=sz;i++){
				
				String tt = driver.findElement(By.xpath(m.replace("#DELIM#",String.valueOf(i)))).getText();
				if(tt.equalsIgnoreCase(input2)){
					
					cl_click(driver.findElement(By.xpath(n.replace("#DELIM#",String.valueOf(i)))));
					Thread.sleep(2000);
					cl_click(deleteconfirm);
					Thread.sleep(2000);
					cl_click(popclose);
					
					
				}
			}
		}else {
		System.out.println(successpopup.getText());
		cl_click(popclose);	
		}
		}

}
