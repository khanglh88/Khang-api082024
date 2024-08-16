package addproperties;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement iconSignIn = driver.findElement(By.linkText("Signup / Login"));
		assertEquals(true, iconSignIn.isDisplayed(), "iconSignIn is not displayed");
		iconSignIn.click();
	}
	
	public void enterEmail(String email) throws InterruptedException {
		WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
		inputEmail.sendKeys(email);
		Thread.sleep(2000);
	}
	
	public void enterPassword(String password) throws InterruptedException {
		WebElement inputPass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
		inputPass.sendKeys(password);
		Thread.sleep(2000);
	}
	
	public void clickSignIn() throws InterruptedException {
		WebElement btnSignIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='btn btn-default']")));
		btnSignIn.click();
		Thread.sleep(2000);
	}
	
	public void login(String email, String password) throws InterruptedException {
		enterEmail(email);
		enterPassword(password);
		clickSignIn();
	}
	
}

