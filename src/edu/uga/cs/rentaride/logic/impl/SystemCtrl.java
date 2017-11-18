package edu.uga.cs.rentaride.logic.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class SystemCtrl {
	
private ObjectLayer objectLayer = null;
	
	// constructor
    public SystemCtrl( ObjectLayer objectModel ){
        this.objectLayer = objectModel;
    }
    
    public RentARideParams findParams() throws RARException {
		return objectLayer.findRentARideParams();
    }
    
    public void updateParams(int memberFee, int lateFee) throws RARException {
    	RentARideParams params = objectLayer.createRentARideParams();
    	if(memberFee != -1)
    		params.setMembershipPrice(memberFee);
    	else
    		params.setLateFee(lateFee);
    	
    	objectLayer.storeRentARideParams(params);
    }
}
