package addproperties;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileUtils {
	private static String CONFIG_PATH = "./configuration/configs.properties";
	
	//lấy giá trị properties bất kì theo key
	public static String getProperty(String key) {
		
		Properties properties = new Properties();
		String value = null;
		FileInputStream fileinputstream = null;
		
		try {
			
		fileinputstream = new FileInputStream(CONFIG_PATH);
		properties.load(fileinputstream);
		value = properties.getProperty(key);
		
		} catch (Exception ex) {
			System.out.println("Xảy ra lỗi đọc giá trị của " + key);
			ex.printStackTrace();
		} finally {	
			//luôn nhảy vào đây dù có xảy ra exception hay không.
			//thực hiện đóng luồng đọc
			if(fileinputstream != null) {
				try {
					fileinputstream.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		return value;
	}
}

