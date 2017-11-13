package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.Comment;
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import java.sql.Timestamp;
import java.util.Date;

//Getters and Setters for Rental 

public class RentalImpl 
	extends Persistent
	implements Rental 
{
	private Date pickupTime;
	private Date returnTime;
	private int charges;
	private Reservation reservation;
	private Vehicle vehicle;
	private Comment comment;
	private Customer customer;
	
	public RentalImpl(){
		super( -1 );
		this.pickupTime = null;
		this.returnTime = null;
		this.charges = 0;
		this.reservation = null;
		this.vehicle = null;
		this.comment = null;
		customer = null;
	}
	
	public RentalImpl(Date pickupTime, Reservation reservation, Vehicle vehicle){
		super( -1 );
		this.pickupTime = pickupTime;
		this.returnTime = null;
		this.charges = 0;//vehicle.getVehicleType().getHourlyPrices().get(0).getPrice();
		this.reservation = reservation;
		this.vehicle = vehicle;
		this.comment = null;
		customer = null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean getLate() {
		
		if(this.returnTime == null){
			Date date = new Date();
			Timestamp timedate = new Timestamp(date.getTime());
			if(timedate.getHours()-this.pickupTime.getTime() < this.reservation.getLength()){
				return false;
			}else{
				return true;
			}
		}
		
		if(this.returnTime.getTime() - this.pickupTime.getTime() > this.reservation.getLength()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Customer getCustomer() {
		return reservation.getCustomer();
	}

	public Date getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public int getCharges() {
		return charges;
	}

	public void setCharges(int charges) {
		this.charges = charges;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RentalImpl ["
				+ "pickupTime=" + pickupTime + ", returnTime=" + returnTime + ", charges=" + charges
				+ ", reservations=" + this.reservation.getId()
				+ ", vehicleId=" + this.vehicle.getId() + 
				"]";
	}
}