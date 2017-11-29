package edu.uga.cs.rentaride.logic.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;


public class RentalCtrl {
	
	private ObjectLayer objectLayer = null;
	private Rental modelRental = null;
	private Rental rental = null;
	private List<Rental> rentals = null;
	
	public RentalCtrl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
	public List<Rental> findRentals( int id ) throws RARException{
		modelRental = objectLayer.createRental();
		if(id < 0){
			rentals = objectLayer.findRental(null);
			return rentals;
		}
		
		modelRental.setId(id);
		rentals = objectLayer.findRental(modelRental);
		return rentals;
	}
	
	public void createRental( Date pickupTime, int reservationId, int vehicleId ) throws RARException {
		
		// check if reservation already exists
		//
		Reservation modelReservation = objectLayer.createReservation();
		modelReservation.setId(reservationId);
		List<Reservation> reservations = objectLayer.findReservation(modelReservation);
		Reservation reservation = null;
		if(reservations.size() > 0) 
			reservation = reservations.get(0);
		
		// check if reservation is found
		//
		if(reservation == null) 
			throw new RARException( "A reservation with this id does not exist exist" );
		
		// retrieve reservation location
		//
		RentalLocation rentalLocation = reservation.getRentalLocation();
		if( rentalLocation == null )
			throw new RARException( "This reservation does not have a location" );
		
		// retrieve vehicle type
		//
		VehicleType vehicleType = reservation.getVehicleType();
		if( vehicleType == null )
			throw new RARException( "This reservation does not have a vehicle type" );
		
		// retrive available vehicle
		//
		Vehicle modelVehicle = objectLayer.createVehicle();
		modelVehicle.setId(vehicleId);
		modelVehicle.setRentalLocation(rentalLocation);
		modelVehicle.setVehicleType(vehicleType);
		modelVehicle.setStatus(VehicleStatus.INLOCATION);
		modelVehicle.setCondition(VehicleCondition.GOOD);
		List<Vehicle> vehicles = objectLayer.findVehicle(modelVehicle);
		Vehicle vehicle = null;
		if(vehicles.size() > 0)
			vehicle = vehicles.get(0);
		
		// check if vehicle found
		//
		if(vehicle == null)
			throw new RARException( "An available vehicle is not available" );
		
		rental = objectLayer.createRental(pickupTime, reservation, vehicle);
		objectLayer.storeRental(rental);
	}
	
	public void deleteRental(int id) throws RARException {
		
		// check if rental already exists
		//
		modelRental = objectLayer.createRental();
		modelRental.setId(id);
		rentals = objectLayer.findRental(modelRental);
		if(rentals.size() > 0){
			rental = rentals.get(0);
		}
		
		// check if rental found
		//
		if(rental == null)
			throw new RARException( "A rental with this id does not exist." );
	
		objectLayer.deleteRental(rental);
	}
	
	public void updateRental(int rentalId, Date pickupTime, int reservationId, int vehicleId) throws RARException {
		
		// retrieve rental
		//
		modelRental = objectLayer.createRental();
		modelRental.setId(rentalId);
		rentals = objectLayer.findRental(modelRental);
		if(rentals.size() > 0)
			rental = rentals.get(0);
		
		// check if null rental
		//
		if(rental == null)
			throw new RARException( "Rental is null" );
		
		rental.setReturnTime(new Date());
		rental.setId(rentalId);
		objectLayer.storeRental(rental);
	}
	
	public void checkPickupTime(int reservationId) throws RARException{
		
		// retrieve reservation object
		//
		Reservation reservation = null;
		Reservation modelReservation = objectLayer.createReservation();
		modelReservation.setId(reservationId);
		List<Reservation> reservations = objectLayer.findReservation(modelReservation);
		if(reservations.size() > 0)
			reservation = reservations.get(0);
		
		// check if null
		//
		if(reservation == null)
			throw new RARException( "Reservation is null" );
		
		// compare times
		//
		if(new Date().getTime() < reservation.getPickupTime().getTime())
			throw new RARException( "You cannot pickup your vehicle before your pickup time." );
		
	}
}