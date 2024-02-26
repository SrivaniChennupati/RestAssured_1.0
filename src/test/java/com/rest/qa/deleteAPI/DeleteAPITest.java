package com.rest.qa.deleteAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.qa.putAPI.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class DeleteAPITest {
	
	
	
	// flow for delete is post ..> delete ..>get 
	@Test
	public void deleteAPITest() {
		
		// crrate data for post call using pojo class
	
		 booking booking = new booking();
		 
		 booking.setFirstname("Lakshmi");
		 booking.setLastname("Marella");
		 booking.setTotalprice(400);
		 booking.setDepositpaid(true);
		 
		 bookingdates bookingdates = new bookingdates();
		 
		 bookingdates.setCheckin("2023-10-10");
		 bookingdates.setCheckout("2023-10-18");
		 
		 booking.setBookingdates(bookingdates);
		 
		 booking.setAdditionalneeds("Hair dryer");
		 
		 // now covert this POJO object to JSON Object 
		 
		 ObjectMapper mapper = new ObjectMapper();
		 
		 String bookingjson = null;
		 
		  try {
			bookingjson = mapper.writeValueAsString(booking);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  System.out.println(bookingjson);
		  
		 //hit the post call and get the booking id that can be used in delete call
		  
		  RestAssured.baseURI = "https://restful-booker.herokuapp.com" ;
			 
			int bookingid = given().log().all()
			 .contentType(ContentType.JSON)
			 .header("token" ,"d82f90b2faa2bac")
			 .body(bookingjson)
			 .when().log().all()
			 .post("/booking")
			 .then().log().all()
			 .assertThat()
			 .statusCode(200)
			 .extract().path("bookingid");
			
			System.out.println("booking id from the post call result is " +bookingid);
			
			
			// hit delete call 
			
			
			  given().log().all() .auth() .preemptive() .basic("admin", "password123")
			  .when().log().all() .delete("/booking/"+bookingid) .then().log().all()
			  .assertThat() .statusCode(201);
			 
		    
			/*
			 * RequestSpecification request = given().log().all() .auth() .preemptive()
			 * .basic("admin", "password123");
			 * 
			 * 
			 * Response repsonse = request.delete("/booking/"+bookingid);
			 * 
			 * ResponseBody responseBody = repsonse.getBody();
			 * 
			 * String bodyasString = responseBody.asString();
			 * 
			 * System.out.println(bodyasString);
			 */
		    
			
	
	
	
	}

}
