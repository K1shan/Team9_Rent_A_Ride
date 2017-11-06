package edu.uga.cs.rentaride.entity;


import java.util.List;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.persistence.Persistable;


/** This class represents possible vehicle types of all vehicles in the Rent-A-Ride system.
 * The types should be similar to sedan, pickup, luxury sedan, etc.
 *
 */
public interface VehicleType
    extends Persistable
{
    /** Return the name of this vehicle type.
     * @return name of this vehicle type
     */
    public String getName();
    
    /** Set the name of this vehicle type.
     * @param name the new name of this vehicle type
     * @throws RARException in case name is non-unique or null
     */
    public void setName( String name ) throws RARException;
    
    /** Return a list of all hourly prices for this VehicleType.
     * @return a list of all hourly prices made by this VehicleType
     */
    public List<HourlyPrice> getHourlyPrices() throws RARException;
    
    // Not needed;  hourly prices for this vehicle type are added one-by-one by creating 
    // HourlyPrice objects.
    //public void setHourlyPrices( HourlyPrice hourlyPrice );
    
    /** Return a list of all vehicles of this VehicleType.
     * @return a list of all vehicles of this VehicleType
     */
    public List<Vehicle> getVehicles() throws RARException;
      
    // Not needed;  vehicles of this vehicle type are added one-by-one by creating 
    // Vehicle objects.
    //public void setVehicles( Vehicle vehicle );
    
    /** Return a list of all reservations for this VehicleType.
     * @return a list of all reservations for this VehicleType
     */
    public List<Reservation> getReservations() throws RARException;

    // Not needed;  reservations for this vehicle type are added one-by-one by creating 
    // Reservation objects.
    //public void setReservations( Reservation reservation );
}