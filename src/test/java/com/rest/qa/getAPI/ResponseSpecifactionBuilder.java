package com.rest.qa.getAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;

public class ResponseSpecifactionBuilder {
	
	
	ResponseSpecBuilder res = new ResponseSpecBuilder();
	
	// ResponseSepecification respec = res.expectStatusCode(200).expectContentType(ContentType.Json).build();
	
	@Test
	public void resonseSpecTest() {
		
		//content tyoe is not needed here as we are not doing post call 
		RestAssured.baseURI = "https://gorest.co.in" ;
		
		/*
		 * given() .header("Authorization",
		 * "Bearer_b70748c40a3d69fe375e629aa69ec38003efd60f2f5a5430b74c0fa447a37415")
		 * .when() .get("/public/v2/users") .then() .assertThat() .statusCode(respec);
		 */
		
		
	}
	
	
}
