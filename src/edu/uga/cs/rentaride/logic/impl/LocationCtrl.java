package edu.uga.cs.rentaride.logic.impl;

import java.util.List;

import com.mysql.jdbc.Connection;

import edu.uga.cs.rentaride.logic.LogicLayer;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.object.impl.ObjectLayerImpl;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.PersistenceLayerImpl;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;


public class LocationCtrl {
	
	private ObjectLayer objectLayer = null;
	
	public LocationCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<RentalLocation> getLocationList() throws RARException{
		return objectLayer.findRentalLocation(null);
	}
	
	public void persistLocation(RentalLocation rentalLocation) throws RARException{
		objectLayer.storeRentalLocation(rentalLocation);
	}
}
