package edu.uga.cs.rentaride.persistence.impl;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Statement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class VehicleTypeManager{
	
	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public VehicleTypeManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
		
	}//constructor
	
	
	/*
	 * 
	 * VehicleType
	 * 
	 */
	public void store( VehicleType vehicleType ) throws RARException{
    	String insertVehicleTypeQuery = 
				"INSERT INTO VEHICLE_TYPE "
				+ "(name) "
				+ "VALUES "
				+ "(?)";
    	
    	String updateVehicleTypeQuery = 
				"UPDATE VEHICLE_TYPE SET "
				+ "name=? "
				+ "WHERE type_id=?";
    	
    	PreparedStatement 	pstmt;
    	long 				vehicleTypeId;
		int 				inscnt;
		
		try{
			
			
			if( !vehicleType.isPersistent() ){
                pstmt = (PreparedStatement) con.prepareStatement( insertVehicleTypeQuery );
			}else{
                pstmt = (PreparedStatement) con.prepareStatement( updateVehicleTypeQuery );
			}
			
			if( vehicleType.getName() != null )
                pstmt.setString( 1, vehicleType.getName());
            else{
                throw new RARException( "VehicleTypeManager.save: can't save a type: Type Name undefined" );
            }
			
			if ( vehicleType.isPersistent() )
				pstmt.setLong(2, vehicleType.getId());
			
			
			System.out.println( "query: " + pstmt.asSql() );
            inscnt = pstmt.executeUpdate();
			
            if( !vehicleType.isPersistent() ) {
                if( inscnt == 1 ) {
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) {
                        ResultSet r = pstmt.getResultSet();
                        while( r.next() ) {
                            vehicleTypeId = r.getLong( 1 );
                            if( vehicleTypeId > 0 )
                            	vehicleType.setId( vehicleTypeId );
                        }
                    }
                }
            }
            if(inscnt < 1)
				throw new RARException("VehicleTypeManager.save: failed to save a VehicleType");
			
            
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "VehicleType.save: failed to save a type: " + e );
		}
    }
    
	
	public List<VehicleType> restore( VehicleType modelVehicleType ) throws RARException{
		String selectVehicleTypeQuery =
				"SELECT "
				+ "VEHICLE_TYPE.* "
				+ "FROM VEHICLE_TYPE";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<VehicleType> vehicleTypes = new ArrayList<VehicleType>();
        condition.setLength( 0 );
        query.append( selectVehicleTypeQuery );
        System.out.println("query: "+ query.toString());
        
        if( modelVehicleType != null ) {
            if( modelVehicleType.getId() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " where VEHICLE_TYPE.type_id = " + modelVehicleType.getId() );
            else if( modelVehicleType.getName() != null ) // userName is unique, so it is sufficient to get a person
                query.append( " where VEHICLE_TYPE.name = '" + modelVehicleType.getName() + "'" );
        }
        try {
            stmt = (Statement) con.createStatement();
            if( stmt.execute( query.toString() ) ) {
                ResultSet rs = stmt.getResultSet();
                // VEHICLE_TYPE
                int   	type_type_id;
                String 	type_name;
				while( rs.next() ){
					 // VEHICLE_TYPE
	                type_type_id = rs.getInt(1);
	                type_name = rs.getString(2);
                    VehicleType vehicleType = objectLayer.createVehicleType(type_name);
                    vehicleType.setId( type_type_id );
					//vehicleType.getVehicles();
					//vehicleType.getReservations();
                    //vehicleType.getHourlyPrices();
                    List<String> paths = restoreTypePath(vehicleType);
                    if(paths.size() > 0)
                    	vehicleType.setPath(paths.get(0));
					vehicleTypes.add( vehicleType );
                }
                return vehicleTypes;
            }
        }
        catch( Exception e ) {      // just in case...
            throw new RARException( "VehicleTypeManager.restore: Could not restore persistent VehicleType object; Root cause: " + e );
        }
        // if we get to this point, it's an error
        throw new RARException( "VehicleTypeManager.restore: Could not restore persistent VehicleType objects" );
	}
	
	
    public void delete( VehicleType vehicleType ) throws RARException{
    	String deleteTypeQuery = 
				"DELETE VEHICLE_TYPE "
				+ "FROM VEHICLE_TYPE";
		
		StringBuffer query = new StringBuffer(1000);
		StringBuffer condition = new StringBuffer(1000);
		Statement stmt = null;
		condition.setLength(0);
		query.append(deleteTypeQuery);
		
		if ( vehicleType != null ){
			query.append( " WHERE VEHICLE_TYPE.type_id=" + vehicleType.getId() );
		}
		
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
			stmt.executeUpdate(query.toString());
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("VehicleTypeManager.delete: failed to delete a vehicle type" + e);
		}
    }
    
    
    /*
     * 
     * HourlyPrice @ VehicleType
     * 
     */
    public void storePrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO
	}
    
    public VehicleType restorePrice(HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<HourlyPrice> restorePrice(VehicleType vehicleType) throws RARException {
    	String selectTypeHourlyPriceQuery =
				"SELECT "
				+ "HOURLY_PRICE.* "
				+ "FROM HOURLY_PRICE "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=HOURLY_PRICE.type_id";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<HourlyPrice> hourlyPrices = new ArrayList<HourlyPrice>();
        condition.setLength( 0 );
        query.append( selectTypeHourlyPriceQuery );
    	
        if( vehicleType != null ){
        	if( vehicleType.getId() >= 0 ){
        		query.append( " WHERE VEHICLE_TYPE.type_id=" + vehicleType.getId() );
        	}
        }
        
        try {
            stmt = (Statement) con.createStatement();
            System.out.println("query: "+ query.toString());
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                int  	hourly_hourly_id;
                int		hourly_max_hrs;
                int		hourly_price;
                HourlyPrice hourlyPrice = null;
                while( rs.next() ){
                	// HOURLY_PRICE
                	hourly_hourly_id	= rs.getInt(1);
                	hourly_max_hrs 		= rs.getInt(3);
                	hourly_price 		= rs.getInt(4);
                	hourlyPrice = objectLayer.createHourlyPrice(hourly_max_hrs, hourly_price, vehicleType);
                	hourlyPrice.setId(hourly_hourly_id);
                	hourlyPrices.add(hourlyPrice);
                }
            }
            return hourlyPrices;
            
        } catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "VehicleTypeManager.restorePrice: failed to restore a Price: " + e );       
        }
	}
    
    public void deletePrice(VehicleType vehicleType, HourlyPrice hourlyPrice) throws RARException {
		// TODO Auto-generated method stub
	}
    
    
    public void storeTypePath ( VehicleType vehicleType, String path ) throws RARException {
    	
    	String insertVehicleTypePathQuery = 
				"INSERT INTO VEHICLE_TYPE_PATH "
				+ "( type_id, image_path ) "
				+ "VALUES "
				+ "( ?, ? )";
    	
    	PreparedStatement 	pstmt;
    	long 				vehicleTypeId;
		int 				inscnt;
		
		try{
            
			pstmt = (PreparedStatement) con.prepareStatement( insertVehicleTypePathQuery );
			
			
			if( vehicleType.getId() > 0 )
                pstmt.setLong( 1, vehicleType.getId() );
            else
                throw new RARException( "VehicleTypeManager.save: can't save a path: type id undefined" );
            
			if( path != null )
                pstmt.setString( 2, path );
            else
                throw new RARException( "VehicleTypeManager.save: can't save a path: path undefined" );
            
			System.out.println( "query: " + pstmt.asSql() );
            inscnt = pstmt.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "VehicleType.save: failed to save a path: " + e );
		}
    }
    
    public List<String> restoreTypePath(VehicleType vehicleType) throws RARException {
    	
    	String selectVehicleTypePathQuery =
				"SELECT "
				+ "VEHICLE_TYPE_PATH.image_path "
				+ "FROM VEHICLE_TYPE_PATH";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        String 		path = "";
        List<String> paths = new ArrayList<String>();
        condition.setLength( 0 );
        query.append( selectVehicleTypePathQuery );
    	
        if( vehicleType != null ){
        	if( vehicleType.getId() >= 0 )
        		query.append( " WHERE type_id=" + vehicleType.getId() );
        }
        
        try {
            stmt = (Statement) con.createStatement();
            System.out.println("query: "+ query.toString());
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                while( rs.next() ){
                	// VEHICLE_TYPE_IMAGE
                	//
                	path = rs.getString(1);
                	paths.add(path);
                }
            }
            return paths;
            
        } catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "VehicleTypeManager.restorePath: failed to restore a path: " + e );       
        }
    }
}