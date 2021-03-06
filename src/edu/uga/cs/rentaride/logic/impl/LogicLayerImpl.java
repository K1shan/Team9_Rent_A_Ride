package edu.uga.cs.rentaride.logic.impl;

import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.session.SessionManager;
import edu.uga.cs.rentaride.logic.*;
import edu.uga.cs.rentaride.object.*;
import edu.uga.cs.rentaride.object.impl.*;
import edu.uga.cs.rentaride.persistence.*;
import edu.uga.cs.rentaride.persistence.impl.*;
import edu.uga.cs.rentaride.session.Session;

public class LogicLayerImpl
	implements LogicLayer 
{
	
	private ObjectLayer objectLayer = null;
	
	public LogicLayerImpl( Connection conn ){
        objectLayer = new ObjectLayerImpl();
        PersistenceLayer persistenceLayer = new PersistenceLayerImpl( conn, objectLayer );
        objectLayer.setPersistence( persistenceLayer );
    }
    
    public LogicLayerImpl( ObjectLayer objectLayer ){
        this.objectLayer = objectLayer;
    }
	
    @Override
	public List<RentalLocation> findLocations( int locationId ) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		return ctrlLocation.findLocations(locationId);
	}

	@Override
	public List<Reservation> findLocationReservations( int locationId ) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		return ctrlLocation.findLocationReservations(locationId);
	}
	
	@Override
	public List<Vehicle> findLocationVehicles(int locationId) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		return ctrlLocation.findLocationVehicles(locationId);
	}
	
	@Override
	public List<VehicleType> findLocationAvailableVehicleTypes(int locationId) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		return ctrlLocation.findLocationAvailableVehicleTypes(locationId);
	}
	
	@Override
	public List<Vehicle> findVehicles( int id ) throws RARException {
		VehicleCtrl ctrlVehicle = new VehicleCtrl ( objectLayer );
		return ctrlVehicle.findVehicles(id);
	}

	@Override
	public List<VehicleType> findVehicleTypes( int id ) throws RARException {
		VehicleTypeCtrl ctrlType = new VehicleTypeCtrl ( objectLayer );
		return ctrlType.findVehicleTypes(id);
	}
	
	@Override
	public List<String> findTypePaths(int id) throws RARException {
		VehicleTypeCtrl ctrlType = new VehicleTypeCtrl ( objectLayer );
		return ctrlType.findTypePaths(id);
	}

	@Override
	public List<HourlyPrice> findHourlyPrices( int id ) throws RARException {
		HourlyPriceCtrl ctrlPrice = new HourlyPriceCtrl ( objectLayer );
		return ctrlPrice.findHourlyPrices(id);
	}

	@Override
	public List<Reservation> findReservations( int id ) throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		return ctrlReservation.findReservation(id);
	}
	
	@Override
	public List<Reservation> findCustomerReservations(int id) throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		return ctrlReservation.findCustomerReservation(id);
	}
	
	@Override
	public List<Vehicle> findReservationVehicles(int reservationId) throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		return ctrlReservation.findReservationVehicles(reservationId);
	}

	@Override
	public List<Rental> findRentals( int id ) throws RARException {
		RentalCtrl ctrlRental = new RentalCtrl ( objectLayer );
		return ctrlRental.findRentals(id);
	}

	@Override
	public List<Comment> findComments( int id ) throws RARException {
		CommentCtrl ctrlComment = new CommentCtrl ( objectLayer );
		return ctrlComment.findComments(id);
	}

	@Override
	public List<Customer> findCustomers( int id ) throws RARException {
		AccountCtrl ctrlAccount = new AccountCtrl ( objectLayer );
		return ctrlAccount.findCustomers(id);
	}

	@Override
	public List<Administrator> findAdministrators( int id ) throws RARException {
		AccountCtrl ctrlAccount = new AccountCtrl ( objectLayer );
		return ctrlAccount.findAdministrators(id);
	}
    
	@Override
	public RentARideParams findParams() throws RARException {
		SystemCtrl ctrlSystem = new SystemCtrl ( objectLayer );
		return ctrlSystem.findParams();
	}
	
	@Override
	public long createAccount(String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, String address, String city, String state, String zip)
			throws RARException {
		CreateAccountCtrl ctrlCreateAccount = new CreateAccountCtrl ( objectLayer );
		return ctrlCreateAccount.createAccount(fName, lName, email, password, driverNo, cardNo, expDate, address, city, state, zip);
	}
	
	public void logout( String ssid ) throws RARException{
        SessionManager.logout( ssid );
    }
	
	@Override
	public String checkCustomerCredentials(Session session, String email, String password)
			throws RARException {
		AccountCtrl ctrlAccount = new AccountCtrl ( objectLayer );
		return ctrlAccount.checkCustomerCredentials(session, email, password);
	}

	@Override
	public String checkAdminCredentials(Session session, String email, String password) 
			throws RARException {
		AccountCtrl ctrlAccount = new AccountCtrl ( objectLayer );
		return ctrlAccount.checkAdminCredentials(session, email, password);
	}
	
	@Override
	public long setAdmin(String username)
			throws RARException {
		CreateAccountCtrl ctrlCreateAccount = new CreateAccountCtrl ( objectLayer );
		return ctrlCreateAccount.setAdmin(username);
	}
	
	@Override
	public void createLocation(String name, String address, String city, String state, String zip, String path,
			int capacity) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		ctrlLocation.createLocation(name, address, city, state, zip, path, capacity);
	}

	@Override
	public void createType(String name, String path) throws RARException {
		VehicleTypeCtrl ctrlType = new VehicleTypeCtrl ( objectLayer );
		ctrlType.createType(name, path);
	}

	@Override
	public void createHourlyPrice(int vehicleTypeId, int maxHrs, int price) throws RARException {
		HourlyPriceCtrl ctrlPrice = new HourlyPriceCtrl ( objectLayer );
		ctrlPrice.createHourlyPrice(vehicleTypeId, maxHrs, price);
	}
	
	@Override
	public void createVehicle(int typeId, int locationId, String make, String model, int year, int mileadge, String tag,
			Date lastServiced, VehicleStatus vehicleStatus, VehicleCondition vehicleCondition) throws RARException {
		VehicleCtrl ctrlVehicle = new VehicleCtrl ( objectLayer );
		ctrlVehicle.createVehicle(typeId, locationId, make, model, year, mileadge, tag, lastServiced, vehicleStatus, vehicleCondition);
	}
	
	@Override
	public void createReservation(Date pickupTime, int rentalLength, int vehicleTypeId, int locationId, int customerId)
			throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		ctrlReservation.createReservation(pickupTime, rentalLength, vehicleTypeId, locationId, customerId);
	}

	@Override
	public void createRental(Date pickupTime, int reservationId, int vehicleId) throws RARException {
		RentalCtrl ctrlRental = new RentalCtrl ( objectLayer );
		ctrlRental.createRental(pickupTime, reservationId, vehicleId);
	}

	@Override
	public void createComment(int rentalId, String text, Date commentDate) throws RARException {
		CommentCtrl ctrlComment = new CommentCtrl ( objectLayer );
		ctrlComment.createComment(rentalId, text, commentDate);
	}

	@Override
	public void updateLocation(int locationId, String name, String address, String city, String state, String zip, String path,
			int capacity) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		ctrlLocation.updateLocation(locationId, name, address, city, state, zip, path, capacity);
	}
	
	@Override
	public void updateVehicleType(int typeId, String name) throws RARException {
		VehicleTypeCtrl ctrlVehicleType = new VehicleTypeCtrl ( objectLayer );
		ctrlVehicleType.updateVehicleType(typeId, name);
	}

	@Override
	public void updateHourlyPrice(int hourlyPriceId, int vehicleTypeId, int maxHrs, int price) throws RARException {
		HourlyPriceCtrl ctrlHourlyPrice = new HourlyPriceCtrl ( objectLayer );
		ctrlHourlyPrice.updateHourlyPrice(hourlyPriceId, vehicleTypeId, maxHrs, price);
	}
	
	@Override
	public void updateVehicle(int vehicleId, int vehicleTypeId, int rentalLocationId, String make, String model,
			int year, int mileage, String tag, Date lastServiced, VehicleStatus vehicleStatus,
			VehicleCondition vehicleCondition) throws RARException {
		VehicleCtrl ctrlVehicle = new VehicleCtrl ( objectLayer );
		ctrlVehicle.updateVehicle(vehicleId, vehicleTypeId, rentalLocationId, make, model, year, mileage, tag, lastServiced, vehicleStatus, vehicleCondition);
	}

	@Override
	public void updateReservation(int reservationId, Date pickupTime, int rentalLength, int typeId,
			int rentalLocationId, int customerId) throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		ctrlReservation.updateReservation(reservationId, pickupTime, rentalLength, typeId, rentalLocationId, customerId);
	}

	@Override
	public void updateRental(int rentalId, Date pickupTime, int reservationId, int vehicleId) throws RARException {
		RentalCtrl ctrlRental = new RentalCtrl ( objectLayer );
		ctrlRental.updateRental(rentalId, pickupTime, reservationId, vehicleId);
	}

	@Override
	public void updateComment(int commentId, int rentalId, String text, Date commentDate) throws RARException {
		CommentCtrl ctrlComment = new CommentCtrl ( objectLayer );
		ctrlComment.updateComment(commentId, rentalId, text, commentDate);
	}
	
	@Override
	public void updateParams(int memberFee, int lateFee) throws RARException {
		SystemCtrl ctrlSystem = new SystemCtrl ( objectLayer );
		ctrlSystem.updateParams(memberFee, lateFee);
	}

	@Override
	public void updateAdmin(Session session, int id, String firstName, String lastName, String userName, String password, String email, String address, 
			Date membershipExpiration, String licenseState, String licenseNumber, String cardNumber, Date cardExpiration) throws RARException {
		AccountCtrl ctrlAccount = new AccountCtrl ( objectLayer );
		ctrlAccount.updateAccount(session, id, firstName, lastName, userName, password, email, address, membershipExpiration, licenseState, licenseNumber, cardNumber, cardExpiration);
	}
	
	@Override
	public void updateCustomer(Session session, int id, String firstName, String lastName, String userName, String password, String email, String address, 
			Date membershipExpiration, String licenseState, String licenseNumber, String cardNumber, Date cardExpiration) throws RARException {
		AccountCtrl ctrlAccount = new AccountCtrl ( objectLayer );
		ctrlAccount.updateAccount(session, id, firstName, lastName, userName, password, email, address, membershipExpiration, licenseState, licenseNumber, cardNumber, cardExpiration);
	}
	
	@Override
	public void updateCustomerStatus(int id, String customerStatus) throws RARException {
		AccountCtrl ctrlAccount = new AccountCtrl ( objectLayer );
		ctrlAccount.updateCustomerStatus(id, customerStatus);
	}
	
	@Override
	public void resetUserPassword(String email, String password, String fname, String lname) throws RARException {
		AccountCtrl accountControl = new AccountCtrl(objectLayer);
		accountControl.resetUserPassword(email, password, fname, lname);
		
	}
	
	@Override
	public void deleteLocation(int id) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		ctrlLocation.deleteLocation(id);
	}

	@Override
	public void deleteVehicle(int id) throws RARException {
		VehicleCtrl ctrlVehicle = new VehicleCtrl ( objectLayer );
		ctrlVehicle.deleteVehicle(id);
	}

	@Override
	public void deleteVehicleType(int id) throws RARException {
		VehicleTypeCtrl ctrlType = new VehicleTypeCtrl ( objectLayer );
		ctrlType.deleteVehicleType(id);
	}

	@Override
	public void deleteHourlyPrice(int id) throws RARException {
		HourlyPriceCtrl ctrlPrice = new HourlyPriceCtrl ( objectLayer );
		ctrlPrice.deleteHourlyPrice(id);
	}

	@Override
	public void deleteReservation(int id) throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		ctrlReservation.deleteReservation(id);
	}

	@Override
	public void deleteRental(int id) throws RARException {
		RentalCtrl ctrlRental = new RentalCtrl ( objectLayer );
		ctrlRental.deleteRental(id);
	}

	@Override
	public void deleteComment(int id) throws RARException {
		CommentCtrl ctrlComment = new CommentCtrl ( objectLayer );
		ctrlComment.deleteComment(id);
	}

	@Override
	public void renewMembership(Session session) throws RARException {
		CustomerCtrl ctrlCustomer = new CustomerCtrl ( objectLayer );
		ctrlCustomer.renewMembership(session);
	}

	@Override
	public void cancelMembership(Session session) throws RARException {
		CustomerCtrl ctrlCustomer = new CustomerCtrl ( objectLayer );
		ctrlCustomer.cancelMembership(session);
	}
	
	@Override
	public void cancelReservation( int id ) throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		ctrlReservation.cancelReservation(id);
	}

	@Override
	public void checkPickupTime(int reservationId) throws RARException {
		RentalCtrl ctrlRental = new RentalCtrl ( objectLayer );
		ctrlRental.checkPickupTime(reservationId);
	}

	@Override
	public void checkReservationNoShow(int reservationId) throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		ctrlReservation.checkReservationNoShow(reservationId);
	}

	@Override
	public List<Reservation> findNoShowReservation() throws RARException {
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );
		return ctrlReservation.findNoShowReservation();
	}
}