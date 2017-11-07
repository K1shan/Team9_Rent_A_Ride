package edu.uga.cs.rentaride.logic.impl;

import com.mysql.jdbc.Connection;

import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.logic.*;
import edu.uga.cs.rentaride.object.*;
import edu.uga.cs.rentaride.object.impl.*;
import edu.uga.cs.rentaride.persistence.*;
import edu.uga.cs.rentaride.persistence.impl.*;

public class LogicLayerImpl 
	implements LogicLayer 
{
	
	private ObjectLayer objectLayer = null;
	
	public LogicLayerImpl( Connection conn ){
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = new PersistenceLayerImpl( conn, objectLayer );
        objectLayer.setPersistence( persistenceLayer );
    }
    
    public LogicLayerImpl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	@Override
	public long createAccount(String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, String address, String state, String zip)
			throws RARException {
		CreateAccountCtrl ctrlCreateAccount = new CreateAccountCtrl ( objectLayer );
		return ctrlCreateAccount.createAccount(fName, lName, email, password, driverNo, cardNo, expDate, address, state, zip);
	}
}