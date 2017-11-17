package edu.uga.cs.rentaride.logic.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class VehicleCtrl {
	
	private ObjectLayer objectLayer = null;
	private Vehicle modelVehicle = null;
	private Vehicle vehicle = null;
	private List<Vehicle> vehicles = null;

	
	public VehicleCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Vehicle> findVehicles( int id ) throws RARException{
		modelVehicle = objectLayer.createVehicle();
		if(id < 0)
			return objectLayer.findVehicle(null);
		
		modelVehicle.setId(id);
		return objectLayer.findVehicle(modelVehicle);
	}
	
	public void createVehicle(int typeId, int locationId, String make, String model, int year, int mileadge, String tag,
			Date lastServiced, VehicleStatus vehicleStatus, VehicleCondition vehicleCondition) throws RARException {
		
		// check if type already exists
		//
		VehicleType modelVehicleType = objectLayer.createVehicleType();
		modelVehicleType.setId(typeId);
		List<VehicleType> vehicleTypes = objectLayer.findVehicleType(modelVehicleType);
		VehicleType vehicleType = null;
		if(vehicleTypes.size() > 0) {
			vehicleType = vehicleTypes.get(0);
		}
		if(vehicleType == null) {
			throw new RARException("A vehicle type with this name does not exist exist");
		}
		
		// check if location exists
		//
		RentalLocation modelRentalLocation = objectLayer.createRentalLocation();
		modelRentalLocation.setId(locationId);
		List<RentalLocation> rentalLocations = objectLayer.findRentalLocation(modelRentalLocation);
		RentalLocation rentalLocation = null;
		if(rentalLocations.size() > 0)
			rentalLocation = rentalLocations.get(0);
		
		// check if location found
		//
		if(rentalLocation == null)
			throw new RARException( "A location with this name does not exist" );
		
		
		// check if vehicle already exists
		//
		modelVehicle = objectLayer.createVehicle();
		modelVehicle.setRegistrationTag(tag);
		vehicles = objectLayer.findVehicle(modelVehicle);
		if(vehicles.size() > 0)
			vehicle = vehicles.get(0);
		
		// check if vehicle found
		//
		if(vehicle != null)
			throw new RARException( "A vehicle with this tag number already exists" );
		
		vehicle = objectLayer.createVehicle(make, model, year, tag, mileadge, lastServiced, vehicleType, rentalLocation, vehicleCondition, vehicleStatus);
		objectLayer.storeVehicle(vehicle);
	}
	
	public void deleteVehicle(int id) throws RARException {
		
		// check if vehicle already exists
		//
		modelVehicle = objectLayer.createVehicle();
		modelVehicle.setId(id);
		vehicles = objectLayer.findVehicle(modelVehicle);
		if(vehicles.size() > 0){
			vehicle = vehicles.get(0);
		}
		
		// check if vehicle found
		//
		if(vehicle == null)
			throw new RARException( "A vehicle with this id does not exist." );
	
		objectLayer.deleteVehicle(vehicle);
	}
}