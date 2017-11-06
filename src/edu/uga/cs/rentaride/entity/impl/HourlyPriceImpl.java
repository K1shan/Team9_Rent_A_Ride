package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistent;

//Getters and Setters for Hourly Price 

public class HourlyPriceImpl 
	extends Persistent
	implements HourlyPrice 
{
	private int maxHours;
	private int price;
	private VehicleType vehicleType;
	
	public HourlyPriceImpl(){
		super( -1 );
		this.maxHours = 0;
		this.price = 0;
		this.vehicleType = null;
	}
	
	public HourlyPriceImpl(int maxHours, int price, VehicleType vehicleType){
		super( -1 );
		this.maxHours = maxHours;
		this.price = price;
		this.vehicleType = vehicleType;
	}

	public int getMaxHours() {
		return maxHours;
	}

	public void setMaxHours(int maxHours) {
		this.maxHours = maxHours;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	@Override
	public String toString() {
		return "HourlyPriceImpl "
				+ "[type="+this.getVehicleType().getName()
				+ ", maxHours=" + maxHours 
				+ ", price=" + price +
				"]";
	}
}