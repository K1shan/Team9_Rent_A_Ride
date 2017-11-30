package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.uga.cs.rentaride.RARException;

import edu.uga.cs.rentaride.object.ObjectLayer;

import edu.uga.cs.rentaride.entity.Administrator;
import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.RentARideParams;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.PersistenceLayer;

public class PersistenceLayerImpl 
    implements PersistenceLayer
{
	 private AdministratorManager administratorManager = null;
	 private CommentManager commentManager = null;
	 private CustomerManager customerManager = null;
	 private HourlyPriceManager hourlyPriceManager = null;
	 private RentalManager rentalManager = null;
	 private RentalLocationManager rentalLocationManager = null;
	 private RentARideParamsManager rentARideParamsManager= null;
	 private ReservationManager reservationManager = null;
	 private VehicleManager vehicleManager = null;
	 private VehicleTypeManager vehicleTypeManager = null;
	
	 public PersistenceLayerImpl( Connection conn, ObjectLayer objectLayer ){
		 administratorManager = new AdministratorManager( conn, objectLayer );
		 commentManager = new CommentManager( conn, objectLayer );
		 customerManager = new CustomerManager( conn, objectLayer );
		 hourlyPriceManager = new HourlyPriceManager( conn, objectLayer ); 
		 rentalManager = new RentalManager( conn, objectLayer );
		 rentalLocationManager = new RentalLocationManager( conn, objectLayer );
		 rentARideParamsManager = new RentARideParamsManager( conn, objectLayer );
		 reservationManager = new ReservationManager( conn, objectLayer );
		 vehicleManager = new VehicleManager( conn, objectLayer );
		 vehicleTypeManager = new VehicleTypeManager( conn, objectLayer );
		 System.out.println( "PersistenceLayerImpl.PersistenceLayerImpl(conn,objectLayer): initialized" );
	 }
	 
	@Override
	public List<Administrator> restoreAdministrator(Administrator modelAdministrator) throws RARException {
		return administratorManager.restore(modelAdministrator);
	}

	@Override
	public void storeAdministrator(Administrator administrator) throws RARException {
		administratorManager.store(administrator);
	}

	@Override
	public List<Customer> restoreCustomer(Customer modelCustomer) throws RARException {
		return customerManager.restore(modelCustomer);
	}

	@Override
	public void storeCustomer(Customer customer) throws RARException {
		customerManager.store(customer);
	}

	@Override
	public List<RentalLocation> restoreRentalLocation(RentalLocation modelRentalLocation) throws RARException {
		return rentalLocationManager.restore(modelRentalLocation);
	}

	@Override
	public void storeRentalLocation(RentalLocation rentalLocation) throws RARException {
		rentalLocationManager.store(rentalLocation);
	}

	@Override
	public void deleteRentalLocation(RentalLocation rentalLocation) throws RARException {
		rentalLocationManager.delete(rentalLocation);
	}

	@Override
	public List<Reservation> restoreReservation(Reservation modelReservation) throws RARException {
		return reservationManager.restore(modelReservation);
	}

	@Override
	public void storeReservation(Reservation reservation) throws RARException {
		reservationManager.store(reservation);
	}

	@Override
	public void deleteReservation(Reservation reservation) throws RARException {
		reservationManager.delete(reservation);
	}

	@Override
	public List<Rental> restoreRental(Rental modelRental) throws RARException {
		return rentalManager.restore(modelRental);
	}

	@Override
	public void storeRental(Rental rental) throws RARException {
		rentalManager.store(rental);
	}

	@Override
	public void deleteRental(Rental rental) throws RARException {
		rentalManager.delete(rental);
	}

	@Override
	public List<VehicleType> restoreVehicleType(VehicleType modelVehicleType) throws RARException {
		return vehicleTypeManager.restore(modelVehicleType);
	}

	@Override
	public void storeVehicleType(VehicleType vehicleType) throws RARException {
		vehicleTypeManager.store(vehicleType);
	}

	@Override
	public void deleteVehicleType(VehicleType vehicleType) throws RARException {
		vehicleTypeManager.delete(vehicleType);
	}

	@Override
	public List<Vehicle> restoreVehicle(Vehicle modelVehicle) throws RARException {
		return vehicleManager.restore(modelVehicle);
	}

	@Override
	public void storeVehicle(Vehicle vehicle) throws RARException {
		vehicleManager.store(vehicle);
	}

	@Override
	public void deleteVehicle(Vehicle vehicle) throws RARException {
		vehicleManager.delete(vehicle);
	}

	@Override
	public List<Comment> restoreComment(Comment modelComment) throws RARException {
		return commentManager.restore(modelComment);
	}

	@Override
	public void storeComment(Comment comment) throws RARException {
		commentManager.store(comment);
	}

	@Override
	public void deleteComment(Comment comment) throws RARException {
		commentManager.delete(comment);
	}

	@Override
	public List<HourlyPrice> restoreHourlyPrice(HourlyPrice modelHourlyPrice) throws RARException {
		return hourlyPriceManager.restore(modelHourlyPrice);
	}
	
	@Override
	public void storeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		hourlyPriceManager.store(hourlyPrice);
	}

	@Override
	public void deleteHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		hourlyPriceManager.delete(hourlyPrice);
	}

	@Override
	public RentARideParams restoreRentARideConfig() throws RARException {
		return rentARideParamsManager.restore();
	}

	@Override
	public void storeRentARideConfig(RentARideParams rentARideConfig) throws RARException {
		rentARideParamsManager.store(rentARideConfig);
	}

	@Override
	public void storeCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		reservationManager.storeCustomerReservation(customer, reservation);
	}

	@Override
	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		return reservationManager.restoreCustomerReservation(reservation);
	}

	@Override
	public List<Reservation> restoreCustomerReservation(Customer customer) throws RARException {
		return reservationManager.restore(customer);
	}

	@Override
	public void deleteCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		reservationManager.delete(customer, reservation);
	}

	@Override
	public void storeReservationRentalLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		reservationManager.storeLocation(reservation, rentalLocation);
	}

	@Override
	public RentalLocation restoreReservationRentalLocation(Reservation reservation) throws RARException {
		return reservationManager.restoreLocation(reservation);
	}

	@Override
	public List<Reservation> restoreReservationRentalLocation(RentalLocation rentalLocation) throws RARException {
		return reservationManager.restoreLocation(rentalLocation);
	}

	@Override
	public void deleteReservationRentalLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		reservationManager.deleteLocation(reservation, rentalLocation);
	}

	@Override
	public void storeReservationVehicleType(Reservation reservation, VehicleType vehicleType) throws RARException {
		reservationManager.storeType(reservation, vehicleType);
	}

	@Override
	public VehicleType restoreReservationVehicleType(Reservation reservation) throws RARException {
		return reservationManager.restoreType(reservation);
	}

	@Override
	public List<Reservation> restoreReservationVehicleType(VehicleType vehicleType) throws RARException {
		return reservationManager.restoreType(vehicleType);
	}

	@Override
	public void deleteReservationVehicleType(Reservation reservation, VehicleType vehicleType) throws RARException {
		reservationManager.deleteType(reservation, vehicleType);
	}

	@Override
	public void storeVehicleRentalLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		vehicleManager.storeLocation(vehicle, rentalLocation);
	}

	@Override
	public RentalLocation restoreVehicleRentalLocation(Vehicle vehicle) throws RARException {
		return vehicleManager.restoreLocation(vehicle);
	}

	@Override
	public List<Vehicle> restoreVehicleRentalLocation(RentalLocation rentalLocation) throws RARException {
		return vehicleManager.restoreLocation(rentalLocation);
	}

	@Override
	public void deleteVehicleRentalLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		vehicleManager.deleteLocation(vehicle, rentalLocation);
	}

	@Override
	public void storeVehicleVehicleType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		vehicleManager.storeType(vehicle, vehicleType);
	}

	@Override
	public VehicleType restoreVehicleVehicleType(Vehicle vehicle) throws RARException {
		return vehicleManager.restoreType(vehicle);
	}

	@Override
	public List<Vehicle> restoreVehicleVehicleType(VehicleType vehicleType) throws RARException {
		return vehicleManager.restoreType(vehicleType);
	}

	@Override
	public void deleteVehicleVehicleType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		vehicleManager.deleteType(vehicle, vehicleType);
	}

	@Override
	public void storeVehicleTypeHourlyPrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		vehicleTypeManager.storePrice(vehicleType, hourlyPrice);
	}

	@Override
	public VehicleType restoreVehicleTypeHourlyPrice(HourlyPrice hourlyPrice) throws RARException {
		return vehicleTypeManager.restorePrice(hourlyPrice);
	}

	@Override
	public List<HourlyPrice> restoreVehicleTypeHourlyPrice(VehicleType vehicleType) throws RARException {
		return vehicleTypeManager.restorePrice(vehicleType);
	}

	@Override
	public void deleteVehicleTypeHourlyPrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		vehicleTypeManager.deletePrice(vehicleType, hourlyPrice);
	}

	@Override
	public void storeRentalComment(Rental rental, Comment comment) throws RARException {
		rentalManager.storeComment(rental, comment);
	}

	@Override
	public Rental restoreRentalComment(Comment comment) throws RARException {
		return rentalManager.restoreComment(comment);
	}

	@Override
	public Comment restoreRentalComment(Rental rental) throws RARException {
		return rentalManager.restoreRentalComment(rental);
	}

	@Override
	public void deleteRentalComment(Rental rental, Comment comment) throws RARException {
		rentalManager.deleteComment(rental, comment);
	}

	@Override
	public void storeRentalReservation(Rental rental, Reservation reservation) throws RARException {
		rentalManager.storeReservation(rental, reservation);
	}

	@Override
	public Rental restoreRentalReservation(Reservation reservation) throws RARException {
		return rentalManager.restoreReservation(reservation);
	}

	@Override
	public Reservation restoreRentalReservation(Rental rental) throws RARException {
		return rentalManager.restoreReservation(rental);
	}

	@Override
	public void deleteRentalReservation(Rental rental, Reservation reservation) throws RARException {
		rentalManager.deleteReservation(rental, reservation);
	}

	@Override
	public void storeVehicleRental(Vehicle vehicle, Rental rental) throws RARException {
		vehicleManager.storeRental(vehicle, rental);
	}

	@Override
	public List<Rental> restoreVehicleRental(Vehicle vehicle) throws RARException {
		return vehicleManager.restoreRental(vehicle);
	}

	@Override
	public Vehicle restoreVehicleRental(Rental rental) throws RARException {
		return vehicleManager.restoreRental(rental);
	}

	@Override
	public void deleteVehicleRental(Vehicle vehicle, Rental rental) throws RARException {
		vehicleManager.deleteRental(vehicle, rental);
	}

	@Override
	public void deleteAdministrator(Administrator administrator) throws RARException {
		administratorManager.delete(administrator);
	}

	@Override
	public void deleteCustomer(Customer customer) throws RARException {
		customerManager.delete(customer);
	}
	
	public void deleteEverything() throws RARException {
		administratorManager.deleteEverything();
	}

	@Override
	public List<VehicleType> restoreRentalLocationAvailVehicleType(RentalLocation rentalLocation) throws RARException {
		return rentalLocationManager.restoreAvailTypes(rentalLocation);
	}

	@Override
	public List<VehicleType> restoreRentalLocationTotalVehicleType(RentalLocation rentalLocation) throws RARException {
		return rentalLocationManager.restoreTotalTypes(rentalLocation);
	}

	@Override
	public void storeTypePath(VehicleType vehicleType, String path) throws RARException {
		vehicleTypeManager.storeTypePath(vehicleType, path);
	}

	@Override
	public List<String> restoreTypePath(VehicleType vehicleType) throws RARException {
		return vehicleTypeManager.restoreTypePath(vehicleType);
	}
	
	@Override
	public int getReservationId(){
		return reservationManager.getReservationId();
	}

	@Override
	public int restoreCharges(int reservationId) throws RARException {
		return reservationManager.restoreCharges(reservationId);
	}

	@Override
	public void storeCharges(Reservation reservation, Rental rental, boolean insert) throws RARException {
		reservationManager.storeCharges(reservation, rental, insert);
	}

	@Override
	public List<Reservation> restoreNoShowReservations() throws RARException {
		return reservationManager.restoreNoShowReservations();
	}
}
