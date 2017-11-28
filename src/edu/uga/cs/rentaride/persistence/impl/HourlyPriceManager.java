package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.HourlyPrice;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class HourlyPriceManager {

	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public HourlyPriceManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	
	/*
	 * 
	 * Hourly Price
	 * 
	 */
	public void store( HourlyPrice hourlyPrice ) throws RARException{
    	
		String insertHourlyPriceQuery = 
				"INSERT INTO HOURLY_PRICE "
				+ "( type_id, max_hrs, price ) "
				+ "VALUES "
				+ "( ?, ?, ? )";
    	
    	String updateHourlyPriceQuery = 
				"UPDATE HOURLY_PRICE SET "
				+ "type_id=?, "
				+ "max_hrs=?, "
				+ "price=? "
				+ "WHERE hourly_id=?"; 
    	
    	PreparedStatement 	pstmt;
		int 				inscnt;
		long				hourlyPriceId;

		try{
			
			if( !hourlyPrice.isPersistent() ){
                pstmt = (PreparedStatement) con.prepareStatement( insertHourlyPriceQuery );
			}else{
                pstmt = (PreparedStatement) con.prepareStatement( updateHourlyPriceQuery );
			}
			
			if( hourlyPrice.getVehicleType().getId() != 0 )
                pstmt.setLong( 1, hourlyPrice.getVehicleType().getId() );
            else{
                throw new RARException( "HourlyPriceManager.save: can't save a price: vehicle type undefined" );
            }
			
			if( hourlyPrice.getMaxHours() != 0 )
                pstmt.setLong( 2, hourlyPrice.getMaxHours() );
            else{
                throw new RARException( "HourlyPriceManager.save: can't save a price: maxHrs undefined" );
            }
			
			if( hourlyPrice.getPrice() != 0 )
                pstmt.setLong( 3, hourlyPrice.getPrice() );
            else{
                throw new RARException( "HourlyPriceManager.save: can't save a price: price undefined" );
            }
			
			if( hourlyPrice.isPersistent() )
                pstmt.setLong( 4, hourlyPrice.getId() );
			
			System.out.println("query: "+pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            
            // retrieve last incremented value if persistent for pk
            //
            if( !hourlyPrice.isPersistent() ) {
                if( inscnt > 0 ) {
                    String sql = "select last_insert_id()";
                    if( pstmt.execute( sql ) ) {
                        ResultSet r = pstmt.getResultSet();
                        while( r.next() ) {
                            hourlyPriceId = r.getLong( 1 );
                            if( hourlyPriceId > 0 )
                                hourlyPrice.setId( hourlyPriceId );
                        }
                    }
                }
            }
            
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "HourlyPriceManager.save: failed to save a type: " + e );
		}
    }
	
	public List<HourlyPrice> restore( HourlyPrice modelHourlyPrice ) throws RARException{
		String selectHourlyPriceQuery = 
				"SELECT "
				+ "HOURLY_PRICE.*, "
				+ "VEHICLE_TYPE.* "
				+ "FROM HOURLY_PRICE "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=HOURLY_PRICE.type_id";
		
		String	order = " ORDER BY HOURLY_PRICE.hourly_id ASC";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<HourlyPrice> hourlyPrices = new ArrayList<HourlyPrice>();
        condition.setLength( 0 );
        query.append( selectHourlyPriceQuery );
        
        // NULL CHECK
		if( modelHourlyPrice != null ){
			if( modelHourlyPrice.getId() >= 0 )
				query.append( " WHERE HOURLY_PRICE.type_id=" + modelHourlyPrice.getId() );
			else{
				if( modelHourlyPrice.getMaxHours() != 0 )
					condition.append( " WHERE HOURLY_PRICE.max_hrs=" + modelHourlyPrice.getMaxHours() );
				if( modelHourlyPrice.getPrice() > 0 ){
					if( condition.length() > 0 )
                        condition.append( " AND" );
					else
						condition.append( " WHERE" );
					condition.append( " HOURLY_PRICE.price=" + modelHourlyPrice.getPrice() );
				}
				if( condition.length() > 0 )
					query.append( condition );
			}
		}
        
        try {
        	query.append(order);
            stmt = con.createStatement();
			System.out.println("query: " + query.toString());
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                // HOURLY_PRICE
                //
                int  	hourly_hourly_id;
                int		hourly_type_id;
                int		hourly_max_hrs;
                int		hourly_price;
                // VEHICLE_TYPE
                //
                int		vehicle_type_id;
                String 	vehicle_type_name;
                // OBJECTS
                //
                VehicleType vehicleType = null;
                HourlyPrice hourlyPrice = null;
                while( rs.next() ) {
                	// HOURLY_PRICE
                	//
                	hourly_hourly_id= rs.getInt(1);
                	hourly_type_id 	= rs.getInt(2);
                	hourly_max_hrs  = rs.getInt(3);
                	hourly_price    = rs.getInt(4);
                	// VEHICLE_TYPE
                	//
                	vehicle_type_id		= rs.getInt(5);
                	vehicle_type_name 	= rs.getString(6);
                	// OBJECTS
                	//
                    vehicleType = objectLayer.createVehicleType(vehicle_type_name);
                    vehicleType.setId(vehicle_type_id);

                    hourlyPrice = objectLayer.createHourlyPrice(hourly_max_hrs, hourly_price, vehicleType);
                    hourlyPrice.setId( hourly_hourly_id );
                    hourlyPrices.add( hourlyPrice );
                }
                return hourlyPrices;
            }
        }
        catch( SQLException e ) {      // just in case...
            throw new RARException( "HourlyPriceManager.restore: Could not restore persistent HourlyPrice object; Root cause: " + e );
        }
        
        // if we get to this point, it's an error
        throw new RARException( "HourlyPriceManager.restore: Could not restore persistent HourlyPrice objects" );
	}
    
    public void delete( HourlyPrice hourlyPrice ) throws RARException{
    	
		String deleteHourly = 
				"DELETE FROM HOURLY_PRICE WHERE hourly_id=?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !hourlyPrice.isPersistent() ) 
            return;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement(deleteHourly);         
            stmt.setLong( 1, hourlyPrice.getId() );
			System.out.println("query: " + stmt.toString());
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "hourlyPrice.delete: failed to delete a Hourly Price" );
        } catch( SQLException e ) {
        	
            e.printStackTrace();
            throw new RARException( "hourlyPrice.delete: failed to delete a Hourly Price: " + e );       
        }
    }
}