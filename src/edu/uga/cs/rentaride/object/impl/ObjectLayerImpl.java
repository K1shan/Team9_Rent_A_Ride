package edu.uga.cs.rentaride.object.impl;

import java.util.Date;
import java.util.List;


import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;

import edu.uga.cs.rentaride.entity.impl.AdministratorImpl;
import edu.uga.cs.rentaride.entity.impl.CommentImpl;
import edu.uga.cs.rentaride.entity.impl.CustomerImpl;
import edu.uga.cs.rentaride.entity.impl.HourlyPriceImpl;
import edu.uga.cs.rentaride.entity.impl.RentalImpl;
import edu.uga.cs.rentaride.entity.impl.RentalLocationImpl;
import edu.uga.cs.rentaride.entity.impl.RentARideParamsImpl;
import edu.uga.cs.rentaride.entity.impl.ReservationImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleImpl;
import edu.uga.cs.rentaride.entity.impl.VehicleTypeImpl;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.object.ObjectLayer;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

public class ObjectLayerImpl
	implements ObjectLayer 
{
	PersistenceLayer persistence = null;

	public ObjectLayerImpl()
	{
			this.persistence = null;	
	
			System.out.println( "ObjectLayerImpl.ObjectLayerImpl(): initialized" );
	}
	
	public ObjectLayerImpl( PersistenceLayer persistence )
	{
		this.persistence = persistence;
		System.out.println( "ObjectLayerImpl.ObjectLayerImpl(persistence): initialized" );
	
	}
	
	//Object Layer for Create Admin 
	
	@Override
	public Administrator createAdministrator(String firstName, String lastName, String userName, String password,
			String email, String address, Date createDate) throws RARException {
		AdministratorImpl administrator = new AdministratorImpl(firstName, lastName, userName, password, email, address, createDate);
        Persistent.setPersistenceLayer( persistence);
		return administrator;
	}

	//Object Layer for Create Admin 
	
	@Override
	public Administrator createAdministrator() {
		AdministratorImpl administrator = new AdministratorImpl( null, null, null, null, null, null, null );
        administrator.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return administrator;
	}

	//Object Layer for Find Admin 
	
	@Override
	public List<Administrator> findAdministrator(Administrator modelAdministrator) throws RARException {
		return persistence.restoreAdministrator(modelAdministrator);
	}

	//Object Layer for Store Admin 
	
	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		persistence.storeAdministrator(administrator);
	}

	//Object Layer for Delete Admin 
	
	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		persistence.deleteAdministrator(administrator);
	}

	//Object Layer for Create Customer 
	
	@Override
	public Customer createCustomer(String firstName, String lastName, String userName, String password, String email,
			String address, Date createDate, Date membershipExpiration, String licenseState, String licenseNumber,
			String cardNumber, Date cardExpiration) throws RARException {
		
		
		CustomerImpl customer = new CustomerImpl(firstName, lastName, userName, password, email,
				address,  createDate,  membershipExpiration,   licenseState,   licenseNumber,
				  cardNumber,   cardExpiration);
		
        Persistent.setPersistenceLayer( persistence);
        return customer;
	}

	//Object Layer for Create Customer 
	
	@Override
	public Customer createCustomer() {
		CustomerImpl customer = new CustomerImpl( null, null, null, null, null, null, null, null, null, null, null, null );
        customer.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return customer;
	}

	//Object Layer for Find Customer 
	
	@Override
	public List<Customer> findCustomer(Customer modelCustomer) throws RARException {
		return persistence.restoreCustomer(modelCustomer);
	}

	//Object Layer for Store Customer 
	
	@Override
	public void storeCustomer(Customer customer) throws RARException {
		persistence.storeCustomer(customer);
	}
	
	//Object Layer for Delete Customer 
	
	public void deleteCustomer(Customer customer) throws RARException {	
		persistence.deleteCustomer(customer);
	}

	//Object Layer for Create Rental Location 
	
	@Override
	public RentalLocation createRentalLocation(String name, String address, String city, String state, String zip, String path, int capacity) throws RARException {
		RentalLocationImpl rentalLocation = new RentalLocationImpl(name, address, city, state, zip, path, capacity);
		Persistent.setPersistenceLayer( persistence );
		return rentalLocation;
	}

	//Object Layer for Create Rental Location 
	
	@Override
	public RentalLocation createRentalLocation() {
		RentalLocationImpl rentalLocation = new RentalLocationImpl();
        rentalLocation.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return rentalLocation;
	}

	//Object Layer for Find Rental Location 
	
	@Override
	public List<RentalLocation> findRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		return persistence.restoreRentalLocation(modelRentalLocation);
	}

	//Object Layer for Store Rental Location 
	
	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.storeRentalLocation(rentalLocation);
	}

	//Object Layer for Delete Rental Location 
	
	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		persistence.deleteRentalLocation(rentalLocation);
	}

	//Object Layer for Create Reservation 
	
	@Override
	public Reservation createReservation(Date pickupTime, int rentalLength, VehicleType vehicleType, 
            RentalLocation rentalLocation, Customer customer){
		ReservationImpl reservation = new ReservationImpl(pickupTime, rentalLength, vehicleType, rentalLocation, customer);
		Persistent.setPersistenceLayer( persistence );
		return reservation;
	}

	//Object Layer for Create Rreservation
	
	@Override
	public Reservation createReservation() {
		ReservationImpl reservation = new ReservationImpl( null, 0, null, null, null );
        reservation.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return reservation;
	}

	//Object Layer for Find Reservation
	
	@Override
	public List<Reservation> findReservation(Reservation modelReservation) throws RARException {
		return persistence.restoreReservation(modelReservation);
	}

	//Object Layer for Store Reservation
	
	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		persistence.storeReservation(reservation);
	}

	//Object Layer for Delete Reservation
	
	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		persistence.deleteReservation(reservation);
	}

	//Object Layer for Create Rental
	
	@Override
	public Rental createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		RentalImpl rental = new RentalImpl(pickupTime, reservation, vehicle);
		Persistent.setPersistenceLayer( persistence );
		return rental;
	}

	//Object Layer for Create Rental
	
	@Override
	public Rental createRental() {
		RentalImpl rental = new RentalImpl( null, null, null );
        rental.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return rental;
	}

	//Object Layer for Find Rental
	
	@Override
	public List<Rental> findRental(Rental modelRental) throws RARException {
		return persistence.restoreRental(modelRental);
	}

	//Object Layer for Store Rental
	
	@Override
	public void storeRental(Rental rental) throws RARException {
		persistence.storeRental(rental);
	}

	//Object Layer for Delete Rental
	
	@Override
	public void deleteRental(Rental rental) throws RARException {
		persistence.deleteRental(rental);
	}

	//Object Layer for Create Vehicle Type
	
	@Override
	public VehicleType createVehicleType(String name) throws RARException {
		VehicleTypeImpl vehicleType = new VehicleTypeImpl(name);
		Persistent.setPersistenceLayer( persistence );
		return vehicleType;
	}

	//Object Layer for Create Vehicle Type
	
	@Override
	public VehicleType createVehicleType() {
		VehicleTypeImpl vehicleType = new VehicleTypeImpl( null );
        vehicleType.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return vehicleType;
	}

	//Object Layer for Find Vehicle Type
	
	@Override
	public List<VehicleType> findVehicleType(VehicleType modelVehicleType) throws RARException {
		return persistence.restoreVehicleType(modelVehicleType);
	}

	//Object Layer for Store Vehicle Type
	
	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		persistence.storeVehicleType(vehicleType);
	}

	//Object Layer for Delete Vehicle Type
	
	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		persistence.deleteVehicleType(vehicleType);
	}

	//Object Layer for Create Vehicle
	
	@Override
	public Vehicle createVehicle(String make, String model, int year, String registrationTag, int mileage,
			Date lastServiced, VehicleType vehicleType, RentalLocation rentalLocation,
			VehicleCondition vehicleCondition, VehicleStatus vehicleStatus) throws RARException {
		VehicleImpl vehicle = new VehicleImpl(make, model, year, registrationTag, mileage, lastServiced, vehicleType, rentalLocation, vehicleCondition, vehicleStatus);
		Persistent.setPersistenceLayer( persistence );
		return vehicle;
	}

	//Object Layer for Create Vehicle
	
	@Override
	public Vehicle createVehicle() {
		VehicleImpl vehicle = new VehicleImpl( null, null, 0, null, 0, null, null, null, null, null );
        vehicle.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return vehicle;
	}

	//Object Layer for Find Vehicle
	
	@Override
	public List<Vehicle> findVehicle(Vehicle modelVehicle) throws RARException {
		return persistence.restoreVehicle(modelVehicle);
	}

	//Object Layer for Store Vehicle
	
	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		persistence.storeVehicle(vehicle);
	}

	//Object Layer for Delete Vehicle
	
	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		persistence.deleteVehicle(vehicle);
	}

	//Object Layer for Create Comment
	
	@Override
	public Comment createComment(String text, Date date, Rental rental) throws RARException {
		CommentImpl comment = new CommentImpl(text, date, rental);
		Persistent.setPersistenceLayer( persistence );
		return comment;
	}

	//Object Layer for Create Comment
	
	@Override
	public Comment createComment() {
		CommentImpl comment = new CommentImpl( null, null, null );
        comment.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return comment;
	}
	
	//Object Layer for Find Comment
	
	@Override
	public List<Comment> findComment(Comment modelComment) throws RARException {
		return persistence.restoreComment(modelComment);
	}
	
	//Object Layer for Store Comment
	
	@Override
	public void storeComment(Comment comment) throws RARException {
		persistence.storeComment(comment);
	}
	
	//Object Layer for Delete Comment

	@Override
	public void deleteComment(Comment comment) throws RARException {
		persistence.deleteComment(comment);
	}
	
	//Object Layer for Create Hourly Price 

	@Override
	public HourlyPrice createHourlyPrice(int maxHrs, int price, VehicleType vehicleType) throws RARException {
		HourlyPriceImpl hourlyPrice = new HourlyPriceImpl(maxHrs, price, vehicleType);
		Persistent.setPersistenceLayer( persistence );
		return hourlyPrice;
	}

	//Object Layer for Create Hourly Price 
	
	@Override
	public HourlyPrice createHourlyPrice() {
		HourlyPriceImpl hourlyPrice = new HourlyPriceImpl( 0, 0, null );
        hourlyPrice.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return hourlyPrice;
	}

	//Object Layer for Find Hourly Price 
	
	@Override
	public List<HourlyPrice> findHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		return persistence.restoreHourlyPrice(modelHourlyPrice);
	}

	//Object Layer for Store Hourly Price 
	
	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.storeHourlyPrice(hourlyPrice);
	}

	//Object Layer for Delete Hourly Price 
	
	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		persistence.deleteHourlyPrice(hourlyPrice);
	}

	//Object Layer for Create Rent A Rider Params 
	
	@Override
	public RentARideParams createRentARideParams() {
		RentARideParamsImpl params = new RentARideParamsImpl();
        params.setId( -1 );
        Persistent.setPersistenceLayer( persistence);
        return params;
	}
	
	//Object Layer for Store Rent A Rider Params 
	
	@Override
	public void storeRentARideParams(RentARideParams rentARideParams) throws RARException {
		persistence.restoreRentARideConfig();
	}
	
	//Object Layer for Set Rent A Rider Params 
	
	@Override
	public void setPersistence(PersistenceLayer persistence) {
		this.persistence = persistence;
	}

	//Object Layer for Find Rent A Rider Params 
	
	@Override
	public RentARideParams findRentARideParams() throws RARException{
		return this.persistence.restoreRentARideConfig();
	}
	
	@Override
	public void deleteEverything() throws RARException{
		persistence.deleteEverything();
	}
}