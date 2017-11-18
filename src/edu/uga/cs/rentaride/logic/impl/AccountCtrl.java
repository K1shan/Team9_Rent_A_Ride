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
   	 		user.setId(customer.getId());
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
			user.setId(admin.getId());
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
	
	public void resetUserPassword(String email, String password, String fname, String lname) throws RARException{
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
   	 	modelCustomer.setFirstName(fname);
   	 	modelCustomer.setLastName(lname);
   	 	List<Customer> customers = objectLayer.findCustomer(modelCustomer);
   	 	if(customers.size() > 0){
	 		customer = customers.get( 0 );
	 		customerId = customer.getId();
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
 			modelAdministrator.setFirstName(fname);
 			modelAdministrator.setLastName(lname);
 			List<Administrator> administrators = objectLayer.findAdministrator(modelAdministrator);
 			if(administrators.size() > 0){
 				administrator = administrators.get( 0 );
 				adminId = administrator.getId();
 				address = administrator.getAddress();
 				createDate = administrator.getCreatedDate();
 				administrator = objectLayer.createAdministrator(fname, lname, email, password, email, address, createDate);
 				administrator.setId(adminId);
 				objectLayer.storeAdministrator(administrator);
 				return;
 			}else
 				throw new RARException( "AccountCtrl.login: Invalid Username" );
 		}
   	 	
	}

	public void updateAccount(Session session, int id, String firstName, String lastName, String userName, String password, String email, String address, 
			Date membershipExpiration, String licenseState, String licenseNumber, String cardNumber, Date cardExpiration) throws RARException {
		Administrator administrator = null;
		Administrator modelAdministrator = objectLayer.createAdministrator();
		modelAdministrator.setEmail(email);
		List<Administrator> administrators = objectLayer.findAdministrator(modelAdministrator);
		if(administrators.size() > 0){
			administrator = administrators.get( 0 );
			if(!firstName.equals(null) && !firstName.equals(""))
				administrator.setFirstName(firstName);
			if(!lastName.equals(null) && !lastName.equals(""))
				administrator.setLastName(lastName);
			if(!userName.equals(null) && !userName.equals(""))
				administrator.setUserName(userName);
			if(!password.equals(null) && !password.equals(""))
				administrator.setPassword(password);
			if(!email.equals(null) && !email.equals(""))
				administrator.setEmail(email);
			if(!address.equals(null) && !address.equals(""))
				administrator.setAddress(address);
			objectLayer.storeAdministrator(administrator);
			User user = new UserImpl();
   	 		user.setFirstName(administrator.getFirstName());
   	 		user.setLastName(administrator.getLastName());
   	 		user.setUserName(administrator.getUserName());
   	 		user.setEmail(administrator.getEmail());
   	 		user.setPassword(administrator.getPassword());
   	 		user.setAddress(administrator.getAddress());
   	 		user.setCreateDate(administrator.getCreatedDate());
   	 		user.setUserStatus(administrator.getUserStatus());
   	 		user.setIsAdmin(true);
   	 		session.setUser( user );
			return;
		}
		Customer customer = null;
		Customer modelCustomer = objectLayer.createCustomer();
		modelCustomer.setId(id);
		List<Customer> customers = objectLayer.findCustomer(modelCustomer);
		if(customers.size() > 0){
			customer = customers.get( 0 );
			customer.setId(id);
			if(!firstName.equals(null))
				customer.setFirstName(firstName);
			if(!lastName.equals(null))
				customer.setLastName(lastName);
			if(!userName.equals(null))
				customer.setUserName(userName);
			if(!password.equals(null))
				customer.setPassword(password);
			if(!email.equals(null))
				customer.setEmail(email);
			if(!address.equals(null))
				customer.setAddress(address);
			if(!membershipExpiration.equals(null))
				customer.setMemberUntil(membershipExpiration);
			if(!licenseState.equals(null))
				customer.setLicenseState(licenseState);
			if(!licenseNumber.equals(null))
				customer.setLicenseNumber(licenseNumber);
			if(!cardNumber.equals(null))
				customer.setCreditCardNumber(cardNumber);
			if(!cardExpiration.equals(null))
				customer.setCreditCardExpiration(cardExpiration);
			objectLayer.storeCustomer(customer);
			return;
		}	
	}
}