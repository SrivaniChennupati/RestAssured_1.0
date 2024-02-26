package com.rest.qa.schema;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CheckSchemaTest {

	
	@Test
	public void bookingSchemaTest() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com" ;
		
		/*Response response = given()
		.contentType(ContentType.JSON)
		.body(new File("C:\\Users\\sriva\\eclipse-workspace\\RestAssured\\src\\test\\java\\DataFiles\\bookingdata.json"))
		.post("/booking");
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);*/
		
		given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("C:\\Users\\sriva\\eclipse-workspace\\RestAssured\\src\\test\\java\\DataFiles\\bookingdata.json"))
		.when().log().all()
		.post("/booking")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body(matchesJsonSchemaInClasspath("bookingschema.josn"));
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
