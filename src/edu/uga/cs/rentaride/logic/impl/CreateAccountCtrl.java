package edu.uga.cs.rentaride.logic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.object.*;


public class CreateAccountCtrl {
	
	private ObjectLayer objectLayer = null;
	
	// constructor
    public CreateAccountCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
    //Create account
	public long createAccount(String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, String address, String city, String state, String zip ) throws RARException{
		
	    	//Creating the customer
	    	Customer		customer = null;
	    	
	    	//combining the address
	    	address = address + ", " + city + ", " + state + ", "+ zip;
			
	    	//TODO
	    	Date date1 = new Date();
	    	long time = 24*60*60*1000;
	    	
	    	Date createDate = new Date();
	    	Date membershipExpiration = new Date(createDate.getTime() + time);
	    	//Passing into the object layer for create customer 
	    	customer = objectLayer.createCustomer(fName, lName, email, password, email, address, createDate, membershipExpiration, state, driverNo, cardNo, date1);
	    	objectLayer.storeCustomer(customer);
	    	return customer.getId();
    }
}