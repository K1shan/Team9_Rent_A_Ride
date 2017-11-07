package edu.uga.cs.rentaride.logic.impl;

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
	public long createAccount( String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, String address, String state, String zip ) throws RARException{
    
	    	//Creating the customer
	    	Customer		customer = null;
	    	
	    	//combining the address
	    	address = address + ", " + state + ", "+ zip;
			
	    	//TODO
	    Date date1 = new Date();
	    Date date2 = new Date();
	    Date date3 = new Date();
	    	
	    	//Passing into the object layer for create customer 
	    	customer = objectLayer.createCustomer(fName, lName, email, password, email, address, date1, date2, state, driverNo, cardNo, date3);
	    	objectLayer.storeCustomer(customer);
	    	return customer.getId();
    }
}