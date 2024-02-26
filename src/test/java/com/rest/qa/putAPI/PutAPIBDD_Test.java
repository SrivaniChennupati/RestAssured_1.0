package com.rest.qa.putAPI;



import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutAPIBDD_Test {
	
	
	// the flow for put is post...>put ...> get
	
	// step1 : post call to create the new  booking
	
	//create a data for post call  using booking.json pojo class 
	
	
	@Test
	
	public void putAPIBDDTest() {
	
	
	 booking booking = new booking();
	 
	 booking.setFirstname("Kalpana");
	 booking.setLastname("Ravipati");
	 booking.setTotalprice(300);
	 booking.setDepositpaid(true);
	 
	 bookingdates bookingdates = new bookingdates();
	 
	 bookingdates.setCheckin("2023-10-10");
	 bookingdates.setCheckout("2023-10-16");
	 
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
	  
	 // post call - hit the post api to create the booking
	  
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
	 
	 // now get the booking id and use it in the put call URI to update the attributes 
	
	 //put call
	
	 booking.setLastname("chennupati");
	 
	 booking.setFirstname("srivani");
	 
	 String updatedbookingjson = null;
	 
	 // once again convert pojo to json 
	 
	 try {
		updatedbookingjson  = mapper.writeValueAsString(booking);
	} catch (JsonProcessingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
	 System.out.println("updatd json is " +updatedbookingjson);
	 
	 // now hit the put call 
	 
	 given().log().all()
	 .contentType(ContentType.JSON)
	 .auth()
	 .preemptive()
	 .basic("admin", "password123")
	 .body(updatedbookingjson)
	 .when().log().all()
	 .put("/booking/"+bookingid)
	 .then().log().all()
	 .assertThat()
	 .statusCode(200)
	 .body("firstname",equalTo(booking.getFirstname()))
	 .body("lastname",equalTo(booking.getLastname()));
	  
	 // now do get call to make validate that the data actually got updated 
	 
	 
	 given().log().all()
	 .when().log().all()
	 .get("/booking/"+bookingid)
	 .then().log().all()
	 .body("firstname",equalTo(booking.getFirstname()))
	 .body("lastname",equalTo(booking.getLastname()));
	 
	
	}

}
