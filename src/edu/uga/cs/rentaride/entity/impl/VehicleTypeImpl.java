package edu.uga.cs.rentaride.entity.impl;

import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.persistence.impl.Persistent;
import java.util.List;
import edu.uga.cs.rentaride.RARException;

//Getters and Setters for Vehicle Type 

public class VehicleTypeImpl 
	extends Persistent
	implements VehicleType 
{
	private String name;
	private String path;
	private List<HourlyPrice> hourlyPrices;
	private List<Vehicle> vehicles;
	private List<Reservation> reservations;
	
	public VehicleTypeImpl(){
		super( -1 );
		this.name = null;
		this.path = null;
		this.hourlyPrices = null;
		this.vehicles = null;
		this.reservations = null;
	}
	
	public VehicleTypeImpl(String name){
		super( -1 );
		this.name = name;
		this.path = null;
		this.hourlyPrices = null;
		this.vehicles = null;
		this.reservations = null;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) throws RARException {
		this.name = name;
	}
	
	@Override
	public List<HourlyPrice> getHourlyPrices() throws RARException {
		if(hourlyPrices == null){
			if(isPersistent() ){
				hourlyPrices = getPersistenceLayer().restoreVehicleTypeHourlyPrice( this );
			}else{
                throw new RARException( "This VehicleType object is not persistent" );
			}
		}
        return hourlyPrices;
	}
	
	@Override
	public List<Vehicle> getVehicles() throws RARException {
		if(vehicles == null){
			if(isPersistent() ){
				vehicles = getPersistenceLayer().restoreVehicleVehicleType( this );
			}else{
                throw new RARException( "This VehicleType object is not persistent" );
			}
		}
        return vehicles;
	}
	
	@Override
	public List<Reservation> getReservations() throws RARException {
		if(reservations == null){
			if(isPersistent() ){
				reservations = getPersistenceLayer().restoreReservationVehicleType( this );
			}else{
                throw new RARException( "This VehicleType object is not persistent" );
			}
		}
        return reservations;
	}

	@Override
	public String toString() {
		return "VehicleTypeImpl "
				+ "[type_id=" +this.getId()
				+ ", name=" + name +
				"]";
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath( String path ) {
		this.path = path;
	}
}
