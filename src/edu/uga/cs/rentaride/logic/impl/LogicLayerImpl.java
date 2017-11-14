package edu.uga.cs.rentaride.logic.impl;

import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
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
	public long createAccount(String fName, String lName, String email, String password, String driverNo, String cardNo, String expDate, String address, String city, String state, String zip)
			throws RARException {
		CreateAccountCtrl ctrlCreateAccount = new CreateAccountCtrl ( objectLayer );
		return ctrlCreateAccount.createAccount(fName, lName, email, password, driverNo, cardNo, expDate, address, city, state, zip);
	}
	
	public void logout( String ssid ) throws RARException
    {
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
	public List<RentalLocation> findLocations(RentalLocation rentalLocation) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		return ctrlLocation.findLocations(rentalLocation);
	}

	@Override
	public List<Vehicle> findLocationVehicles(RentalLocation rentalLocation) throws RARException {
		// TODO
		VehicleCtrl ctrlVehicle = new VehicleCtrl ( objectLayer );
		return null;
	}

	@Override
	public void createLocation(String name, String address, String city, String state, String zip, String path,
			int capacity) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		ctrlLocation.createLocation(name, address, city, state, zip, path, capacity);
	}

	@Override
	public void createVehicle(VehicleType vehicleType, RentalLocation rentalLocation, String make, String model,
			int year, int mileadge, String tag, Date lastServiced, int status, int cond) throws RARException {
		// TODO
		VehicleCtrl ctrlVehicle = new VehicleCtrl ( objectLayer );
		
	}

	@Override
	public void createType(String name) throws RARException {
		// TODO Auto-generated method stub
		VehicleTypeCtrl ctrlType = new VehicleTypeCtrl ( objectLayer );
		
	}

	@Override
	public void createHourlyPrice(VehicleType vehicleType, int maxHrs, int price) throws RARException {
		// TODO Auto-generated method stub
		HourlyPriceCtrl ctrlPrice = new HourlyPriceCtrl ( objectLayer );
		
	}

	@Override
	public void createReservation(Date pickupTime, int rentalLength, VehicleType type, RentalLocation rentalLocation,
			Customer customer) throws RARException {
		// TODO Auto-generated method stub
		ReservationCtrl ctrlReservation = new ReservationCtrl ( objectLayer );

	}

	@Override
	public void createRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		RentalCtrl ctrlRental = new RentalCtrl ( objectLayer );

	}
	
	@Override
	public void createComment(Rental rental, String text, Date commentDate) throws RARException {
		// TODO Auto-generated method stub
		CommentCtrl ctrlComment = new CommentCtrl ( objectLayer );
		
	}

	@Override
	public void updateLocation(String name, String address, String city, String state, String zip, String path,
			int capacity) throws RARException {
		LocationCtrl ctrlLocation = new LocationCtrl ( objectLayer );
		ctrlLocation.updateLocation(name, address, city, state, zip, path, capacity);
	}

	@Override
	public List<Vehicle> findVehicles(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateVehicle(VehicleType vehicleType, RentalLocation rentalLocation, String make, String model,
			int year, int mileadge, String tag, Date lastServiced, int status, int cond) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateVehicleType(String name) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateHourlyPrice(VehicleType vehicleType, int maxHrs, int price) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReservation(Date pickupTime, int rentalLength, VehicleType type, RentalLocation rentalLocation,
			Customer customer) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRental(Date pickupTime, Reservation reservation, Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComment(Rental rental, String text, Date commentDate) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLocation(int id) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicle(int id) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteVehicleType(int id) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteHourlyPrice(int id) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReservation(int id) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRental(int id) throws RARException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(int id) throws RARException {
		// TODO Auto-generated method stub
		
	}
	
}