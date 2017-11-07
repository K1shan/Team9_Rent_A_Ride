package edu.uga.cs.rentaride.logic;

import edu.uga.cs.rentaride.*;

public interface LogicLayer {
	
	//Create Account interface
	public long createAccount ( String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, String address, String state, String zip ) throws RARException;
}