package lab8.thuchanh;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class TC002_baitapthuchanh {
    private Response response;
    private ResponseBody resBody;
    private JsonPath bodyJson;
    int userId;
    
    @BeforeClass
    public void init_thuchanh002() {
    	int userId = 40;
    	
    	RestAssured.baseURI = "https://reqres.in";
		RestAssured.basePath = "api/users/";
		
		RequestSpecification req = RestAssured.given()
			.contentType(ContentType.JSON)
			.when()
			.pathParam("userId", userId);
		
		response = req.get("/{userId}");
		resBody = response.getBody();
		bodyJson = resBody.jsonPath();

    }
    
    @Test
    public void testStatusCode() {
    	assertEquals(response.getStatusCode(), 404);
    }
    
    
    @Test
    public void testMessageExistence() {
    	assertTrue(bodyJson.getMap("").containsKey("message"));
    }
    @Test
    public void testMessage() {  	
    	assertEquals(bodyJson.getString("message"), "User not found!");
    }
}
