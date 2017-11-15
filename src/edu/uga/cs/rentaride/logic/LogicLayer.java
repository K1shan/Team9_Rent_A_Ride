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
	 * 
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
	 * @return
	 * @throws RARException
	 */
	public void createVehicle( VehicleType vehicleType, RentalLocation rentalLocation, String make, String model, int year, int mileadge, 
			String tag, Date lastServiced, int status, int cond ) throws RARException;
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws RARException
	 */
	public void createType( String name ) throws RARException;
	
	/**
	 * 
	 * @param vehicleType
	 * @param maxHrs
	 * @param price
	 * @return
	 * @throws RARException
	 */
	public void createHourlyPrice ( VehicleType vehicleType, int maxHrs, int price ) throws RARException;
	
	/**
	 * 
	 * @param pickupTime
	 * @param rentalLength
	 * @param type
	 * @param rentalLocation
	 * @param customer
	 * @throws RARException
	 */
	public void createReservation( Date pickupTime, int rentalLength, VehicleType type, 
			RentalLocation rentalLocation, Customer customer ) throws RARException;
	
	/**
	 * 
	 * @param pickupTime
	 * @param reservation
	 * @param vehicle
	 * @throws RARException
	 */
	public void createRental( Date pickupTime, Reservation reservation, Vehicle vehicle ) throws RARException;
	
	/**
	 * 
	 * @param rental
	 * @param text
	 * @param commentDate
	 * @return
	 * @throws RARException
	 */
	public void createComment ( Rental rental, String text, Date commentDate ) throws RARException;
	
	
	/******************************************
	 * UPDATE / EDIT
	 */
	
	/**
	 * 
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param zip
	 * @param path
	 * @param capacity
	 * @throws RARException
	 */
	public void updateLocation ( String name, String address, String city,
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
	public void updateVehicle ( VehicleType vehicleType, RentalLocation rentalLocation, String make, String model, int year, int mileadge, 
			String tag, Date lastServiced, int status, int cond ) throws RARException;
	
	/**
	 * 
	 * @param name
	 * @throws RARException
	 */
	public void updateVehicleType ( String name ) throws RARException;
	
	/**
	 * 
	 * @param vehicleType
	 * @param maxHrs
	 * @param price
	 * @throws RARException
	 */
	public void updateHourlyPrice ( VehicleType vehicleType, int maxHrs, int price ) throws RARException;
	
	/**
	 * 
	 * @param pickupTime
	 * @param rentalLength
	 * @param type
	 * @param rentalLocation
	 * @param customer
	 * @throws RARException
	 */
	public void updateReservation ( Date pickupTime, int rentalLength, VehicleType type, 
			RentalLocation rentalLocation, Customer customer ) throws RARException;
	
	/**
	 * 
	 * @param pickupTime
	 * @param reservation
	 * @param vehicle
	 * @throws RARException
	 */
	public void updateRental ( Date pickupTime, Reservation reservation, Vehicle vehicle ) throws RARException;
	
	/**
	 * 
	 * @param rental
	 * @param text
	 * @param commentDate
	 * @throws RARException
	 */
	public void updateComment ( Rental rental, String text, Date commentDate ) throws RARException;
	
	/**
	 * 
	 * @param username
	 * @return
	 * @throws RARException
	 */
	public long setAdmin ( String username ) throws RARException;
	
	
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
}