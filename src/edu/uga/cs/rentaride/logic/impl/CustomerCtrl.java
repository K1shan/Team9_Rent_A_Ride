package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
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
		
		Customer modelCustomer = null;
		modelCustomer.setId(id);
		return objectLayer.findCustomer(modelCustomer);
	}
	
}
