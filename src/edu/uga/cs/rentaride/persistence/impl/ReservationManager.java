package edu.uga.cs.rentaride.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.rentaride.RARException;
import edu.uga.cs.rentaride.entity.*;
import edu.uga.cs.rentaride.object.ObjectLayer;

public class ReservationManager {
	
	private ObjectLayer objectLayer = null;
	private Connection con = null;
	private int reservationId = 0;
	
	public ReservationManager(Connection con, ObjectLayer objectLayer){
		this.con = con;
		this.objectLayer = objectLayer;
	}//constructor
	
	public int getReservationId(){
		return this.reservationId;
	}
	
	/*
	 * 
	 * Reservation
	 * 
	 */
	public void store( Reservation reservation ) throws RARException{
		
		String insertReservationQuery =
				"INSERT INTO RESERVATION "
				+ "( location_id, type_id, customer_id, pickup_date, length, cancelled ) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ? )";
		
		String updateReservationQuery =
				"UPDATE RESERVATION SET "
				+ "location_id=?, "
				+ "type_id=?, "
				+ "customer_id=?, "
				+ "pickup_date=?, "
				+ "length=?, "
				+ "cancelled=? "
				+ "WHERE reservation_id=?";
		
		PreparedStatement 	pstmt;
		int 				inscnt;
		long 				reservationId;
		
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
				pstmt.setTimestamp( 4, new java.sql.Timestamp(myDate.getTime()));
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: pickup undefined" );
			}
			
			if( reservation.getLength() != 0 ){
				pstmt.setLong( 5, reservation.getLength() );
			}else{
				throw new RARException( "Reservation.save: can't save a reservation: length undefined" );
			}
			
			if( reservation.getCancelled() == false)
				pstmt.setLong( 6, 0 );
			else
				pstmt.setLong( 6, 1 );
			
