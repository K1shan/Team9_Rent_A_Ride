package edu.uga.cs.rentaride.logic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.impl.AdministratorImpl;
import edu.uga.cs.rentaride.entity.impl.UserImpl;
import edu.uga.cs.rentaride.object.*;
import edu.uga.cs.rentaride.session.SessionManager;


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
			
	    	Date ccExp = new Date();
	    	int year = Integer.parseInt(expDate.substring(expDate.indexOf("/")+1)); // gets year after "/"
	    	int month = Integer.parseInt(expDate.substring(0, expDate.indexOf("/"))); // gets month before "/"
	    	YearMonth yearMonth = YearMonth.of(year, month);
	    	LocalDate ccExpLocalDate = yearMonth.atEndOfMonth(); // gets the last day of the month
	    	ccExpLocalDate.format(DateTimeFormatter.ISO_LOCAL_DATE); // formats it to YYYY-MM-DD
	    	ccExp = java.sql.Date.valueOf(ccExpLocalDate); // converts LocalDate object back to Date
	    	
	    	long membershipLength = 24*60*60*1000;
	    	
	    	Date createDate = new Date();
	    	Date membershipExpiration = new Date(createDate.getTime() + membershipLength);
	    	//Passing into the object layer for create customer 
	    	customer = objectLayer.createCustomer(fName, lName, email, password, email, address, createDate, membershipExpiration, state, driverNo, cardNo, ccExp);
	    	objectLayer.storeCustomer(customer);
	    	return customer.getId();
    }
	
	public long setAdmin(String username) throws RARException{
		Customer modelCustomer = objectLayer.createCustomer();
		Customer customer = null;
		modelCustomer.setUserName(username);
   	 	List<Customer> customers = objectLayer.findCustomer(modelCustomer);
   	 	if(customers.size() > 0){
	 		customer = customers.get( 0 );
	 	}else{
   	 		throw new RARException( "setAdmin(): Nothing in Customer table" );
	 	}
   	 	objectLayer.deleteCustomer(customer);
		Administrator admin = objectLayer.createAdministrator(customer.getFirstName(), customer.getLastName(), customer.getUserName(), customer.getPassword(), customer.getEmail(), customer.getAddress(), customer.getCreatedDate());
		objectLayer.storeAdministrator(admin);
		return admin.getId();
	}
}