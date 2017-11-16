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
				+ "(name, addr, addr_city, addr_state, addr_zip, image_path, capacity) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, ?)";
		
		String updateRentalLocationQuery =
				"UPDATE LOCATION SET "
				+ "name=?, "
				+ "addr=?, "
				+ "addr_city=?, "
				+ "addr_state=?, "
				+ "addr_zip=?, "
				+ "image_path=?, "
				+ "capacity=? "
				+ "WHERE location_id=?";

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
			
			if( rentalLocation.getCity() != null ){
				pstmt.setString( 3, rentalLocation.getCity());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: City undefined" );
			}
			
			if( rentalLocation.getState() != null ){
				pstmt.setString( 4, rentalLocation.getState());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: State undefined" );
			}
			
			if( rentalLocation.getZip() != null ){
				pstmt.setString( 5, rentalLocation.getZip());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: Zip undefined" );
			}
			
			if( rentalLocation.getPath() != null ){
				pstmt.setString( 6, rentalLocation.getPath());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: Path undefined" );
			}
			
			if( rentalLocation.getCapacity() != 0 ){
				pstmt.setLong( 7, rentalLocation.getCapacity());
			}else{
				throw new RARException( "RentalLocationManager.save: can't save a location: Capacity undefined" );
			}
			
			if( rentalLocation.isPersistent() ){
				pstmt.setLong( 8, rentalLocation.getId() );
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
		String selectRentalLocationQuery = 
				"SELECT "
				+ "location_id, name, addr, addr_city, addr_state, addr_zip, image_path, capacity "
				+ "FROM LOCATION";
		
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
					condition.append( " where LOCATION.addr='" + modelRentalLocation.getAddress() + "'" );
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
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
				while( rs.next() ){
					location_location_id= rs.getInt(1);
					location_name 		= rs.getString(2);
					location_addr	 	= rs.getString(3);
					location_addr_city	 = rs.getString(4);
					location_addr_state	= rs.getString(5);
					location_addr_zip	= rs.getString(6);
					location_image_path	= rs.getString(7);
					location_capacity 	= rs.getInt(8);
					RentalLocation rentalLocation = objectLayer.createRentalLocation(location_name, location_addr, location_addr_city, location_addr_state, location_addr_zip, location_image_path, location_capacity);
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
    	String deleteLocationQuery = 
				"DELETE LOCATION "
				+ "FROM LOCATION";
		
		StringBuffer query = new StringBuffer(1000);
		StringBuffer condition = new StringBuffer(1000);
		Statement stmt = null;
		condition.setLength(0);
		query.append(deleteLocationQuery);
		
		if ( rentalLocation != null ){
			query.append( " WHERE LOCATION.location_id=" + rentalLocation.getId() );
		}
		
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
			stmt.executeUpdate(query.toString());
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw new RARException("RentalLocationManager.delete: failed to delete a location" + e);
		}
    }
}
