package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
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
		this.customer = null;
	}
	
	public RentalImpl(Date pickupTime, Reservation reservation, Vehicle vehicle){
		super( -1 );
		this.pickupTime = pickupTime;
		this.returnTime = null;//new Date(pickupTime.getTime() + (reservation.getLength()*60*60*1000));
		this.charges = 0;
		this.reservation = reservation;
		this.vehicle = vehicle;
		this.comment = null;
		this.customer = null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean getLate() {
		
		if(returnTime == null){
			
			Timestamp timedate = new Timestamp(new Date().getTime());
	
			// when return check time diff w/ reservation len
			//
			if(timedate.getTime()-pickupTime.getTime() < reservation.getLength()*60*60*1000){
				return false;
			}else{
				return true;
			}
			
			// already been returned
			//
		}else if(returnTime.getTime() - pickupTime.getTime() > reservation.getLength()*60*60*1000){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Customer getCustomer() {
		if(customer == null)
			customer = reservation.getCustomer();
		return customer;
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

	public int getCharges() throws RARException {
		
		if( reservation == null)
			return charges;
		if(charges == 0){
			for(HourlyPrice hourlyPrice : reservation.getVehicleType().getHourlyPrices() ){
				if(hourlyPrice.getMaxHours() == reservation.getLength()){
					if(pickupTime != null && returnTime != null){
						long diff = returnTime.getTime()-pickupTime.getTime();
						long diffHours = diff / (60 * 60 * 1000);
						charges = (int) (hourlyPrice.getPrice()*diffHours);
						if(charges == 0)
							charges = hourlyPrice.getPrice();
						break;
					}
				}
			}
		}
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
				+ "pickupTime=" 	+ pickupTime 
				+ ", returnTime=" 	+ returnTime 
				+ ", charges=" 		+ charges 
				+ "]";
	}
}