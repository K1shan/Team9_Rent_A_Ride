package edu.uga.cs.rentaride.logic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.object.*;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;


public class AccountCtrl {
	
	private ObjectLayer objectLayer = null;
	
	// constructor
    public AccountCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
    //Create account
	public String checkCredentials( Session session, String email, String password ) throws RARException{
		String ssid = null;
		Customer modelCustomer = objectLayer.createCustomer();
   	 	modelCustomer.setEmail(email);
   	 	modelCustomer.setPassword(password);
   	 	List<Customer> customers = objectLayer.findCustomer(modelCustomer);
   	 	if(customers.size() > 0){
   	 		Customer customer = customers.get( 0 );
   	 		session.setCustomer( customer );
   	 		ssid = SessionManager.storeSession(session);
   	 	}else{
   	 		throw new RARException( "SessionManager.login: Invalid User Name or Password" );
   	 	}
   	 	return ssid;
    }
}
