package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class RentalLocationManager {
	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public RentalLocationManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	public void store( RentalLocation rentalLocation ) throws RARException{
		String insertRentalLocationQuery = 
				"INSERT INTO LOCATION "
				+ "(name, address, capacity) "
				+ "VALUES "
				+ "(?, ?, ?)";
		
		String updateRentalLocationQuery =
				"UPDATE INTO LOCATION "
				+ "(name, address, capacity) "
				+ "VALUES "
				+ "(?, ?, ?)";
		
		PreparedStatement pstmt;
		int inscnt;
		long locationId;
		
		try {
			if( !rentalLocation.isPersistent() ){
				pstmt = (PreparedStatement) con.prepareStatement( insertRentalLocationQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateRentalLocationQuery );
			}

			if( rentalLocation.getName() != null ){
				pstmt.setString( 1, rentalLocation.getName());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: Name undefined" );
			}
			
			if( rentalLocation.getAddress() != null ){
				pstmt.setString( 2, rentalLocation.getAddress());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: Address undefined" );
			}
			
			if( rentalLocation.getCapacity() != 0 ){
				pstmt.setLong( 3, rentalLocation.getCapacity());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: Capacity undefined" );
			}
			
			System.out.println("query: " + pstmt.asSql());
            inscnt = pstmt.executeUpdate();
            
            if ( !rentalLocation.isPersistent() ){
            	if( inscnt == 1 ){
            		String sql = "select last_insert_id()";
            		if( pstmt.execute( sql ) ){
            			ResultSet rs = pstmt.getResultSet();
            			while( rs.next() ){
            				locationId = rs.getLong( 1 );
            				if( locationId > 0 ){
            					rentalLocation.setId( locationId );
            				}
            			}
            		}
            	}
            }else{
            	if( inscnt < 1 ){
            		throw new RARException( "RentalLocationManager.save: failed to save a location" );
            	}
            }
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "RentalLocationManager.store: failed to store a location: " + e );
		}
    }
	
	public List<RentalLocation> restore( RentalLocation modelRentalLocation ) throws RARException{
		String selectRentalLocationQuery = "SELECT * FROM LOCATION ";
		
		Statement 	stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<RentalLocation> rentalLocations = new ArrayList<RentalLocation>();
		condition.setLength(0);
		query.append(selectRentalLocationQuery);
		
		// NULL CHECKER
		if( modelRentalLocation != null ){
			if( modelRentalLocation.getId() >= 0 ){
				query.append( " where LOCATION.location_id = " + modelRentalLocation.getId() );
			}else if( modelRentalLocation.getName() != null ){  // name is unique
				query.append( " where LOCATION.name='" + modelRentalLocation.getName() + "'" );
			}else{
				if( modelRentalLocation.getAddress() != null ){
					condition.append( " where LOCATION.address='" + modelRentalLocation.getAddress() + "'" );
					if( modelRentalLocation.getCapacity() > 0 ){
						condition.append( " AND LOCATION.capacity=" + modelRentalLocation.getCapacity() );
					}
				}
				else if( modelRentalLocation.getCapacity() > 0 ){
					condition.append( " where LOCATION.capacity=" + modelRentalLocation.getCapacity() );
				}
				if( condition.length() > 0 ){
					query.append( condition );
				}
			}
		}
		
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
	            if( stmt.execute(query.toString()) ){
				ResultSet rs = stmt.getResultSet();
				int 	location_location_id;
				String 	location_name;
				String 	location_address;
				int 	location_capacity;
				while( rs.next() ){
					location_location_id= rs.getInt(1);
					location_name 		= rs.getString(2);
					location_address 	= rs.getString(3);
					location_capacity 	= rs.getInt(4);
					
					RentalLocation rentalLocation = objectLayer.createRentalLocation(location_name, location_address, location_capacity);
					rentalLocation.setId(location_location_id);
					rentalLocations.add(rentalLocation);
				}
			}
			return rentalLocations;
		} catch (SQLException e){
			e.printStackTrace();
			throw new RARException( "RentalLocationManager.get: failed to get any locations: " + e );
		}
	}
    
    public void delete( RentalLocation rentalLocation ) throws RARException{
    	String deleteRentalLoco = "DELETE FROM LOCATION WHERE location_id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
		
        if( !rentalLocation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        try {
            stmt = (PreparedStatement) con.prepareStatement(deleteRentalLoco);         
            stmt.setLong( 1, rentalLocation.getId() );
			System.out.println("query: " + stmt.asSql());
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "RentalLocationManager.delete: failed to delete a RentalLocation" );
        }
        catch( SQLException e ) {
        		e.printStackTrace();
        		throw new RARException( "RentalLocationManager.delete: failed to delete a RentalLocation: " + e );       
            }
    }
    
    public void storePath( RentalLocation rentalLocation ) throws RARException{
    	String insertRentalLocationPathQuery = 
				"INSERT INTO LOCATION_IMAGE "
				+ "( location_id, image_path) "
				+ "VALUES "
				+ "(?, ?)";
    	
    	PreparedStatement pstmt;
		int inscnt;
		long locationId;
    	
		try {
			pstmt = (PreparedStatement) con.prepareStatement( insertRentalLocationPathQuery );
			
			if( rentalLocation.getId() != 0 ){
				pstmt.setLong( 1, rentalLocation.getId());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location_path: id undefined" );
			}
			
			if( rentalLocation.getPath() != null ){
				pstmt.setString( 2, rentalLocation.getPath());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: Address undefined" );
			}
			
			System.out.println("query: " + pstmt.asSql());
	        inscnt = pstmt.executeUpdate();
        
	        if( inscnt < 1 ){
	    		throw new RARException( "RentalLocationManager.save: failed to save a location_path" );	
	        }
	        
		} catch( SQLException e ) {
    		e.printStackTrace();
    		throw new RARException( "RentalLocationManager.delete: failed to save a RentalLocation_path: " + e );       
        }
	}

    public List<RentalLocation> restorePath( RentalLocation modelRentalLocation ) throws RARException{
		List<RentalLocation> rentalLocations = new ArrayList<RentalLocation>();
		return rentalLocations;
    }
}
