package edu.uga.cs.rentaride.logic.impl;

import java.sql.Timestamp;
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
		
		// create rental
		//
		rental = objectLayer.createRental(pickupTime, reservation, vehicle);
		objectLayer.storeRental(rental);
		
		// set vehicle to INRENTAL
		//
		vehicle.setStatus(VehicleStatus.INRENTAL);
		objectLayer.storeVehicle(vehicle);
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
		
		// retrieve reservation
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

		// check if already returned
		//
		rental = objectLayer.createRental();
		modelRental = objectLayer.createRental();
		modelRental.setId(rentalId);
		rentals = objectLayer.findRental(modelRental);
		if(rentals.size() > 0){
			rental = rentals.get(0);
		}
		
		if(rental == null)
			throw new RARException( "This vehicle was never picked up" );
			
		if(rental.getReturnTime() != null)
			throw new RARException( "This vehicle has already been returned" );
		
		// check if already picked up
		//
		if(pickupTime == null)
			throw new RARException( "This vehicle was never picked up" );
		
		// check if cancelled
		//
		if(reservation.getCancelled())
			throw new RARException( "This reservation was cancelled" );
		
		// update rental
		//
		rental = null;
		rental = objectLayer.createRental();
		rental.setReservation(reservation);
		rental.setPickupTime(pickupTime);
		rental.setReturnTime(new Date());
		rental.setId(rentalId);
		rental.getCharges();

		if(rental.getLate()){
			RentARideParams params = objectLayer.createRentARideParams();
			params = objectLayer.findRentARideParams();
			rental.setCharges(rental.getCharges()+params.getLateFee());
		}
		
		objectLayer.storeRental(rental);
		
		// retrieve vehicle
		//
		rental = objectLayer.createRental();
		modelRental = objectLayer.createRental();
		modelRental.setId(rentalId);
		rentals = objectLayer.findRental(modelRental);
		if(rentals.size() > 0){
			rental = rentals.get(0);
		}
		
		// update vehicle status to in location
		//
		Vehicle vehicle = rental.getVehicle();
		vehicle.setStatus(VehicleStatus.INLOCATION);
		objectLayer.storeVehicle(vehicle);
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
		
		// current time-10 minutes < reservation time = error
		//
//		if( new Date().getTime() < reservation.getPickupTime().getTime()-600000 )
//			throw new RARException( "You can only pickup your vehicle 10 minutes before your pickup time." );
		

		
		// check if already checked out
		//
		if(objectLayer.rentalFromReservation(reservationId)){
			throw new RARException ("This vehicle has already been rented out.");
		}
	}
}