package edu.uga.cs.rentaride.entity.impl;

import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

//Getters and Setters for Rental Locaiton 

public class RentalLocationImpl 
	extends Persistent
	implements RentalLocation 
{
	private String name;
	private String address;
	private int capacity;
	private List<Reservation> reservations;
	private List<Vehicle> vehicles;
	
	public RentalLocationImpl(){
		super( -1 );
		this.name = null;
		this.address = null;
		this.capacity = 0;
		this.reservations = null;
		this.vehicles = null;
	}
	
	public RentalLocationImpl(String name, String address, int capacity){
		super( -1 );
		this.name = name;
		this.address = address;
		this.capacity = capacity;
		this.reservations = null;
		this.vehicles = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
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
		return "RentalLocationImpl [name=" + name + ", address=" + address + ", capacity=" + capacity
				+ ", reservations=" + reservations + ", vehicles=" + vehicles + "]";
	}

	
}