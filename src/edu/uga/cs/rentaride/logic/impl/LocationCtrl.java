package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;


public class LocationCtrl {
	
	private ObjectLayer objectLayer = null;
	
	public LocationCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<RentalLocation> getLocationList(RentalLocation rentalLocation) throws RARException{
		if(rentalLocation == null)
			return objectLayer.findRentalLocation(null);
		return objectLayer.findRentalLocation(rentalLocation);
	}
	
	public void persistLocation(RentalLocation rentalLocation) throws RARException{
		objectLayer.storeRentalLocation(rentalLocation);
	}
}
