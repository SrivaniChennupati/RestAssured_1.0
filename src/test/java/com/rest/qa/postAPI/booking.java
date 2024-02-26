package com.rest.qa.postAPI;

import java.util.List;

public class booking {

	// so now here we can craete multiple booking objects with respect to boking class - wrto one class , can craete multiple objects 
	
	// our json request contains nested json array as well i'e multiple json objects 
	
	// so first we have to create the parent pojo class for all the children and then create a pojo class for the nested json array (child)
	
	private  String firstname;
	
	private String lastname;
	private int totalprice;
	 private boolean depositpaid;
	 private String  additionalneeds;
	 private bookingdates bookingdates;
	 
	 // type of booingdates here is bookingdates (class type)
	 
		/*
		 * public booking(String firstname,String lastname,int totalprice ,boolean
		 * depositpaid,bookingdates bookingdates,String additionalneeds ) {
		 * 
		 * this.firstname= firstname; this.lastname= lastname; this.totalprice=
		 * totalprice; this.depositpaid= depositpaid; this.additionalneeds =
		 * additionalneeds; this.bookingdates = bookingdates;
		 * 
		 * 
		 * }
		 */

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public boolean isDepositpaid() {
		return depositpaid;
	}

	public void setDepositpaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
	}
	
	public String getAdditionalneeds() {
		return additionalneeds;
	}

	public void setAdditionalneeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
	}

	public bookingdates getBookingdates() {
		return bookingdates;
	}

	public void setBookingdates(bookingdates bookingdates) {
		this.bookingdates = bookingdates;
	}

	
	 
	
   

	
	 
	 
	
	
}
