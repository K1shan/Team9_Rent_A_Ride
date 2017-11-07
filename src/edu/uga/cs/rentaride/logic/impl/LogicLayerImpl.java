package edu.uga.cs.rentaride.logic.impl;

import java.util.Date;

import com.mysql.jdbc.Connection;

import edu.uga.cs.rentaride.*;
import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;

public class LogicLayerImpl 
	implements LogicLayer 
{
	
	private ObjectLayer objectLayer = null;
	
	public LogicLayerImpl( Connection conn )
    {
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = new PersistenceLayerImpl( conn, objectLayer );
        objectLayer.setPersistence( persistenceLayer );
        System.out.println( "LogicLayerImpl.LogicLayerImpl(conn): initialized" );
    }
    
    public LogicLayerImpl( ObjectLayer objectLayer )
    {
        this.objectLayer = objectLayer;
        System.out.println( "LogicLayerImpl.LogicLayerImpl(objectLayer): initialized" );
    }


	@Override
	public void createAccount1(String fName, String lName, String email, String password) throws RARException {
		
		CreateAccountCtrl ctrlCreateAccount = new CreateAccountCtrl (objectLayer);
		ctrlCreateAccount.createAccount1(fName, lName, email, password);
	}
	
	@Override
	public long createAccount2(String driverNo, String cardNo, String expDate, String address, String state, String zip)
			throws RARException {
		CreateAccountCtrl ctrlCreateAccount = new CreateAccountCtrl ( objectLayer );
		return ctrlCreateAccount.createAccount2(driverNo, cardNo, expDate, address, state, zip);
	}
}