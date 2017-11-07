package edu.uga.cs.rentaride.logic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.object.*;
import freemarker.core.ParseException;


public class CreateAccountCtrl {
	
	private ObjectLayer objectLayer = null;
    
	private String 			fName;
	private String			lName;
	private String			pword;
	private String 			email;
	
	// constructor
    public CreateAccountCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
    public void createAccount1( String fName, String lName, String email, String password ) throws RARException{
	    	this.fName = fName;
	    	this.lName = lName;
	    	this.email = email;
	    	this.pword = password;
	    	System.out.println("exit createAccount1");
    }
    
    public long createAccount2( String driverNo, String cardNo, String expDate, String address, String state, String zip ) throws RARException{
    	long id = 0;
    	
    	Customer		customer = null;
    	Customer		modelCustomer = null;
    	List<Customer>	customers = null;
    	address = address + ", " + state + ", "+ zip;
    	Date createDate = new Date();
    	createDate.getDate();
    	
    	Calendar c = Calendar.getInstance();
    	c.setTime(createDate);
    	c.add(Calendar.DATE, 180);
    	Date membershipExpiration = c.getTime();
    	 
    	// check if the email already exists
    	modelCustomer = objectLayer.createCustomer();
    	modelCustomer.setUserName(email);
    	customers = objectLayer.findCustomer(modelCustomer);
    	if(customers.size() > 0){
    		customer = customers.get(0);
    	}
    	if(customer != null){
    		throw new RARException( "A user with this username already exists" );
    	}
    	
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
    	Date startDate = null;
    	try {
			startDate = df.parse(expDate);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
    	customer = objectLayer.createCustomer(fName, lName, email, pword, email, address, createDate, membershipExpiration, state, driverNo, cardNo, startDate);
    	objectLayer.storeCustomer(customer);
    	System.out.println("customer: "+customer);
    	System.out.println("exit createAccount2");
    	return customer.getId();
    }
}