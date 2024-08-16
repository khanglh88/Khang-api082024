package Demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import addproperties.CaptureScreenshot;
import addproperties.LoginPage;
import addproperties.PropertiesFileUtils;

public class Lab7DemoTest {
	
	private ChromeDriver driver;
	
	@BeforeClass
	public void init() {
		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	
	@Test(priority = 0)
	public void TC01_LoginFirstAccount() throws InterruptedException {
	    String base_url = PropertiesFileUtils.getProperty("base_url"); 
	    System.out.println("base_url=" + base_url);
	    
	    String email_1 = PropertiesFileUtils.getProperty("email_1");
	    System.out.println("email_1=" + email_1);

	    String password_1 = PropertiesFileUtils.getProperty("password_1"); 
	    System.out.println("password_1=" + password_1);
	    
	    driver.get(base_url);

	    WebElement iconSignIn = driver.findElement(By.linkText("Signup / Login"));
	    iconSignIn.click();

	  //Khởi tạo LoginPage từ PageFactory và nhập vào thông tin email, password
	  //Click vào button SignIn để đăng nhập với thông tin email_1, password_1.
	    LoginPage loginPage = new LoginPage(driver);
	    PageFactory.initElements(driver, loginPage);
	    loginPage.enterEmail(email_1);
	    loginPage.enterPassword(password_1);
	    loginPage.clickSignIn(); 

//		WebElement iconSignOut = driver.findElement(By.linkText("Sign out"));
////	assertEquals(true, iconSignOut.isDisplayed(), "icon Sign Out is not displayed");
//		iconSignOut.click();

	}
	
	@Test(priority = 1)
	public void TC01_LoginSecondAccount() throws InterruptedException {
		 	String base_url = PropertiesFileUtils.getProperty("base_url"); 
		    System.out.println("base_url=" + base_url);

		    String email_2 = PropertiesFileUtils.getProperty("email_2");
		    System.out.println("email_2=" + email_2);

		    String password_2 = PropertiesFileUtils.getProperty("password_2"); 
		    System.out.println("password_2=" + password_2);
		   
			driver.get(base_url);

		    WebElement iconSignIn = driver.findElement(By.linkText("Signup / Login"));
		    iconSignIn.click();

		    LoginPage loginPage = new LoginPage(driver);
		    PageFactory.initElements(driver, loginPage);
		    loginPage.enterEmail(email_2);
		    loginPage.enterPassword(password_2);
		    loginPage.clickSignIn(); 

//			WebElement iconSignOut = driver.findElement(By.linkText("Sign out"));
////			assertEquals(true, iconSignOut.isDisplayed(), "icon Sign Out is not displayed");
//			iconSignOut.click();		
	}
	
	@AfterMethod
	public void takeScreenshot(ITestResult testResult) throws InterruptedException {
		Thread.sleep(1000);
		
		  // Nếu kết quả failed thì chụp lại màn hình
		if (ITestResult.FAILURE == testResult.getStatus()) {
			try {
				CaptureScreenshot.takeScreenshot(driver, testResult.getName());
				System.out.println("Đã chụp màn hình: " + testResult.getName());
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		}
	}
	
	@AfterClass
	public void closeinit() {
		System.out.println("end");
		driver.close();
	}
}
