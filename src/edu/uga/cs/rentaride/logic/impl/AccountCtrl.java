package edu.uga.cs.rentaride.logic.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.entity.impl.*;
import edu.uga.cs.rentaride.object.*;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.session.SessionManager;


public class AccountCtrl {
	
	private ObjectLayer objectLayer = null;
	
	// constructor
    public AccountCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
    public List<Customer> findCustomers( int id ) throws RARException{
    	Customer modelCustomer = objectLayer.createCustomer();
		if(id < 0)
			return objectLayer.findCustomer(null);
		
		modelCustomer.setId(id);
		return objectLayer.findCustomer(modelCustomer);
	}
    
    public List<Administrator> findAdministrators( int id ) throws RARException{
    	Administrator modelAdministrator = objectLayer.createAdministrator();
    	
		if(id < 0)
			return objectLayer.findAdministrator(null);
		
		modelAdministrator.setId(id);
		return objectLayer.findAdministrator(modelAdministrator);
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
		String fname;
		String lname;
		String address;
		Date createDate;
		Date membershipExpiration;
		String licState;
		String licNum;
		String ccNum;
		Date ccExp;
		Customer customer;
		Customer modelCustomer = objectLayer.createCustomer();
		long customerId;
   	 	modelCustomer.setEmail(email);
   	 	List<Customer> customers = objectLayer.findCustomer(modelCustomer);
   	 	if(customers.size() > 0){
	 		customer = customers.get( 0 );
	 		customerId = customer.getId();
	 		fname = customer.getFirstName();
	 		lname = customer.getLastName();
	 		address = customer.getAddress();
	 		createDate = customer.getCreatedDate();
	 		membershipExpiration = customer.getMemberUntil();
	 		licState = customer.getLicenseState();
	 		licNum = customer.getLicenseNumber();
	 		ccNum = customer.getCreditCardNumber();
	 		ccExp = customer.getCreditCardExpiration();
	 		customer = objectLayer.createCustomer(fname, lname, email, password, email, address, createDate, membershipExpiration, licState, licNum, ccNum, ccExp);
	 		customer.setId(customerId);
	   	 	objectLayer.storeCustomer(customer);
	   	 	return;
   	 	}else{
 			Administrator administrator;
 			Administrator modelAdministrator = objectLayer.createAdministrator();
 			long adminId;
 			modelAdministrator.setEmail(email);
 			List<Administrator> administrators = objectLayer.findAdministrator(modelAdministrator);
 			if(administrators.size() > 0){
 				administrator = administrators.get( 0 );
 				adminId = administrator.getId();
 				fname = administrator.getFirstName();
 				lname = administrator.getLastName();
 				address = administrator.getAddress();
 				createDate = administrator.getCreatedDate();
 				administrator = objectLayer.createAdministrator(fname, lname, email, password, email, address, createDate);
 				administrator.setId(adminId);
 				objectLayer.storeAdministrator(administrator);
 				return;
 			}
 		}
   	 	throw new RARException( "AccountCtrl.login: Invalid Username" );
	}
}
