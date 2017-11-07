package edu.uga.cs.rentaride.logic;

import java.util.Date;

import edu.uga.cs.rentaride.*;

public interface LogicLayer {
	public void createAccount1 ( String fName, String lName, String email, String password ) throws RARException;
	public long createAccount2 ( String driverNo, String cardNo, String expDate, String address, String state, String zip ) throws RARException;
}