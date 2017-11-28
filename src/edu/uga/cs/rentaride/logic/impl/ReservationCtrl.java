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
		if(id < 0){
			reservations = objectLayer.findReservation(null);
			return reservations;
		}
		
		modelReservation.setId(id);
		reservations = objectLayer.findReservation(modelReservation);
		return reservations;
	}
	
	public List<Reservation> findCustomerReservation( int id ) throws RARException{
		
		Customer customer = null;
		Customer modelCustomer = objectLayer.createCustomer();
		modelCustomer.setId(id);
		List<Customer> customers = objectLayer.findCustomer(modelCustomer);
		if(customers.size() > 0)
			customer = customers.get(0);
		
		if(customer == null) 
			throw new RARException("customer does not exist");
		
		return customer.getReservations();
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
		
		// check customer status
		//
		if(!(customer.getUserStatus().equals(UserStatus.ACTIVE)))
			throw new RARException( "You must be an active member to reserve a vehicle." );
		
		reservation = objectLayer.createReservation(pickupTime, rentalLength, vehicleType, rentalLocation, customer);
		objectLayer.storeReservation(reservation);
	}
	
	public List<Vehicle> findReservationVehicles(int id) throws RARException {
		
		modelReservation = objectLayer.createReservation();
		modelReservation.setId(id);
		reservations = objectLayer.findReservation(modelReservation);
		if(reservations.size() > 0){
			reservation = reservations.get(0);
		}
		
		// check if reservation found
		//
		if(reservation == null)
			throw new RARException( "A reservation with this id does not exist." );
		
		Vehicle modelVehicle = objectLayer.createVehicle();
		modelVehicle.setRentalLocation(reservation.getRentalLocation());
		modelVehicle.setVehicleType(reservation.getVehicleType());
		modelVehicle.setStatus(VehicleStatus.INLOCATION);
		modelVehicle.setCondition(VehicleCondition.GOOD);
		List<Vehicle> vehicles = objectLayer.findVehicle(modelVehicle);
		
		// check if vehicles found
		//
		if(vehicles == null)
			throw new RARException( "Vehicles are not available at the moment." );
		
		return vehicles;
	}
	
	public void deleteReservation(int id) throws RARException {
		
		// check if reservation already exists
		//
		modelReservation = objectLayer.createReservation();
		modelReservation.setId(id);
		reservations = objectLayer.findReservation(modelReservation);
		if(reservations.size() > 0){
			reservation = reservations.get(0);
		}
		
		// check if reservation found
		//
		if(reservation == null)
			throw new RARException( "A reservation with this id does not exist." );
	
		objectLayer.deleteReservation(reservation);
	}
	
	public void updateReservation(int reservationId, Date pickupTime, int rentalLength, int typeId,
			int rentalLocationId, int customerId) throws RARException {
		
		// check if reservation already exists
		//
		modelReservation = objectLayer.createReservation();
		modelReservation.setId(reservationId);
		reservations = objectLayer.findReservation(modelReservation);
		if(reservations.size() > 0){
			reservation = reservations.get(0);
		}
			
		// check if reservation found
		//
		if(reservation == null)
			throw new RARException( "A reservation with this id does not exist" );
		
		// check if type already exists
		//
		VehicleType modelVehicleType = objectLayer.createVehicleType();
		modelVehicleType.setId(typeId);
		List<VehicleType> vehicleTypes = objectLayer.findVehicleType(modelVehicleType);
		VehicleType vehicleType = null;
		if(vehicleTypes.size() > 0) 
			vehicleType = vehicleTypes.get(0);
		
		if(vehicleType == null) 
			throw new RARException("A vehicle type with this id does not exist exists");
		
		// check if location exists
		//
		RentalLocation modelRentalLocation = objectLayer.createRentalLocation();
		modelRentalLocation.setId(rentalLocationId);
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
		
		reservation = null;
		reservation = objectLayer.createReservation(pickupTime, rentalLength, vehicleType, rentalLocation, customer);
		reservation.setId(reservationId);
		objectLayer.storeReservation(reservation);
	}
	
	public void cancelReservation ( int id ) throws RARException {
		
		// check if reservation already exists
		//
		modelReservation = objectLayer.createReservation();
		modelReservation.setId(id);
		reservations = objectLayer.findReservation(modelReservation);
		if(reservations.size() > 0){
			reservation = reservations.get(0);
		}
			
		// check if reservation found
		//
		if(reservation == null)
			throw new RARException( "A reservation with this id does not exist" );
		
		// check if reservation is already cancelled
		//
		if(reservation.getCancelled())
			throw new RARException( "This reservation is already cancelled" );
		
		// 3600000 ms in an hour
		//
		if(reservation.getPickupTime().getTime() - new Date().getTime() < 3600000)
			throw new RARException( "You cannot cancel a reservation within an hour of the pickup time" );
		
		// update reservation to cancelled
		//
		reservation = objectLayer.createReservation(reservation.getPickupTime(), reservation.getLength(), 
				reservation.getVehicleType(), reservation.getRentalLocation(), reservation.getCustomer());
		reservation.setId(id);
		reservation.setCancelled(true);
		objectLayer.storeReservation(reservation);
	}
}