package edu.uga.cs.rentaride.logic.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class ReservationCtrl {
	
	private ObjectLayer objectLayer = null;
	private Reservation modelReservation = null;
	private Reservation reservation = null;
	private List<Reservation> reservations = null;
	
	public ReservationCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Reservation> findReservation( int id ) throws RARException{
		modelReservation = objectLayer.createReservation();
		if(id < 0)
			return objectLayer.findReservation(null);
		
		modelReservation.setId(id);
		return objectLayer.findReservation(modelReservation);
	}
	
	public void createReservation(Date pickupTime, int rentalLength, int vehicleTypeId, int locationId, int customerId) throws RARException {
		
		// check if type already exists
		//
		VehicleType modelVehicleType = objectLayer.createVehicleType();
		modelVehicleType.setId(vehicleTypeId);
		List<VehicleType> vehicleTypes = objectLayer.findVehicleType(modelVehicleType);
		VehicleType vehicleType = null;
		if(vehicleTypes.size() > 0) 
			vehicleType = vehicleTypes.get(0);
		
		if(vehicleType == null) 
			throw new RARException("A vehicle type with this id does not exist exists");
		
				
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
			throw new RARException( "A location with this id does not exist" );
		
		// check if customer exists
		//
		Customer modelCustomer = objectLayer.createCustomer();
		modelCustomer.setId(customerId);
		List<Customer> customers = objectLayer.findCustomer(modelCustomer);
		Customer customer = null;
		if(customers.size() > 0)
			customer = customers.get(0);
		
		// check if customer found
		//
		if(customer == null)
			throw new RARException( "A customer with this id does not exist" );
		
		reservation = objectLayer.createReservation(pickupTime, rentalLength, vehicleType, rentalLocation, customer);
		objectLayer.storeReservation(reservation);
	}
}
