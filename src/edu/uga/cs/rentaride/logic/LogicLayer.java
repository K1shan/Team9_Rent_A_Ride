package edu.uga.cs.rentaride.logic;

import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.session.Session;

public interface LogicLayer {
	
	//Create Account interface
	public long createAccount ( String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, String address, String state, String zip ) throws RARException;
	
	// check email/password
	public String checkCredentials ( Session session, String email, String password ) throws RARException;
}