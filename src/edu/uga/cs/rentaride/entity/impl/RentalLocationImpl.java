package edu.uga.cs.rentaride.entity.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

//Getters and Setters for Rental Location 

public class RentalLocationImpl 
	extends Persistent
	implements RentalLocation 
{
	private String name;
	private String address;
	private String city;
	private String state;
	private String path;
	private String zip;
	private int capacity;
	private List<Reservation> reservations;
	private List<Vehicle> vehicles;
	
	public RentalLocationImpl(){
		super( -1 );
		this.name = null;
		this.address = null;
		this.city = null;
		this.state = null;
		this.zip = null;
		this.capacity = 0;
		this.reservations = null;
		this.vehicles = null;
		this.path = "city/Athens.png";
	}
	
	public RentalLocationImpl(String name, String address, String city, String state, String zip, String path, int capacity){
		super( -1 );
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.capacity = capacity;
		this.reservations = null;
		this.vehicles = null;
		this.path = path;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String getZip() {
		return zip;
	}

	@Override
	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public int getCapacity() {
		return capacity;
	}

	@Override
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	@Override
	public void setPath(String path) throws RARException {
		this.path = path;
	}

	@Override
	public String getPath() throws RARException {
		return this.path;
	}

	@Override
	public List<Reservation> getReservations() throws RARException {
		if(reservations == null){
			if(isPersistent() ){
				reservations = getPersistenceLayer().restoreReservationRentalLocation( this );
			}else{
                throw new RARException( "This Location object is not persistent" );
			}
		}
        return reservations;
	}

	@Override
	public List<Vehicle> getVehicles() throws RARException {
		if(vehicles == null){
			if(isPersistent() ){
				vehicles = getPersistenceLayer().restoreVehicleRentalLocation( this );
			}else{
                throw new RARException( "This Location object is not persistent" );
			}
		}
        return vehicles;
	}

	@Override
	public String toString() {
		return "RentalLocationImpl ["
				+ "name=" + name + ", address=" + address + ", capacity=" + capacity + ", path=" + path
				+ ", reservations=" + reservations + ", vehicles=" + vehicles + 
				"]";
	}
}