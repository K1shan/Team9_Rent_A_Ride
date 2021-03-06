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
   	 		if(customer.getUserStatus().equals(UserStatus.TERMINATED)){
   	 			throw new RARException( "AccountCtrl.login: You have been banned." );
   	 		}
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
   	 		user.setMemberUntil(customer.getMemberUntil());
   	 		user.setLicenseState(customer.getLicenseState());
   	 		user.setLicenseNum(customer.getLicenseNumber());
   	 		user.setCcNum(customer.getCreditCardNumber());
   	 		user.setCcExp(customer.getCreditCardExpiration());
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
			
			Customer modelCustomer = objectLayer.createCustomer();
			modelCustomer.setId(admin.getId());
			List<Customer> customers = objectLayer.findCustomer(modelCustomer);
			Customer customer = customers.get(0);
			if(customers.size() > 0) {
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
	   	 		user.setLicenseState(customer.getLicenseState());
	   	 		user.setLicenseNum(customer.getLicenseNumber());
	   	 		user.setCcNum(customer.getCreditCardNumber());
	   	 		user.setCcExp(customer.getCreditCardExpiration());
	   	 		user.setIsAdmin(true);
	   	 		session.setUser( user );
				ssid = SessionManager.storeSession(session);
			}
			
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

		Customer customer = null;
		Customer modelCustomer = objectLayer.createCustomer();
		modelCustomer.setId(session.getUser().getId());
		List<Customer> customers = objectLayer.findCustomer(modelCustomer);
		if(customers.size() > 0){
			customer = customers.get( 0 );
			if(firstName != null && !firstName.isEmpty())
				customer.setFirstName(firstName);
			if(lastName != null && !lastName.isEmpty())
				customer.setLastName(lastName);
			if(userName != null && !userName.isEmpty())
				customer.setUserName(userName);
			if(password != null && !password.isEmpty())
				customer.setPassword(password);
			if(email != null && !email.isEmpty())
				customer.setEmail(email);
			if(address != null && !address.isEmpty())
				customer.setAddress(address);
			if(membershipExpiration != null)
				customer.setMemberUntil(membershipExpiration);
			if(licenseState != null && !licenseState.isEmpty())
				customer.setLicenseState(licenseState);
			if(licenseNumber != null && !licenseNumber.isEmpty())
				customer.setLicenseNumber(licenseNumber);
			if(cardNumber != null && !cardNumber.isEmpty())
				customer.setCreditCardNumber(cardNumber);
			if(cardExpiration != null)
				customer.setCreditCardExpiration(cardExpiration);
			objectLayer.storeCustomer(customer);
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
   	 		user.setMemberUntil(customer.getMemberUntil());
   	 		user.setLicenseState(customer.getLicenseState());
   	 		user.setLicenseNum(customer.getLicenseNumber());
   	 		user.setCcNum(customer.getCreditCardNumber());
   	 		user.setCcExp(customer.getCreditCardExpiration());
   	 		session.setUser( user );
			return;
		}	
	}
	
	public void updateCustomerStatus ( int customerId, String customerStatus) throws RARException{
		
		// get customer from id
		//
		Customer customer = null;
		Customer modelCustomer = objectLayer.createCustomer();
		modelCustomer.setId(customerId);
		List<Customer> customers = objectLayer.findCustomer(modelCustomer);
		if(customers.size() > 0){
			customer = customers.get( 0 );
			customer.setId(customerId);
		}
		
		// change userStatus terminated -> active
		//
		if(customerStatus.equals("ACTIVE")){
			// if already active
			//
			if(customer.getUserStatus().equals(UserStatus.ACTIVE))
				throw new RARException( "AccountCtrl.updateCustomerStatus: This customer is already active." );
			
			
			// if cancelled
			//
			else if(customer.getUserStatus().equals(UserStatus.CANCELLED))
				throw new RARException( "AccountCtrl.updateCustomerStatus: This customer is cancelled." );
			
			
			// terminated -> active
			//
			else if(customer.getUserStatus().equals(UserStatus.TERMINATED)){
				customer.setUserStatus(UserStatus.ACTIVE);
				objectLayer.storeCustomer(customer);
			}
		}
		
		// change userStatus active -> terminated
		//
		else if(customerStatus.equals("TERMINATED")){
			// if already terminated
			//
			if(customer.getUserStatus().equals(UserStatus.TERMINATED)){
				throw new RARException( "AccountCtrl.updateCustomerStatus: This customer is already terminated." );
			}
			
			// if cancelled
			//
			else if(customer.getUserStatus().equals(UserStatus.CANCELLED)){
				customer.setUserStatus(UserStatus.TERMINATED);
				customer.getReservations();
				objectLayer.storeCustomer(customer);
			}
			
			// active -> terminated
			//
			else if(customer.getUserStatus().equals(UserStatus.ACTIVE)){
				customer.setUserStatus(UserStatus.TERMINATED);
				customer.getReservations();
				objectLayer.storeCustomer(customer);
			}
		}	
	}
}