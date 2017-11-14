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
	
	public List<RentalLocation> findLocations(RentalLocation rentalLocation) throws RARException{
		if(rentalLocation == null)
			return objectLayer.findRentalLocation(null);
		return objectLayer.findRentalLocation(rentalLocation);
	}
	
	public void createLocation (String name, String address, String city, String state, String zip, String path,
			int capacity) throws RARException {
		RentalLocation rentalLocation = null;
		RentalLocation modelRentalLocation = null;
		List<RentalLocation> rentalLocations = null;
		
		// check if location already exists
		modelRentalLocation = objectLayer.createRentalLocation();
		modelRentalLocation.setName(name);
		rentalLocations = objectLayer.findRentalLocation(modelRentalLocation);
		if(rentalLocations.size() > 0)
			rentalLocation = rentalLocations.get(0);
		
		// check if location exists
		if(rentalLocation != null)
			throw new RARException( "A location with this name already exists" );
		
		rentalLocation = objectLayer.createRentalLocation(name, address, city, state, zip, path, capacity);
		objectLayer.storeRentalLocation(rentalLocation);
	}
	
	public void updateLocation (String name, String address, String city,
			String state, String zip, String path, int capacity) throws RARException{
		RentalLocation rentalLocation = null;
		RentalLocation modelRentalLocation = null;
		List<RentalLocation> rentalLocations = null;
		
		// check if location already exists
		modelRentalLocation = objectLayer.createRentalLocation();
		modelRentalLocation.setName(name);
		rentalLocations = objectLayer.findRentalLocation(modelRentalLocation);
		if(rentalLocations.size() > 0)
			rentalLocation = rentalLocations.get(0);
			
		// check if location exists
		if(rentalLocation == null)
			throw new RARException( "A location with this name does not exist" );

		rentalLocation = objectLayer.createRentalLocation(name, address, city, state, zip, path, capacity);
		objectLayer.storeRentalLocation(rentalLocation);
	}
}
