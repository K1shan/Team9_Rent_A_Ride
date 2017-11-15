package edu.uga.cs.rentaride.logic.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.User;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.UserImpl;
import edu.uga.cs.rentaride.object.*;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;


public class AccountCtrl {
	
	private ObjectLayer objectLayer = null;
	
	// constructor
    public AccountCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
    //check isCustomer
	public String checkCustomerCredentials( Session session, String email, String password ) throws RARException{
		String ssid = null;
		Customer modelCustomer = objectLayer.createCustomer();
   	 	modelCustomer.setEmail(email);
   	 	modelCustomer.setPassword(password);
   	 	List<Customer> customers = objectLayer.findCustomer(modelCustomer);
   	 	if(customers.size() > 0){
   	 		Customer customer = customers.get( 0 );
   	 		User user = new UserImpl();
   	 		user.setFirstName(customer.getFirstName());
   	 		user.setLastName(customer.getLastName());
   	 		user.setUserName(customer.getUserName());
   	 		user.setEmail(customer.getEmail());
   	 		user.setPassword(customer.getPassword());
   	 		user.setAddress(customer.getAddress());
   	 		user.setCreateDate(customer.getCreatedDate());
   	 		user.setUserStatus(customer.getUserStatus());
   	 		session.setUser( user );
   	 		ssid = SessionManager.storeSession(session);
   	 	}else
   	 		throw new RARException( "AccountCtrl.login: Invalid User Name or Password" );
   	 	return ssid;
    } // checkCustomer

	// check isAdmin
	public String checkAdminCredentials(Session session, String email, String password) throws RARException{
		String ssid = null;
		Administrator modelAdmin = objectLayer.createAdministrator();
		modelAdmin.setEmail(email);
		modelAdmin.setPassword(password);
		List<Administrator> administrators = objectLayer.findAdministrator(modelAdmin);
		if(administrators.size() > 0){
			Administrator admin = administrators.get(0);
			User user = new UserImpl();
   	 		user.setFirstName(admin.getFirstName());
   	 		user.setLastName(admin.getLastName());
   	 		user.setUserName(admin.getUserName());
   	 		user.setEmail(admin.getEmail());
   	 		user.setPassword(admin.getPassword());
   	 		user.setAddress(admin.getAddress());
   	 		user.setCreateDate(admin.getCreatedDate());
   	 		user.setUserStatus(admin.getUserStatus());
   	 		user.setIsAdmin(true);
   	 		session.setUser( user );
			ssid = SessionManager.storeSession(session);
   	 	}else
   	 		throw new RARException( "AccountCtrl.login: Invalid User Name or Password" );
   	 	return ssid;
	} // checkAdmin
	
	public void resetUserPassword(String email, String password) throws RARException{
		Customer customer;
		Customer modelCustomer = objectLayer.createCustomer();
		long customerid;
   	 	modelCustomer.setEmail(email);
   	 	List<Customer> customers = objectLayer.findCustomer(modelCustomer);
   	 	if(customers.size() > 0){
	 		customer = customers.get( 0 );
   	 	}
 		else
   	 		throw new RARException( "AccountCtrl.login: Invalid Username" );
   	 	
   	 	customer.setPassword(password);
   	 	objectLayer.storeCustomer(customer);
   	 	
	}
	
}
