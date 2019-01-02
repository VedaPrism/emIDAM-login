package PageFactory;

import org.openqa.selenium.WebElement;

public class pf_genericmethods {

	public void cl_click(WebElement element){
		
		element.click();
		}
	
	public void cl_entertext(WebElement element, String txt){
		
		element.sendKeys(txt);
	}
}
