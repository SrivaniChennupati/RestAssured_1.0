package com.rest.qa.postAPI;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class POSTAPI_BDD_POJO_Test {
	
	
	@Test
	public void bookingPostAPIBDDTest_POJO() {
		
		String bookingjson = null;
		
		bookingdates  bookinddates = new bookingdates();
		
		bookinddates.setCheckin("2023-01-01");
		
	    bookinddates.setCheckout("2023-01-01");
		
		
	  booking booking = new booking();
	  
	  booking.setFirstname("Kalpana");
	  booking.setLastname("Alleni");
	  booking.setTotalprice(145);
	  booking.setDepositpaid(true);
	  booking.setBookingdates(bookinddates);
	   booking.setAdditionalneeds("Hair dryer");
	  
	
	  
	  
	 
	   // as discussed can create multiple bookings wr to booking pojo class we created 
		 
	   // booking booking2 = new booking("srivani", "Alleni", 113, true, "2023-01-01", "2023-01-01", "Hair dryer");
	   
	   // also can add all  diff bookings  that we  create to list by creating list
	   
	   //List<booking> list = new ArrayList<booking>();
	   
	   //list.add(booking1);
	   //list.add(booking2);
	   
	   //convert pojo to json
	   
	   
	   
	   
	   
	   ObjectMapper mapper = new ObjectMapper();
	   
	   try {
		   bookingjson = mapper.writeValueAsString(booking);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   RestAssured.baseURI = "https://restful-booker.herokuapp.com" ;
	   
	   given().log().all()
	   .contentType(ContentType.JSON)
	   .body(bookingjson)
	   .when().log().all()
	   .post("/booking")
	   .then().log().all()
	   .assertThat()
	   .statusCode(200);
	   
	   
	   
	   
	}
	
	
	

}
