package com.rest.qa.getAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class GetAPITest {
	
	
	@Test

	public void getAPICircuitTest_1() {
		Response response = given().log().all().when().log().all().get("https://ergast.com/api/f1/2017/circuits.json");
		// .then()
		// .assertThat()
		// .body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));

		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 200);

	}
	
	@Test
	
	public void getAPICircuitTest_2() {
		RestAssured.baseURI = "https://ergast.com";
		
		/*
		 * given() .when() .get("/api/f1/2017/circuits.json") .then() .assertThat()
		 * .statusCode(200) .and() .contentType(ContentType.JSON) .and()
		 * .header("Content-Length", equalTo("4552"));
		 */
		
		Response response = given().log().all()
		.when().log().all()
		.get("/api/f1/2017/circuits.json");
		
		int statusCode = response.getStatusCode();
		
		Assert.assertEquals(statusCode, 200);
		
		
		  String header = response.header("Content-Type");
		  
		  Assert.assertEquals(header, "application/json; charset=utf-8");
		  
		 // Assert.assertEquals(header.contains("application/json"), true);
	
			
		}
	
	  @Test
	  
	  public void getAPICircuitTest_3() {
		
		  Response response = given().log().all()
		  .when().log().all()
		  .param("text", "test")
		  .get("http://md5.jsontest.com");
		  /*.then()
		  .assertThat()
		  .body("md5", equalTo("098f6bcd4621d373cade4e832627b4f6"));*/
		   
		   ResponseBody body = response.getBody();
		 
		 // convert this to string 
		 
		String bodyasString = body.asString();
		
		System.out.println(bodyasString);
		
		Assert.assertTrue(bodyasString.contains("098f6bcd4621d373cade4e832627b4f6"));
		//Assert.assertEquals(bodyasString.contains("098f6bcd4621d373cade4e832627b4f6"),true);
		
		  
	}
	  
	  @DataProvider(name="getCircuitYeardata")
	  
	  public Object[][] getCircuitYearInfo() {
		  
		return   new Object[][] {
			  
			  {"2017",20 },
			  {"2016",21},
			  {"1966",9}
			  
		  };
		   
		  
	  }
	  
	  @Test(dataProvider="getCircuitYeardata")
	  
	  public void  noofcircuitsyeartest(String Seasonyear,int noofcircuits) {
		 Response response = given().log().all()
				 .pathParam("year", Seasonyear)
		  .when().log().all()
		  
		  .get("http://ergast.com/api/f1/{year}/circuits.json");
		 
		 JsonPath jsonPath = response.jsonPath();
		 
		String totalcircuits = jsonPath.getString("total");
		
		System.out.println(totalcircuits);
		
		Assert.assertEquals(totalcircuits, noofcircuits );
		 
		
	  }
	
	

}
