package addproperties;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class CaptureScreenshot {
	
	public static void takeScreenshot(WebDriver driver, String testResult) {
		try {
		//Tạo tên ảnh trùng với tên testcase, kiểu ảnh là png
		String imageName = testResult + ".png";
		
		// Thực hiện chụp ảnh màn hình, lấy ra đối tượng file ảnh source
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source = screenshot.getScreenshotAs(OutputType.FILE);
		
		//Tạo đối tượng file với tên đã đặt bên trên tại thư mục /ScreenShots,
        // Sau đó thực viện cope file ảnh nguồn vào file đích đó
		File destiny = new File("./ScreenShots/" + imageName);
		FileHandler.copy(source, destiny);
	} catch (Exception ex) {
		System.out.println("Đã xảy ra lỗi khi chụp màn hình");
		ex.printStackTrace();
		}
	}
}
