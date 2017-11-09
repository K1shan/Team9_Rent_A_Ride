package edu.uga.cs.rentaride.logic;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.session.Session;

public interface LogicLayer {
	
	//Create Account interface
	public long createAccount ( String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, String address, String city, String state, String zip) throws RARException;
	
	// logout
	public void logout( String ssid ) throws RARException;
	
	// check email/password
	public String checkCustomerCredentials ( Session session, String email, String password ) throws RARException;

	// check admin email/password
	public String checkAdminCredentials ( Session session, String email, String password ) throws RARException;

}