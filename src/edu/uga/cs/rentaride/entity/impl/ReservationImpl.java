package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import java.util.Date;
import edu.uga.cs.rentaride.RARException;

//Getters and Setters for Reservation 

public class ReservationImpl 
	extends Persistent
	implements Reservation 
{
	private Date pickupTime;
	private int rentalLength;
	private boolean cancelled;
	private VehicleType vehicleType;
	private RentalLocation rentalLocation;
	private Customer customer;
	private Rental rental;
	
	public ReservationImpl(){
		super( -1 );
		this.pickupTime = null;
		this.rentalLength = 0;
		this.vehicleType = null;
		this.rentalLocation = null;
		this.customer = null;
		this.rental = null;
		this.cancelled = false;
	}
	
	public ReservationImpl(Date pickupTime, int rentalLength, VehicleType vehicleType, RentalLocation rentalLocation,
			Customer customer) {
		super(-1);
		this.pickupTime = pickupTime;
		this.rentalLength = rentalLength;
		this.vehicleType = vehicleType;
		this.rentalLocation = rentalLocation;
		this.customer = customer;
		this.rental = null;
		this.cancelled = false;
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public RentalLocation getRentalLocation() {
		return rentalLocation;
	}

	public void setRentalLocation(RentalLocation rentalLocation) {
		this.rentalLocation = rentalLocation;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int getLength() {
		return this.rentalLength;
	}

	@Override
	public void setLength(int length) throws RARException {
		this.rentalLength = length;
	}

	@Override
	public Rental getRental() {
		return this.rental;
	}

	@Override
	public void setRental(Rental rental) {
		this.rental = rental;
	}
	
	public boolean getCancelled() {
		return this.cancelled;
	}
	
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	@Override
	public String toString() {
		return "ReservationImpl [pickupTime=" + this.pickupTime + ", rentalLength=" + this.rentalLength 
				+ ", vehicleTypeName=" + this.vehicleType.getName()
				+ ", rentalLocationName=" + this.rentalLocation.getName()
				+ ", customerName=" + this.customer.getFirstName()+" "+this.customer.getLastName()
				+ ", rental=" + this.getRental() +
				"]";
	}
}