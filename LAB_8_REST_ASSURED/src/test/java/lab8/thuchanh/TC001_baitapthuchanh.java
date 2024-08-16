package lab8.thuchanh;

import org.apache.http.protocol.ResponseServer;
import static org.testng.Assert.*;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC001_baitapthuchanh {
	
	private Response response;
	private ResponseBody resBody;
	private JsonPath bodyJson;
	int userId;
	
	@BeforeClass
	public void init_thuchanh001() {
		
		userId = 2;
		
		// thiết lập endpoint		
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "api/users/";
		
		//tạo request
		RequestSpecification req = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.pathParam("userId", userId);
		
		//gọi POST và lấy về response
		response = req.get("/{userId}");
		resBody = response.getBody();
		bodyJson = resBody.jsonPath();
		
		System.out.println(response.asPrettyString());
		
	}
	
	@Test 
	public void testStatusCode() {
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test 
	public void testId() {
		assertNotNull(bodyJson.getInt("data.id"));
	}
	
	@Test
	public void testEmail() {
		assertNotNull(bodyJson.getString("data.email"));
	}
	
	@Test
	public void testFirstName() {
		assertNotNull(bodyJson.getString("data.first_name"));
	}
	
	@Test
	public void testLastName() {
		assertNotNull(bodyJson.getString("data.last_name"));
	}

	@Test
	public void testAvatar() {
		assertNotNull(bodyJson.getString("data.avatar"));
	}
	@Test
	public void testIdMatchingUserId() {
		int id = bodyJson.getInt("data.id");
		assertEquals(id, userId);
	}
}
