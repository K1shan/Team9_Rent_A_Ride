package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.Rental;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.UserStatus;
import edu.uga.cs.rentaride.entity.Vehicle;
import edu.uga.cs.rentaride.entity.VehicleCondition;
import edu.uga.cs.rentaride.entity.VehicleStatus;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class VehicleManager {

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public VehicleManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	
	/*
	 * 
	 * Vehicle
	 * 
	 */
	public void store( Vehicle vehicle ) throws RARException{
		String insertVehicleQuery = 
				"INSERT INTO VEHICLE "
				+ "(type_id, location_id, make, model, year, mileage, tag, service_date, status, cond) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    	
    	String updateVehicleQuery = 
				"UPDATE VEHICLE SET "
				+ "type_id=?, "
				+ "location_id=?, "
				+ "make=?, "
				+ "model=?, "
				+ "year=?, "
				+ "mileage=?, "
				+ "tag=?, "
				+ "service_date=?, "
				+ "status=?, "
				+ "cond=? "
				+ "WHERE vehicle_id=?"; 
    	
    	PreparedStatement pstmt;
    	long vehicleId;
		int inscnt;
		
		if(vehicle.getRentalLocation() == null){
			
			throw new RARException ("VehicleManager.save: Attempting ot save a Vehicle with no RentalLocation defined");
		}
		if(vehicle.getVehicleType() == null){
			
			throw new RARException ("VehicleManager.save: Attempting ot save a Vehicle with no VehicleType defined");
		}
		if(!vehicle.getRentalLocation().isPersistent()){	
			
			throw new RARException ("VehicleManager.save: Attempting ot save a vehcile Where RentalLocation is not persistent");
		}
		if(!vehicle.getVehicleType().isPersistent()){	
		
			throw new RARException ("VehicleManager.save: Attempting ot save a Vehicle Where VehicleType is not persistent");
		}
		
		try {
			if( !vehicle.isPersistent() ){
	            pstmt = (PreparedStatement) con.prepareStatement( insertVehicleQuery );
			}else{
	            pstmt = (PreparedStatement) con.prepareStatement( updateVehicleQuery );
			}
			
			if( vehicle.getVehicleType().getId() != 0 )
	            pstmt.setLong( 1, vehicle.getVehicleType().getId() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: vehicletype undefined" );
	        }
			
			if( vehicle.getRentalLocation().getId() != 0 )
	            pstmt.setLong( 2, vehicle.getRentalLocation().getId() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: location undefined" );
	        }
			
			if( vehicle.getMake() != null )
	            pstmt.setString( 3, vehicle.getMake() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: make undefined" );
	        }
			
			if( vehicle.getModel() != null )
	            pstmt.setString( 4, vehicle.getModel() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: model undefined" );
	        }
			
			if( vehicle.getYear() != 0 )
	            pstmt.setLong( 5, vehicle.getYear() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: year undefined" );
	        }
			
			if( vehicle.getMileage() != 0 )
	            pstmt.setLong( 6, vehicle.getMileage() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: mileage undefined" );
	        }
			
			if( vehicle.getRegistrationTag() != null )
	            pstmt.setString( 7, vehicle.getRegistrationTag() );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: tag undefined" );
	        }
			
			java.util.Date myDate = vehicle.getLastServiced();
        	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
    		pstmt.setDate(8, sqlDate);
			
    		if( vehicle.getStatus() != null )
	            pstmt.setLong( 9, 0 );
	        else{
	            throw new RARException( "VehicleManager.save: can't save a vehicle: status undefined" );
	        }
    		
    		if( vehicle.getCondition() != null )
    			pstmt.setLong(10, 0);
    		else{
    			throw new RARException( "VehicleManager.save: can't save a vehicle: condition undefined" );
    		}
    		
    		if( vehicle.isPersistent() )
    			pstmt.setLong(11, vehicle.getId());
			
			System.out.println("query: "+pstmt.asSql());
	        inscnt = pstmt.executeUpdate();
	        
	        if( !vehicle.isPersistent() ) {

                if( inscnt == 1 ) {
                	
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) { 

                        ResultSet r = pstmt.getResultSet();

                        while( r.next() ) {

                            vehicleId = r.getLong( 1 );
                            if( vehicleId > 0 )
                            	vehicle.setId( vehicleId ); 
                        }
                    }
                }
            }else {
				if(inscnt < 1)
					throw new RARException("ReservationManager.save: failed to save a reservation");
			}
	        
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RARException( "Vehicle.save: failed to save a vehicle: " + e );
		}
	}
	
	public List<Vehicle> restore( Vehicle modelVehicle ) throws RARException{
		String selectVehicleSql = "SELECT "
				+ "VEHICLE.*, "
				+ "VEHICLE_TYPE.*, "
				+ "LOCATION.* "
				+ "FROM VEHICLE "
				+ "INNER JOIN VEHICLE_TYPE on VEHICLE_TYPE.type_id=VEHICLE.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=VEHICLE.location_id";
		
		Statement stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		condition.setLength(0);
		query.append(selectVehicleSql);
		System.out.println("query: "+ query.toString());
		
		if(modelVehicle != null) {
			if (modelVehicle.getId() >= 0) { // id is unique, so it is sufficient to get a vehicle
				query.append(" WHERE vehicle_id=" + modelVehicle.getId());
			}else {
				if(modelVehicle.getVehicleType() != null) { // not sure if this is okay or I should get the type name itself
					condition.append( " type_id='" + modelVehicle.getVehicleType().getId() + "'");
				}
				
				if (modelVehicle.getRentalLocation() != null) {
					condition.append( " location_id='" + modelVehicle.getRentalLocation().getId() + "'");
				}
				
				if(modelVehicle.getMake()!= null) {
					condition.append( " make='" + modelVehicle.getMake() + "'");
				}
				
				if(modelVehicle.getModel()!= null) {
					if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
					condition.append( " model='" + modelVehicle.getModel() + "'");
				}
				
				if(modelVehicle.getYear() != 0) { // 0 and not null because year is int
					condition.append( " year='" + modelVehicle.getYear() + "'");
				}
				
				if(modelVehicle.getMileage() != 0) { // 0 and not null because mileage is int
					condition.append( " mileage='" + modelVehicle.getMileage() + "'");
				}
				
				if(modelVehicle.getRegistrationTag() != null) {
					condition.append( " tag='" + modelVehicle.getRegistrationTag() + "'");
				}
				
				if(modelVehicle.getLastServiced()!= null) {
					if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
					condition.append( " service_date='" + modelVehicle.getLastServiced() + "'");
				}
				
				// VehicleStatus not yet implemented, may have to change methods used below
				if(modelVehicle.getStatus() != null) {
					int status; 
					if(modelVehicle.getStatus().equals(UserStatus.ACTIVE)){
						status = 0;
					}else if(modelVehicle.getStatus().equals(UserStatus.CANCELLED)){
						status = 1;
					}else{
						status = 2;
					}
					condition.append( " status='" + status + "'");
				}
				
				// VehicleCondition not yet implemented, may have to change methods used below
				if(modelVehicle.getCondition() != null) {
					int cond = 0;
					if(modelVehicle.getCondition().equals(VehicleCondition.NEEDSMAINTENANCE)){
						cond = 1;
					}
					condition.append( " cond='" + cond + "'");
				}
				
				if( condition.length() > 0 ) {
                    query.append(  " where " );
                    query.append( condition );
                }
			}
		}
		
		try {
			stmt = con.createStatement();
			System.out.println("query: "+query.toString());
			if(stmt.execute(query.toString())) {
				ResultSet rs = stmt.getResultSet();
				
				// VEHICLE
                int 	vehicle_vehicle_id;
                int		vehicle_type_id;
                int 	vehicle_location_id;
				String 	vehicle_make;
				String 	vehicle_model;
				int 	vehicle_year;
				int 	vehicle_mileage;
				String 	vehicle_tag;
				Date 	vehicle_service_date;
				int 	vehicle_status = 0;
				int 	vehicle_cond = 0;
				
				// VEHICLE_TYPE
                int 	type_type_id;
                String 	type_name;
				
				// LOCATION
				int 	location_location_id;
				String 	location_name;
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
				
				VehicleType vehicleType = null;
				RentalLocation rentalLocation = null;
				Vehicle vehicle = null;
				VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
				VehicleCondition vehicleCondition = VehicleCondition.GOOD;
				
				while( rs.next() ){
					
					// VEHICLE
					vehicle_vehicle_id 	= rs.getInt(1);
					vehicle_type_id 	= rs.getInt(2);
					vehicle_location_id = rs.getInt(3);
					vehicle_make 		= rs.getString(4);
					vehicle_model 		= rs.getString(5);
					vehicle_year 		= rs.getInt(6);
					vehicle_mileage 	= rs.getInt(7);
					vehicle_tag 		= rs.getString(8);
					vehicle_service_date= rs.getDate(9);
					vehicle_status 		= rs.getInt(10);
					if(vehicle_status == 1)
						vehicleStatus 	= VehicleStatus.INRENTAL;
					vehicle_cond 		= rs.getInt(11);
					if(vehicle_cond == 1)
						vehicleCondition= VehicleCondition.NEEDSMAINTENANCE;
					
					// VEHICLE_TYPE
	                type_type_id 		= rs.getInt(12);
	                type_name 			= rs.getString(13);
					
	                // LOCATION
					location_location_id= rs.getInt(14);
					location_name 		= rs.getString(15);
					location_addr	 	= rs.getString(16);
					location_addr_city	= rs.getString(17);
					location_addr_state	= rs.getString(18);
					location_addr_zip	= rs.getString(19);
					location_image_path	= rs.getString(20);
					location_capacity 	= rs.getInt(21);
					
					// OBJECTS
					rentalLocation = objectLayer.createRentalLocation(location_name, location_addr, location_addr_city, location_addr_state, location_addr_zip, location_image_path, location_capacity);
					rentalLocation.setId(vehicle_location_id);
					
					vehicleType = objectLayer.createVehicleType(type_name);
					vehicleType.setId(vehicle_type_id);
					
					vehicle = objectLayer.createVehicle(vehicle_make, vehicle_model, vehicle_year, vehicle_tag, vehicle_mileage, vehicle_service_date, vehicleType, rentalLocation, vehicleCondition, vehicleStatus);
					vehicle.setId(vehicle_vehicle_id);
					vehicles.add(vehicle);
				}
			}
			return vehicles;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new RARException("VehicleManager.get: failed to get any vehicles: " + e);
		}
	}
	
    
    public void delete( Vehicle vehicle ) throws RARException{
    	String deleteVehicle = "DELETE FROM VEHICLE WHERE vehicle_id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !vehicle.isPersistent() ) // is the vehicle object persistent?  If not, nothing to actually delete
            return;
        
        try {
        	
            stmt = (PreparedStatement) con.prepareStatement(deleteVehicle);         
            stmt.setLong( 1, vehicle.getId() );
            System.out.println("query: "+stmt.toString());
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "VehicleManager.delete: failed to delete a vehicle" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "VehicleManager.delete: failed to delete a vehicle: " + e );       
        }
    }
    
    
    /*
     * 
     * Location @ Vehicle
     * 
     */
    public void storeLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
	}
    
    public RentalLocation restoreLocation(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<Vehicle> restoreLocation(RentalLocation rentalLocation) throws RARException {
    	String selectLocationVehicleQuery =
				"SELECT "
				+ "VEHICLE.*, "
				+ "VEHICLE_TYPE.*, "
				+ "LOCATION.* "
				+ "FROM VEHICLE "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=VEHICLE.location_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=VEHICLE.type_id";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        condition.setLength( 0 );
        query.append( selectLocationVehicleQuery );
    	
        if( rentalLocation != null ){
        	if( rentalLocation.getId() >= 0 ){
        		query.append( " and VEHICLE.location_id=" + rentalLocation.getId() );
        	}else{
        		if( rentalLocation.getName() != null ){
            		query.append(" and LOCATION.name='"+rentalLocation.getName()+"'");
        		}
        	}
        }
        try {
            stmt = (Statement) con.createStatement();
            System.out.println("query: "+ query.toString());
            if( stmt.execute( query.toString() ) ) {
                ResultSet rs = stmt.getResultSet();
                
                // VEHICLE
                int 	vehicle_vehicle_id;
                int		vehicle_type_id;
                int 	vehicle_location_id;
				String 	vehicle_make;
				String 	vehicle_model;
				int 	vehicle_year;
				int 	vehicle_mileage;
				String 	vehicle_tag;
				Date 	vehicle_service_date;
				int 	vehicle_status = 0;
				int 	vehicle_cond = 0;
				
				// VEHICLE_TYPE
				int		type_type_id;
				String	type_name;
				
				// LOCATION
				int 	location_location_id;
				String 	location_name;
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
				
				Vehicle vehicle = null;
				VehicleType vehicleType = null;
				VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
				VehicleCondition vehicleCondition = VehicleCondition.GOOD;
                while( rs.next() ){
                	
                	// VEHICLE
                	vehicle_vehicle_id 	= rs.getInt(1);
                	vehicle_type_id		= rs.getInt(2);
					vehicle_location_id = rs.getInt(3);
					vehicle_make 		= rs.getString(4);
					vehicle_model 		= rs.getString(5);
					vehicle_year 		= rs.getInt(6);
					vehicle_mileage 	= rs.getInt(7);
					vehicle_tag 		= rs.getString(8);
					vehicle_service_date= rs.getDate(9);
					vehicle_status 		= rs.getInt(10);
					if(vehicle_status == 1)
						vehicleStatus 	= VehicleStatus.INRENTAL;
					vehicle_cond 		= rs.getInt(11);
					if(vehicle_cond == 1)
						vehicleCondition= VehicleCondition.NEEDSMAINTENANCE;
					
					// VEHICLE_TYPE
					type_type_id		= rs.getInt(12);
					type_name			= rs.getString(13);
					
					// LOCATION
					location_location_id		= rs.getInt(14);
					location_name 				= rs.getString(15);
					location_addr	 			= rs.getString(16);
					location_addr_city			= rs.getString(17);
					location_addr_state			= rs.getString(18);
					location_addr_zip			= rs.getString(19);
					location_image_path			= rs.getString(20);
					location_capacity 			= rs.getInt(21);
					
					//OBJECTS
					vehicleType = objectLayer.createVehicleType(type_name);
					vehicleType.setId(type_type_id);
					
                	vehicle = objectLayer.createVehicle(vehicle_make, vehicle_model, vehicle_year, vehicle_tag, vehicle_mileage, vehicle_service_date, vehicleType, rentalLocation, vehicleCondition, vehicleStatus);
                	vehicle.setId(vehicle_vehicle_id);
                	vehicles.add(vehicle);
                }
            }
            return vehicles;
        } catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "VehicleTypeManager.restorePrice: failed to restore a Price: " + e );       
        }
	}
    
    public void deleteLocation(Vehicle vehicle, RentalLocation rentalLocation) throws RARException {
		// TODO Auto-generated method stub
	}
    
    
    /*
     * 
     * VehicleType @ Vehicle
     * 
     */
    public void storeType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
	}
    
    public VehicleType restoreType(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<Vehicle> restoreType(VehicleType vehicleType) throws RARException {
    	String selectTypeVehicleQuery =
				"SELECT "
				+ "VEHICLE.*,"
				+ "LOCATION.* "
				+ "FROM VEHICLE "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=VEHICLE.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=VEHICLE.location_id";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        condition.setLength( 0 );
        query.append( selectTypeVehicleQuery );
        System.out.println("query: "+ query.toString());
    	
        if( vehicleType != null ){
        	if( vehicleType.getId() >= 0 ){
        		query.append( " and VEHICLE_TYPE.type_id=" + vehicleType.getId() );
        	}else if( vehicleType.getName() != null ){
        		query.append(" and VEHICLE_TYPE.name='"+vehicleType.getName()+"'");
        	}
        }
        try {
            stmt = (Statement) con.createStatement();
            if( stmt.execute( query.toString() ) ) {
                ResultSet rs = stmt.getResultSet();
                // VEHICLE
                int 	vehicle_vehicle_id;
                int		vehicle_type_id;
                int 	vehicle_location_id;
				String 	vehicle_make;
				String 	vehicle_model;
				int 	vehicle_year;
				int 	vehicle_mileage;
				String 	vehicle_tag;
				Date 	vehicle_service_date;
				int 	vehicle_status = 0;
				int 	vehicle_cond = 0;
				// LOCATION
				int 	location_location_id;
				String 	location_name;
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
				// OBJECTS
				Vehicle vehicle = null;
				RentalLocation rentalLocation = null;
				VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
				VehicleCondition vehicleCondition = VehicleCondition.GOOD;
                while( rs.next() ){
                	
                	// VEHICLE
                	vehicle_vehicle_id 	= rs.getInt(1);
                	vehicle_type_id		= rs.getInt(2);
					vehicle_location_id = rs.getInt(3);
					vehicle_make 		= rs.getString(4);
					vehicle_model 		= rs.getString(5);
					vehicle_year 		= rs.getInt(6);
					vehicle_mileage 	= rs.getInt(7);
					vehicle_tag 		= rs.getString(8);
					vehicle_service_date= rs.getDate(9);
					vehicle_status 		= rs.getInt(10);
					if(vehicle_status == 1)
						vehicleStatus 	= VehicleStatus.INRENTAL;
					vehicle_cond 		= rs.getInt(11);
					if(vehicle_cond == 1)
						vehicleCondition= VehicleCondition.NEEDSMAINTENANCE;
					
					// LOCATION
					location_location_id		= rs.getInt(12);
					location_name 				= rs.getString(13);
					location_addr	 			= rs.getString(14);
					location_addr_city			= rs.getString(15);
					location_addr_state			= rs.getString(16);
					location_addr_zip			= rs.getString(17);
					location_image_path			= rs.getString(18);
					location_capacity 			= rs.getInt(19);
					
					//OBJECTS
					rentalLocation = objectLayer.createRentalLocation(location_name, location_addr, location_addr_city, location_addr_state, location_addr_zip, location_image_path, location_capacity);
					rentalLocation.setId(location_location_id);
                	vehicle = objectLayer.createVehicle(vehicle_make, vehicle_model, vehicle_year, vehicle_tag, vehicle_mileage, vehicle_service_date, vehicleType, rentalLocation, vehicleCondition, vehicleStatus);
                	vehicle.setId(vehicle_vehicle_id);
                	vehicles.add(vehicle);
                }
            }
            return vehicles;
        } catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "VehicleTypeManager.restorePrice: failed to restore a Price: " + e );       
        }
	}
    
    public void deleteType(Vehicle vehicle, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
	}
    
    
    /*
     * 
     * Rental @ Vehicle
     * 
     */
	public void storeRental(Vehicle vehicle, Rental rental) throws RARException {
		// TODO Auto-generated method stub
	}

	public List<Rental> restoreRental(Vehicle vehicle) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	public Vehicle restoreRental(Rental rental) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteRental(Vehicle vehicle, Rental rental) throws RARException {
		// TODO Auto-generated method stub
	}
}
