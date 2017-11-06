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
import edu.uga.cs.rentaride.entity.Customer;
import edu.uga.cs.rentaride.entity.RentalLocation;
import edu.uga.cs.rentaride.entity.Reservation;
import edu.uga.cs.rentaride.entity.VehicleType;
import edu.uga.cs.rentaride.object.ObjectLayer;



public class ReservationManager {
	
	private ObjectLayer objectLayer = null;
	private Connection con = null;
	
	public ReservationManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	
	/*
	 * 
	 * Reservation
	 * 
	 */
	public void store( Reservation reservation ) throws RARException{
		
		String insertReservationQuery =
				"INSERT INTO RESERVATION "
				+ "(location_id, type_id, customer_id, pickup_date, length, cancelled ) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?)";
		
		String updateReservationQuery =
				"UPDATE INTO RESERVATION "
				+ "(location_id, type_id, customer_id, pickup_date, length, cancelled ) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt;
		int inscnt;
		long reservationId;
		
		try {
			
			// check persist
			if( !reservation.isPersistent() ){
				pstmt = (PreparedStatement) con.prepareStatement( insertReservationQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateReservationQuery );
			}
			
			// update pstmt
			if( reservation.getRentalLocation().getId() != 0 ){
				pstmt.setLong( 1, reservation.getRentalLocation().getId() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: location undefined" );
			}
			
			if( reservation.getVehicleType().getId() != 0 ){
				pstmt.setLong( 2, reservation.getVehicleType().getId() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: vehicleType undefined" );
			}
			
			if( reservation.getCustomer().getId() != 0 ){
				pstmt.setLong( 3, reservation.getCustomer().getId() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: customer undefined" );
			}
			
			if( reservation.getPickupTime() != null ){
				java.util.Date myDate = reservation.getPickupTime();
	        	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
				pstmt.setDate( 4, sqlDate );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: pickup undefined" );
			}
			
			if( reservation.getLength() != 0 ){
				pstmt.setLong( 5, reservation.getLength() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: length undefined" );
			}
			
			pstmt.setLong( 6, 0 );
			
			System.out.println("query: " + pstmt.asSql());
            inscnt = pstmt.executeUpdate();
			
            // auto_inc pk to object pk
            if ( !reservation.isPersistent() ){
            	if( inscnt == 1 ){
            		String sql = "select last_insert_id()";
            		if( pstmt.execute( sql ) ){
            			ResultSet rs = pstmt.getResultSet();
            			while( rs.next() ){
            				reservationId = rs.getLong( 1 );
            				if( reservationId > 0 ){
            					reservation.setId( reservationId );
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
	
	public List<Reservation> restore( Reservation modelReservation ) throws RARException{
    	String selectReservationQuery =
				"SELECT RESERVATION.*, "
				+ "CUSTOMER.customer_id, "
				+ "USER.fname, USER.lname, "
				+ "VEHICLE_TYPE.type_id, VEHICLE_TYPE.name, "
				+ "LOCATION.location_id, LOCATION.name "
				+ "FROM RESERVATION "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id "
				+ "INNER JOIN RENTAL ON RENTAL.reservation_id=RESERVATION.reservation_id";
		
		
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Reservation> reservations = new ArrayList<Reservation>();
		Statement stmt = null;
		condition.setLength(0);
		query.append(selectReservationQuery);
		
		// NULL CHECKER
		if( modelReservation != null ){
			
			if(modelReservation.getId() >= 0){
				query.append( " where RESERVATION.reservation_id =" + modelReservation.getId() );
			}else if(modelReservation.getCustomer().getId() >= 0){
				query.append( " where CUSTOMER.customer_id = '" + modelReservation.getCustomer().getId() + "'");
			}else if(modelReservation.getCustomer().getUserName() != null) {
				query.append( " where USER.uname = '" + modelReservation.getCustomer().getUserName() + "'");
			}else if(modelReservation.getRentalLocation().getId() >= 0) {
				query.append( " where LOCATION.location_id = '" + modelReservation.getRentalLocation().getId() + "'");
			}else if(modelReservation.getVehicleType().getId() >= 0) {
				query.append( " where VEHICLE_TYPE.type_id = '" + modelReservation.getVehicleType().getId() + "'");
			} else {
				
				if(modelReservation.getPickupTime() != null){
					
					if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " RESERVATION.pickup_date = '" + modelReservation.getPickupTime() + "'" );
				}
				
				if(modelReservation.getLength() >= 0){
								
					if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " RESERVATION.length = '" + modelReservation.getLength() + "'" );
				}
				
				if(modelReservation.getCustomer().getMemberUntil() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.member_until = '" + modelReservation.getCustomer().getMemberUntil() + "'" );
                }
				
				if(modelReservation.getCustomer().getLicenseState() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.lic_state = '" + modelReservation.getCustomer().getLicenseState()  + "'" );
                }
				
				if(modelReservation.getCustomer().getLicenseNumber() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.lic_num = '" + modelReservation.getCustomer().getLicenseNumber() + "'" );
                }
				
				if(modelReservation.getCustomer().getCreditCardNumber() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.cc_num = '" + modelReservation.getCustomer().getCreditCardNumber() + "'" );
                }
				
				if(modelReservation.getCustomer().getCreditCardExpiration() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.cc_exp = '" + modelReservation.getCustomer().getCreditCardExpiration() + "'" );
                }
				
				if(modelReservation.getCustomer().getUserStatus() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " CUSTOMER.status = '" + modelReservation.getCustomer().getUserStatus() + "'" );
                }
				
				if(modelReservation.getCustomer().getPassword() != null){
					condition.append( " USER.pword = '" + modelReservation.getCustomer().getPassword() + "'");
				}
				
				if(modelReservation.getCustomer().getEmail() != null ) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.email = '" + modelReservation.getCustomer().getEmail() + "'" );
                }
				
				if(modelReservation.getCustomer().getFirstName() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.fname = '" + modelReservation.getCustomer().getFirstName() + "'" );
                }
				
				if(modelReservation.getCustomer().getLastName() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.lname = '" + modelReservation.getCustomer().getLastName() + "'" );
                }
				
				if(modelReservation.getCustomer().getAddress() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.address = '" + modelReservation.getCustomer().getAddress() + "'" );
                }   
				
				if(modelReservation.getCustomer().getCreatedDate() != null) {
                    if( condition.length() > 0 ){
                        condition.append( " and" );
                    }
                    condition.append( " USER.create_date = '" + modelReservation.getCustomer().getCreatedDate() + "'" );
                }
				
				if( modelReservation.getRentalLocation().getAddress() != null ){
					condition.append( " where LOCATION.address='" + modelReservation.getRentalLocation().getAddress() + "'" );
					if( condition.length() > 0 ){
						condition.append( " AND LOCATION.capacity=" + modelReservation.getRentalLocation().getAddress() );
					}
				}
				if( modelReservation.getRentalLocation().getCapacity() > 0 ){
					condition.append( " where LOCATION.capacity=" + modelReservation.getRentalLocation().getCapacity() );
					if( condition.length() > 0 ){
						query.append( condition );
					}
				}
				
				if( modelReservation.getVehicleType().getName() != null ) {
	                query.append( " where VEHICLE_TYPE.name = '" + modelReservation.getVehicleType().getName() + "'" );
	                if( condition.length() > 0 ){
						query.append( condition );
					}
				}
			}
			
		}
				
		try {
			stmt = con.createStatement();
			
			if( stmt.execute(query.toString()) ){
				ResultSet rs = stmt.getResultSet();
				
				//"(location_id, type_id, customer_id, pickup_date, length, cancelled ) "

				
				// RESERVATION
				int reservation_id;
				Date pickupTime;
				int rentalLength;
				// USER / CUSTOMER
				int 	customer_customer_id;
	            String 	user_fname;
	            String 	user_lname;
	            
	            // VEHICLE_TYPE
	            int		vehicleType_type_id;
	            String	vehicleType_name;
	            
	            // LOCATION
	            int		location_location_id;
	            String	location_name;
				
				
				VehicleType vehicleType = null;
				RentalLocation rentalLocation = null;
				Customer customer = null;
				Reservation reservation = null;
				
				while( rs.next() ){
					
					// RESERVATION
					reservation_id = rs.getInt(1);
					rs.getInt(2);
					rs.getInt(3);
					rs.getInt(4);
					pickupTime = rs.getDate(5);
					rentalLength = rs.getInt(6);
					rs.getInt(7);
					
					// USER
					customer_customer_id	= rs.getInt(8);
					user_fname = rs.getString(9);
					user_lname = rs.getString(10);
					
					// VEHICLE_TYPE
					vehicleType_type_id = rs.getInt(11);
					vehicleType_name = rs.getString(12);
					
					// LOCATION
					location_location_id = rs.getInt(13);
					location_name = rs.getString(14);
					
					
					customer = objectLayer.createCustomer();
					customer.setId(customer_customer_id);
					customer.setFirstName(user_fname);
					customer.setLastName(user_lname);
					
					vehicleType = objectLayer.createVehicleType();
					vehicleType.setId(vehicleType_type_id);
					vehicleType.setName(vehicleType_name);
					
					rentalLocation = objectLayer.createRentalLocation();
					rentalLocation.setId(location_location_id);
					rentalLocation.setName(location_name);
//					
//					rental = objectLayer.createRental(rental_pickupTime, reservation, vehicle);
//					rental.setId(rental_id);
//					rental.setRental(rental);
					
					reservation = objectLayer.createReservation(pickupTime, rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_id);
//					reservation.setRental(rental);
					reservations.add(reservation);
				}
			}
			return reservations;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new RARException( "RentalLocationManager.get: failed to get any locations: " + e );
		}
    }

	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void delete( Reservation reservation ) throws RARException{
		
		String deleteReserv = "DELETE FROM RESERVATION WHERE reservation_id = ?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !reservation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement(deleteReserv);         
            stmt.setLong( 1, reservation.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new RARException( "ReservationManager.delete: failed to delete a reservation" );
        }catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "ReservationManager.delete: failed to delete a reservation: " + e );       
        }
    }
	
	
	/*
	 * 
	 * Customer @ Reservation
	 * 
	 */
	public void store(Customer customer, Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		
	}

    public List<Reservation> restore(Customer customer) throws RARException {
    	String selectCustomerReservationQuery =
				"SELECT RESERVATION.*, "
				+ "VEHICLE_TYPE.*, "
				+ "LOCATION.* "
				+ "FROM RESERVATION "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=VEHICLE_TYPE.type_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id ";
		
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Reservation> reservations = new ArrayList<Reservation>();
		Statement stmt = null;
		condition.setLength(0);
		query.append(selectCustomerReservationQuery);
		
		try {
			stmt = con.createStatement();
			
			if( stmt.execute(query.toString()) ){
				ResultSet rs = stmt.getResultSet();
				
				// RESERVATION
				int 	reservation_reservation_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				// VEHICLE_TYPE
                int   	type_type_id;
                String 	type_name;
                
				// LOCATION
				int 	location_location_id;
				String 	location_name;
				String 	location_address;
				int 	location_capacity;
				
				Reservation 	reservation = null;
				RentalLocation 	rentalLocation = null;
				VehicleType 	vehicleType = null;
				
				while( rs.next() ){
					
					// RESERVATION
					reservation_reservation_id 	= rs.getInt(1);
					rs.getInt(2);
					rs.getInt(3);
					rs.getInt(4);
					reservation_pickupTime 		= rs.getDate(5);
					reservation_rentalLength 	= rs.getInt(6);
					rs.getInt(7);
					
					// VEHICLE_TYPE
	                type_type_id 				= rs.getInt(8);
	                type_name 					= rs.getString(9);
					
					// LOCATION
					location_location_id 		= rs.getInt(10);
					location_name 				= rs.getString(11);
					location_address 			= rs.getString(12);
					location_capacity 			= rs.getInt(13);
					
					vehicleType = objectLayer.createVehicleType(type_name);
					vehicleType.setId(type_type_id);
					
					rentalLocation = objectLayer.createRentalLocation(location_name, location_address, location_capacity);
					rentalLocation.setId(location_location_id);
					
					reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_reservation_id);
					reservations.add(reservation);
				}
			}
			return reservations;
			
		} catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "ReservationManager.restore: failed to restore reservations from customer: " + e );       
        }
	}
    
    public void delete(Customer customer, Reservation reservation) throws RARException {
		// TODO
	}
    
    
    /*
     * 
     * RentalLocation @ Reservation
     * 
     */
    public void storeLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		// TODO Auto-generated method stub	
	}
    
    public RentalLocation restoreLocation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<Reservation> restoreLocation(RentalLocation rentalLocation) throws RARException {
    	String selectTypeReservationQuery =
				"SELECT "
				+ "RESERVATION.*, "
				+ "LOCATION.*, "
				+ "VEHICLE_TYPE.*, "
				+ "USER.*, "
				+ "CUSTOMER.* "
				+ "FROM RESERVATION "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Reservation> reservations = new ArrayList<Reservation>();
        condition.setLength( 0 );
        query.append( selectTypeReservationQuery );
    	
        if( rentalLocation != null ){
        	if( rentalLocation.getId() >= 0 ){
        		query.append( " and RESERVATION.location_id=" + rentalLocation.getId() );
        	}else if( rentalLocation.getName() != null ){
        		query.append(" and LOCATION.name='"+rentalLocation.getName()+"'");
        	}else{
        		
        	}
        }
        try {
            stmt = (Statement) con.createStatement();
            System.out.println("query: "+ query.toString());
            if( stmt.execute( query.toString() ) ) {
                ResultSet rs = stmt.getResultSet();
                // RESERVATION
				int 	reservation_reservation_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				// VEHICLE_TYPE
				int		type_type_id;
				String	type_name;
				String 	user_fname;
	            String 	user_lname;
	            String 	user_uname;
	            String 	user_pword;
	            String 	user_email;
	            String 	user_address;
	            Date 	user_createDate;
				// CUSTOMER
	            int 	customer_customer_id;
	            Date 	customer_memberUntil;
	            String 	customer_licState;
	            String 	customer_licNum;
	            String 	customer_ccNum;
	            Date 	customer_ccExp;
	            VehicleType vehicleType = null;
				Reservation reservation = null;
				Customer customer = null;
                while( rs.next() ){
                	// RESERVATION
                	reservation_reservation_id 	= rs.getInt(1);
					rs.getInt(2);
					rs.getInt(3);
					rs.getInt(4);
					reservation_pickupTime 		= rs.getDate(5);
					reservation_rentalLength 	= rs.getInt(6);
					rs.getInt(7);
					rs.getInt(8);
					rs.getString(9);
					rs.getString(10);
					rs.getInt(11);
					// VEHICLE_TYPE
					type_type_id				= rs.getInt(12);
					type_name					= rs.getString(13);
					rs.getInt(14);
					user_fname 					= rs.getString(15);
					user_lname 					= rs.getString(16);
					user_uname 					= rs.getString(17);
					user_pword 					= rs.getString(18);
					user_email 					= rs.getString(19);
					user_address 				= rs.getString(20);
					user_createDate 			= rs.getDate(21);
					// CUSTOMER
					customer_customer_id 		= rs.getInt(22);
					rs.getInt(23);
					customer_memberUntil 		= rs.getDate(24);
					customer_licState 			= rs.getString(25);
					customer_licNum 			= rs.getString(26);
					customer_ccNum 				= rs.getString(27);
					customer_ccExp 				= rs.getDate(28);
					rs.getInt(29);
					
					customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
					customer.setId(customer_customer_id);
					vehicleType = objectLayer.createVehicleType(type_name);
					vehicleType.setId(type_type_id);
                	reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
                	reservation.setId(reservation_reservation_id);
                	reservations.add(reservation);
                }
            }
            return reservations;
        } catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "ReservationManager.restoreLocation: failed to restore a Reservation: " + e );       
        }
	}
    
    public void deleteLocation(Reservation reservation, RentalLocation rentalLocation)
			throws RARException {
		// TODO Auto-generated method stub
	}
    
    
    /*
     * 
     * VehicleType @ Reservation
     * 
     */
    public void storeType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
	}
    
    public VehicleType restoreType(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
    
    public List<Reservation> restoreType(VehicleType vehicleType) throws RARException {
    	String selectTypeReservationQuery =
				"SELECT "
				+ "RESERVATION.*, "
				+ "LOCATION.*, "
				+ "USER.*, "
				+ "CUSTOMER.* "
				+ "FROM RESERVATION "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Reservation> reservations = new ArrayList<Reservation>();
        condition.setLength( 0 );
        query.append( selectTypeReservationQuery );
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
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                // RESERVATION
				int 	reservation_reservation_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				// LOCATION
				int 	location_location_id;
				String 	location_name;
				String 	location_address;
				int 	location_capacity;
				String 	user_fname;
	            String 	user_lname;
	            String 	user_uname;
	            String 	user_pword;
	            String 	user_email;
	            String 	user_address;
	            Date 	user_createDate;
				// CUSTOMER
	            int 	customer_customer_id;
	            Date 	customer_memberUntil;
	            String 	customer_licState;
	            String 	customer_licNum;
	            String 	customer_ccNum;
	            Date 	customer_ccExp;
	            Reservation reservation = null;
				RentalLocation rentalLocation = null;
				Customer customer = null;
                while( rs.next() ){
                	// RESERVATION
                	reservation_reservation_id 	= rs.getInt(1);
					rs.getInt(2);
					rs.getInt(3);
					rs.getInt(4);
					reservation_pickupTime 		= rs.getDate(5);
					reservation_rentalLength 	= rs.getInt(6);
					rs.getInt(7);
					// LOCATION
					location_location_id 		= rs.getInt(8);
					location_name 				= rs.getString(9);
					location_address 			= rs.getString(10);
					location_capacity 			= rs.getInt(11);
					rs.getInt(12);
					user_fname 					= rs.getString(13);
					user_lname 					= rs.getString(14);
					user_uname 					= rs.getString(15);
					user_pword 					= rs.getString(16);
					user_email 					= rs.getString(17);
					user_address 				= rs.getString(18);
					user_createDate 			= rs.getDate(19);
					// CUSTOMER
					customer_customer_id 		= rs.getInt(20);
					rs.getInt(21);
					customer_memberUntil 		= rs.getDate(22);
					customer_licState 			= rs.getString(23);
					customer_licNum 			= rs.getString(24);
					customer_ccNum 				= rs.getString(25);
					customer_ccExp 				= rs.getDate(26);
					rs.getInt(27);
					
					customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
					customer.setId(customer_customer_id);
					rentalLocation = objectLayer.createRentalLocation(location_name, location_address, location_capacity);
					rentalLocation.setId(location_location_id);
                	reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
                	reservation.setId(reservation_reservation_id);
                	reservations.add(reservation);
                }
            }
            return reservations;
            
        } catch( SQLException e ) {
            e.printStackTrace();
            throw new RARException( "VehicleTypeManager.restorePrice: failed to restore a Price: " + e );       
        }
	}
    
    public void deleteType(Reservation reservation, VehicleType vehicleType) throws RARException {
		// TODO Auto-generated method stub
	}
}
