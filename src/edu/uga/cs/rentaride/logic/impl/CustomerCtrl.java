package edu.uga.cs.rentaride.logic.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.session.Session;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class CustomerCtrl {
	
	private ObjectLayer objectLayer = null;
	
	public CustomerCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Customer> findCustomers( int id ) throws RARException{
		if(id < 0)
			return objectLayer.findCustomer(null);
		
		Customer modelCustomer = objectLayer.createCustomer();
		modelCustomer.setId(id);
		return objectLayer.findCustomer(modelCustomer);
	}
	
	@SuppressWarnings("deprecation")
	public void renewMembership( Session session ) throws RARException {
		
		Calendar cal;
   	 	Date dateMemberTill = null;
		User user = session.getUser();
		if(user.getUserStatus().equals(UserStatus.ACTIVE)){
			dateMemberTill = user.getMemberUntil();
			dateMemberTill.setMonth((dateMemberTill.getMonth() - 1 + 6) + 1);
			//dateMemberTill.setYear((dateMemberTill.getYear() ));
		}else if(user.getUserStatus().equals(UserStatus.CANCELLED)){
			cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 6);
			dateMemberTill = cal.getTime();
			user.setUserStatus(UserStatus.ACTIVE);
			user.setMemberUntil(dateMemberTill);
			session.setUser( user );
		}
		
		Customer customer = null;
		Customer modelCustomer = objectLayer.createCustomer();
		modelCustomer.setId(user.getId());
		List<Customer> customers = objectLayer.findCustomer(modelCustomer);
		if(customers.size() > 0){
			customer = customers.get(0);
		}
		
		if(customer == null)
			throw new RARException( "customer does not exist" );
		
		customer = objectLayer.createCustomer(user.getFirstName(), user.getLastName(), user.getUserName(), 
				user.getPassword(), user.getEmail(), user.getAddress(), user.getCreatedDate(), 
				dateMemberTill, user.getLicenseState(), user.getLicenseNum(), 
				user.getCcNum(), user.getCcExp());
		customer.setId(user.getId());
		customer.setUserStatus(UserStatus.ACTIVE);
		objectLayer.storeCustomer(customer);
	}
	
	public void cancelMembership( Session session ) throws RARException {
		
		User user = session.getUser();
		if(user.getUserStatus().equals(UserStatus.CANCELLED))
			throw new RARException( "Your membership has already been cancelled." );
		else if(user.getUserStatus().equals(UserStatus.ACTIVE)){
			Customer customer = null;
			Customer modelCustomer = objectLayer.createCustomer();
			modelCustomer.setId(user.getId());
			List<Customer> customers = objectLayer.findCustomer(modelCustomer);
			if(customers.size() > 0){
				customer = customers.get(0);
			}
			
			if(customer == null)
				throw new RARException( "customer does not exist" );
			
			user.setUserStatus(UserStatus.CANCELLED);
			user.setMemberUntil(new Date());
			session.setUser( user );
			
			customer = objectLayer.createCustomer(user.getFirstName(), user.getLastName(), user.getUserName(), 
					user.getPassword(), user.getEmail(), user.getAddress(), user.getCreatedDate(), 
					user.getMemberUntil(), user.getLicenseState(), user.getLicenseNum(), 
					user.getCcNum(), user.getCcExp());
			customer.setId(user.getId());
			customer.setUserStatus(user.getUserStatus());
			objectLayer.storeCustomer(customer);
		}
	}
}