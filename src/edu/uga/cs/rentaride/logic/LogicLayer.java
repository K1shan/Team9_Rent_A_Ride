package edu.uga.cs.rentaride.logic;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;

import java.util.Date;
import java.util.List;

import edu.uga.cs.rentaride.session.Session;

public interface LogicLayer {
	
	/******************************************
	 *  FIND
	 */
	
	/**
	 * 
	 * @param rentalLocation
	 * @return
	 * @throws RARException
	 */
	public List<RentalLocation> findLocations( int locationId ) throws RARException;	
	
	/**
	 * 
	 * @param rentalLocation
	 * @return
	 * @throws RARException
	 */
	public List<Vehicle> findLocationVehicles( int locationId ) throws RARException;

	/**
	 * 
	 * @param rentalLocation
	 * @return
	 * @throws RARException
	 */
	public List<Reservation> findLocationReservations( int locationId ) throws RARException;
	
	/**
	 * 
	 * @param locationId
	 * @return
	 * @throws RARException
	 */
	public List<VehicleType> findLocationAvailableVehicleTypes( int locationId ) throws RARException;
	/**
	 * 
	 * @param vehicle
	 * @return
	 * @throws RARException
	 */
	public List<Vehicle> findVehicles( int id ) throws RARException;

	/**
	 * 
	 * @param vehicleType
	 * @return
	 * @throws RARException
	 */
	public List<VehicleType> findVehicleTypes( int id ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RARException
	 */
	public List<String> findTypePaths( int id ) throws RARException;
	
	/**
	 * 
	 * @param hourlyPrice
	 * @return
	 * @throws RARException
	 */
	public List<HourlyPrice> findHourlyPrices( int id ) throws RARException;
	
	/**
	 * 
	 * @param reservation
	 * @return
	 * @throws RARException
	 */
	public List<Reservation> findReservations( int id ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws RARException
	 */
	public List<Reservation> findCustomerReservations( int id ) throws RARException;

	/**
	 * 
	 * @param reservationId
	 * @return
	 * @throws RARException
	 */
	public List<Vehicle> findReservationVehicles( int reservationId ) throws RARException;
	/**
	 * 
	 * @param rental
	 * @return
	 * @throws RARException
	 */
	public List<Rental> findRentals( int id ) throws RARException;
	
	/**
	 * 
	 * @param comment
	 * @return
	 * @throws RARException
	 */
	public List<Comment> findComments( int id ) throws RARException;
	
	/**
	 * 
	 * @param customer
	 * @return
	 * @throws RARException
	 */
	public List<Customer> findCustomers( int id ) throws RARException;
	
	/**
	 * 
	 * @param administrator
	 * @return
	 * @throws RARException
	 */
	public List<Administrator> findAdministrators( int id ) throws RARException;
	
	/**
	 * 
	 * @return
	 * @throws RARException
	 */
	public RentARideParams findParams() throws RARException;
	
	
	/******************************************
	 *  CREATION
	 */
	
	/**
	 * 
	 * @param fName
	 * @param lName
	 * @param email
	 * @param password
	 * @param driverNo
	 * @param cardNo
	 * @param expDate
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @return
	 * @throws RARException
	 */
	public long createAccount ( String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, 
			String address, String city, String state, String zip ) throws RARException;

	/**
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param path
	 * @param capacity
	 * @return
	 * @throws RARException
	 */
	public void createLocation ( String name, String address, String city,
			String state, String zip, String path, int capacity ) throws RARException;

	/**
	 * 
	 * @param typeId
	 * @param locationId
	 * @param make
	 * @param model
	 * @param year
	 * @param mileadge
	 * @param tag
	 * @param lastServiced
	 * @param status
	 * @param cond
	 * @return
	 * @throws RARException
	 */
	public void createVehicle( int typeId, int locationId, String make, String model, int year, int mileage, 
			String tag, Date lastServiced, VehicleStatus vehicleStatus, VehicleCondition vehicleCondition ) throws RARException;
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws RARException
	 */
	public void createType( String name, String path ) throws RARException;
	
	/**
	 * 
	 * @param vehicleType
	 * @param maxHrs
	 * @param price
	 * @return
	 * @throws RARException
	 */
	public void createHourlyPrice ( int vehicleTypeId, int maxHrs, int price ) throws RARException;
	
	/**
	 * 
	 * @param pickupTime
	 * @param rentalLength
	 * @param type
	 * @param rentalLocation
	 * @param customer
	 * @throws RARException
	 */
	public void createReservation( Date pickupTime, int rentalLength, int vehicleTypeId, 
			int locationId, int customerId ) throws RARException;
	
	/**
	 * 
	 * @param pickupTime
	 * @param reservation
	 * @param vehicle
	 * @throws RARException
	 */
	public void createRental( Date pickupTime, int reservationId, int vehicleId ) throws RARException;
	
	/**
	 * 
	 * @param rental
	 * @param text
	 * @param commentDate
	 * @return
	 * @throws RARException
	 */
	public void createComment ( int rentalId, String text, Date commentDate ) throws RARException;
	
	
	/******************************************
	 * UPDATE / EDIT
	 */
	
	/**
	 * @param locationId
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param path
	 * @param capacity
	 * @throws RARException
	 */
	public void updateLocation ( int locationId, String name, String address, String city,
			String state, String zip, String path, int capacity ) throws RARException;
	
	/**
	 * 
	 * @param vehicleType
	 * @param rentalLocation
	 * @param make
	 * @param model
	 * @param year
	 * @param mileadge
	 * @param tag
	 * @param lastServiced
	 * @param status
	 * @param cond
	 * @throws RARException
	 */
	public void updateVehicle ( int vehicleId, int vehicleTypeId, int rentalLocationId, String make, String model, int year, int mileadge, 
			String tag, Date lastServiced, VehicleStatus vehicleStatus, VehicleCondition vehicleCondition ) throws RARException;
	
	/**
	 * 
	 * @param name
	 * @throws RARException
	 */
	public void updateVehicleType ( int typeId, String name ) throws RARException;
	
	/**
	 * 
	 * @param vehicleType
	 * @param maxHrs
	 * @param price
	 * @throws RARException
	 */
	public void updateHourlyPrice ( int hourlyPriceId, int vehicleTypeId, int maxHrs, int price ) throws RARException;
	
	/**
	 * 
	 * @param pickupTime
	 * @param rentalLength
	 * @param type
	 * @param rentalLocation
	 * @param customer
	 * @throws RARException
	 */
	public void updateReservation ( int reservationId, Date pickupTime, int rentalLength, int typeId, 
			int rentalLocationId, int customerId ) throws RARException;
	
	/**
	 * 
	 * @param pickupTime
	 * @param reservation
	 * @param vehicle
	 * @throws RARException
	 */
	public void updateRental ( int rentalId, Date pickupTime, int reservationId, int vehicleId ) throws RARException;
	
	/**
	 * 
	 * @param rental
	 * @param text
	 * @param commentDate
	 * @throws RARException
	 */
	public void updateComment ( int commentId, int rentalId, String text, Date commentDate ) throws RARException;
	
	/**
	 * 
	 * @param username
	 * @return
	 * @throws RARException
	 */
	public long setAdmin ( String username ) throws RARException;
	
	/**
	 * 
	 * @param memberFee
	 * @param lateFee
	 * @throws RARException
	 */
	public void updateParams( int memberFee, int lateFee ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param email
	 * @param address
	 * @param membershipExpiration
	 * @param licenseState
	 * @param licenseNumber
	 * @param cardNumber
	 * @param cardExpiration
	 * @throws RARException
	 */
	public void updateAdmin(Session session, int id, String firstName, String lastName, String userName, String password, String email, String address, 
			Date membershipExpiration, String licenseState, String licenseNumber, String cardNumber, Date cardExpiration) throws RARException;
	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param email
	 * @param address
	 * @param membershipExpiration
	 * @param licenseState
	 * @param licenseNumber
	 * @param cardNumber
	 * @param cardExpiration
	 * @throws RARException
	 */
	public void updateCustomer(Session session, int id, String firstName, String lastName, String userName, String password, String email, String address, 
			Date membershipExpiration, String licenseState, String licenseNumber, String cardNumber, Date cardExpiration) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @param customerStatus
	 * @throws RARException
	 */
	public void updateCustomerStatus(int id, String customerStatus) throws RARException;
	
	
	/******************************************
	 * DELETE
	 */
	
	/**
	 * 
	 * @param id
	 * @throws RARException
	 */
	public void deleteLocation ( int id ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @throws RARException
	 */
	public void deleteVehicle ( int id ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @throws RARException
	 */
	public void deleteVehicleType ( int id ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @throws RARException
	 */
	public void deleteHourlyPrice ( int id ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @throws RARException
	 */
	public void deleteReservation ( int id ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @throws RARException
	 */
	public void deleteRental ( int id ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @throws RARException
	 */
	public void deleteComment ( int id ) throws RARException;


	
	
	
	/******************************************
	 * LOGIC
	 */
	
	/**
	 * 
	 * @param ssid
	 * @throws RARException
	 */
	public void logout( String ssid ) throws RARException;
	
	/**
	 * 
	 * @param session
	 * @param email
	 * @param password
	 * @return
	 * @throws RARException
	 */
	public String checkCustomerCredentials ( Session session, String email, String password ) throws RARException;

	/**
	 * 
	 * @param session
	 * @param email
	 * @param password
	 * @return
	 * @throws RARException
	 */
	public String checkAdminCredentials ( Session session, String email, String password ) throws RARException;

	/**
	 * 
	 * @param session
	 * @param email
	 * @param password
	 * @param fname
	 * @param lname
	 * @return
	 * @throws RARException
	 */
	public void resetUserPassword (String email, String password, String fname, String lname) throws RARException;
	
	/**
	 * 
	 * @param session
	 * @throws RARException
	 */
	public void renewMembership ( Session session ) throws RARException;
	
	/**
	 * 
	 * @param session
	 * @throws RARException
	 */
	public void cancelMembership ( Session session ) throws RARException;
	
	/**
	 * 
	 * @param id
	 * @throws RARException
	 */
	public void cancelReservation( int id ) throws RARException;
	
	/**
	 * 
	 * @param reservationId
	 * @throws RARException
	 */
	public void checkPickupTime ( int reservationId ) throws RARException;
	
	/**
	 * 
	 * @param reservationId
	 * @throws RARException
	 */
	public void checkReservationNoShow ( int reservationId ) throws RARException;
	
	/**
	 * 
	 * @return
	 * @throws RARException
	 */
	public List<Reservation> findNoShowReservation () throws RARException;
}