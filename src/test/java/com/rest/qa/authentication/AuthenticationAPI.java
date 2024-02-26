package com.rest.qa.authentication;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matcher.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthenticationAPI {
	
	
	@Test
	
	public void basicAuth_API_Test() {
		RestAssured.baseURI = "https://the-internet.herokuapp.com" ;
		Response response = given().log().all()
		.auth()
		.preemptive()
		.basic("admin", "admin")
		.when().log().all()
		.get("/basic_auth");
		
		response.statusCode();
		
	}
	
	@Test
	
	public void Oauth1_API_Test(){
		
		RequestSpecification request = RestAssured.given().log().all()
		.auth()
		.oauth("fstbYXknO8u3XfgjlH1bAxYDg", "2cwooTeuJ1pr92i8RWmtldtZP2ZGpjVHqhWkJYeysBdtxmyr0e", "1656052769772433408-Xhd2Wm8VBf8XAClaCZkIx3O2YyinKn","FaeI4OauzioXvd1iQ8HFze3Ro6ZzbKqWx3aeGHLUPGsPZ");
		
		Response response = request.get("https://api.twitter.com/2/users/me");
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode,200);
		
	}
	
	@Test

	public void Oauth2_API_Test() {
		RequestSpecification request = RestAssured.given().log().all()
				.queryParam("name", "Meghnath Joshi")
		.auth()
		.oauth2("3882d4b2f1dd48f0b339224aa9e5cd8613ac90e2ac77e901500417c84aae4972");
	
		
		Response response = request.get("https://gorest.co.in/public-api/users");
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
		
	
	}
	
	@Test
	
	public void Oauth2_API_withheader_Test() {
		RestAssured.baseURI = "https://gorest.co.in" ;
		
		Response response = given()
		.contentType("application/json")
		.header("authorization","bearer_3882d4b2f1dd48f0b339224aa9e5cd8613ac90e2ac77e901500417c84aae4972")
		.queryParam("name", "Meghnath Joshi")
		.when()
		.get("/public-api/users");
		
       int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
		
		String header = response.header("Content-Type");
		
		Assert.assertEquals(header.contains("application/json"), true);
		
	}
	
	@Test
	
	public void Oauth2_API_Test1() {
		
		// generate a access token 
		RequestSpecification request = RestAssured.given()
		.formParam("Client ID", "eywgyewfbef")
		.formParam("Client Secret", "gwydgwdbwd")
		.formParam("Grant Type", "shuhxuhxuhx");
		
		 
		Response response = request.post("https://coops.apps");
		
		//get the token id from the repsonse 
		
		
		JsonPath jsonpath = response.jsonPath();
		
		String tokenid = jsonpath.getString("tokenID");
		
		// pass this token id to another API 
		
		
		RequestSpecification request1 = RestAssured.given()
		.auth()
		.oauth2(tokenid);
		
		Response response2 = request1.post("https://coops.apps");
		
		
	}

}
