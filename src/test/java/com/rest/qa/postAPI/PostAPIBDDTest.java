package com.rest.qa.postAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostAPIBDDTest {
	
	@Test
	public void tokenPostAPITest_01() {
		
		// passing json body as string 
		//getting the token from the repsonse instead of using jsonpath()
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com" ;
		
		String token = given().log().all()
		.contentType(ContentType.JSON)
		.body("{\"username\":\"admin\",\"password\":\"password123\"}")
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.extract().path("token");
		
		System.out.println(token);
		
				
	}
	@Test
    public void tokenPostAPITest_File_02() {
		
		// passing json body as string 
		//getting the token from the repsonse instead of using jsonpath()
		//passing json as file : crrating the object of  a file and pass the location of the data file
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com" ;
		
		String token = given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("C:\\Users\\sriva\\eclipse-workspace\\RestAssured\\src\\test\\java\\DataFiles\\TokenData.json"))
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.extract().path("token");
		
		System.out.println(token);
		Assert.assertNotNull(token);			
	}

}
