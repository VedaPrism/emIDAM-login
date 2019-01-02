package Generic_Library;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Basefunctions {
	
	public static ExtentReports repo;
	public static ExtentTest ext;
	public String browser_type;
	public WebDriver ei;
	
	@BeforeSuite
	public void createreport(){
		
		System.out.println("HI");
	repo = new ExtentReports("E:\\Selenium\\Reports"+gettimestamp()+".html",false);
	
	}
	
	@Parameters({"browser"})
	@BeforeMethod(groups = {"Smoke","Regression","UAT"})
	public void browserselection(String btype) throws Exception{
		
		browser_type = btype;
		if(btype.equalsIgnoreCase("ch")){
			
			System.setProperty("webdriver.chrome.driver","E:\\Selenium\\chromedriver_win32\\chromedriver.exe");
			ei = new ChromeDriver();
			
		}else if(btype.equalsIgnoreCase("IE")){
			
			System.setProperty("webdriver.ie.driver","E:\\Selenium\\IEDriverServer_x64_2.53.1\\IEDriverServer.exe");
			ei = new InternetExplorerDriver();
			
		}else if(btype.equalsIgnoreCase("ff")){
			
			System.setProperty("webdriver.gecko.driver","E:\\Selenium\\Geckodriver\\geckodriver.exe");
			ei = new FirefoxDriver();
		}
		ei.get(Utility.getpropertyvalue(Utility.getpropertyvalue("env")));
		ei.manage().window().maximize();
		ei.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	
	@AfterMethod(groups = {"Smoke", "Regression", "UAT"})
	public void closeftn(){
		
		ei.quit();
	//	repo.endTest(ext);
		//repo.flush();
	}
	
	public String gettimestamp() {
		
		Date d = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss");
		String fr = sd.format(d);
		return fr;
	}

	public String screenshot(WebDriver driver) throws Exception{
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File fe = ts.getScreenshotAs(OutputType.FILE);
		String fp = Utility.getpropertyvalue("Screenshotpath")+gettimestamp()+".png";
		FileUtils.copyFile(fe,new File(fp));
		System.out.println("Screenshot taken successfully!!!");
		return fp;
	}
}