			if( reservation.isPersistent() )
                pstmt.setLong( 7, reservation.getId() );
			
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
            					this.reservationId = (int) reservationId;
            				}
            			}
            		}
            	}
            }else{
            	if( inscnt < 1 ){
            		throw new RARException( "ReservationManager.save: failed to save a reservation" );
            	}
            }
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "ReservationManager.store: failed to store a reservation: " + e );
		}			
	}
	
	public List<Reservation> restore( Reservation modelReservation ) throws RARException{
    	String selectReservationQuery =
				"SELECT "
				+ "RESERVATION.*, "
				+ "USER.*, "
				+ "CUSTOMER.*, "
				+ "VEHICLE_TYPE.*, "
				+ "LOCATION.* "
				+ "FROM RESERVATION "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id";
		
    	String	order = " ORDER BY RESERVATION.pickup_date ASC";
		
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Reservation> reservations = new ArrayList<Reservation>();
		Statement stmt = null;
		condition.setLength(0);
		query.append(selectReservationQuery);
		
		// NULL CHECKER
		if( modelReservation != null ){
			
			if(modelReservation.getId() >= 0){
				query.append( " WHERE RESERVATION.reservation_id=" + modelReservation.getId() );
			}else if(modelReservation.getCustomer().getId() >= 0){
				query.append( " WHERE CUSTOMER.customer_id = '" + modelReservation.getCustomer().getId() + "'");
			}else if(modelReservation.getCustomer().getUserName() != null) {
				query.append( " WHERE USER.uname = '" + modelReservation.getCustomer().getUserName() + "'");
			}else if(modelReservation.getRentalLocation().getId() >= 0) {
				query.append( " WHERE LOCATION.location_id = '" + modelReservation.getRentalLocation().getId() + "'");
			}else if(modelReservation.getVehicleType().getId() >= 0) {
				query.append( " WHERE VEHICLE_TYPE.type_id = '" + modelReservation.getVehicleType().getId() + "'");
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
					condition.append( " where LOCATION.addr='" + modelReservation.getRentalLocation().getAddress() + "'" );
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
			query.append(order);
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());

			if( stmt.execute(query.toString()) ){
				ResultSet rs = stmt.getResultSet();
				
				// RESERVATION
				//
				int 	reservation_reservation_id;
				int 	reservation_location_id;
				int		reservation_type_id;
				int 	reservation_customer_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				int		reservation_cancelled;
				// USER
				//
				int		user_user_id;
				String 	user_fname;
	            String 	user_lname;
	            String 	user_uname;
	            String 	user_pword;
	            String 	user_email;
	            String 	user_address;
	            Date 	user_createDate;
				// CUSTOMER
	            //
	            int		customer_customer_id;
	            int 	customer_user_id;
	            Date 	customer_memberUntil;
	            String 	customer_licState;
	            String 	customer_licNum;
	            String 	customer_ccNum;
	            Date 	customer_ccExp;
	            int		customer_status;
	            // VEHICLE_TYPE
	            //
	            int		vehicleType_type_id;
	            String	vehicleType_name;
	            // LOCATION
	            //
				int 	location_location_id;
				String 	location_name;
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
				// RENTAL
				//
				int		rental_rental_id = 0;
				int		rental_reservation_id;
				int		rental_vehicle_id;
				Date 	rental_pickupTime = null;
				Date	rental_returnTime = null;
				int 	rental_late;
				int 	rental_charges;
				// OBJECTS
				//
				VehicleType vehicleType = null;
				RentalLocation rentalLocation = null;
				Customer customer = null;
				Reservation reservation = null;
				Rental rental = null;
				UserStatus userStatus = UserStatus.ACTIVE;
				
				
				while( rs.next() ){
					
					// RESERVATION
					reservation_reservation_id 	= rs.getInt(1);
					reservation_location_id		= rs.getInt(2);
					reservation_type_id 		= rs.getInt(3);
					reservation_customer_id		= rs.getInt(4);
					reservation_pickupTime 		= rs.getTimestamp(5);
					reservation_rentalLength 	= rs.getInt(6);
					reservation_cancelled		= rs.getInt(7);
					
					// USER
					user_user_id = rs.getInt(8);
	           	 	user_fname = rs.getString(9);
	           	 	user_lname = rs.getString(10);
	           	 	user_uname = rs.getString(11);
	           	 	user_pword = rs.getString(12);
	           	 	user_email = rs.getString(13);
	           	 	user_address = rs.getString(14);
	           	 	user_createDate = rs.getDate(15);
	           	 	
	           	 	// CUSTOMER
	                customer_customer_id = rs.getInt(16);
	                customer_user_id = rs.getInt(17);
	                customer_memberUntil = rs.getDate(18);
	                customer_licState = rs.getString(19);
	                customer_licNum = rs.getString(20);
	                customer_ccNum = rs.getString(21);
	                customer_ccExp = rs.getDate(22);
	                customer_status = rs.getInt(23);
	                if(customer_status == 0)
	                	userStatus = UserStatus.ACTIVE;
	                else if(customer_status == 1)
	                	userStatus = UserStatus.CANCELLED;
	                else if(customer_status == 2)
	                	userStatus = UserStatus.TERMINATED;
	                
					// VEHICLE_TYPE
					vehicleType_type_id = rs.getInt(24);
					vehicleType_name = rs.getString(25);
					
					// LOCATION
					location_location_id		= rs.getInt(26);
					location_name 				= rs.getString(27);
					location_addr	 			= rs.getString(28);
					location_addr_city			= rs.getString(29);
					location_addr_state			= rs.getString(30);
					location_addr_zip			= rs.getString(31);
					location_image_path			= rs.getString(32);
					location_capacity 			= rs.getInt(33);
					
//					// RENTAL
//					//
//					rental_rental_id 		= rs.getInt(34);
//					rental_reservation_id 	= rs.getInt(35);
//					rental_vehicle_id 		= rs.getInt(36);
//					rental_pickupTime 		= rs.getTimestamp(37);
//					rental_returnTime 		= rs.getTimestamp(38);
//					rental_late 			= rs.getInt(39);
//					rental_charges 			= rs.getInt(40);
					
					
					customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
					customer.setId(customer_customer_id);
					
					vehicleType = objectLayer.createVehicleType(vehicleType_name);
					vehicleType.setId(vehicleType_type_id);
					
					rentalLocation = objectLayer.createRentalLocation(location_name, location_addr, location_addr_city, location_addr_state, location_addr_zip, location_image_path, location_capacity);
					rentalLocation.setId(location_location_id);
					
					reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_reservation_id);
					if(reservation_cancelled == 1) reservation.setCancelled(true);
					else reservation.setCancelled(false);
					
					rental = objectLayer.createRental(rental_pickupTime, null, null);
					rental.setId(rental_rental_id);
					//rental.setReservation(reservation);
					//reservation.setRental(rental);
					
					reservations.add(reservation);
				}
			}
			return reservations;
			
		} catch (SQLException e){
			e.printStackTrace();
			throw new RARException( "ReservationManager.get: failed to get any reservations: " + e );
		}
    }

	public List<Reservation> restoreNoShowReservations() throws RARException {
		
		java.util.Date myDate = new Date();
   	 	Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1);
        myDate = cal.getTime();
        Timestamp timestamp =  new java.sql.Timestamp(myDate.getTime());
		
		String selectReservationQuery =
				"SELECT "
				+ "RESERVATION.*, "
				+ "USER.*, "
				+ "CUSTOMER.*, "
				+ "VEHICLE_TYPE.*, "
				+ "LOCATION.* "
				+ "FROM RESERVATION "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "LEFT JOIN RENTAL ON RENTAL.reservation_id=RESERVATION.reservation_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id "
				+ "WHERE RESERVATION.pickup_date <= '" + timestamp.toString() + "'"
				+ "AND RENTAL.pickup_date IS NULL "
				+ "AND RESERVATION.cancelled=0";
		
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Reservation> reservations = new ArrayList<Reservation>();
		Statement stmt = null;
		condition.setLength(0);
		query.append(selectReservationQuery);
		
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
			if( stmt.execute(query.toString()) ){
				ResultSet rs = stmt.getResultSet();
				// RESERVATION
				//
				int 	reservation_reservation_id;
				int 	reservation_location_id;
				int		reservation_type_id;
				int 	reservation_customer_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				int		reservation_cancelled;
				// USER
				//
				int		user_user_id;
				String 	user_fname;
		        String 	user_lname;
		        String 	user_uname;
		        String 	user_pword;
		        String 	user_email;
		        String 	user_address;
		        Date 	user_createDate;
				// CUSTOMER
		        //
		        int		customer_customer_id;
		        int 	customer_user_id;
		        Date 	customer_memberUntil;
		        String 	customer_licState;
		        String 	customer_licNum;
		        String 	customer_ccNum;
		        Date 	customer_ccExp;
		        int		customer_status;
		        // VEHICLE_TYPE
	            //
	            int		vehicleType_type_id;
	            String	vehicleType_name;
	            // LOCATION
	            //
				int 	location_location_id;
				String 	location_name;
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
		        // OBJECTS
				//
				VehicleType vehicleType = null;
				RentalLocation rentalLocation = null;
				Customer customer = null;
				Reservation reservation = null;
				Rental rental = null;
				UserStatus userStatus = UserStatus.ACTIVE;
		        
		        while( rs.next() ){
		        	// RESERVATION
					reservation_reservation_id 	= rs.getInt(1);
					reservation_location_id		= rs.getInt(2);
					reservation_type_id 		= rs.getInt(3);
					reservation_customer_id		= rs.getInt(4);
					reservation_pickupTime 		= rs.getTimestamp(5);
					reservation_rentalLength 	= rs.getInt(6);
					reservation_cancelled		= rs.getInt(7);
					
					// USER
					user_user_id = rs.getInt(8);
	           	 	user_fname = rs.getString(9);
	           	 	user_lname = rs.getString(10);
	           	 	user_uname = rs.getString(11);
	           	 	user_pword = rs.getString(12);
	           	 	user_email = rs.getString(13);
	           	 	user_address = rs.getString(14);
	           	 	user_createDate = rs.getDate(15);
	           	 	
	           	 	// CUSTOMER
	                customer_customer_id = rs.getInt(16);
	                customer_user_id = rs.getInt(17);
	                customer_memberUntil = rs.getDate(18);
	                customer_licState = rs.getString(19);
	                customer_licNum = rs.getString(20);
	                customer_ccNum = rs.getString(21);
	                customer_ccExp = rs.getDate(22);
	                customer_status = rs.getInt(23);
	                if(customer_status == 0)
	                	userStatus = UserStatus.ACTIVE;
	                else if(customer_status == 1)
	                	userStatus = UserStatus.CANCELLED;
	                else if(customer_status == 2)
	                	userStatus = UserStatus.TERMINATED;
	                
	                // VEHICLE_TYPE
					vehicleType_type_id = rs.getInt(24);
					vehicleType_name = rs.getString(25);
					
					// LOCATION
					location_location_id		= rs.getInt(26);
					location_name 				= rs.getString(27);
					location_addr	 			= rs.getString(28);
					location_addr_city			= rs.getString(29);
					location_addr_state			= rs.getString(30);
					location_addr_zip			= rs.getString(31);
					location_image_path			= rs.getString(32);
					location_capacity 			= rs.getInt(33);
					
	                customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
					customer.setId(customer_customer_id);
					
					vehicleType = objectLayer.createVehicleType(vehicleType_name);
					vehicleType.setId(vehicleType_type_id);
					
					rentalLocation = objectLayer.createRentalLocation(location_name, location_addr, location_addr_city, location_addr_state, location_addr_zip, location_image_path, location_capacity);
					rentalLocation.setId(location_location_id);
					
					reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_reservation_id);
					if(reservation_cancelled == 1) reservation.setCancelled(true);
					else reservation.setCancelled(false);
					reservations.add(reservation);
		        }
			}
			return reservations;
		} catch (SQLException e){
			e.printStackTrace();
			throw new RARException( "ReservationManager.get: failed to get any reservations: " + e );
		}
	}
	
	
	public Customer restoreCustomerReservation(Reservation reservation) throws RARException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void delete( Reservation reservation ) throws RARException{
		
		String deleteReserv = "DELETE FROM RESERVATION WHERE reservation_id=?";              
		PreparedStatement stmt = null;
		int inscnt = 0;
		             
        if( !reservation.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) con.prepareStatement(deleteReserv);         
            stmt.setLong( 1, reservation.getId() );
			System.out.println("query: " + stmt.asSql());

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
	public void storeCustomerReservation(Customer customer, Reservation reservation) throws RARException {
		
		// queries
		// 
		String insertCustomerReservationQuery =
				"INSERT INTO RESERVATION "
				+ "(location_id, type_id, customer_id, pickup_date, length, cancelled ) "
				+ "VALUES "
				+ "( ?, ?, ?, ?, ?, ? )";
		
		String updateCustomerReservationQuery = 
				"UPDATE RESERVATION SET "
				+ "location_id=?, "
				+ "type_id=?, "
				+ "customer_id=?, "
				+ "pickup_date=?, "
				+ "length=?, "
				+ "cancelled=? "
				+ "WHERE reservation_id=?";
		
		PreparedStatement 	pstmt;
		int 				inscnt;
		long 				customerId;
		long 				reservationId;
		
		try {
			
			// check persist
			if( !customer.isPersistent() ){
				pstmt = (PreparedStatement) con.prepareStatement( insertCustomerReservationQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateCustomerReservationQuery );
			}
			
			// update pstmt
			//
			if( reservation.getRentalLocation().getId() != 0 ){
				pstmt.setLong( 1, reservation.getRentalLocation().getId() );
			}else{
				throw new RARException( "Reservation.storeCustomerReservation: can't save a customer reservation: location undefined" );
			}
			
			if( reservation.getVehicleType().getId() != 0 ){
				pstmt.setLong( 2, reservation.getVehicleType().getId() );
			}else{
				throw new RARException( "Reservation.storeCustomerReservation: can't save a customer reservation: type undefined" );
			}
			
			if( reservation.getCustomer().getId() != 0 ){
				pstmt.setLong( 3, reservation.getCustomer().getId() );
			}else{
				throw new RARException( "Reservation.storeCustomerReservation: can't save a customer reservation: customer undefined" );
			}
			
			if( reservation.getPickupTime() != null ){
				java.util.Date myDate = reservation.getPickupTime();
	        	java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
				pstmt.setDate( 4, sqlDate );
			}else{
				throw new RARException( "Reservation.storeCustomerReservation: can't save a customer reservation: pickup undefined" );
			}
			
			if( reservation.getLength() != 0 ){
				pstmt.setLong( 5, reservation.getLength() );
			}else{
				throw new RARException( "Reservation.storeCustomerReservation: can't save a customer reservation: length undefined" );
			}
			
			if( reservation.getCancelled() == false || reservation.getCancelled() == true ){
				if(reservation.getCancelled() == false) pstmt.setLong(6, 0);
				if(reservation.getCancelled() == true) pstmt.setLong(6, 1);
			}else{
				throw new RARException( "Reservation.storeCustomerReservation: can't save a customer reservation: cancelled undefined" );
			}
			
			if( customer.isPersistent())
				pstmt.setLong( 7, reservation.getId() );
			
			System.out.println("query: " + pstmt.asSql());
            inscnt = pstmt.executeUpdate();
			
			
		} catch(SQLException e){
				e.printStackTrace();
				throw new RARException( "ReservationManager.storeCustomerReservation: failed to store a customer reservation: " + e );
		}
	}

    public List<Reservation> restore(Customer customer) throws RARException {
    	String selectCustomerReservationQuery =
				"SELECT "
				+ "RESERVATION.*, "
				+ "VEHICLE_TYPE.*, "
				+ "LOCATION.* "
				+ "FROM RESERVATION "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id";
		
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		List<Reservation> reservations = new ArrayList<Reservation>();
		Statement stmt = null;
		condition.setLength(0);
		query.append(selectCustomerReservationQuery);
		
		if( customer != null){
			
			if( customer.getId() >= 0 ){
				query.append( " WHERE CUSTOMER.customer_id="+customer.getId() );
			}
			
		}
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
			if( stmt.execute(query.toString()) ){
				ResultSet rs = stmt.getResultSet();
				
				// RESERVATION
				//
				int 	reservation_reservation_id;
				int 	reservation_location_id;
				int		reservation_type_id;
				int 	reservation_customer_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				int		reservation_cancelled;
				// VEHICLE_TYPE
				//
                int   	type_type_id;
                String 	type_name;
                // LOCATION
	            //
				int 	location_location_id;
				String 	location_name;
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
				// OBJECTS
				//
	            VehicleType vehicleType = null;
				RentalLocation rentalLocation = null;
				Reservation reservation = null;
				Rental rental = null;
				
				while( rs.next() ){
					
					// RESERVATION
                	//
					reservation_reservation_id 	= rs.getInt(1);
					reservation_location_id		= rs.getInt(2);
					reservation_type_id 		= rs.getInt(3);
					reservation_customer_id		= rs.getInt(4);
					reservation_pickupTime 		= rs.getTimestamp(5);
					reservation_rentalLength 	= rs.getInt(6);
					reservation_cancelled		= rs.getInt(7);
					
					// VEHICLE_TYPE
					//
	                type_type_id 				= rs.getInt(8);
	                type_name 					= rs.getString(9);
					
	                // LOCATION
					//
					location_location_id= rs.getInt(10);
					location_name 		= rs.getString(11);
					location_addr	 	= rs.getString(12);
					location_addr_city	= rs.getString(13);
					location_addr_state	= rs.getString(14);
					location_addr_zip	= rs.getString(15);
					location_image_path	= rs.getString(16);
					location_capacity 	= rs.getInt(17);
					
					// OBJECTS
					//
					vehicleType = objectLayer.createVehicleType(type_name);
					vehicleType.setId(type_type_id);
					
					rentalLocation = objectLayer.createRentalLocation(location_name, location_addr, location_addr_city, location_addr_state, location_addr_zip, location_image_path, location_capacity);
					rentalLocation.setId(location_location_id);
					
					reservation = objectLayer.createReservation(reservation_pickupTime, reservation_rentalLength, vehicleType, rentalLocation, customer);
					reservation.setId(reservation_reservation_id);
					reservation.getRental();
					
					if(reservation_cancelled == 1) reservation.setCancelled(true);
					else reservation.setCancelled(false);
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
                	//
                	reservation_reservation_id 	= rs.getInt(1);
					rs.getInt(2);
					rs.getInt(3);
					rs.getInt(4);
					reservation_pickupTime 		= rs.getTimestamp(5);
					reservation_rentalLength 	= rs.getInt(6);
					rs.getInt(7);
					rs.getInt(8);
					rs.getString(9);
					rs.getString(10);
					rs.getInt(11);
					// VEHICLE_TYPE
					//
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
					//
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
				+ "VEHICLE.*, "
				+ "VEHICLE_TYPE.*, "
				+ "USER.*, "
				+ "CUSTOMER.*, "
				+ "RENTAL.* "
				+ "FROM RESERVATION "
				+ "INNER JOIN VEHICLE_TYPE ON VEHICLE_TYPE.type_id=RESERVATION.type_id "
				+ "INNER JOIN LOCATION ON LOCATION.location_id=RESERVATION.location_id "
				+ "INNER JOIN VEHICLE ON VEHICLE.location_id=LOCATION.location_id "
				+ "INNER JOIN VEHICLE_TYPE on VEHICLE_TYPE.type_id=VEHICLE.type_id "
				+ "INNER JOIN CUSTOMER ON CUSTOMER.customer_id=RESERVATION.customer_id "
				+ "INNER JOIN USER ON USER.user_id=CUSTOMER.user_id "
				+ "INNER JOIN RENTAL ON RENTAL.reservation_id=RESERVATION.reservation_id";
		
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Reservation> reservations = new ArrayList<Reservation>();
        condition.setLength( 0 );
        query.append( selectTypeReservationQuery );
    	
        if( vehicleType != null ){
        	if( vehicleType.getId() >= 0 ){
        		query.append( " where VEHICLE_TYPE.type_id=" + vehicleType.getId() );
        	}else if( vehicleType.getName() != null ){
        		query.append(" and VEHICLE_TYPE.name='"+vehicleType.getName()+"'");
        	}
        }
        try {
            stmt = (Statement) con.createStatement();
            System.out.println("query: "+ query.toString());
            if( stmt.execute( query.toString() ) ) { // statement returned a result
                ResultSet rs = stmt.getResultSet();
                // RESERVATION
				//
				int 	reservation_reservation_id;
				int 	reservation_location_id;
				int		reservation_type_id;
				int 	reservation_customer_id;
				Date 	reservation_pickupTime;
				int 	reservation_rentalLength;
				int		reservation_cancelled;
				// USER
				//
				int		user_user_id;
				String 	user_fname;
	            String 	user_lname;
	            String 	user_uname;
	            String 	user_pword;
	            String 	user_email;
	            String 	user_address;
	            Date 	user_createDate;
				// CUSTOMER
	            //
	            int		customer_customer_id;
	            int 	customer_user_id;
	            Date 	customer_memberUntil;
	            String 	customer_licState;
	            String 	customer_licNum;
	            String 	customer_ccNum;
	            Date 	customer_ccExp;
	            int		customer_status = 0;
	            // VEHICLE
	            //
                int 	vehicle_vehicle_id;
                int		vehicle_type_id;
                int 	vehicle_location_id;
				String 	vehicle_make;
				String 	vehicle_model;
				int 	vehicle_year;
				int 	vehicle_mileage;
				String 	vehicle_tag;
				Date 	vehicle_service_date;
				int 	vehicle_status;
				int 	vehicle_cond;
	            // VEHICLE_TYPE
	            //
	            int		vehicleType_type_id;
	            String	vehicleType_name;
	            // LOCATION
	            //
				int 	location_location_id;
				String 	location_name;
				String 	location_addr;
				String 	location_addr_city;
				String 	location_addr_state;
				String 	location_addr_zip;
				String 	location_image_path;
				int 	location_capacity;
				// OBJECTS
				//
				RentalLocation rentalLocation = null;
				Customer customer = null;
				Reservation reservation = null;
				UserStatus userStatus = UserStatus.ACTIVE;
				VehicleStatus vehicleStatus = VehicleStatus.INLOCATION;
				VehicleCondition vehicleCondition = VehicleCondition.GOOD;
                while( rs.next() ){
                	// RESERVATION
                	//
					reservation_reservation_id 	= rs.getInt(1);
					reservation_location_id		= rs.getInt(2);
					reservation_type_id 		= rs.getInt(3);
					reservation_customer_id		= rs.getInt(4);
					reservation_pickupTime 		= rs.getTimestamp(5);
					reservation_rentalLength 	= rs.getInt(6);
					reservation_cancelled		= rs.getInt(7);
					// LOCATION
					//
					location_location_id= rs.getInt(8);
					location_name 		= rs.getString(9);
					location_addr	 	= rs.getString(10);
					location_addr_city	= rs.getString(11);
					location_addr_state	= rs.getString(12);
					location_addr_zip	= rs.getString(13);
					location_image_path	= rs.getString(14);
					location_capacity 	= rs.getInt(15);
					// VEHICLE
					//
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
					if(vehicle_status == 0)
						vehicleStatus 	= VehicleStatus.INLOCATION;
					else if(vehicle_status == 1)
						vehicleStatus	= VehicleStatus.INRENTAL;
					vehicle_cond 		= rs.getInt(11);
					if(vehicle_cond == 0)
						vehicleCondition= VehicleCondition.GOOD;
					else if(vehicle_cond == 1)
						vehicleCondition= VehicleCondition.NEEDSMAINTENANCE;
					// VEHICLE_TYPE
					//
					vehicleType_type_id = rs.getInt(16);
					vehicleType_name 	= rs.getString(17);
					// USER
					//
					user_user_id		= rs.getInt(16);
					user_fname 			= rs.getString(17);
					user_lname 			= rs.getString(18);
					user_uname 			= rs.getString(19);
					user_pword 			= rs.getString(20);
					user_email 			= rs.getString(21);
					user_address 		= rs.getString(22);
					user_createDate 	= rs.getDate(23);
					// CUSTOMER
					//
					customer_customer_id 	= rs.getInt(24);
					customer_user_id		= rs.getInt(25);
					customer_memberUntil 	= rs.getDate(26);
					customer_licState 		= rs.getString(27);
					customer_licNum 		= rs.getString(28);
					customer_ccNum 			= rs.getString(29);
					customer_ccExp 			= rs.getDate(30);
					customer_status			= rs.getInt(31);
					if(customer_status == 2){
						userStatus			= UserStatus.CANCELLED;
                	}else if(customer_status == 3)
                		userStatus			= UserStatus.TERMINATED;
					
					customer = objectLayer.createCustomer(user_fname, user_lname, user_uname, user_pword, user_email, user_address, user_createDate, customer_memberUntil, customer_licState, customer_licNum, customer_ccNum, customer_ccExp);
					customer.setId(customer_customer_id);
					rentalLocation = objectLayer.createRentalLocation(location_name, location_addr, location_addr_city, location_addr_state, location_addr_zip, location_image_path, location_capacity);
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
    
    /*
     * 
     * 	CHARGES
     * 
     * 
     */
    
    public void storeCharges(Reservation reservation, Rental rental, boolean insert) throws RARException{
    
    	// query
    	//
    	String insertChargesQuery = 
				"INSERT INTO CHARGES "
				+ "( reservation_id, charges ) "
				+ "VALUES "
				+ "( ?, ? )";
		
		String updateChargesQuery =
				"UPDATE CHARGES SET "
				+ "charges=? "
				+ "WHERE reservation_id=?";
		
		PreparedStatement pstmt;
		int inscnt;
		long reservationId;
		
		try {
			if( insert ){
				pstmt = (PreparedStatement) con.prepareStatement( insertChargesQuery );
			}else{
				pstmt = (PreparedStatement) con.prepareStatement( updateChargesQuery );
			}

			if(insert){
				if( reservation.getId() >= 0 ){
					pstmt.setLong( 1, reservation.getId() );
				}else{
					throw new RARException( "ReservationManager.save: can't save a charge: reservation id undefined" );
				}
				
				pstmt.setInt( 2, 0 );
				
			}else{
				if( rental.getCharges() != -1 ){
					pstmt.setInt( 1, rental.getCharges() );
				}else{
					throw new RARException( "ReservationManager.save: can't save a charge: charge undefined" );
				}
				
				if( reservation.getId() >= 0 ){
					pstmt.setLong( 2, reservation.getId() );
				}else{
					throw new RARException( "ReservationManager.save: can't save a charge: reservation id undefined" );
				}
			}
			
			System.out.println("query: " + pstmt.asSql());
            inscnt = pstmt.executeUpdate();
			
		} catch(SQLException e){
			e.printStackTrace();
			throw new RARException( "ReservationManager.store: failed to store a charge: " + e );
		}
    }
    
    public int restoreCharges( int reservationId ) throws RARException{
    	
    	// query
    	//
    	String selectChargesQuery = 
				"SELECT "
				+ "charges "
				+ "FROM CHARGES";
		
		Statement 	stmt = null;
		StringBuffer query = new StringBuffer(100);
		StringBuffer condition = new StringBuffer(100);
		int charges = 0;
		condition.setLength(0);
		query.append(selectChargesQuery);
		
		if(reservationId != -1){
			query.append( " WHERE reservation_id=" + reservationId );
		}
		
		try {
			stmt = con.createStatement();
			System.out.println("query: " + query.toString());
	            if( stmt.execute(query.toString()) ){
	            	ResultSet rs = stmt.getResultSet();
					while( rs.next() ){
						charges= rs.getInt(1);
						return charges;
					}
	            }
        } catch (SQLException e){
			e.printStackTrace();
			throw new RARException( "RentalLocationManager.get: failed to get any locations: " + e );
		}
    	
    	
    	return charges;
    }
}